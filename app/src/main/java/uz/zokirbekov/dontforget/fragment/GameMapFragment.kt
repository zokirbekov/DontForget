package uz.zokirbekov.dontforget.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import uz.zokirbekov.dontforget.R
import uz.zokirbekov.dontforget.game.GameListener
import uz.zokirbekov.dontforget.game.GameManager

class GameMapFragment : Fragment(), GameListener {

    @BindView(R.id.grid) lateinit var grid: GridLayout
    @BindView(R.id.textView_score) lateinit var scoreText: TextView
    @BindView(R.id.button_new_game) lateinit var btnNewGame : Button

    override fun onStartGame() {
        btnNewGame.isEnabled = false
    }

    override fun onNextStep() {
        scoreText.setText("Your score is : ${game.count}")
    }

    override fun onFinish() {
        Toast.makeText(context,"Game created by Zokirbekov", Toast.LENGTH_LONG).show()
        btnNewGame.isEnabled = true
        val fragment = ScoreDialogFragment()
        val bundle = Bundle()
        bundle.putInt("score",game.count)
        fragment.arguments = bundle
        fragment.show(fragmentManager,"SCORE_DIALOG_FRAGMENT")
    }

    @OnClick(R.id.button_new_game)
    fun onClick()
    {
        game.newGame()
    }

    lateinit var game: GameManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_game_map, container, false)
        ButterKnife.bind(this,v!!)
        game = GameManager(5,grid)
        game.gameListener = this
        return v
    }
}