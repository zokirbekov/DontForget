package uz.zokirbekov.dontforget.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.TextInputEditText
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import uz.zokirbekov.dontforget.R
import uz.zokirbekov.dontforget.models.Player
import uz.zokirbekov.dontforget.util.ScoreManager

class ScoreDialogFragment : DialogFragment() {

    private var score:Int = 0

    @BindView(R.id.editText_name) lateinit var nameText:TextInputEditText

    @OnClick(R.id.button_ok)
    fun okClick()
    {
        if (nameText.text.isNotEmpty())
        {
            val name = nameText.text.toString()
            ScoreManager.getInstance(PreferenceManager.getDefaultSharedPreferences(context))?.addScore(Player(name,score))
            ScoreManager.getInstance(PreferenceManager.getDefaultSharedPreferences(context))?.writeToPreferences()
            dismiss()
        }
        else
        {
            Toast.makeText(context,"Name is empty", Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.dialog_fragment_score, null, false)
        ButterKnife.bind(this,v)
        dialog.window.setBackgroundDrawableResource(R.drawable.round_border_for_dialog)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        score = arguments?.getInt("score")!!
    }
}