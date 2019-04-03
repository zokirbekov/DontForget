package uz.zokirbekov.dontforget.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_game_map.*
import uz.zokirbekov.dontforget.R
import uz.zokirbekov.dontforget.game.GameListener
import uz.zokirbekov.dontforget.game.GameManager

class GameMapFragment : Fragment(), GameListener {

    @BindView(R.id.grid) lateinit var grid: GridLayout
    @BindView(R.id.textView_score) lateinit var scoreText: TextView

    override fun onStartGame() {

    }

    override fun onNextStep() {
        scoreText?.setText("Your score is : ${game.count}")
    }

    override fun onFinish() {
        Toast.makeText(context,"Game created by Zokirbekov", Toast.LENGTH_LONG).show()
    }

    @OnClick(R.id.button_new_game)
    fun onClick()
    {
        game.newGame()
        ObjectAnimator.ofFloat(grid, View.ROTATION, 0f, 90f).start()
    }

    lateinit var game: GameManager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_game_map, container, false)
        ButterKnife.bind(this,v!!)
        game = GameManager(5,grid)
        game.gameListener = this

        return v
    }
}