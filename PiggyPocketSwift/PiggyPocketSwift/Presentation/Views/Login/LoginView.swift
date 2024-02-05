//
//  LoginView.swift
//  PiggyPocketSwift
//
//  Created by Joel on 1/02/24.
//

import SwiftUI

struct LoginView: View {
    
    @State var user = ""
    @State var pass = ""
    @State var isVisible = false
    
    var body: some View {
        VStack(spacing: 30){
            
            Image(.logo)
                .resizable()
                .scaledToFit()
                .frame(width: 180)
                .clipShape(.buttonBorder)
            
            VStack{
                TextField("Usuario", text: $user)
                Divider()
            }
            VStack{
                HStack {
                    if isVisible {
                        TextField("Contraseña", text: $pass)
                    } else {
                        SecureField("Contraseña", text: $pass)
                    }
                    
                    Button {
                        withAnimation {
                            isVisible.toggle()
                        }
                    } label: {
                        Image(systemName: isVisible ? "eye" : "eye.slash")
                            .foregroundStyle(.colorS1)
                    }
                }
                Divider()
            }
            
            Button {
                
            } label: {
                Text("Ingresar")
                    .bold()
                    .foregroundStyle(.white)
                    .frame(height: 50)
                    .frame(maxWidth: .infinity)
                    .background(.colorP1)
                    .clipShape(.buttonBorder)
            }
            
            HStack{
                Text("Eres nuevo?")
                NavigationLink {
                    EmptyView()
                } label: {
                    Text("Registrate")
                        .bold()
                        .foregroundStyle(.colorP1)
                }
            }
            
            Spacer()
            HStack{
                Spacer()
                Rectangle()
                    .frame(width: 80,height: 0.5)
                Spacer()
                Text("o continua con")
                Spacer()
                Rectangle()
                    .frame(width: 80,height: 0.5)
                Spacer()
            }
            .font(.footnote)
            .foregroundStyle(.gray)
            
            Button {
                
            } label: {
                HStack(spacing: 12){
                    Image(.icGoogle)
                        .resizable()
                        .scaledToFit()
                        .frame(width: 30)
                    Text("Ingresar con Google")
                        .foregroundStyle(.black)
                        .bold()
                }
                .frame(height: 50)
                .frame(maxWidth: .infinity)
                .overlay(
                RoundedRectangle(cornerRadius: 15)
                    .stroke(.gray.opacity(0.3), lineWidth: 1.5)
                )
            }
            Text("V1.0")
                .foregroundStyle(.gray)
                .font(.caption)
        }
        .padding()
    }
}

#Preview {
    LoginView()
}
