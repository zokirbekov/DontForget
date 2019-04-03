package uz.zokirbekov.dontforget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayout
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import uz.zokirbekov.dontforget.fragment.MainMenuFragment
import uz.zokirbekov.dontforget.game.GameListener
import uz.zokirbekov.dontforget.game.GameManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        ButterKnife.bind(this)
        addFragment(MainMenuFragment())
    }
    fun addFragment(fragment: Fragment)
    {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_conent,fragment)
        ft.commit()
    }

}
