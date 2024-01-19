package com.jjmf.android.checkbar.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
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
    click:() -> Unit
) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth().clickable {
                                      click()
            },
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
            Text(text = "Cantidad: ${inv.cant}")
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent
        )
    )
}
