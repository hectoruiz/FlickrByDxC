package hector.ruiz.datasource.api.adapter

import com.squareup.moshi.*

internal class TagAdapter : JsonAdapter<Int>() {
    @ToJson
    override fun toJson(writer: JsonWriter, value: Int?) {
    }

    @FromJson
    override fun fromJson(reader: JsonReader): Int {
        return when (reader.peek()) {
            JsonReader.Token.BOOLEAN -> if (reader.nextBoolean()) 1 else 0
            else -> {
                reader.nextInt()
            }
        }
    }
}
