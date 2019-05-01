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

class GameModeFragment : BaseFragment() {

    @OnClick(R.id.button_easy)
    fun easyClick()
    {
        val fragment = GameMapFragment.newInstance(GameMapFragment.EASY)
        fragment.baseFragment = this
        (activity as MainActivity).toFragment(fragment,false)
    }

    @OnClick(R.id.button_medium)
    fun mediumClick()
    {
        val fragment = GameMapFragment.newInstance(GameMapFragment.MEDIUM)
        fragment.baseFragment = this
        (activity as MainActivity).toFragment(fragment,false)
    }

    @OnClick(R.id.button_hard)
    fun hardClick()
    {
        val fragment = GameMapFragment.newInstance(GameMapFragment.HARD)
        fragment.baseFragment = this
        (activity as MainActivity).toFragment(fragment,false)
    }

    @OnClick(R.id.button_custom)
    fun customClick()
    {
        val fragment = CustomGameFragment()
        fragment.baseFragment = this
        (activity as MainActivity).toFragment(fragment,false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_game_mode_menu,container,false)
        ButterKnife.bind(this,v)
        return v
    }
}