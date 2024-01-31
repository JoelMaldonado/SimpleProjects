//
//  TextViewRounded.swift
//  RecetarioSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

struct TextViewRounded: ViewModifier {
    func body(content: Content) -> some View {
        content
            .overlay(
                RoundedRectangle(cornerRadius: 24)
                    .stroke(Color(.systemGray3), lineWidth: 1)
                    .padding(.horizontal, -12)
            )
            .padding(.horizontal, 24)
    }
}
