package com.jjmf.android.checkbar.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.checkbar.ui.theme.ColorError

@Composable
fun CajaTexto(
    valor: String,
    newValor: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    modifier: Modifier = Modifier,
    error: String? = null,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    max: Int? = null,
) {
    Column {
        OutlinedTextField(
            value = valor,
            onValueChange = {
                if (max != null) {
                    if (it.length <= max) {
                        newValor(it)
                    }
                } else {
                    newValor(it)
                }
            },
            label = {
                label?.let { Text(text = it) }
            },
            placeholder = {
                placeholder?.let { Text(text = it) }
            },
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            maxLines = 1,
            singleLine = true,
            visualTransformation = visualTransformation,
            readOnly = readOnly
        )
        error?.let {
            Text(
                text = it,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 20.dp),
                color = ColorError
            )
        }
    }
}