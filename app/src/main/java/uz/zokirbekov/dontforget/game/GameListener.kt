package uz.zokirbekov.dontforget.game

import android.view.View

interface GameListener {
    fun onStartGame()
    fun onNextStep()
    fun onFinish()
}