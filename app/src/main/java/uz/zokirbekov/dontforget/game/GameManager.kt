package uz.zokirbekov.dontforget.game

import android.graphics.Color
import android.media.Image
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.GridLayout
import android.widget.ImageView
import uz.zokirbekov.dontforget.util.AnimationManager
import uz.zokirbekov.dontforget.util.RandomManager
import uz.zokirbekov.dontforget.util.RotateManager

class GameManager(val size:Int,val grid: GridLayout) : View.OnClickListener{

    var gameListener:GameListener? = null
    var game:Game? = null
    var mapOfImages:Array<Array<ImageView?>>? = null

    private var clickedCount:Int = 0
    private var count:Int = 0

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
        gameListener?.onStart()
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
                {hideTrues()
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
        game?.rotate(toward)
        mapOfImages = RotateManager.rotate(mapOfImages!!,toward)
        setTagToImages()
        AnimationManager.animate(grid,toward)
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
                            }, 300*j.toLong() + 300*i.toLong())
                }
    }

    private fun initImages()
    {
        grid.removeAllViews()
        grid.columnCount = size
        grid.rowCount = size
        for (i in 0..size - 1)
        {
            for (j in 0..size - 1)
            {
                val imageView = ImageView(grid.context)
                imageView.setBackgroundColor(GRAY_COLOR)
                val params = GridLayout.LayoutParams()
                params.width = 100
                params.height = 100
                params.setMargins(5,5,5,5)
                imageView.setOnClickListener(this)
                mapOfImages!![i][j] = imageView
                grid.addView(imageView,params)
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
        mapOfImages!![i][j]?.setBackgroundColor(YELLOW_COLOR)
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

    class Point(val i:Int,val j:Int)

}