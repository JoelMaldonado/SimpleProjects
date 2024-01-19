package com.jjmf.android.checkbar.data.module

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule{

    /** Instancia de Firebase **/

    @Singleton
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()


    /** Coleccion de Inventario **/

    @InventarioCollection
    @Singleton
    @Provides
    fun provideInventarioCollection(fb: FirebaseFirestore) = fb.collection("Inventario")

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InventarioCollection


    /** Colección de Usuario **/

    @UsuarioCollection
    @Singleton
    @Provides
    fun provideUsuarioCollection(fb: FirebaseFirestore) = fb.collection("Usuario")

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsuarioCollection


    /** Colección de Area **/

    @AreaCollection
    @Singleton
    @Provides
    fun provideAreaCollection(fb: FirebaseFirestore) = fb.collection("Area")

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AreaCollection
}