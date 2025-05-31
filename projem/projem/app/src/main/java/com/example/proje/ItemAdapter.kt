package com.example.proje

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(private val items: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImage)
        val titleTextView: TextView = itemView.findViewById(R.id.itemTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.itemDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Log.d("ItemAdapter", "Bağlanıyor: ${item.title}, ${item.description}, ${item.imageUrl}")
        holder.titleTextView.text = item.title
        holder.descriptionTextView.text = item.description
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = items.size
}