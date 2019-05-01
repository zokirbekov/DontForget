package uz.zokirbekov.dontforget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import butterknife.ButterKnife
import uz.zokirbekov.dontforget.fragment.MainMenuFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        ButterKnife.bind(this)
        toFragment(MainMenuFragment())
    }
    fun toFragment(fragment: Fragment)
    {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_conent,fragment)
        ft.commit()
    }

}
