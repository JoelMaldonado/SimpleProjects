package com.jjmf.android.checkbar.ui.Features.AddUsuario.Screens

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jjmf.android.checkbar.ui.Features.AddUsuario.AddUsuarioViewModel
import com.jjmf.android.checkbar.ui.Features.AddUsuario.components.SelectRol
import com.jjmf.android.checkbar.ui.components.CajaTexto2
import com.jjmf.android.checkbar.ui.components.ImagenUsuario
import com.jjmf.android.checkbar.ui.theme.ColorP1

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeAddUsuarioScreen(
    id: String?,
    toSelectArea: () -> Unit,
    viewModel: AddUsuarioViewModel,
) {

    val context = LocalContext.current
    val focus = LocalFocusManager.current
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
                viewModel.uri = uri.value
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
                if (viewModel.uri == null) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(50.dp)
                    )
                } else {
                    ImagenUsuario(
                        modifier = Modifier.fillMaxSize(),
                        foto = viewModel.uri.toString(),
                        nombre = viewModel.nombre,
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

        // Input Usuario
        CajaTexto2(
            valor = viewModel.usuario,
            newValor = {
                viewModel.usuario = it
            },
            label = "Usuario",
            placeholder = "Ingresar usuario",
            error = viewModel.errorUsuario,
            capitalization = KeyboardCapitalization.Characters,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            ),
            isEnabled = id == null
        )

        // Input Nombre
        CajaTexto2(
            valor = viewModel.nombre,
            newValor = {
                viewModel.nombre = it
            },
            label = "Nombres",
            placeholder = "Ingresar nombres",
            error = viewModel.errorNombre,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            )
        )

        // Input Apellido
        CajaTexto2(
            valor = viewModel.apellido,
            newValor = {
                viewModel.apellido = it
            },
            label = "Apellidos",
            placeholder = "Ingresar apellidos",
            error = viewModel.errorApellido,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            )
        )


        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Area", fontWeight = FontWeight.Medium, color = ColorP1)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray.copy(0.2f))
                    .clickable {
                        toSelectArea()
                    }
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = viewModel.area.ifEmpty { "Seleccionar area" }, color = Color.DarkGray)
                Icon(
                    imageVector = Icons.Default.NavigateNext,
                    contentDescription = null,
                    tint = Color.DarkGray
                )
            }
        }

        SelectRol(
            rol = viewModel.rol,
            newValor = {
                viewModel.rol = it
            }
        )

        if (viewModel.loader) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    viewModel.addEditUsuario(id)
                }
            ) {
                Text(text = "Guardar")
            }

        }
    }
}