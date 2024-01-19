package com.jjmf.android.checkbar.ui.Features.AddUsuario.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.checkbar.ui.theme.ColorP3

@Composable
fun CajaBuscarArea(
    valor: String,
    newValor: (String) -> Unit,
    modifier: Modifier = Modifier,
    bool: Boolean,
    click: () -> Unit,
) {
    val colorText = Color.DarkGray
    val focus = LocalFocusManager.current
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray.copy(0.2f))
                .height(35.dp)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f).padding(start = 10.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = valor,
                    onValueChange = newValor,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    maxLines = 1,
                    textStyle = TextStyle(fontSize = 14.sp, color = colorText),
                    cursorBrush = SolidColor(Color.White)
                )
                if (valor.isEmpty()) {
                    Text(text = "Area", color = colorText, fontSize = 14.sp)
                }
            }
            if (valor.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = null,
                    tint = colorText,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            newValor("")
                        }
                )
            }
        }

        AnimatedVisibility(visible = bool) {
            Text(
                text = "Guardar",
                color = ColorP3,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        click()
                        focus.clearFocus()
                    },
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}