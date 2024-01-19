package com.jjmf.android.checkbar.ui.Features.AddUsuario.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jjmf.android.checkbar.domain.model.Rol
import com.jjmf.android.checkbar.ui.theme.ColorP1

@Composable
fun SelectRol(
    rol: Rol,
    newValor:(Rol)->Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Rol", fontWeight = FontWeight.Medium, color = ColorP1)
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = rol == Rol.ADMIN,
                    onClick = {
                        newValor(Rol.ADMIN)
                    }
                )
                Text(text = "Admin")
            }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = rol == Rol.USER,
                    onClick = {
                        newValor(Rol.USER)
                    }
                )
                Text(text = "Usuario")
            }

        }
    }
}