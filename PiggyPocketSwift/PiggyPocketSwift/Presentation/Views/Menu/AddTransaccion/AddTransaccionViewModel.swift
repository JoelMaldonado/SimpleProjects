//
//  AddTransaccionViewModel.swift
//  PiggyPocketSwift
//
//  Created by Joel on 9/02/24.
//

import Foundation

class AddTransaccionViewModel : ObservableObject {
    
    @Published var tipo : TipoTransaccion = .Gasto
    @Published var monto = ""
    @Published var detalle = ""
    @Published var cat : UUID? = nil
    
    let listCategorias = [
        Categoria(id: 1, nombre: "Casa", image: "house", color: .init(red: 0.5, green: 0.1, blue: 0.8)),
        Categoria(id: 2, nombre: "Cafe", image: "mug", color: .init(red: 0.2, green: 0.7, blue: 0.3)),
        Categoria(id: 3, nombre: "Pasajes", image: "airplane", color: .init(red: 0.8, green: 0.4, blue: 0.1)),
    ]

    
}

struct Categoria: Codable {
    var id: Int
    var nombre: String
    var image: String
    var color: CatColor
    
    init(id: Int, nombre: String, image: String, color: CatColor) {
        self.id = id
        self.nombre = nombre
        self.image = image
        self.color = color
    }
}

struct CatColor : Codable {
    var red: Double
    var green: Double
    var blue: Double
    
    init(red: Double, green: Double, blue: Double) {
        self.red = red
        self.green = green
        self.blue = blue
    }
}

