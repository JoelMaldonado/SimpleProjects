//
//  LoginView.swift
//  RecetarioSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

struct LoginView: View {
    
    @State private var email = ""
    @State private var pass = ""
    @State private var isVisible = false
    
    var body: some View {
        NavigationStack{
            VStack(spacing: 16){
                Text("Bienvenido de Vuelta!")
                    .font(.title2)
                    .fontWeight(.semibold)
                Text("Porfavor ingresa tu cuenta aqui")
                    .font(.footnote)
                    .foregroundStyle(.gray)
                VStack(spacing: 20){
                    HStack{
                        Image(systemName: "envelope")
                            .fontWeight(.semibold)
                        TextField("Correo", text: $email)
                            .font(.subheadline)
                            .padding(12)
                            .cornerRadius(12)
                    }
                    .modifier(TextViewRounded())
                    HStack{
                        Image(systemName: "lock")
                            .fontWeight(.semibold)
                        if isVisible {
                            
                            TextField("Contraseña", text: $pass)
                                .font(.subheadline)
                                .padding(12)
                                .cornerRadius(12)
                        } else {
                            
                            SecureField("Contraseña", text: $pass)
                                .font(.subheadline)
                                .padding(12)
                                .cornerRadius(12)
                        }
                        Button {
                            isVisible.toggle()
                        } label: {
                            Image(systemName: isVisible ? "eye" : "eye.slash")
                                .foregroundStyle(.gray)
                        }
                    }
                    .modifier(TextViewRounded())
                    
                    HStack{
                        Spacer()
                        Button {
                            
                        } label: {
                            Text("Olvidaste tu contraseña?")
                                .foregroundStyle(.black)
                        }
                    }
                    .padding(.trailing, 24)
                    
                    
                    VStack(spacing: 16){
                        Button{
                            
                        } label: {
                            Text("Ingresar")
                                .modifier(RoundedColorButton(color: .green))
                        }
                        
                        Text("O continua con")
                        
                        Button{
                            
                        } label: {
                            Text("Google")
                                .modifier(RoundedColorButton(color: .red))
                        }
                        
                        NavigationLink {
                            RegistroView()
                                .navigationBarBackButtonHidden()
                        } label: {
                            HStack {
                                Text("No tienes una cuenta?")
                                    .foregroundStyle(.black)
                                Text("Registrate")
                                    .foregroundStyle(.green)
                            }
                            .font(.footnote)
                        }
                    }
                }
            }
        }
    }
}

#Preview {
    LoginView()
}
