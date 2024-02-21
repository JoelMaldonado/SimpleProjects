//
//  File.swift
//  PiggyPocketSwift
//
//  Created by Joel on 12/02/24.
//

import Foundation
import SwiftUI
import Alamofire

class AddCategoriaViewModel : ObservableObject {
    @Published var nombre = ""
    @Published var tipo: TipoTransaccion = .Gasto
    @Published var color: Color = .red
    @Published var icono = "person"
    
    func addCategoria(){
        let f = color.description
        print(f)
        let categoria = Categoria(
            id: 1,
            nombre: nombre,
            image: icono,
            color: CatColor(red: 0.5, green: 0.7, blue: 0.9)
        )
        
        
        return;
        
        AF.request(
            "",
            method: .post,
            parameters: categoria,
            encoder: JSONParameterEncoder.default
        )
        .responseDecodable(of: [Categoria].self){res in
            
        }
    }
}
