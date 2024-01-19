package com.jjmf.android.checkbar.ui.Features.VerInventarios.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jjmf.android.checkbar.data.dto.InventarioDto
import com.jjmf.android.checkbar.domain.model.Inventario
import com.jjmf.android.checkbar.ui.components.CajaTexto2
import com.jjmf.android.checkbar.ui.theme.ColorP1

@Composable
fun DialogEditInventarioScreen(
    inv: Inventario,
    close: () -> Unit,
    save:(InventarioDto) -> Unit,
    loader:Boolean
) {

    val valor = remember { mutableStateOf(inv.nombre) }
    val cant = remember { mutableIntStateOf(inv.cant) }

    Dialog(onDismissRequest = close) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = ColorP1)
                Text(
                    text = "Editar",
                    color = ColorP1,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            CajaTexto2(
                valor = valor.value,
                newValor = { valor.value = it },
                label = "Nombre",
                placeholder = "Ingresa nombre del inventario",
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Cantidad", fontWeight = FontWeight.Medium, color = ColorP1)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    IconButton(
                        onClick = {
                            if (cant.intValue > 0) {
                                cant.intValue--
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = null,
                            tint = ColorP1
                        )
                    }

                    Text(text = cant.intValue.toString())

                    IconButton(
                        onClick = {
                            cant.intValue++
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = null,
                            tint = ColorP1
                        )
                    }
                }
            }

            if (loader){
                CircularProgressIndicator()
            }else{
                Button(
                    onClick = {
                        val invDto = InventarioDto(
                            id = inv.id,
                            nombre = valor.value,
                            cant = cant.intValue,
                            foto = inv.foto
                        )
                        save(invDto)
                    }
                ) {
                    Text(text = "Guardar")
                }
            }

        }
    }

}