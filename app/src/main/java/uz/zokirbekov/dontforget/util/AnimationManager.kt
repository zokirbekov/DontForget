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
        private val animInterval = 1000L
        private fun newObjectAnimator(v:View?,fDagree:Float,tDagree:Float) : ObjectAnimator
        {
            var anim = ObjectAnimator.ofFloat(v, View.ROTATION, fDagree,tDagree)
            anim.duration = animInterval
            return anim
        }
        fun animate(v: View?,tower:Int)
        {
            when(tower) {

                RotateManager.ROTATE_TO_PLUS_90 -> {
                    newObjectAnimator(v, currentDagree, currentDagree-90f).start()
                    currentDagree -= 90
                    //anim = AnimationUtils.loadAnimation(v?.context, R.anim.anim_rotate_to_minus_90)
                }
                RotateManager.ROTATE_TO_MINUS_90 -> {
                    newObjectAnimator(v, currentDagree, currentDagree+90f).start()
                    currentDagree += 90
                    //anim = AnimationUtils.loadAnimation(v?.context, R.anim.anim_rotate_to_plus_90)
                }
                RotateManager.ROTATE_TO_180 -> {
                    newObjectAnimator(v, currentDagree, currentDagree - 180f).start()
                    currentDagree -= 180
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