package uz.zokirbekov.dontforget.util

import uz.zokirbekov.dontforget.game.Game

class RotateManager
{
    companion object {

        val ROTATE_TO_PLUS_90 = 0
        val ROTATE_TO_MINUS_90 = 1
        val ROTATE_TO_180 = 2
        val COUNT_OF_ROTATE_TYPES = 3

        fun <T : Any?> rotate(oldMap : Array<Array<T>>,map: Array<Array<T>>, tower: Int): Array<Array<T>>? {
            when (tower) {
                ROTATE_TO_PLUS_90 -> return rotateToPlus90(oldMap, map)
                ROTATE_TO_MINUS_90 -> return rotateToMinus90(oldMap, map)
                ROTATE_TO_180 -> return rotateTo180(oldMap, map)
                else -> return null
            }
        }

        private fun <T : Any?> rotateToPlus90(oldMap : Array<Array<T>>,map: Array<Array<T>>): Array<Array<T>> {
            for (i in 0..map.size - 1)
                for (j in 0..map.size - 1) {
                    map[map.size - j - 1][i] = oldMap[i][j]
                }
            return map
        }

        private fun <T : Any?> rotateToMinus90(oldMap : Array<Array<T>>, map: Array<Array<T>>): Array<Array<T>> {
            for (i in 0..map.size - 1)
                for (j in 0..map.size - 1) {
                     map[j][map.size - i - 1] = oldMap[i][j]
                }
            return map
        }

        private fun <T : Any?>rotateTo180(oldMap : Array<Array<T>>, map: Array<Array<T>>): Array<Array<T>> {
            for (i in 0..map.size - 1)
                for (j in 0..map.size - 1) {
                     map[map.size - i - 1][map.size - j - 1] = oldMap[i][j]
                }
            return map
        }
    }
}