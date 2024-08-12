package com.example.myKotlinProject

sealed class Item(open var like:Boolean) {
    data class TextItem(val text:String,override var like:Boolean):Item(like)
    data class ImageItem(val imageResId:Int,override var like:Boolean):Item(like)
}