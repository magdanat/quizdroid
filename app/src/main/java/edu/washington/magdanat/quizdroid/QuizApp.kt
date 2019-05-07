package edu.washington.magdanat.quizdroid

import android.app.Application
import android.util.Log

class QuizApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.i("Quiz App", "Application is being loaded and ran")
    }

    companion object {
        val repo = TopicRepo()
        init {
            repo.startRepo()
        }
    }
}
