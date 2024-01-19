package com.jjmf.android.checkbar.domain.usecase

import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.destination
import id.zelory.compressor.constraint.quality
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class ComprimirFotoUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend operator fun invoke(foto: Uri) : Uri?{
        return try {
            val file = compressImage(
                uri = foto,
                namePhoto = foto.lastPathSegment ?: System.currentTimeMillis().toString()
            )
            Uri.fromFile(file)
        }catch (e:Exception){
            null
        }
    }

    private suspend fun compressImage(uri: Uri, namePhoto: String): File {
        val file = getFile(context, uri, namePhoto)
        Compressor.compress(context, file){
            quality(50)
            destination(file)
        }
        return file
    }

    private fun getFile(context: Context, uri: Uri?, namePhoto: String): File {
        val dir = ContextWrapper(context).getDir("photo", Context.MODE_PRIVATE)
        val destinationFilename = File(dir, "$namePhoto.jpg")
        context.contentResolver.openInputStream(uri!!).use { inputStream ->
            createFileFromStream(inputStream!!, destinationFilename)
        }
        return destinationFilename
    }

    private fun createFileFromStream(ins: InputStream, destination: File?) {
        val fl = FileOutputStream(destination)
        val buffer = ByteArray(4096)
        var length: Int
        while (ins.read(buffer).also { length = it } > 0) {
            fl.write(buffer, 0, length)
        }
        fl.flush()
    }
}