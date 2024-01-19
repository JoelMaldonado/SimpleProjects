package com.jjmf.android.checkbar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.jjmf.android.checkbar.domain.model.Usuario
import com.jjmf.android.checkbar.ui.theme.ColorP1
import com.jjmf.android.checkbar.ui.theme.ColorP4


@Composable
fun ItemUsuario(usuario: Usuario, click: () -> Unit) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                click()
            },
        leadingContent = {
            ImagenUsuario(
                modifier = Modifier.size(50.dp),
                foto = usuario.fotoUrl,
                nombre = usuario.nombre
            )
        },
        headlineContent = {
            Text(text = "${usuario.nombre} ${usuario.apellido}")
        },
        supportingContent = {
            Text(text = "Area: ${usuario.area}")
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent
        ),
        trailingContent = {
            Icon(
                imageVector = Icons.Default.NavigateNext,
                contentDescription = null,
                tint = ColorP4
            )
        }
    )
}

@Composable
fun ImagenUsuario(
    modifier: Modifier = Modifier,
    foto: String,
    nombre: String? = null,
    isBig: Boolean = false,
) {
    SubcomposeAsyncImage(
        model = foto,
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .background(if (isBig) Color.LightGray.copy(0.3f) else ColorP1),
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = nombre?.firstOrNull()?.uppercase() ?: "?",
                    fontWeight = FontWeight.SemiBold,
                    color = if (isBig) Color.Gray else Color.White,
                    fontSize = if (isBig) 48.sp else 24.sp
                )
            }
        }
    )
}
