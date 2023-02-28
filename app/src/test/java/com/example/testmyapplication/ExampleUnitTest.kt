package com.example.testmyapplication

import android.util.Log
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

interface Engin {
    fun runOn()
}

class EleEngin : Engin {
    override fun runOn() {
        Log.d("TAG", "runOn: ")
    }

     class TestEngin(val engin2: Engin) : Engin by engin2
    ////////OR/////////////////////////////
//    class TestEngin(val engin2: Engin){
//        fun runOn(){
//            engin2.runOn()
//        }
//    }

    fun main(arr:ArrayList<String>){
        val sampleEngin = TestEngin(EleEngin())
        sampleEngin.runOn()
    }

}