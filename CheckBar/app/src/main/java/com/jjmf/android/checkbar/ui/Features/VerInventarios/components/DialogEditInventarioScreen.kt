package com.jjmf.android.checkbar.ui.Features.VerInventarios.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import com.jjmf.android.checkbar.data.dto.InventarioDto
import com.jjmf.android.checkbar.data.dto.MovimientoDto
import com.jjmf.android.checkbar.data.dto.TipoMovimiento
import com.jjmf.android.checkbar.domain.model.Inventario
import com.jjmf.android.checkbar.ui.Features.VerInventarios.VerInventariosViewModel
import com.jjmf.android.checkbar.ui.components.CajaTexto2
import com.jjmf.android.checkbar.ui.theme.ColorP1

@Composable
fun DialogEditInventarioScreen(
    inv: Inventario,
    close: () -> Unit,
    save: (MovimientoDto) -> Unit,
    loader: Boolean
) {

    val cant = remember { mutableIntStateOf(0) }
    val tipo = remember { mutableStateOf(TipoMovimiento.Salida) }

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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(
                    selected = tipo.value == TipoMovimiento.Salida,
                    onClick = {
                        tipo.value = TipoMovimiento.Salida
                    }
                )
                Text(text = "Salida")
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(
                    selected = tipo.value == TipoMovimiento.Ingreso,
                    onClick = {
                        tipo.value = TipoMovimiento.Ingreso
                    }
                )
                Text(text = "Ingreso")
                Spacer(modifier = Modifier.weight(1f))
            }

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

            if (loader) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        val movimientoDto = MovimientoDto(
                            cant = cant.intValue,
                            tipo = tipo.value,
                            fecha = Timestamp.now()
                        )
                        save(movimientoDto)
                    }
                ) {
                    Text(text = "Guardar")
                }
            }

        }
    }

}