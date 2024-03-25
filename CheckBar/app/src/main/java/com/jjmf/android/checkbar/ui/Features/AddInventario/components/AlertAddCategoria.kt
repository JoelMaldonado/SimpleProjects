package com.jjmf.android.checkbar.ui.Features.AddInventario.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.checkbar.ui.Features.AddInventario.AddInventarioViewModel
import com.jjmf.android.checkbar.ui.components.CajaTexto2

@Composable
fun AlertAddCategoria(
    viewModel: AddInventarioViewModel = hiltViewModel()
) {
    val nombre = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            viewModel.alertAddCategoria = false
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CajaTexto2(
                valor = nombre.value, newValor = { nombre.value = it },
                label = "Agregar Categoria",
                placeholder = "Ingrese nombre"
            )

            if (viewModel.isLoadingAlertAddCategoria) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        viewModel.addCategoria(nombre.value)
                    }
                ) {
                    Text(text = "Guardar")
                }
            }

        }

    }
}