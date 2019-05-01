package uz.zokirbekov.dontforget.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import uz.zokirbekov.dontforget.MainActivity
import uz.zokirbekov.dontforget.R

class MainMenuFragment : Fragment() {

    @OnClick(R.id.button_new_game)
    fun onNewGameClick()
    {
        (activity as MainActivity).toFragment(GameMapFragment())
    }

    @OnClick(R.id.button_score)
    fun onScoreClick()
    {
        (activity as MainActivity).toFragment(ScoreFragment())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_main_menu,container,false)
        ButterKnife.bind(this, v!!)
        return v
    }
}