//
//  RegistroViewModel.swift
//  RecetarioSwift
//
//  Created by Joel on 30/01/24.
//

import Foundation

class RegistroViewModel : ObservableObject {
    
    @Published var email = ""
    @Published var pass = ""
    
    
    @MainActor
    func createUser() async throws {
        try await AuthService.shared.createUser(email: email, pass: pass)
    }
    
}
