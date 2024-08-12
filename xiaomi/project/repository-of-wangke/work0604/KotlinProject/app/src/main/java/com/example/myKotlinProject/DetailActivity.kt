package com.example.myKotlinProject

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinlessson.R
import org.greenrobot.eventbus.EventBus

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_POSITION = "extra_position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val position = intent.getIntExtra(EXTRA_POSITION, -1)

        val textView = findViewById<TextView>(R.id.textView)
        val imageView = findViewById<ImageView>(R.id.image)
        val likeView = findViewById<TextView>(R.id.likeView)

        when (val item = ItemDataManager.items[position]) {
            is Item.TextItem -> {
                textView.let {
                    it.text = item.text
                    it.visibility = View.VISIBLE
                    imageView.visibility=View.GONE
                }
                likeView.run{
                    isSelected = item.like
                    setOnClickListener {
                        item.like = !item.like
                        isSelected = item.like
                    }
                }
            }
            is Item.ImageItem -> {
                imageView.let {
                    it.setImageResource(item.imageResId)
                    it.visibility=View.VISIBLE
                    textView.visibility=View.GONE
                }
                likeView.run{
                    isSelected = item.like
                    setOnClickListener {
                        item.like = !item.like
                        isSelected = item.like
                    }
                }
            }
        }
    }

}