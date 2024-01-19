package com.jjmf.android.checkbar.ui.Features.VerUsuarios

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.checkbar.domain.model.Rol
import com.jjmf.android.checkbar.ui.components.CardItem
import com.jjmf.android.checkbar.ui.components.TopBar
import com.jjmf.android.checkbar.ui.components.ItemUsuario

@Composable
fun VerUsuariosScreen(
    toAddUsuario: (id:String?) -> Unit,
    viewModel: VerUsuariosViewModel = hiltViewModel(),
) {

    val listAdmins = viewModel.listUsuarios.filter { it.rol == Rol.ADMIN }
    val listUsers = viewModel.listUsuarios.filter { it.rol == Rol.USER }

    LaunchedEffect(key1 = Unit) {
        viewModel.getUsuarios()
    }

    TopBar(
        title = "Gestión de Usuarios",
        trailing = {
            IconButton(
                onClick = {
                    toAddUsuario(null)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            CardItem(
                modifier = Modifier,
                title = {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                        Text(text = "Administradores")
                        Text(text = "(${listAdmins.size})")
                    }
                },
                alignment = Alignment.CenterHorizontally
            ) {
                if (listAdmins.isEmpty()) {
                    Text(
                        text = "Sin resultados",
                        modifier = Modifier.padding(8.dp),
                        color = Color.Gray
                    )
                } else {
                    listAdmins.forEachIndexed { index, usuario ->
                        ItemUsuario(
                            usuario = usuario,
                            click = {
                                toAddUsuario(usuario.id)
                            }
                        )
                        if (index != listAdmins.size - 1) {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                            )
                        }
                    }
                }
            }

            CardItem(
                title = {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Group, contentDescription = null)
                        Text(text = "Usuarios")
                        Text(text = "(${listUsers.size})")
                    }
                },
                alignment = Alignment.CenterHorizontally,
            ) {
                if (listUsers.isEmpty()) {
                    Text(
                        text = "Sin resultados",
                        modifier = Modifier.padding(8.dp),
                        color = Color.Gray
                    )
                } else {
                    listUsers.forEachIndexed { index, usuario ->
                        ItemUsuario(
                            usuario = usuario,
                            click = {
                                toAddUsuario(usuario.id)
                            }
                        )
                        if (index != listUsers.size - 1) {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                            )
                        }
                    }
                }
            }

        }

    }
}
