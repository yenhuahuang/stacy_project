//
//  singleRecordController.swift
//  ZooPower
//
//  Created by ZooPower on 2018/10/12.
//  Copyright © 2018 com.fjuim. All rights reserved.
//

import UIKit
import MapKit
import Firebase

class singleRecordController: UIViewController {

    var run : Run!
    var currenID = Auth.auth().currentUser?.uid
    
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var oceanLabel: UILabel!
    @IBOutlet weak var grasslandLabel: UILabel!
    @IBOutlet weak var rainforestLabel: UILabel!
    @IBOutlet weak var distanceLabel: UILabel!
    @IBOutlet weak var paceLabel: UILabel!
    @IBOutlet weak var timeLabel: UILabel!
    @IBOutlet weak var calorieLabel: UILabel!
    
    
    var date : [String] = []
    var distance : [String] = []
    var oceanDistance : [String] = []
    var grassLandDistance : [String] = []
    var rainForestDistance : [String] = []
    var duration : [String] = []
    var pace : [String] = []
    var calories : [String] = []
    
    @IBOutlet weak var mapView: MKMapView!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.mapView.delegate = self
        self.mapView.isZoomEnabled = false
        self.mapView.isScrollEnabled = false
        self.mapView.isPitchEnabled = false
        self.mapView.isRotateEnabled = false
        
        Database.database().reference().child("Records/\(currenID!)").observe(.childAdded) { (snapshot) in
            let value = snapshot.value as? [String : AnyObject]
            
            let x = value!["date"]! as! Double / 1000
            let date = NSDate(timeIntervalSince1970: TimeInterval(x))
            let formatter = DateFormatter()
            formatter.dateStyle = .medium
            let dateString = formatter.string(from: date as Date)
            
            let distanceString = Double(round((value!["distance"] as! Double / 1000) * 1000) / 1000)
            let oceanDistanceString = Double(round((value!["oceanDistance"] as! Double / 1000) * 1000) / 1000)
            let grassLandDistanceString = Double(round((value!["grassLandDistance"] as! Double / 1000) * 1000) / 1000)
            let rainForestDistanceString = Double(round((value!["rainForestDistance"] as! Double / 1000) * 1000) / 1000)
            
            let durationString = FormatDisplay.time(value!["duration"] as! Int)
            let paceString = Double(round((((value!["duration"] as! Double) / 60) / distanceString) * 100) / 100)
            let caloriesString = Double(round((value!["calorie"] as! Double) * 100) / 100)
            
            self.date.insert(dateString, at: 0)
            self.distance.insert(String(distanceString), at: 0)
            self.oceanDistance.insert(String(oceanDistanceString), at: 0)
            self.grassLandDistance.insert(String(grassLandDistanceString), at: 0)
            self.rainForestDistance.insert(String(rainForestDistanceString), at: 0)
            self.duration.insert(durationString, at: 0)
            self.pace.insert(String(paceString), at: 0)
            self.calories.insert(String(caloriesString), at: 0)
            
            self.dateLabel.text = self.date[0]
            self.distanceLabel.text =  self.distance[0]
            self.rainforestLabel.text =  self.rainForestDistance[0]
            self.grasslandLabel.text =  self.grassLandDistance[0]
            self.oceanLabel.text =  self.oceanDistance[0]
            self.timeLabel.text =  self.duration[0]
            self.calorieLabel.text =  self.calories[0]
            self.paceLabel.text =  self.pace[0]
        }
        
   
        
