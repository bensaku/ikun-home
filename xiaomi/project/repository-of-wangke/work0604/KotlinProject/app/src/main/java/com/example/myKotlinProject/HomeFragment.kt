package com.example.myKotlinProject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinlessson.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    private lateinit var adapter: MyAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycleView = view.findViewById<RecyclerView>(R.id.list)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = MyAdapter(ItemDataManager.items, onClick = { position ->
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_POSITION, position)
            }
            startActivity(intent)
        })
        recycleView.adapter = adapter
    }


    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}