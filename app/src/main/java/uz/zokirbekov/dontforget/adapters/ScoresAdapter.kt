package uz.zokirbekov.dontforget.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import uz.zokirbekov.dontforget.R
import uz.zokirbekov.dontforget.models.Player

class ScoresAdapter(context:Context,val scores:MutableList<Player>) : RecyclerView.Adapter<ScoresAdapter.VH>()
{
    private var layoutInflater:LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        return VH(layoutInflater.inflate(R.layout.layout_score,parent,false))
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    override fun onBindViewHolder(holder: VH?, position: Int) {
        holder?.textName?.setText(scores[position].name)
        holder?.textScore?.setText(scores[position].score)
    }

    class VH(view: View) : RecyclerView.ViewHolder(view)
    {
        val textName:TextView = view.findViewById(R.id.textView_name)
        val textScore:TextView = view.findViewById(R.id.textView_score)
    }
}