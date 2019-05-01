package uz.zokirbekov.dontforget.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import uz.zokirbekov.dontforget.MainActivity
import uz.zokirbekov.dontforget.R

class CustomGameFragment : BaseFragment(){

    @BindView(R.id.seekBar) lateinit var seekBar:SeekBar
    @BindView(R.id.textView_size) lateinit var sizeText:TextView

    @OnClick(R.id.button_ok)
    fun okClick()
    {
        val mode = seekBar.progress + 2
        val fragment = GameMapFragment.newInstance(mode)
        fragment.baseFragment = this
        (activity as MainActivity).toFragment(fragment,false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_custom_game,container,false)
        ButterKnife.bind(this,v)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener
        {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sizeText.text = "Size : ${progress+2}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        return v
    }
}