package uz.zokirbekov.dontforget.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import uz.zokirbekov.dontforget.MainActivity
import uz.zokirbekov.dontforget.R
import uz.zokirbekov.dontforget.adapters.ScoresAdapter
import uz.zokirbekov.dontforget.util.ScoreManager

class ScoreFragment : Fragment() {

    @BindView(R.id.listView) lateinit var listView:RecyclerView

    @OnClick(R.id.button_back)

    fun backClick()
    {
        (activity as MainActivity).toFragment(MainMenuFragment())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_scores,container, false)
        ButterKnife.bind(this,v!!)
        initAdapter()
        return v
    }

    private fun initAdapter()
    {
        val manager = ScoreManager.getInstance(PreferenceManager.getDefaultSharedPreferences(context))
        listView.adapter = ScoresAdapter(context,manager?.listOfScores!!)
    }
}