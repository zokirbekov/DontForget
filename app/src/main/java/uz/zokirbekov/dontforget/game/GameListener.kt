package uz.zokirbekov.dontforget.game

import android.view.View

interface GameListener {
    fun onStart()
    fun onNextStep()
    fun onFinish()
}