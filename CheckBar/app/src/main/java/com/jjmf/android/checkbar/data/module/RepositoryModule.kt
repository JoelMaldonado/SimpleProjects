package com.jjmf.android.checkbar.data.module

import com.jjmf.android.checkbar.data.repository.AreaRepository
import com.jjmf.android.checkbar.data.repository.InventarioRepository
import com.jjmf.android.checkbar.data.repository.UsuarioRepository
import com.jjmf.android.checkbar.domain.repository.AreaRepositoryImpl
import com.jjmf.android.checkbar.domain.repository.InventarioRepositoryImpl
import com.jjmf.android.checkbar.domain.repository.UsuarioRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindInventarioRepository(impl: InventarioRepositoryImpl): InventarioRepository

    @Binds
    abstract fun bindUsuarioRepository(impl: UsuarioRepositoryImpl): UsuarioRepository

    @Binds
    abstract fun bindAreaRepository(impl: AreaRepositoryImpl): AreaRepository

}