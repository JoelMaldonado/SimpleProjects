package com.jjmf.android.checkbar.ui.Features.AddInventario

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jjmf.android.checkbar.ui.components.CajaTexto2
import com.jjmf.android.checkbar.ui.components.ImagenUsuario
import com.jjmf.android.checkbar.ui.components.TopBar

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddInventarioScreen(
    id: String? = null,
    back: () -> Unit,
    viewModel: AddInventarioViewModel = hiltViewModel(),
) {


    val context = LocalContext.current

    viewModel.error?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        viewModel.error = null
    }

    if (viewModel.back) {
        back()
        viewModel.back = false
    }

    val uri = remember { mutableStateOf<Uri?>(null) }

    // Permisos
    val permisos = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )

    // Tomar foto con la camara
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if (it) {
                viewModel.foto = uri.value
            }
        }
    )

    TopBar(
        leading = {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        title = "Crear Inventario"
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Foto de perfil
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(0.2f))
                        .clickable {
                            if (permisos.allPermissionsGranted) {
                                uri.value = context.contentResolver.insert(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    ContentValues()
                                )
                                cameraLauncher.launch(uri.value)
                            } else {
                                permisos.launchMultiplePermissionRequest()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (viewModel.foto == null) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(50.dp)
                        )
                    } else {
                        ImagenUsuario(
                            modifier = Modifier.fillMaxSize(),
                            foto = viewModel.foto.toString(),
                            nombre = "?",
                            isBig = true
                        )
                    }
                }
                Text(
                    text = if (id == null) "Agregar foto" else "Editar foto",
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }

            CajaTexto2(
                valor = viewModel.detalle,
                newValor = { viewModel.detalle = it },
                label = "Detalle",
                placeholder = "Ingresar detalle de inventario"
            )

            CajaTexto2(
                valor = viewModel.cant,
                newValor = { viewModel.cant = it },
                label = "Cantidad",
                placeholder = "Ingresar cantidad",
                keyboardType = KeyboardType.Number
            )
            if (viewModel.loader) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = viewModel::insert
                ) {
                    Text(text = "Agregar")
                }
            }
        }

    }

}