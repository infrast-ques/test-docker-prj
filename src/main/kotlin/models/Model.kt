package models


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ModelItem(
        @SerializedName("id")
        var id: String,
        @SerializedName("text")
        var text: String
) {
    companion object {
        fun deserialize(source: String) = Gson().apply { serializeNulls() }.fromJson(source, ModelItem::class.java)
    }

    override fun toString() = Gson().apply { serializeNulls() }.toJson(this)
}
