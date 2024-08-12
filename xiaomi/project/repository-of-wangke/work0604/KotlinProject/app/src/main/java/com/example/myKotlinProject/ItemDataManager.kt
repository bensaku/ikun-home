package com.example.myKotlinProject

import com.example.mykotlinlessson.R

object ItemDataManager {
    val items by lazy {
        listOf(
            Item.TextItem("測試文本1",false),
            Item.TextItem("測試文本測試文本測試文本測試文本測試文本測試文本測試文本",false),
            Item.ImageItem(R.drawable.img5,false),
            Item.TextItem("　　当蜘蛛网无情地查封了我的炉台，\n" +
                    "\n" +
                    "　　当灰烬的余烟叹息着贫困的悲哀，\n" +
                    "\n" +
                    "　　我依然固执地铺平失望的灰烬，\n" +
                    "\n" +
                    "　　用美丽的雪花写下：相信未来。",false),
            Item.ImageItem(R.drawable.img1,false),
        )
    }

}