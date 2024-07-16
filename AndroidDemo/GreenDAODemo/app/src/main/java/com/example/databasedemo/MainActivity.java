package com.example.databasedemo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // get the note DAO
        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        NoteDao noteDao = daoSession.getNoteDao();

// 插入数据
        Note note = new Note();
        note.setText("Hello, GreenDAO!");
        noteDao.insert(note);

// 查询数据
        List<Note> notes = noteDao.queryBuilder().list();
        for(Note note1 :notes){
            Log.i("main",note1.getText());
        }


    }
}