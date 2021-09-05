package emil.lubczynski.projecttemtem.model

import com.google.gson.annotations.SerializedName

data class TemTem(
    @SerializedName("number")
    val TemTemNumber: String?,

    @SerializedName("name")
    val TemTemName: String?,

    @SerializedName("portraitWikiUrl")
    val TemTemImage: String?

)