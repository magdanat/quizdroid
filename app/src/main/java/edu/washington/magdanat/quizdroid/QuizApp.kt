package edu.washington.magdanat.quizdroid

import android.app.Application
import android.util.Log

class QuizApp: Application() {

    lateinit var repo: TopicRepo

    override fun onCreate() {
        super.onCreate()
        Log.i("Quiz App", "Application is being loaded and ran")

        sharedInstance = this
        repo = TopicRepo(applicationContext)
    }

    companion object {
//        val repo = TopicRepo()
//        init {
//            repo.
//        }
        lateinit var sharedInstance: QuizApp
            private set
    }
}
