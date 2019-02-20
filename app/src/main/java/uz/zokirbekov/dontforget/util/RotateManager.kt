package uz.zokirbekov.dontforget.util

import uz.zokirbekov.dontforget.game.Game

class RotateManager
{
    companion object {

        val ROTATE_TO_PLUS_90 = 0
        val ROTATE_TO_MINUS_90 = 1
        val ROTATE_TO_180 = 2
        val COUNT_OF_ROTATE_TYPES = 3

        fun <T : Any?> rotate(map: Array<Array<T>>, tower: Int): Array<Array<T>>? {
            when (tower) {
                ROTATE_TO_PLUS_90 -> return rotateToPlus90(map)
                ROTATE_TO_MINUS_90 -> return rotateToMinus90(map)
                ROTATE_TO_180 -> return rotateTo180(map)
                else -> return null
            }
        }

        private fun <T : Any?> rotateToPlus90(map: Array<Array<T>>): Array<Array<T>> {
            //val array = copyq(map)
            for (i in 0..map.size - 1)
                for (j in 0..map.size - 1) {
               //     map[j][i] = array[i][j]
                }
            return map
        }

        private fun <T : Any?> rotateToMinus90(map: Array<Array<T>>): Array<Array<T>> {
           // val array = copyq(map)
            for (i in 0..map.size - 1)
                for (j in 0..map.size - 1) {
              //      map[j][map.size - i - 1] = array[i][j]
                }
            return map
        }

        private fun <T : Any?>rotateTo180(map: Array<Array<T>>): Array<Array<T>> {
            //val array = copyq(map)
            for (i in 0..map.size - 1)
                for (j in 0..map.size - 1) {
               //     map[map.size - i - 1][j] = array[i][j]
                }
            return map
        }

        private fun copyOfBoolean(array:Array<Array<Boolean>>) : Array<Array<Boolean>>
        {
            var arr = Array(array.size, { Array(array.size, { false }) })
            for (i in 0..array.size - 1)
            {
                for (j in 0..array.size - 1)
                {
                    arr[i][j] = array[i][j]
                }
            }
            return arr
        }
    }
}