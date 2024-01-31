//
//  AuthService.swift
//  RecetarioSwift
//
//  Created by Joel on 30/01/24.
//


import FirebaseAuth

class AuthService {
    
    @Published var userSession: FirebaseAuth.User?
    
    static let shared = AuthService()
    
    init() {
        self.userSession = Auth.auth().currentUser
    }
    
    @MainActor
    func createUser(email: String, pass:String) async throws {
        do {
            let result = try await Auth.auth().createUser(withEmail: email, password: pass)
            self.userSession = result.user
            print("Usuario Creado \(result.user.uid)")
        } catch {
            print("Debug error \(error.localizedDescription)")
        }
    }
    
}
