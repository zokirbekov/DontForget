package uz.zokirbekov.dontforget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayout
import uz.zokirbekov.dontforget.game.GameManager

class MainActivity : AppCompatActivity() {

    var grid:GridLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        grid = findViewById(R.id.grid)

        GameManager(3,grid!!).newGame()
    }
}
