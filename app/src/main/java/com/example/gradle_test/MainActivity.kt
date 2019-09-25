package com.example.gradle_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.apitest.ApiEvent.DATA
import com.example.apitest.ApiEvent.SUCCESS
import com.example.apitest.ApiManager
import com.example.apitest.DataEvent
import com.example.apitest.Event
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.*
import kotlin.Int
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val subject: PublishSubject<Int> = PublishSubject.create()
    lateinit var apiManager: ApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiManager = ApiManager.on()
        subscribeEvent()
        doSomeWork()
    }


    fun subscribeEvent() {
        apiManager.on(DATA, this::listenData)
        apiManager.on(SUCCESS, this::successListener)
    }

    fun listenData(dataEvent: Event) {
        if(dataEvent is DataEvent){
            Toast.makeText(baseContext, "Value ${dataEvent.value}", Toast.LENGTH_SHORT).show()
        }
    }
    fun successListener(event : Event){

    }

    fun onButtonClick(view: View) {
        apiManager.sendData(Random.nextInt())
    }

    private fun doSomeWork() {


        subject.subscribe(getFirstObserver())

        subject.onNext(4)
    }

    private fun getFirstObserver(): Observer<Int> {

        return object : Observer<Int> {

            override fun onNext(t: Int) {
                Toast.makeText(baseContext, "Value $t", Toast.LENGTH_SHORT).show()
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

