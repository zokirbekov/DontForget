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

class MainMenuFragment : BaseFragment() {

    @OnClick(R.id.button_new_game)
    fun onNewGameClick()
    {
        val fragment = GameModeFragment()
        fragment.baseFragment = this
        (activity as MainActivity).toFragment(fragment,false)
    }

    @OnClick(R.id.button_score)
    fun onScoreClick()
    {
        val fragment = ScoreFragment()
        fragment.baseFragment = this
        (activity as MainActivity).toFragment(fragment,false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main_menu,container,false)
        ButterKnife.bind(this, v!!)
        return v
    }
}