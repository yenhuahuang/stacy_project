//
//  AppDelegate.swift
//  ZooPower
//
//  Created by User8 on 2018/7/29.
//  Copyright © 2018年 com.fjuim. All rights reserved.
//

import UIKit
import Firebase
import FBSDKCoreKit
import GoogleSignIn

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate , GIDSignInDelegate  {
    
    
    
    var window: UIWindow?
    var ref : DatabaseReference?
    var check = UserDefaults.standard.integer(forKey: "checkLogIn")
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        FirebaseApp.configure()
        
        ApplicationDelegate.shared.application(application, didFinishLaunchingWithOptions: launchOptions)
        GIDSignIn.sharedInstance().clientID = FirebaseApp.app()?.options.clientID
        GIDSignIn.sharedInstance()?.delegate = self
        // Override point for customization after application launch.
        
        // 判斷是否登入，分別帶入不同畫面
        if check != 1 {
            let controller = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "LoginController") as? LoginController
            self.window?.rootViewController = controller
        } else {
            let controller = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "TabBarController")as? TabBarController
            self.window?.rootViewController = controller
        }
        
        return true
    }
    
    
    // get google user data into firebase
    func sign(_ signIn: GIDSignIn!, didSignInFor user: GIDGoogleUser!, withError error: Error!) {
        if let error = error {
            print("Faild to log into google" , error)
            return
        }else{
            print("Successfully log into google" , user)
        }
        guard let idToken = user.authentication.idToken else { return }
        guard let accessToken = user.authentication.accessToken else { return }
        let credentials = GoogleAuthProvider.credential(withIDToken: idToken, accessToken: accessToken)
        
        Auth.auth().signIn(with: credentials, completion: { (user, error) in
            if let err = error {
                print("Failed to create a Firebase User with Google account: ", err)
                return
            }else{
                guard let uid = user?.user.uid else { return }
                guard let name = user?.user.displayName else { return }
                guard let email = user?.user.email else { return }
                guard let picture = user?.user.photoURL else { return }
                let values = ["email" : email , "name" : name ,"picture" : picture.absoluteString + "?sz=480"] as [String : Any]
                self.ref = Database.database().reference()
                self.ref?.child("Users").child(uid).updateChildValues(values)
                let sb = UIStoryboard(name: "Main", bundle: nil)
                let usersDataController = sb.instantiateViewController(withIdentifier: "UsersDataController") as? UsersDataController
                usersDataController?.googleID = uid
                self.window?.rootViewController?.present(usersDataController!, animated: true, completion: nil)
            }
            
        })
        
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        
        let handled = ApplicationDelegate.shared.application(app, open: url, sourceApplication: options[UIApplication.OpenURLOptionsKey.sourceApplication] as! String!, annotation: options[UIApplication.OpenURLOptionsKey.annotation])
        
        GIDSignIn.sharedInstance().handle(url)
        
        return handled
    }
    
    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }
    
    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
        CoreDataStack.saveContext()
        
    }
    
    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }
    
    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }
    
    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
        CoreDataStack.saveContext()
        
    }
    
    
}

