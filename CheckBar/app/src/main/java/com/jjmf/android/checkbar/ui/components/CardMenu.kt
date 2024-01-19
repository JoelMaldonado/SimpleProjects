package com.jjmf.android.checkbar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jjmf.android.checkbar.ui.theme.ColorP3
import com.jjmf.android.checkbar.ui.theme.ColorP4


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMenu(
    modifier: Modifier,
    icon: ImageVector,
    text: String,
    click: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier.height(110.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = ColorP4
        ),
        elevation = CardDefaults.elevatedCardElevation(16.dp),
        onClick = click
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                    .background(ColorP3)
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                //Icono del Card
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(35.dp)
                )
            }
            //Texto del Card
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}