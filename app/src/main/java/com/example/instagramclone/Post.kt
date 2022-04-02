package com.example.instagramclone

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Post")
class Post : ParseObject() {
    companion object {
        const val KEY_DESCRIPTION = "description"
        const val KEY_IMAGE = "image"
        const val KEY_USER = "user"
        const val KEY_DATE = "date"
    }

    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }

    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun getDate(): String? {
        return getString(KEY_DATE)
    }
    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }

    fun setImage(parseFile: ParseFile) {
        put(KEY_IMAGE, parseFile)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

    fun setDate(date: String) {
        put(KEY_DATE, date)
    }
}