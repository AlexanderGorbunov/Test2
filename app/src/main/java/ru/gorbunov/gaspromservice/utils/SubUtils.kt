package ru.gorbunov.gaspromservice.utils

import android.graphics.Color

class SubUtils {
    companion object{
        fun getMainColor(bState : Int, gState: Int) = when(100 * bState/(bState + gState))
            {
                in 1..10->
                    Color.rgb(255,165,0)
                in 11..100->
                    Color.RED
                0-> Color.GREEN
                else -> Color.BLACK
        }
    }
}