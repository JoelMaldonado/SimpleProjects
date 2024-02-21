//
//  SelectIconSheet.swift
//  PiggyPocketSwift
//
//  Created by Joel on 13/02/24.
//

import SwiftUI

struct SelectIconSheet: View {
    
    let listIcons = [
        "bag", "cart", "basket", "creditcard", "giftcard", "dollarsign", "bus", "car", "box.truck", "bicycle", "house", "lightbulb", "globe.americas", "oven", "heater.vertical", "leaf", "bolt", "fish", "tree", "pawprint", "clock", "alarm", "hourglass", "heart", "list.bullet.clipboard", "microbe", "ear", "pill", "airplane"
    ]
    
    var selectIcon : (String)->Void
    
    var body: some View {
        VStack{
            ScrollView {
                LazyVGrid(columns: [
                    GridItem(),
                    GridItem(),
                    GridItem(),
                    GridItem()
                ], content: {
                    ForEach(self.listIcons, id: \.self){item in
                        Button {
                            selectIcon(item)
                        } label: {
                            Image(systemName: item)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 40, height: 40)
                                .foregroundStyle(.white)
                                .padding()
                                .background(.colorP1)
                                .clipShape(.circle)
                        }
                    }
                })
            }
        }
        .padding()
    }
}
