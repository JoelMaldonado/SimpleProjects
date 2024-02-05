//
//  ContentView.swift
//  ToolsSwift
//
//  Created by Joel on 1/02/24.
//

import SwiftUI

enum Tab: String, CaseIterable {
    case Home = "home"
    case Search = "magnifyingglass"
    case Notification = "bell"
    case Cart = "bag"
    case Profile = "person"
    
    var Tabname: String {
        switch self {
            
        case .Home:
            return "home"
        case .Search:
            return "Search"
        case .Notification:
            return "Notification"
        case .Cart:
            return "Cart"
        case .Profile:
            return "Profile"
        }
    }
}

struct ContentView: View {
    @State private var currentTab: Tab = .Home
    
    init(){
        UITabBar.appearance().isHidden = true
    }
    
    @Namespace var animation
    
    var body: some View {
        TabView(selection: $currentTab) {
            Text("Home View")
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background()
                .tag(Tab.Home)
            Text("Search View")
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background()
                .tag(Tab.Search)
            Text("Notification View")
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background()
                .tag(Tab.Notification)
            Text("Cart View")
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background()
                .tag(Tab.Cart)
            Text("Profile View")
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background()
                .tag(Tab.Profile)
        }
        .overlay(
            HStack(spacing: 0) {
                ForEach(Tab.allCases, id: \.rawValue){tab in
                    TabButton(tab: tab)
                }
            }
        )
    }
    
    func TabButton(tab:Tab) -> some View {
        GeometryReader {proxy in
            Button {
                withAnimation(.spring()){
                    currentTab = tab
                }
            } label: {
                VStack{
                    Image(systemName: currentTab == tab ? tab.rawValue + ".fill" : tab.rawValue)
                        .resizable()
                        .foregroundColor(.red)
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 25, height: 25)
                        .frame(maxWidth: .infinity)
                        .background(
                            ZStack{
                                if currentTab == tab {
                                    
                                }
                            }
                        )
                }
            }
        }
    }
}

extension View {
    func getSafeArea() -> UIEdgeInsets {
        guard let screen = UIApplication.shared.connectedScenes.first as? UIWindowScene else {
            return .zero
        }
        guard let safeArea = screen.windows.first?.safeAreaInsets else {
            return .zero
        }
        
        return safeArea
    }
}

struct MaterialEffect: UIViewRepresentable {
    var style: UIBlurEffect.Style
    
    func makeUIView(context: Context) -> some UIVisualEffectView {
        let view = UIVisualEffectView(effect: UIBlurEffect(style: style))
        return view
    }
    
    func updateUIView(_ uiView: UIVisualEffectView, context: Context) {
        
    }
}



#Preview {
    ContentView()
}