        if self.run != nil {
            self.loadMap()
        }else{
            
        }
        
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    private func mapRegion() -> MKCoordinateRegion? {
        guard
            let locations = run.locations,
            locations.count > 0
            else {
                return nil
        }
        
        let latitudes = locations.map { location -> Double in
            let location = location as! Location
            return location.latitude
        }
        
        let longitudes = locations.map { location -> Double in
            let location = location as! Location
            return location.longitude
        }
        
        let maxLat = latitudes.max()!
        let minLat = latitudes.min()!
        let maxLong = longitudes.max()!
        let minLong = longitudes.min()!
        
        let center = CLLocationCoordinate2D(latitude: (minLat + maxLat) / 2,
                                            longitude: (minLong + maxLong) / 2)
        let span = MKCoordinateSpan(latitudeDelta: (maxLat - minLat) * 1.3,
                                    longitudeDelta: (maxLong - minLong) * 1.3)
        return MKCoordinateRegion(center: center, span: span)
    }
    
    
    private func polyLine() -> [MulticolorPolyline] {
        
        // 1
        let locations = run.locations?.array as! [Location]
        var coordinates: [(CLLocation, CLLocation)] = []
        var speeds: [Double] = []
        var minSpeed = Double.greatestFiniteMagnitude
        var maxSpeed = 0.0
        
        // 2
        for (first, second) in zip(locations, locations.dropFirst()) {
            let start = CLLocation(latitude: first.latitude, longitude: first.longitude)
            let end = CLLocation(latitude: second.latitude, longitude: second.longitude)
            coordinates.append((start, end))
            
            //3
            let distance = end.distance(from: start)
            let time = second.timestamp!.timeIntervalSince(first.timestamp! as Date)
            let speed = time > 0 ? distance / time : 0
            speeds.append(speed)
            minSpeed = min(minSpeed, speed)
            maxSpeed = max(maxSpeed, speed)
        }
        
        //4
        let midSpeed = speeds.reduce(0, +) / Double(speeds.count)
        
        //5
        var segments: [MulticolorPolyline] = []
        for ((start, end), speed) in zip(coordinates, speeds) {
            let coords = [start.coordinate, end.coordinate]
            let segment = MulticolorPolyline(coordinates: coords, count: 2)
            segment.color = segmentColor(speed: speed,
                                         midSpeed: midSpeed,
                                         slowestSpeed: minSpeed,
                                         fastestSpeed: maxSpeed)
            segments.append(segment)
        }
        return segments
    }
    
    private func loadMap() {
        guard
            let locations = run.locations,
            locations.count > 0,
            let region = mapRegion()
            else {
                let alert = UIAlertController(title: "Error",
                                              message: "Sorry, this run has no locations saved",
                                              preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: "OK", style: .cancel))
                present(alert, animated: true)
                return
        }
        
        
        mapView.setRegion(region, animated: true)
        mapView.addOverlays(polyLine())
    }
    
    private func segmentColor(speed: Double, midSpeed: Double, slowestSpeed: Double, fastestSpeed: Double) -> UIColor {
        enum BaseColors {
            static let r_red: CGFloat = 1
            static let r_green: CGFloat = 20 / 255
            static let r_blue: CGFloat = 44 / 255
            
            static let y_red: CGFloat = 1
            static let y_green: CGFloat = 215 / 255
            static let y_blue: CGFloat = 0
            
            static let g_red: CGFloat = 0
            static let g_green: CGFloat = 146 / 255
            static let g_blue: CGFloat = 78 / 255
        }
        
        let red, green, blue: CGFloat
        
        if speed < midSpeed {
            let ratio = CGFloat((speed - slowestSpeed) / (midSpeed - slowestSpeed))
            red = BaseColors.r_red + ratio * (BaseColors.y_red - BaseColors.r_red)
            green = BaseColors.r_green + ratio * (BaseColors.y_green - BaseColors.r_green)
            blue = BaseColors.r_blue + ratio * (BaseColors.y_blue - BaseColors.r_blue)
        } else {
            let ratio = CGFloat((speed - midSpeed) / (fastestSpeed - midSpeed))
            red = BaseColors.y_red + ratio * (BaseColors.g_red - BaseColors.y_red)
            green = BaseColors.y_green + ratio * (BaseColors.g_green - BaseColors.y_green)
            blue = BaseColors.y_blue + ratio * (BaseColors.g_blue - BaseColors.y_blue)
        }
        
        return UIColor(red: red, green: green, blue: blue, alpha: 1)
    }
    
    
    

}
extension singleRecordController: MKMapViewDelegate {
    func mapView(_ mapView: MKMapView, rendererFor overlay: MKOverlay) -> MKOverlayRenderer {
        guard let polyline = overlay as? MulticolorPolyline else {
            return MKOverlayRenderer(overlay: overlay)
        }
        let renderer = MKPolylineRenderer(polyline: polyline)
        renderer.strokeColor = polyline.color
        renderer.lineWidth = 3
        return renderer
    }
}
