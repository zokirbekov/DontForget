package uz.zokirbekov.dontforget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import uz.zokirbekov.dontforget.fragment.BaseFragment
import uz.zokirbekov.dontforget.fragment.MainMenuFragment
import uz.zokirbekov.dontforget.util.AnimationManager
import uz.zokirbekov.dontforget.util.RotateManager

class MainActivity : AppCompatActivity() {

    @BindView(R.id.imageView) lateinit var imageView: ImageView

    private var currentFragment:Fragment? = null
    private var animationManager:AnimationManager = AnimationManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        ButterKnife.bind(this)
        toFragment(MainMenuFragment(),false)
    }

    override fun onBackPressed() {
        val fragment = (currentFragment as BaseFragment).baseFragment
        if (fragment != null)
            toFragment(fragment,true)
        else
        {
            val dialog = currentFragment as? DialogFragment
            if (dialog != null)
            {
                dialog.dismiss()
            }
            else {
                AlertDialog.Builder(this).setMessage("Do you want to exit ?")
                        .setPositiveButton("Yes") { dialog, which -> finish() }
                        .setNegativeButton("No", null)
                        .create()
                        .show()
            }
        }
    }

    fun toFragment(fragment: Fragment, isBack:Boolean)
    {
        if (isBack)
            animationManager.animate(imageView,RotateManager.ROTATE_TO_PLUS_90)
        else
            animationManager.animate(imageView,RotateManager.ROTATE_TO_MINUS_90)

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        ft.replace(R.id.fragment_conent,fragment)
        ft.commit()
        currentFragment = fragment
    }

}
