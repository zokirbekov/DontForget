package uz.zokirbekov.dontforget.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import uz.zokirbekov.dontforget.R

class ScoreFragment : Fragment() {

    @OnClick(R.id.button_back)
    fun backClick()
    {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_scores,container, false)
        ButterKnife.bind(this,v!!)
        return v
    }
}