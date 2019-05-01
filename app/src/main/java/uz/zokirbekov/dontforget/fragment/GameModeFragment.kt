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

class GameModeFragment : Fragment() {

    @OnClick(R.id.button_easy)
    fun easyClick()
    {
        val fragment = GameMapFragment.newInstance(GameMapFragment.EASY)
        (activity as MainActivity).toFragment(fragment)
    }

    @OnClick(R.id.button_medium)
    fun mediumClick()
    {
        val fragment = GameMapFragment.newInstance(GameMapFragment.MEDIUM)
        (activity as MainActivity).toFragment(fragment)
    }

    @OnClick(R.id.button_hard)
    fun hardClick()
    {
        val fragment = GameMapFragment.newInstance(GameMapFragment.HARD)
        (activity as MainActivity).toFragment(fragment)
    }

    @OnClick(R.id.button_custom)
    fun customClick()
    {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_game_mode_menu,container,false)
        ButterKnife.bind(this,v)
        return v
    }
}