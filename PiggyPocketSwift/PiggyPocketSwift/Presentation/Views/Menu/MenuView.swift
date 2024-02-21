//
//  MenuView.swift
//  PiggyPocketSwift
//
//  Created by Joel on 9/02/24.
//

import SwiftUI

struct MenuView: View {
    @State private var showDrawer = false
    @State private var selectedTab = 0
    var body: some View {
        ZStack {
            TabView(selection: $selectedTab) {
                InicioView(){
                    selectedTab  = 3
                }
                    .tag(0)
                
                CuentasView()
                    .tag(1)
                
                CategoriasView()
                    .tag(2)
                
                AddTransaccionView()
                    .tag(3)
            }
            
            MenuDrawer(isShowing: $showDrawer, selectedTab: $selectedTab)
        }
        .toolbar {
            ToolbarItem(placement: .topBarLeading){
                Button {
                    showDrawer.toggle()
                } label: {
                    Image(systemName: "line.3.horizontal")
                }
            }
        }
    }
}

struct MenuDrawer: View {
    @Binding var isShowing: Bool
    @Binding var selectedTab: Int
    @State private var selectedOption: DrawerOption?
    @State private var darkMode = false
    @AppStorage("isDarkModeOn") private var isDarkModeOn = false
    var body: some View {
        ZStack {
            if isShowing {
                Rectangle()
                    .opacity(0.3)
                    .ignoresSafeArea()
                    .onTapGesture {
                        isShowing.toggle()
                    }
                
                
                HStack {
                    VStack(alignment: .leading, spacing: 32) {
                        VStack {
                            ForEach(DrawerOption.allCases) { option in
                                Button {
                                    
                                        onOptionTapped(option)
                                } label: {
                                    
                                    DrawerItem(option: option, selectedOption: $selectedOption)
                                }
                            }
                            
                            Spacer()
                            
                            //Perfil
                            Divider()
                            VStack(alignment: .leading){
                                HStack {
                                    Image(.icGoogle)
                                        .resizable()
                                        .foregroundStyle(.white)
                                        .frame(width: 48, height: 48)
                                        .background(.blue)
                                        .clipShape(.circle)
                                        .padding(.vertical)
                                    
                                    VStack(alignment: .leading, spacing: 6) {
                                        Text("Joel Maldonado").font(.subheadline)
                                        Text("joelmaldonado@gmail.com").font(.footnote)
                                    }
                                }
                                
                                HStack {
                                    Image(systemName: "moon.fill")
                                    
                                    Text("Modo Oscuro")
                                        .font(.footnote)
                                    
                                    Toggle("", isOn: $isDarkModeOn)
                                    
                                    Spacer()
                                }
                                
                                HStack {
                                    Image(systemName: "person.fill")
                                    
                                    Text("Cerrar Sesión")
                                        .font(.footnote)
                                    
                                    Spacer()
                                }
                            }
                            .padding(.leading)
                        }
                    }
                    .padding()
                    .frame(width: 270, alignment: .leading)
                    .background(isDarkModeOn ? .gray : .white)
                    
                    Spacer()
                    
                }
                .transition(.move(edge: .leading))
            }
        }
        .animation(.easeInOut, value: isShowing)
    }
    
    private func onOptionTapped(_ option: DrawerOption) {
        selectedOption = option
        selectedTab = option.rawValue
        isShowing = false
    }
}

struct DrawerItem: View {
    let option: DrawerOption
    @Binding var selectedOption: DrawerOption?
    private var isSelected: Bool {
        return selectedOption == option
    }
    var body: some View {
        HStack {
            Image(systemName: option.image)
                .imageScale(.small)
            
            Text(option.title)
                .font(.subheadline)
            
            Spacer()
            
            Image(systemName: "chevron.forward")
                .imageScale(.small)
        }
        .padding(.horizontal)
        .foregroundStyle(.black)
        .frame(width: 220, height: 48)
        .background(isSelected ? .gray.opacity(0.18) : .clear)
        .cornerRadius(24)
    }
}

enum DrawerOption: Int, CaseIterable, Identifiable {
    case home
    case perfomance
    case profile
    
    var title: String {
        switch self{
        case .home:
            return "Inicio"
        case .perfomance:
            return "Cuentas"
        case .profile:
            return "Categorias"
        }
    }
    
    var image: String {
        switch self{
        case .home:
            return "house"
        case .perfomance:
            return "creditcard"
        case .profile:
            return "bag"
        }
    }
    
    var id: Int { return self.rawValue }
}

#Preview {
    MenuView()
}
