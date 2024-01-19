package com.jjmf.android.checkbar.ui.Features.Login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.android.checkbar.ui.Features.Login.LoginViewModel
import com.jjmf.android.checkbar.ui.theme.ColorP1
import com.jjmf.android.checkbar.ui.theme.ColorTextos

@Composable
fun LoginRecuerdame(
    modifier: Modifier,
    viewModel: LoginViewModel
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = "Recordar usuario", color = Color.Gray)
        Switch(
            checked = viewModel.recuerdame,
            onCheckedChange = {
                viewModel.recuerdame = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = ColorP1,
                checkedTrackColor = ColorP1.copy(alpha = 0.5f),
                uncheckedThumbColor = ColorTextos,
                uncheckedTrackColor = ColorTextos.copy(alpha = 0.5f),
                uncheckedBorderColor = Color.Transparent,
                checkedBorderColor = Color.Transparent
            )

        )
    }
}