package com.jjmf.android.checkbar.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.android.checkbar.domain.model.Inventario
import com.jjmf.android.checkbar.ui.theme.ColorP4


@Composable
fun ItemInventario(
    inv: Inventario,
    edit: () -> Unit,
    toDetalle: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth(),
        leadingContent = {
            ImagenUsuario(
                modifier = Modifier.size(50.dp),
                foto = inv.foto,
                nombre = inv.nombre
            )
        },
        headlineContent = {
            Text(text = inv.nombre)
        },
        supportingContent = {
            Text(text = "Cantidad: ${inv.getTotal()}")

        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent
        ),
        trailingContent = {
            Row {
                IconButton(onClick = edit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
                IconButton(onClick = toDetalle) {
                    Icon(imageVector = Icons.Default.Visibility, contentDescription = null)
                }
            }
        }
    )
}
