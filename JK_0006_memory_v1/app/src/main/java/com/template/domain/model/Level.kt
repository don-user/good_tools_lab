package com.template.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Level(val numberCard: Int) : Parcelable {
    MODE_2X2 (2),
    MODE_4X4 (4),
    MODE_6X6 (6),
    MODE_8X8 (8),
    CONTINUE (8)
}