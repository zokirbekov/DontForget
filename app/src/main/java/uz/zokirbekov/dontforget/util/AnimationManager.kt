package uz.zokirbekov.dontforget.util

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.support.graphics.drawable.ArgbEvaluator
import android.view.View
import uz.zokirbekov.dontforget.R

class AnimationManager {

        private var currentDagree:Float = 0f
        private val animInterval = 1000L
        private var currentAnimator:ObjectAnimator? = null
        private fun newObjectAnimator(v:View?,fDagree:Float,tDagree:Float) : ObjectAnimator
        {
            val anim = ObjectAnimator.ofFloat(v, View.ROTATION, fDagree,tDagree)
            anim.duration = animInterval
            currentAnimator = anim
            return anim
        }

        fun cancelAnimation()
        {
            currentAnimator?.cancel()
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
        fun animateColor(v:View?,grayToYellow:Boolean)
        {
            if (grayToYellow) {
                val objectAnimator = (AnimatorInflater.loadAnimator(v?.context, R.animator.gray_to_yellow) as ObjectAnimator)
                objectAnimator.setTarget(v)
                objectAnimator.setEvaluator(ArgbEvaluator())
                objectAnimator.start()
            }
            else {
                val objectAnimator = (AnimatorInflater.loadAnimator(v?.context, R.animator.yellow_to_gray) as ObjectAnimator)
                objectAnimator.setTarget(v)
                objectAnimator.setEvaluator(ArgbEvaluator())
                objectAnimator.start()
            }

        }
}