//
//  CuentasView.swift
//  PiggyPocketSwift
//
//  Created by Joel on 13/02/24.
//

import SwiftUI

struct CuentasView: View {
    var body: some View {
        VStack{
            ZStack {
                Color(.colorP1)
                    .clipShape(MyShape(bottomLeft: 50, bottomRight: 50))
                    .shadow(radius: 10)
                    .ignoresSafeArea(.all)
                        
                Text("Cuentas")
                    .font(.title)
                    .foregroundStyle(.white)
                    .bold()
            }
            .frame(height: 30)
            
            Spacer()
        }
    }
}
struct MyShape: Shape {
    var topLeft: CGFloat = 0.0
    var topRight: CGFloat = 0.0
    var bottomLeft: CGFloat = 0.0
    var bottomRight: CGFloat = 0.0
    
    func path(in rect: CGRect) -> Path {
        var path = Path()

        let width = rect.size.width
        let height = rect.size.height

        path.move(to: CGPoint(x: 0, y: topLeft))
        path.addQuadCurve(to: CGPoint(x: topLeft, y: 0), control: CGPoint(x: 0, y: 0))
        path.addLine(to: CGPoint(x: width - topRight, y: 0))
        path.addQuadCurve(to: CGPoint(x: width, y: topRight), control: CGPoint(x: width, y: 0))
        path.addLine(to: CGPoint(x: width, y: height - bottomRight))
        path.addQuadCurve(to: CGPoint(x: width - bottomRight, y: height), control: CGPoint(x: width, y: height))
        path.addLine(to: CGPoint(x: bottomLeft, y: height))
        path.addQuadCurve(to: CGPoint(x: 0, y: height - bottomLeft), control: CGPoint(x: 0, y: height))
        path.closeSubpath()

        return path
    }
}

#Preview {
    CuentasView()
}
