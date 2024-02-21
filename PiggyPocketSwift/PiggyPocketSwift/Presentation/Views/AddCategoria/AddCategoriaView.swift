//
//  AddCategoriaView.swift
//  PiggyPocketSwift
//
//  Created by Joel on 12/02/24.
//

import SwiftUI

struct AddCategoriaView: View {
    @StateObject private var viewModel = AddCategoriaViewModel()
    
    @State private var isPresented = false
    @State private var isPresented2 = false
    
    var body: some View {
        VStack{
            TextField("Nombre", text: $viewModel.nombre)
                .textFieldStyle(.roundedBorder)
            
            HStack {
                Text("Tipo")
                    .font(.title3)
                    .fontWeight(.medium)
                
                Spacer()
                Picker("", selection: $viewModel.tipo) {
                    Text("Gastos")
                        .tag(TipoTransaccion.Gasto)
                    Text("Ingresos")
                        .tag(TipoTransaccion.Ingreso)
                }
                .pickerStyle(.menu)
            }
            
            HStack {
                Text("Icono")
                    .font(.title3)
                    .fontWeight(.medium)
                
                Spacer()
                
                Button {
                    isPresented2 = true
                } label: {
                    Image(systemName: viewModel.icono)
                        .resizable()
                        .scaledToFit()
                        .foregroundStyle(.white)
                        .frame(width: 20, height: 20)
                        .frame(width: 40, height: 40)
                        .background(viewModel.color)
                        .clipShape(.circle)
                }
            }
            
            HStack {
                Text("Color")
                    .font(.title3)
                    .fontWeight(.medium)
                
                Spacer()
                
                Button {
                    isPresented = true
                } label: {
                    Circle()
                        .frame(width: 40)
                        .foregroundStyle(viewModel.color)
                }
            }
            
            Button {
                
            } label: {
                Text("Guardar")
            }
            
        }
        .navigationTitle("Agregar Categoria")
        .sheet(isPresented: $isPresented, content: {
            SelectColorSheet(){ color in
                viewModel.color = color
                isPresented = false
            }
        })
        .sheet(isPresented: $isPresented2, content: {
            SelectIconSheet(){icono in
                viewModel.icono = icono
                isPresented2 = false
            }
        })
        .padding()
    }
}

#Preview {
    AddCategoriaView()
}
