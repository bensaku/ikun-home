package com.example.roomdemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;


//抽象类 继承 RoomDatabase      我们不用实现people中的方法
// version = 1 数据库版本，exportSchema = false不允许导出数据库的架构
@Database(entities = {People.class}, version = 1, exportSchema = false)
public abstract class PeopleDataBase extends RoomDatabase {
/**
 * Java 抽象类的一些特点
 * 不能被实例化：抽象类不能被实例化，因为它没有具体的实现细节。它只能被继承，并且子类必须实现抽象类中的所有抽象方法。
 * 包含抽象方法：抽象类可以包含抽象方法，这些方法只有声明但没有实现。子类必须实现这些抽象方法，否则它也必须被声明为抽象类。
 * 提供通用功能和行为：抽象类提供了一些通用功能和行为，这些功能和行为可以被其子类继承和重用。子类可以根据需要扩展这些功能和行为，或者重写它们以提供不同的实现。
 * 用于设计高层次的抽象：抽象类可以用于表示一个高层次的抽象概念或者一个通用模板，使得代码更加模块化和可维护。通过将一些公共方法和行为提取到一个抽象类中，可以避免在多个子类中重复实现相同的功能。
 * 扩展性：抽象类可以定义一些扩展的接口，子类可以选择实现这些接口来提供额外的功能。这使得抽象类具有很好的扩展性，可以根据需要添加新的功能和行为
 */

public abstract PeopleDao peopleDao();

}
