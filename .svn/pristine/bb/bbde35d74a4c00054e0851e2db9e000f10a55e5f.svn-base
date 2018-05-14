package com.mtool.toolslib.core

/**
 * Created by JayCruz on 2018/3/22.
 */

fun String.castToBankCard(divider: String): String {
    return if (this.isEmpty()) {
        "0000${divider}0000${divider}0000${divider}0000"
    } else {
        val totalLengh = this.length
        val gap = 3
        var currentIndex = 0
        val totalCoords = mutableListOf<String>()
        val groupCoords = mutableListOf<Char>()
        for (item in this.toCharArray()) {
            if (currentIndex == gap) {
                groupCoords.add(item)

                totalCoords.add(groupCoords.joinToString(""))
                groupCoords.clear()
                currentIndex = 0
            } else {
                groupCoords.add(item)
                currentIndex++
            }
        }
        val afterFormat = totalCoords.joinToString(divider)

        afterFormat
    }
}

