package com.jjmf.android.checkbar.ui.Features.AddUsuario.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.android.checkbar.ui.Features.AddUsuario.AddUsuarioViewModel
import com.jjmf.android.checkbar.ui.Features.AddUsuario.components.CajaBuscarArea
import com.jjmf.android.checkbar.ui.theme.ColorP1

@Composable
fun SelectAreaScreen(
    //listAreas : List<String>,
    back: () -> Unit,
    viewModel: AddUsuarioViewModel,
) {

    val listAreas = viewModel.listAreas
    val valor = remember { mutableStateOf("") }
    val list = remember { mutableStateOf(listAreas) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CajaBuscarArea(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = 18.dp),
                valor = valor.value,
                newValor = {
                    valor.value = it
                    if (it.isEmpty()) {
                        list.value = listAreas
                    } else {
                        list.value = listAreas.filter { area ->
                            area.contains(it, ignoreCase = true)
                        }
                    }
                },
                bool = valor.value.uppercase() !in listAreas.map { it.uppercase() } && valor.value.isNotEmpty() && list.value.isEmpty(),
                click = {
                    back()
                    viewModel.area = valor.value
                }
            )
        }
        if (list.value.isEmpty()) {
            item {
                Text(
                    text = "No se encontraron resultados",
                    modifier = Modifier.padding(18.dp),
                    color = Color.LightGray
                )
            }
        }
        items(list.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        back()
                        viewModel.area = it
                    }
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = it,
                )
                if (it.uppercase() == viewModel.area.uppercase()) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            }
        }
    }
}