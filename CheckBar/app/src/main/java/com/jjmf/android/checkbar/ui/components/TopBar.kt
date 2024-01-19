package com.jjmf.android.checkbar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.checkbar.ui.theme.ColorFondo
import com.jjmf.android.checkbar.ui.theme.ColorP1
import com.jjmf.android.checkbar.ui.theme.ColorP3


@Composable
fun TopBar(
    title:String,
    leading: @Composable (()->Unit)? = null,
    trailing: @Composable (()->Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP1),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = if (leading!=null) 8.dp else 50.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            leading?.let { it() }
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            trailing?.let { it() }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .padding(start = 30.dp)
                .clip(
                    RoundedCornerShape(topStart = 50.dp)
                )
                .background(ColorP3)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 30.dp))
                .background(Color.White),
            content = content
        )
    }
}