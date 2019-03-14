package uz.zokirbekov.dontforget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayout
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import uz.zokirbekov.dontforget.game.GameListener
import uz.zokirbekov.dontforget.game.GameManager

class MainActivity : AppCompatActivity(), GameListener {
    override fun onStartGame() {

    }

    override fun onNextStep() {
        textView?.setText("Your score is : ${game.count}")
    }

    override fun onFinish() {
        Toast.makeText(this,"Game created by Zokirbekov",Toast.LENGTH_LONG).show()
    }

    var grid:GridLayout? = null
    var grid2:GridLayout? = null
    var buttonNewGame: Button? = null
    var textView:TextView? = null
    lateinit var game: GameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        grid = findViewById(R.id.grid)
        buttonNewGame = findViewById(R.id.button_new_game)
        textView = findViewById(R.id.textView)

        game = GameManager(5,grid!!)
        game.gameListener = this
        buttonNewGame?.setOnClickListener {
            game.newGame()
        }
    }
}
