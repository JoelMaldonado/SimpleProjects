package com.jjmf.android.checkbar.ui.Features.DetalleInventario

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import com.jjmf.android.checkbar.ui.components.TopBar
import com.jjmf.android.checkbar.util.format
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DetalleInventarioScreen(
    id: String,
    back: () -> Unit,
    viewModel: DetalleInventarioViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.get(id)
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
        title = "Detalle"
    ) {

        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        val inv = viewModel.inventario ?: return@TopBar

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(inv.movimientos.sortedByDescending { it.fecha }) {
                ListItem(
                    headlineContent = {
                        Text(text = "${it.tipo.name}: ${it.cant}")
                    },
                    supportingContent = {
                        Text(text = "Fecha: ${it.fecha.format("dd/MM/yyyy - hh:mm a")}")
                    }
                )

            }
        }

    }
}