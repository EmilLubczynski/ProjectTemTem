package emil.lubczynski.projecttemtem.model

import com.google.gson.annotations.SerializedName

data class TemTem(
    @SerializedName("number")
    val TemTemNumber: String? = null,
    @SerializedName("name")
    val TemTemName: String? = null,

    @SerializedName("icon")
    val TemTemImage: String? = null,

    @SerializedName("types")
    val TemTemType: List<String?>? = null

)