package com.example.roomdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PeopleDataBase db;

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


        db = Room.databaseBuilder(this, PeopleDataBase.class, "peopleDB")
                // 默认不允许在主线程中连接数据库   强制在主线程中处理
                .allowMainThreadQueries()
                .build();

        String name =  "张三";
        int age =  20 ;
        String sex = "男";
        People people = new People(name, age, sex);
        db.peopleDao().insertDataS(people);
        //查询数据
        selectData(null);
    }

    /**
     * 查询数据的方法
     */
    public void selectData(Integer id) {
        try {
            StringBuilder text = new StringBuilder();
            if (id == null || id == 0) {
                List<People> peopleList = db.peopleDao().getPeoples();
                if (peopleList.size() != 0) {
                    for (int i = 0; i < peopleList.size(); i++) {
                        People people = peopleList.get(i);
                        text.append("id:")
                                .append(people.getId())
                                .append("---name:")
                                .append(people.getName())
                                .append("---age:")
                                .append(people.getAge())
                                .append("---sex:")
                                .append(people.getSex())
                                .append("\n");
                    }
                } else {
                    text.append("没有可用的数据");
                }

            } else {
                People people = db.peopleDao().getPeople(id);
                text.append("id:")
                        .append(people.getId())
                        .append("---name:")
                        .append(people.getName())
                        .append("---age:")
                        .append(people.getAge())
                        .append("---sex:")
                        .append(people.getSex());
            }

//            textContent.setText(text.toString());
            Log.i("TAG",text.toString());

        } catch (Exception e) {
            Log.e("TAG", "----查询失败------------" + e.getMessage());
        }


    }

}