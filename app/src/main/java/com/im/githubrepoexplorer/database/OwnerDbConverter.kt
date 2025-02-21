package com.im.githubrepoexplorer.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.im.githubrepoexplorer.networking.Owner
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class OwnerDbConverter {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @TypeConverter
    fun fromOwner(owner: Owner): String {
        return json.encodeToString(owner)
    }

    @TypeConverter
    fun toOwner(ownerString: String): Owner {
        return try {
            json.decodeFromString(ownerString)
        } catch (e: Exception) {
            Owner()
        }
    }
}