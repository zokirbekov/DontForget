package uz.zokirbekov.dontforget.game

import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.GridLayout
import android.view.View
import android.widget.ImageView
import uz.zokirbekov.dontforget.R
import uz.zokirbekov.dontforget.util.AnimationManager
import uz.zokirbekov.dontforget.util.RandomManager
import uz.zokirbekov.dontforget.util.RotateManager

class GameManager(val size:Int,var grid: GridLayout) : View.OnClickListener{

    var gameListener:GameListener? = null
    var game:Game? = null
    var mapOfImages:MutableList<MutableList<ImageView?>>? = null

    private var isFirstTime:Boolean = true
    private var clickedCount:Int = 0
    var count:Int = 0

    private val GRAY_COLOR = ContextCompat.getColor(grid.context,R.color.colorLightColor)
    private val YELLOW_COLOR = Color.YELLOW

    init {
        game = Game(size)
        mapOfImages = MutableList(size, {MutableList<ImageView?>(size, { null })})
    }

    fun newGame() : GameManager
    {
        AnimationManager.cancelAnimation()
        game?.initMap(size)
        if (isFirstTime) {
            initImages()
            setTagToImages()
        }
        else
        {
            enableOnClick()
            allInGray()
        }
        gameListener?.onStartGame()
        count = 0
        clickedCount = 0
        isFirstTime = false
        nextStep()
        return this
    }

    fun nextStep()
    {
        val handler = Handler()
        randomValue()
        showTrues()
        count++
        clickedCount = 0

        handler.postDelayed(
                {
                    hideTrues()
                },2*100*size.toLong() + 100)
        handler.postDelayed(
                {
                    rotate(RandomManager.randomNumber(RotateManager.COUNT_OF_ROTATE_TYPES))
                },4*100*size.toLong() + 100)

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

    private fun enableOnClick()
    {
        for (i in mapOfImages!!)
            for (j in i)
                j?.setOnClickListener(this)
    }

    fun rotate(toward:Int)
    {
        AnimationManager.animate(grid,toward)
    }

    fun setTagToImages()
    {
        for (i in 0..size - 1)
        {
            for (j in 0..size - 1)
            {
                mapOfImages!![i][j]?.tag = Point(i,j,false)
            }
        }
    }

    fun showTrueAnswers()
    {
        for (i in 0..size - 1)
            for (j in 0..size - 1)
                if (checkPlace(i,j)) {
                    setTint(mapOfImages!![i][j]!!, YELLOW_COLOR)
                }
    }

    fun showTrues()
    {
        val handler = Handler()
        for (i in 0..size - 1)
            for (j in 0..size - 1)
                if (checkPlace(i,j)) {
                    handler.postDelayed(
                            {
                                setTint(mapOfImages!![i][j]!!, YELLOW_COLOR)
                            }, 100*j.toLong() + 100*i.toLong())
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
                                setTint(mapOfImages!![i][j]!!, GRAY_COLOR)
                                clickedTo(false, mapOfImages!![i][j])
                            }, 100*j.toLong() + 100*i.toLong())
                }
    }

    private fun setTint(imageView: ImageView,color:Int)
    {
        var drawable = imageView.background
        DrawableCompat.setTint(drawable!!, color)
    }

    private fun allInGray()
    {
        for (i in 0..size - 1) {
            for (j in 0..size - 1) {
                setTint(mapOfImages!![i][j]!!,GRAY_COLOR)
            }

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
                var imageView = ImageView(grid.context)
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
        return checkPlace(point.i,point.j)
    }

    private fun clickedTo(b: Boolean,v: ImageView?)
    {
        val point =  v?.tag as Point
        point.isClicked = b
    }

    override fun onClick(v: View?) {
        val imageView = v as? ImageView
        val point =  v?.tag as Point
        if (checkPlace(point)) {
            if (!point.isClicked) {
                point.isClicked = true
                setTint(imageView!!, YELLOW_COLOR)
                clickedCount++
                if (clickedCount == count) {
                    nextStep()
                }
            }
        }
        else
        {
            gameOver()
        }
    }

    private inline fun <reified T> Array<Array<T>>.copy() = map { it.clone() }.toTypedArray()

    class Point(val i:Int,val j:Int, var isClicked:Boolean)

}