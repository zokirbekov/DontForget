package uz.zokirbekov.dontforget.game

import android.graphics.Color
import android.media.Image
import android.opengl.Visibility
import android.os.Build
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import uz.zokirbekov.dontforget.R
import uz.zokirbekov.dontforget.util.AnimationManager
import uz.zokirbekov.dontforget.util.RandomManager
import uz.zokirbekov.dontforget.util.RotateManager

class GameManager(val size:Int,var grid: GridLayout) : View.OnClickListener{

    var gameListener:GameListener? = null
    var game:Game? = null
    var mapOfImages:Array<Array<ImageView?>>? = null

    private var clickedCount:Int = 0
    var count:Int = 0

    companion object {
        private val GRAY_COLOR = Color.GRAY
        private val YELLOW_COLOR = Color.YELLOW
    }

    init {
        game = Game(size)
        mapOfImages = Array(size, {Array<ImageView?>(size, { null })})
    }

    fun newGame() : GameManager
    {
        game?.initMap(size)
        initImages()
        setTagToImages()
        gameListener?.onStartGame()
        nextStep()
        return this
    }

    fun nextStep()
    {
        val handler = Handler()
        randomValue()
        rotate(RandomManager.randomNumber(RotateManager.COUNT_OF_ROTATE_TYPES))
        count++
        clickedCount = 0
        grid.isEnabled = false
        showTrues()
        handler.postDelayed(
                {   hideTrues()
                    grid.isEnabled = true
                },2*300*size.toLong() + 100)
        gameListener?.onNextStep()
    }

    fun gameOver()
    {
        showTrueAnswers()
        disableOnClick()
        count = 0
        clickedCount = 0
        gameListener?.onFinish()
    }

    private fun disableOnClick()
    {
        for (i in mapOfImages!!)
            for (j in i)
                j?.setOnClickListener(null)
    }

    fun rotate(toward:Int)
    {
        //game?.map = RotateManager.rotate(game?.map!!.copy(),game?.map!!,toward)
        //mapOfImages = RotateManager.rotate(mapOfImages!!.copy(),mapOfImages!!,toward)
        //setTagToImages()
        //AnimationManager.animate(grid,toward)
    }

    fun setTagToImages()
    {
        for (i in 0..size - 1)
        {
            for (j in 0..size - 1)
            {
                mapOfImages!![i][j]?.tag = Point(i,j)
            }
        }
    }

    fun showTrueAnswers()
    {
        for (i in 0..size - 1)
            for (j in 0..size - 1)
                if (checkPlace(i,j))
                    mapOfImages!![i][j]?.setBackgroundColor(YELLOW_COLOR)
    }

    fun showTrues()
    {
        val handler = Handler()
        for (i in 0..size - 1)
            for (j in 0..size - 1)
                if (checkPlace(i,j)) {
                    handler.postDelayed(
                            {
                                mapOfImages!![i][j]?.setBackgroundColor(YELLOW_COLOR)
                            }, 300*j.toLong() + 300*i.toLong())
                }
    }

    fun hideTrues()
    {
        val handler = Handler()
        for (i in 0..size - 1)
            for (j in 0..size - 1)
                if (checkPlace(i,j)) {
                    handler.postDelayed(
                            {
                                mapOfImages!![i][j]?.setBackgroundColor(GRAY_COLOR)
                            }, 30*j.toLong() + 30*i.toLong())
                }
    }

    private fun initImages()
    {
        grid.removeAllViews()
        grid.columnCount = size
        grid.rowCount = size

//        grid2.removeAllViews()
//        grid2.columnCount = size
//        grid2.rowCount = size

        for (i in 0..size - 1)
        {
            for (j in 0..size - 1)
            {
                val imageView = ImageView(grid.context)
                imageView.setBackgroundColor(GRAY_COLOR)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.background = grid.context.getDrawable(R.drawable.round_border)
                }
                else
                {
                    imageView.background = grid.context.resources.getDrawable(R.drawable.round_border)
                }
                //imageView.setText("$i $j")
                val params = GridLayout.LayoutParams()
                params.width = 100
                params.height = 100
                params.setMargins(5,5,5,5)
                imageView.setOnClickListener(this)
                mapOfImages!![i][j] = imageView
                grid.addView(imageView,params)
                //grid2.addView(imageView,params)
            }
        }
    }

    fun randomValue()
    {
        var i: Int
        var j: Int
        do {
            i = RandomManager.randomNumber(size)
            j = RandomManager.randomNumber(size)
        }while (checkPlace(i,j))

        game?.map!![i][j] = true
    }

    private fun checkPlace(i:Int,j:Int) : Boolean
    {
        return game?.map!![i][j]
    }

    private fun checkPlace(point:Point) : Boolean
    {
        return game?.map!![point.i][point.j]
    }

    override fun onClick(v: View?) {
        val imageView = v as? ImageView
        val point =  v?.tag as Point
        Toast.makeText(grid.context, "${point.i} ${point.j}", Toast.LENGTH_LONG).show()
        if (checkPlace(point)) {
            imageView?.setBackgroundColor(YELLOW_COLOR)
            clickedCount++
            if (clickedCount == count)
                nextStep()
        }
        else
        {
            gameOver()
        }
    }

    private inline fun <reified T> Array<Array<T>>.copy() = map { it.clone() }.toTypedArray()

    class Point(val i:Int,val j:Int)

}