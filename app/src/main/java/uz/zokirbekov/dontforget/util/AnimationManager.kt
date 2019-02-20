package uz.zokirbekov.dontforget.util

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import uz.zokirbekov.dontforget.R

class AnimationManager {
    companion object {
        fun animate(v: View?,tower:Int)
        {
            var anim:Animation?
            when(tower)
            {
                RotateManager.ROTATE_TO_PLUS_90 ->
                {
                    anim = AnimationUtils.loadAnimation(v?.context, R.anim.anim_rotate_to_minus_90)
                }
                RotateManager.ROTATE_TO_MINUS_90 ->
                {
                    anim = AnimationUtils.loadAnimation(v?.context, R.anim.anim_rotate_to_plus_90)
                }
                RotateManager.ROTATE_TO_180 ->
                {
                    anim = AnimationUtils.loadAnimation(v?.context, R.anim.anim_rotate_to_180)
                }
                else -> anim = null
            }
            v?.startAnimation(anim)
        }
    }
}