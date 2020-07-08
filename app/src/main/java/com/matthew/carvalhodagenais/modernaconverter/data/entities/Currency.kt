package com.matthew.carvalhodagenais.modernaconverter.data.entities

import android.graphics.drawable.Drawable

data class Currency(
    var code: String,
    var imageId: Int
) {
    fun copy(from: Currency) {
        this.code = from.code
        this.imageId = from.imageId
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        } else if (other is Currency) {
            return this.code == other.code
        }
        return false
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + imageId
        return result
    }
}