//
//  RoundedColorButton.swift
//  RecetarioSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

struct RoundedColorButton: ViewModifier {
    let color : Color
    func body(content: Content) -> some View {
        content
            .fontWeight(.semibold)
            .font(.subheadline)
            .frame(height: 48)
            .frame(maxWidth: .infinity)
            .foregroundStyle(.white)
            .padding(.horizontal)
            .background(color)
            .clipShape(.capsule)
            .padding(.horizontal, 24)
    }
}
