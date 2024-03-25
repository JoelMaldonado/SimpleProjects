package com.jjmf.android.checkbar.ui.Features.DetalleInventario

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
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

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.get(id)
    }

    if (viewModel.back){
        LaunchedEffect(key1 = Unit){
            viewModel.back = false
            back()
        }
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
        title = "Detalle",
        trailing = {
            IconButton(
                onClick = {
                    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
                        titleText = "Eliminar Inventario"
                        contentText = "Estas seguro?"

                        setCancelButton("Cancelar"){
                            dismissWithAnimation()
                        }
                        setConfirmButton("Confirmar"){
                            dismissWithAnimation()
                            viewModel.delete(id)
                        }

                        show()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
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