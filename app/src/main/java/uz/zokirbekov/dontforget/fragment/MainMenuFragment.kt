package uz.zokirbekov.dontforget.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_game_map.*
import uz.zokirbekov.dontforget.MainActivity
import uz.zokirbekov.dontforget.R

class MainMenuFragment : Fragment() {

    @OnClick(R.id.button_new_game)
    fun onClick()
    {
        (activity as MainActivity).addFragment(GameMapFragment())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_main_menu,container,false)
        ButterKnife.bind(this, v!!)

        return v
    }
}