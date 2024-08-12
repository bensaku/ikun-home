package com.example.myKotlinProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinlessson.R

class MyAdapter(
    private val items: List<Item>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TextViewHHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView? = itemView.findViewById(R.id.textView)
        val likeView: TextView? = itemView.findViewById(R.id.likeView)
    }

    class ImageViewHHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView? = itemView.findViewById(R.id.image)
        val likeView: TextView? = itemView.findViewById(R.id.likeView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TEXT_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
                TextViewHHolder(view)
            }

            IMAGE_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
                ImageViewHHolder(view)
            }

            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
                TextViewHHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (item) {
            is Item.TextItem -> {
                val textHolder = holder as TextViewHHolder
                textHolder.likeView?.isSelected = item.like
//                textHolder.textView?.text = item.text
                textHolder.textView?.let {
                    it.text = item.text
                    it.setOnClickListener {
                        onClick(position)
                    }
                }

            }

            is Item.ImageItem -> {
                val imageHolder = holder as ImageViewHHolder
                imageHolder.imageView?.setImageResource(item.imageResId)
                imageHolder.likeView?.isSelected = item.like
                imageHolder.imageView?.setOnClickListener {
                    onClick(position)
                }
            }
        }
    }


    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = when (items[position]) {
        is Item.TextItem -> TEXT_TYPE
        is Item.ImageItem -> IMAGE_TYPE
    }

    companion object {
        private const val TEXT_TYPE = 1;
        private const val IMAGE_TYPE = 2;
    }

}