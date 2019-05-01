package uz.zokirbekov.dontforget.util

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.zokirbekov.dontforget.models.Player

class ScoreManager {

    companion object {
        val scoresKey:String = "scores"
        private var instance:ScoreManager? = null

        fun getInstance(preferences: SharedPreferences) : ScoreManager?
        {
            if (instance == null)
                instance = ScoreManager()
            instance?.preferences = preferences
            return instance
        }
    }


    private var preferences:SharedPreferences?= null

    private var _listOfScores:MutableList<Player>? = null
    val listOfScores:MutableList<Player>
        get() {
            getScoresFromPreferensec()
            if (_listOfScores == null)
                _listOfScores = mutableListOf<Player>()
            _listOfScores!!.sortByDescending { x -> x.score}
            return _listOfScores as MutableList<Player>
        }

    private fun getScoresFromPreferensec()
    {
        val json = preferences?.getString(scoresKey,"")
        val token = object : TypeToken<MutableList<Player>>() {}.type
        _listOfScores = Gson().fromJson(json,token)
    }

    fun addScore(player:Player)
    {
        _listOfScores?.add(player)
    }

    fun writeToPreferences()
    {
        val json = Gson().toJson(listOfScores)
        preferences?.edit()?.putString(scoresKey,json)?.apply()
    }

}