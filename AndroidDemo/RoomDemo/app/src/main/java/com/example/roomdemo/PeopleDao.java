package com.example.roomdemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

//dao database access object 接口 处理数据库的方法签名
@Dao
public interface PeopleDao {

    //添加  传递一个参数 对象
    @Insert
    void insertDataOne(People people);

    //添加  可以传递多个参数 对象
    @Insert
    void insertDataS(People... people);

    //删除
    @Delete
    int deleteDataS(People... people);

    //修改 传入对象 设置 id 进行修改某一个
    @Update
    int updateData(People... people);

    //查询 根据id倒序
    @Query("select * from people order by id desc")
    List<People> getPeoples();

    //根据id查询
    @Query("select * from people where id =:numb")
    People getPeople(Integer numb);

    //删除表数据
    @Query("delete from people")
    void deleteTableData();

}
