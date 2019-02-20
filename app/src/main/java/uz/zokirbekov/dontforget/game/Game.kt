package uz.zokirbekov.dontforget.game

import uz.zokirbekov.dontforget.util.RotateManager
import kotlin.random.Random

class Game(val size:Int) {
    var map:Array<Array<Boolean>>? = null

    companion object {

    }

    init {
        initMap(size)
    }

    fun initMap(size:Int)
    {
        map = Array(size,{ Array<Boolean> ( size, { false }) })
    }

    fun isAllTrue(): Boolean
    {
        for (i in map!!)
            for (j in i)
                if (!j)
                    return false
        return true
    }

}