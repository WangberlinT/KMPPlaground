//
//  AppDelegate.swift
//  iosApp
//
//  Created by Xiao Cheng on 16/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import ComposeApp
import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    let root: RootComponent = RootComponent(
        componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle())
    )
}
