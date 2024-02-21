//
//  SelectColorSheet.swift
//  PiggyPocketSwift
//
//  Created by Joel on 12/02/24.
//

import SwiftUI

struct SelectColorSheet: View {
    
    var selectColor : (Color)-> Void
    
    let listColores: [Color] = [
        .amarillo1, .amarillo2, .amarillo3, .amarillo4, .amarillo5,
        .azul1, .azul2, .azul3, .azul4, .azul5,
        .cian1, .cian2, .cian3, .cian4, .cian5,
        .gris1, .gris2, .gris3, .gris4, .gris5,
        .marron1, .marron2, .marron3, .marron4, .marron5,
        .morado1, .morado2, .morado3, .morado4, .morado5,
        .naranja1, .naranja2, .naranja3, .naranja4, .naranja5,
        .rojo1, .rojo2, .rojo3, .rojo4, .rojo5,
        .rosa1, .rosa2, .rosa3, .rosa4, .rosa5,
        .turquesa1, .turquesa2, .turquesa3, .turquesa4, .turquesa5,
        .verde1, .verde2, .verde3, .verde4, .verde5
    ]
    var body: some View {
        VStack(alignment: .leading) {
            Text("Selecciona un color")
                .font(.title2)
            ScrollView {
                LazyVGrid(columns: [
                GridItem(),
                GridItem(),
                GridItem(),
                GridItem(),
                GridItem(),
                ], content: {
                    ForEach(self.listColores, id: \.self){col in
                        Button {
                            selectColor(col)
                        } label: {
                            Circle()
                                .frame(width: 50)
                                .foregroundStyle(col)
                        }
                    }
                })
            }
        }
        .padding()
    }
}
