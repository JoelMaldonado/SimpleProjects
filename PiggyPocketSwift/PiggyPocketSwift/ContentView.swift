//
//  ContentView.swift
//  PiggyPocketSwift
//
//  Created by Joel on 1/02/24.
//

import SwiftUI
import LocalAuthentication

struct ContentView: View {
    var body: some View {
        NavigationStack{
           LoginView()
        }
    }
}

#Preview {
    ContentView()
}

struct FaceIdView: View {
    
    @State private var isPresented = false
    let context = LAContext()
    
    var body: some View {
        VStack {
            Button {
                autenticate()
            } label: {
                VStack {
                    Image(systemName: "faceid")
                        .font(.system(size: 120))
                    Text("Pulsa para mostrar el secreto")
                        .padding(.top, 20)
                }
            }
            if isPresented {
                Text("MEnsaje Secreto Desactivado")
            }
        }
    }
    
    func autenticate() {
        var error: NSError?
        if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
            context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: "Por favor autenticate para continuar"){success, error in
                if success {
                    isPresented.toggle()
                } else {
                    print("Error en la autenticacion biometrica")
                }
            }
        } else {
            print("El dispositivo no soporta autenticacion biometrica")
        }
    }
}
