package uz.zokirbekov.dontforget.util

import java.util.Random

class RandomManager {
    companion object {
        val rand = Random()
        fun randomNumber(seed:Int) : Int
        {
            return rand.nextInt(seed)
        }
    }
}