//
//  InicioView.swift
//  PiggyPocketSwift
//
//  Created by Joel on 9/02/24.
//

import SwiftUI

struct InicioView: View {
    var click: ()->Void
    var body: some View {
        ZStack(alignment: .bottomTrailing){
            Button {
                click()
            } label: {
                Image(systemName: "plus")
                    .foregroundStyle(.white)
                    .padding()
                    .background(.colorP1)
                    .clipShape(.circle)
            }
        }
    }
}
