package uz.zokirbekov.dontforget.util

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import uz.zokirbekov.dontforget.R

class AnimationManager {
    companion object {
        var currentDagree:Float = 0f;
        fun animate(v: View?,tower:Int)
        {
            when(tower) {
                RotateManager.ROTATE_TO_PLUS_90 -> {
                    currentDagree -= 90
                    ObjectAnimator.ofFloat(v, View.ROTATION, 0f, 90f).start()
                    //anim = AnimationUtils.loadAnimation(v?.context, R.anim.anim_rotate_to_minus_90)
                }
                RotateManager.ROTATE_TO_MINUS_90 -> {
                    currentDagree += 90
                    ObjectAnimator.ofFloat(v, View.ROTATION, 0f, 90f).start()
                    //anim = AnimationUtils.loadAnimation(v?.context, R.anim.anim_rotate_to_plus_90)
                }
                RotateManager.ROTATE_TO_180 -> {
                    currentDagree -= 180
                    ObjectAnimator.ofFloat(v, View.ROTATION, 0f, 90f).start()
                    //anim = AnimationUtils.loadAnimation(v?.context, R.anim.anim_rotate_to_180)
                }
                else -> return
            }
        }
        fun checkDagree(dagree:Float)
        {

        }

    }
}