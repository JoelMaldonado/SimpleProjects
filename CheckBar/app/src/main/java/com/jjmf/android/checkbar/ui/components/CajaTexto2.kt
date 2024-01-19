package com.jjmf.android.checkbar.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.checkbar.ui.theme.ColorP1


@Composable
fun CajaTexto2(
    modifier: Modifier = Modifier,
    valor: String,
    newValor: (String) -> Unit,
    label: String,
    placeholder: String,
    error: String? = null,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isEnabled: Boolean = true,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = label, fontWeight = FontWeight.Medium, color = ColorP1)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray.copy(0.2f))
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (valor.isEmpty()) {
                Text(text = placeholder, color = Color.DarkGray)
            }
            BasicTextField(
                value = valor,
                onValueChange = newValor,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    capitalization = capitalization,
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = keyboardActions,
                enabled = isEnabled,
            )
        }
        AnimatedVisibility(visible = error != null) {
            Text(
                text = error ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp, start = 10.dp),
                fontSize = 12.sp
            )
        }
    }
}