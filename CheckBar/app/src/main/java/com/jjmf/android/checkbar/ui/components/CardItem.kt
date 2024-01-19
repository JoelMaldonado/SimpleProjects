package com.jjmf.android.checkbar.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jjmf.android.checkbar.ui.theme.ColorFondo

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    title: @Composable ColumnScope.() -> Unit,
    alignment: Alignment.Horizontal = Alignment.Start,
    paddingContent: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = ColorFondo
            ),
            content = title
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = ColorFondo
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingContent),
                horizontalAlignment = alignment,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                content = content
            )
        }
    }
}