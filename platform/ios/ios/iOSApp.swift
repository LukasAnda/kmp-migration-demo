import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitializeKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
