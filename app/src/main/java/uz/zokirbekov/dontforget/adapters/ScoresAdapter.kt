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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = layoutInflater.inflate(R.layout.layout_score,parent,false)
        return VH(v)
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textName.text = scores.get(position).name
        holder.textScore.text = scores.get(position).score.toString()
    }

    class VH(view: View) : RecyclerView.ViewHolder(view)
    {
        val textName:TextView = view.findViewById(R.id.textView_name)
        val textScore:TextView = view.findViewById(R.id.textView_score)
    }
}