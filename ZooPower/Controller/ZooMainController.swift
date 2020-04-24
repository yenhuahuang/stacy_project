//
//  ZooMainController.swift
//  ZooPower
//
//  Created by User8 on 2018/8/5.
//  Copyright © 2018年 com.fjuim. All rights reserved.
//

import UIKit
import Firebase

class ZooMainController: UICollectionViewController , UICollectionViewDelegateFlowLayout {
    
    @IBOutlet weak var zooMainCollectionViewFlowLayout: UICollectionViewFlowLayout!
    
    var areaNameImage = ["海洋地區標題","草原地區標題","雨林地區標題","特區標題"]
    var cellFlag = 0
    var currentID = Auth.auth().currentUser?.uid

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        collectionView.isPagingEnabled = true
        //換頁指示條隱藏
        collectionView.showsHorizontalScrollIndicator = false
        
    }
    
   
    
    // MARK: UICollectionViewDataSource
    
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        return areaNameImage.count
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "Cell", for: indexPath) as? ZooMainCell
        
        // Configure the cell
       
        cell?.areaNameImageView.image = UIImage(named: areaNameImage[indexPath.row])
        
        switch indexPath.row {
        case 0:
            cell?.areaButton.setBackgroundImage(UIImage(named: "海洋區背景"), for: .normal)
        case 1:
            cell?.areaButton.setBackgroundImage(UIImage(named: "草原區背景"), for: .normal)
        case 2:
            cell?.areaButton.setBackgroundImage(UIImage(named: "雨林區背景"), for: .normal)
        case 3:
            cell?.areaButton.setBackgroundImage(UIImage(named: "特區背景"), for: .normal)
        default:
            print("something wrong")
        }
        
        return cell!
    }
    
    
    private func indexOfMajorCell() -> Int {
        let itemWidth = zooMainCollectionViewFlowLayout.itemSize.width
        let proportionalOffset = collectionViewLayout.collectionView!.contentOffset.x / itemWidth
        let index = Int(round(proportionalOffset))
        let safeIndex = max(0, min(areaNameImage.count - 1, index))
        
        return safeIndex
    }
    
    @IBAction func areaButton(_ sender: Any) {
        let index = self.indexOfMajorCell()
        let sb = UIStoryboard(name: "Main", bundle: nil)
        let ZooCollectionController = sb.instantiateViewController(withIdentifier: "ZooCollectionController") as? ZooCollectionController
        ZooCollectionController?.tab = index
        self.show(ZooCollectionController!, sender: nil)
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        collectionView.scrollToItem(at: IndexPath.init(row: self.cellFlag, section: 0), at: .centeredHorizontally, animated: false)
        
    }
   
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: view.frame.width, height: view.frame.height)
    }
    
    
    
}
