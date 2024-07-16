package com.example.coroutinesdemo

import android.util.Log
import kotlinx.coroutines.*


fun printParams(num: Int = 100, str: String="str") {
    println("num is $num , str is $str")
}

fun main() {
    printParams(str = "world")
    printParams(num = 123)
}





