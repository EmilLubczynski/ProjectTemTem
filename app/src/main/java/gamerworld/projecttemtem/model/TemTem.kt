package gamerworld.projecttemtem.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TemTem(
    val number: String? = null,
    val name: String? = null,
    val icon: String? = null,
    val types: List<String?>? = null,
    val stats: Stats? = null
) : Parcelable

@Parcelize
data class Stats(
    val hp: Int? = null,
    val sta: Int? = null,
    val spd: Int? = null,
    val atk: Int? = null,
    val def: Int? = null,
    val spatk: Int? = null,
    val spdef: Int? = null
) : Parcelable