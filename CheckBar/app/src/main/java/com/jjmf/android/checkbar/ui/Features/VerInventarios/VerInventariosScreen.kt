package com.jjmf.android.checkbar.ui.Features.VerInventarios

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.checkbar.ui.Features.VerInventarios.components.DialogEditInventarioScreen
import com.jjmf.android.checkbar.ui.components.ItemInventario
import com.jjmf.android.checkbar.ui.components.TopBar
import com.jjmf.android.checkbar.util.show

@Composable
fun VerInventariosScreen(
    back: () -> Unit,
    addInventario: () -> Unit,
    viewModel: VerInventariosViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.getListInventario()
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
            save = {
                viewModel.editInventario(it)
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
            items(viewModel.listInventario) { inventario ->
                ItemInventario(
                    inv = inventario,
                    click = {
                        viewModel.dialogEditInventario = inventario
                    }
                )
            }
        }
    }
}
