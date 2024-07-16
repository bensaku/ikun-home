package com.example.coroutinesdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1:Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            val data = "test string"
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("extra_data",data)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item-> Toast.makeText(this,"Add clicked",Toast.LENGTH_SHORT).show()
            R.id.remove_item-> Toast.makeText(this,"Remove clicked",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}