package com.example.gradle_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.*
import kotlin.Int
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val subject : PublishSubject<Int> = PublishSubject.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doSomeWork()
    }

    fun onButtonClick(view : View){
        subject.onNext(Random.nextInt())
    }

    private fun doSomeWork(){


        subject.subscribe(getFirstObserver())

        subject.onNext(4)
    }

    private fun getFirstObserver(): Observer<Int> {

        return object : Observer<Int> {

            override fun onNext(t: Int) {
                Toast.makeText(baseContext,"Value $t", Toast.LENGTH_LONG).show()
            }

            override fun onSubscribe(d: Disposable) {

            }



            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        }
    }

}

