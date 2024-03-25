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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jjmf.android.checkbar.ui.Features.AddInventario.components.AlertAddArea
import com.jjmf.android.checkbar.ui.Features.AddInventario.components.AlertAddCategoria
import com.jjmf.android.checkbar.ui.components.CajaTexto2
import com.jjmf.android.checkbar.ui.components.ImagenUsuario
import com.jjmf.android.checkbar.ui.components.TopBar
import com.jjmf.android.checkbar.ui.theme.ColorP1

@OptIn(ExperimentalPermissionsApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddInventarioScreen(
    id: String? = null,
    back: () -> Unit,
    viewModel: AddInventarioViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getCategorias()
        viewModel.getAreas()
    }

    val isExpanded = remember { mutableStateOf(false) }
    val isExpanded2 = remember { mutableStateOf(false) }

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

    if (viewModel.alertAddCategoria) {
        AlertAddCategoria()
    }

    if (viewModel.alertAddArea){
        AlertAddArea()
    }

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

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Categoria", fontWeight = FontWeight.Medium, color = ColorP1)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray.copy(0.2f))
                        .clickable { isExpanded.value = !isExpanded.value }
                        .padding(horizontal = 10.dp)
                ) {

                    Text(
                        text = viewModel.categoria.ifEmpty { "Seleccionar Categoria" },
                        modifier = Modifier.align(Alignment.CenterStart),
                        fontSize = 14.sp
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = isExpanded.value,
                    onDismissRequest = { isExpanded.value = false },
                    properties = PopupProperties(
                        usePlatformDefaultWidth = true
                    )
                ) {
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = { Text(text = "Agregar Nuevo") },
                        onClick = {
                            viewModel.alertAddCategoria = true
                            isExpanded.value = false
                        },
                        trailingIcon = {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    )
                    viewModel.listCategorias.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.nombre) },
                            onClick = {
                                isExpanded.value = false
                                viewModel.categoria = it.nombre
                            }
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Area", fontWeight = FontWeight.Medium, color = ColorP1)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray.copy(0.2f))
                        .clickable { isExpanded2.value = !isExpanded2.value }
                        .padding(horizontal = 10.dp)
                ) {

                    Text(
                        text = viewModel.area.ifEmpty { "Seleccionar Area" },
                        modifier = Modifier.align(Alignment.CenterStart),
                        fontSize = 14.sp
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = isExpanded2.value,
                    onDismissRequest = { isExpanded2.value = false },
                    properties = PopupProperties(
                        usePlatformDefaultWidth = true
                    )
                ) {
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = { Text(text = "Agregar Nuevo") },
                        onClick = {
                            viewModel.alertAddArea = true
                            isExpanded2.value = false
                        },
                        trailingIcon = {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    )
                    viewModel.listAreas.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.nombre) },
                            onClick = {
                                isExpanded2.value = false
                                viewModel.area = it.nombre
                            }
                        )
                    }
                }
            }


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