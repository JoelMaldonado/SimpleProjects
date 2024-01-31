//
//  RegistroView.swift
//  RecetarioSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

struct RegistroView: View {
    
    @Environment(\.dismiss) private var dismiss
    
    @StateObject private var viewModel = RegistroViewModel()
    
    var isPassValidLength : Bool {
        return viewModel.pass.count >= 6
    }
    
    var isPassWithNumber : Bool {
        return viewModel.pass.contains(where: { $0.isNumber })
    }
    
    var isPassWithUpper: Bool {
        return viewModel.pass.contains(where: { $0.isUppercase })
    }
    
    var isPassWithLower: Bool {
        return viewModel.pass.contains(where: { $0.isLowercase })
    }
    
    var isPassValid : Bool {
        return isPassValidLength &&
        isPassWithNumber &&
        isPassWithUpper &&
        isPassWithLower
    }
    
    var body: some View {
        VStack(spacing: 16){
            Text("Bienvenido!")
                .font(.title2)
                .fontWeight(.semibold)
            Text("Porfavor ingresa tu cuenta aqui")
                .font(.footnote)
                .foregroundStyle(.gray)
            VStack(spacing: 20){
                HStack{
                    Image(systemName: "envelope")
                        .fontWeight(.semibold)
                    TextField("Correo", text: $viewModel.email)
                        .font(.subheadline)
                        .padding(12)
                        .cornerRadius(12)
                }
                .modifier(TextViewRounded())
                HStack{
                    Image(systemName: "lock")
                        .fontWeight(.semibold)
                    SecureField("Contraseña", text: $viewModel.pass)
                        .font(.subheadline)
                        .padding(12)
                        .cornerRadius(12)
                    Button {
                        
                    } label: {
                        Image(systemName: "eye")
                            .foregroundStyle(.gray)
                    }
                }
                .modifier(TextViewRounded())
                
            }
            
            VStack(spacing: 16) {
                Text("Tu contraseña debe contener:")
                VStack(alignment: .leading){
                    HStack {
                        Image(
                            systemName: isPassValidLength ? "checkmark.circle.fill" : "checkmark.circle"
                        )
                        .foregroundStyle(isPassValidLength ? .green : .gray)
                        Text("Almenos 6 caracteres")
                    }
                    HStack {
                        Image(
                            systemName: isPassWithNumber ? "checkmark.circle.fill" : "checkmark.circle"
                        )
                        .foregroundStyle(isPassWithNumber ? .green : .gray)
                        Text("Contiene un número")
                    }
                    HStack {
                        Image(
                            systemName: isPassWithUpper ? "checkmark.circle.fill" : "checkmark.circle"
                        )
                        .foregroundStyle(isPassWithUpper ? .green : .gray)
                        Text("Contiene una mayuscula")
                    }
                    HStack {
                        Image(
                            systemName: isPassWithLower ? "checkmark.circle.fill" : "checkmark.circle"
                        )
                        .foregroundStyle(isPassWithLower ? .green : .gray)
                        Text("Contiene una minuscula")
                    }
                }
                .font(.footnote)
            }
            
            VStack(spacing: 16){
                Button {
                    Task {
                        try await viewModel.createUser()
                    }
                } label: {
                    Text("Registrar")
                        .modifier(RoundedColorButton(color: .green))
                }
                .opacity(isPassValid ? 1 : 0.5)
                .disabled(!isPassValid)
                
                Button {
                    //Regresar Login
                    dismiss()
                } label: {
                    HStack {
                        Text("Ya tienes una cuenta?")
                            .foregroundStyle(.black)
                        Text("Ingresa")
                            .foregroundStyle(.green)
                    }
                    .font(.footnote)
                }
            }
        }
    }
}

#Preview {
    RegistroView()
}
