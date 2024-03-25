package com.jjmf.android.checkbar.ui.Features.VerInventarios

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.checkbar.ui.Features.VerInventarios.components.DialogEditInventarioScreen
import com.jjmf.android.checkbar.ui.components.CajaTexto2
import com.jjmf.android.checkbar.ui.components.ItemInventario
import com.jjmf.android.checkbar.ui.components.TopBar
import com.jjmf.android.checkbar.ui.theme.ColorP1
import com.jjmf.android.checkbar.ui.theme.ColorP3
import com.jjmf.android.checkbar.util.show

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerInventariosScreen(
    back: () -> Unit,
    addInventario: () -> Unit,
    toDetalle: (id: String) -> Unit,
    viewModel: VerInventariosViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val isFilterVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getListInventario()
        viewModel.getAreas()
        viewModel.getCategorias()
    }

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    viewModel.dialogEditInventario?.let {
        DialogEditInventarioScreen(
            inv = it,
            close = {
                viewModel.dialogEditInventario = null
            },
            save = { mov ->
                viewModel.editInventario(it.id, mov)
            },
            loader = viewModel.loaderAlert
        )
    }

    TopBar(
        leading = {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        title = "Inventarios",
        trailing = {
            IconButton(onClick = addInventario) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${viewModel.listInventario.size} Inventarios")
                IconButton(onClick = { isFilterVisible.value = !isFilterVisible.value }) {
                    Icon(
                        imageVector = Icons.Default.FilterAlt,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            }
            AnimatedVisibility(visible = isFilterVisible.value) {
                Column {
                    CajaTexto2(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        valor = viewModel.buscador,
                        newValor = { viewModel.buscador = it },
                        label = "Buscar",
                        placeholder = " Ingresar nombre de inventario"
                    )
                    Text(
                        text = "Areas",
                        modifier = Modifier.padding(start = 16.dp),
                        color = ColorP3,
                        fontWeight = FontWeight.Medium
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item { Spacer(modifier = Modifier.width(8.dp)) }
                        item {
                            FilterChip(
                                selected = viewModel.area == null,
                                onClick = { viewModel.area = null },
                                label = { Text(text = "Todos") }
                            )
                        }
                        items(viewModel.listAreas) {
                            FilterChip(
                                selected = viewModel.area == it.nombre,
                                onClick = { viewModel.area = it.nombre },
                                label = { Text(text = it.nombre) }
                            )
                        }
                        item { Spacer(modifier = Modifier.width(8.dp)) }
                    }
                    Text(
                        text = "Categorias",
                        modifier = Modifier.padding(start = 16.dp),
                        color = ColorP3,
                        fontWeight = FontWeight.Medium
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item { Spacer(modifier = Modifier.width(8.dp)) }
                        item {
                            FilterChip(
                                selected = viewModel.categoria == null,
                                onClick = { viewModel.categoria = null },
                                label = { Text(text = "Todos") }
                            )
                        }
                        items(viewModel.listCategorias) {
                            FilterChip(
                                selected = viewModel.categoria == it.nombre,
                                onClick = { viewModel.categoria = it.nombre },
                                label = { Text(text = it.nombre) }
                            )
                        }
                        item { Spacer(modifier = Modifier.width(8.dp)) }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                if (viewModel.loader) {
                    item {
                        CircularProgressIndicator()
                    }
                }

                if (viewModel.listInventario.isEmpty()) {
                    item {
                        Text(text = "No hay inventarios")
                    }
                }
                items(
                    viewModel.listInventario
                        .sortedByDescending { it.fecha }
                        .filter {
                            if (viewModel.area == null) {
                                true
                            } else {
                                it.area == viewModel.area
                            }
                        }
                        .filter {
                            if (viewModel.categoria == null) {
                                true
                            } else {
                                it.categoria == viewModel.categoria
                            }
                        }
                        .filter {
                            if (viewModel.buscador.isEmpty()) {
                                true
                            } else {
                                it.nombre.contains(viewModel.buscador)
                            }
                        }
                ) { inventario ->
                    ItemInventario(
                        inv = inventario,
                        edit = {
                            viewModel.dialogEditInventario = inventario
                        },
                        toDetalle = {
                            toDetalle(inventario.id)
                        }
                    )
                }
            }
        }
    }
}
