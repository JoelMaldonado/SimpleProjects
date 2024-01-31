//
//  ContentView.swift
//  RecetarioSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

struct ContentView: View {
    @State var isPresented = false
    var body: some View {
        VStack {
            Image(.recipe)
                .resizable()
                .frame(height: 400)
            VStack{
                Text("Empezemos a Cocinar!")
                    .font(.title)
                    .fontWeight(.semibold)
                Text("Unete a nuestra comunidad\npara cocinar una mejor comida!")
                    .font(.title3)
                    .multilineTextAlignment(.center)
                    .foregroundStyle(.gray)
            }
            
            Spacer()
            
            Button {
                isPresented = true
            } label: {
                Text("Comencemos")
                    .modifier(RoundedColorButton(color: .green))
            }
            
            Spacer()
                .fullScreenCover(isPresented: $isPresented, content: {
                    LoginView()
                })
        }
    }
}

#Preview {
    ContentView()
}
