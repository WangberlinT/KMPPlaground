import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    private let root: RootComponent
    init(root: RootComponent) {
        self.root = root
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(rootComponent: root)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    private let root: RootComponent
    init(root: RootComponent) {
        self.root = root
    }
    var body: some View {
        ComposeView(root: root)
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



