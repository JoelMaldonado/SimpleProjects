//
//  AddTransaccion.swift
//  PiggyPocketSwift
//
//  Created by Joel on 9/02/24.
//

import SwiftUI

struct AddTransaccionView: View {
    
    @StateObject private var viewModel = AddTransaccionViewModel()
    
    var body: some View {
        VStack {
            Picker("", selection: $viewModel.tipo) {
                Text("Ingreso")
                    .tag(TipoTransaccion.Ingreso)
                Text("Gasto")
                    .tag(TipoTransaccion.Gasto)
            }
            .pickerStyle(.segmented)
            
            
            /*
            LazyVGrid(columns: [
                GridItem(.flexible(), spacing: 16),
                GridItem(.flexible(), spacing: 16),
                GridItem(.flexible(), spacing: 16),
                GridItem(.flexible(), spacing: 16),
            ],spacing: 16, content: {
                ForEach(viewModel.listCategorias, id: \.self.id){cat in
                    Button {
                    } label: {
                        
                        var shape =  0.0
                        
                        VStack{
                            Image(systemName: cat.image)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 30)
                                .padding()
                                .background(Color(red: cat.color.red, green: cat.color.green, blue:cat.color.blue))
                                .clipShape(.circle)
                            
                                .foregroundStyle(.white)
                            Text(cat.nombre)
                                .foregroundStyle(.black)
                        }
                        .background(viewModel.cat == cat.id ? Color(red: cat.color.red, green: cat.color.green, blue:cat.color.blue) : nil)
                        .foregroundColor(viewModel.cat == cat.id ? Color.white : nil)
                        
                    }
                }
                
                NavigationLink {
                    AddCategoriaView()
                } label: {
                    
                    VStack{
                        Image(systemName: "plus")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 30)
                            .padding()
                            .background(.colorP1)
                            .clipShape(.circle)
                        
                            .foregroundStyle(.white)
                        Text("Agregar")
                            .foregroundStyle(.black)
                    }
                }
            })
            
            */
            
            HStack {
                Text("S/")
                TextField(text: $viewModel.monto, label: {
                    Text("0.0")
                })
                .keyboardType(.decimalPad)
                .frame(maxWidth: 80)
            }
            .font(.system(size: 40))
            
            TextField("Detalle", text: $viewModel.detalle)
                .textFieldStyle(.roundedBorder)
            
            HStack {
                Button {
                    
                } label: {
                    Image(systemName: "plus")
                }
                .frame(maxWidth: .infinity)
                .frame(height: 80)
                Button {
                    
                } label: {
                    Image(systemName: "plus")
                }
                .frame(maxWidth: .infinity)
                Button {
                    
                } label: {
                    Image(systemName: "plus")
                }
                .frame(maxWidth: .infinity)
            }
            
            HStack{
                Button {
                    
                } label: {
                    Text("Cancelar")
                }
                .buttonStyle(.bordered)
                
                Button {
                    
                } label: {
                    Text("Guardar")
                }
                .buttonStyle(.borderedProminent)
            }
            
        }
        .padding()
    }
}

enum TipoTransaccion {
    case Ingreso
    case Gasto
}

#Preview {
    AddTransaccionView()
}
