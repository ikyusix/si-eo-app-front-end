package com.enigma.si_eo_app.utils

import android.content.Context
import android.net.Uri

class FileUtil {
    companion object{
        fun getRealPathFromUri(contentURI: Uri, context: Context): String? {
            var result: String?
            val cursor = context.contentResolver
                .query(contentURI, null, null, null, null)
            if (cursor==null){
                result = contentURI.path
            }else {
                cursor.moveToFirst()
                val idx = cursor.getColumnIndex("_data")
                result = cursor.getString(idx)
                cursor.close()
            }
            return result
        }
    }
}