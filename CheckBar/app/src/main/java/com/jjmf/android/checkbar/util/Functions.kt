package com.jjmf.android.checkbar.util

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale


fun Timestamp.format(pattern:String = "dd/MM/yyyy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(this.toDate())
}