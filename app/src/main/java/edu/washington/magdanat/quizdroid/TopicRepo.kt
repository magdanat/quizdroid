package edu.washington.magdanat.quizdroid

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.os.bundleOf
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

interface TopicRepositoryInterface {
//    fun startRepo()
//    fun listOfTopics(list) : List<String>
//    fun getTopic(name: String): Topic?
//    fun makeBundle(topic: Topic): Bundle
//    fun getQuestions(topic: Topic): Array<QuizQuestion>
}

class TopicRepo : TopicRepositoryInterface{
    private lateinit var sharedPreferences: SharedPreferences
    var list: List<String>

    companion object {

        const val JSON_FILE_NAME = "questions.json"

        const val TITLE = "title"
        const val DESCRIPTION = "desc"
        const val QUESTIONS = "questions"

        const val TEXT = "text"
        const val ANSWER = "answer"
        const val ANSWERS = "answers"
    }

//    fun readWriteJson() {
//        sharedPreferences = getSharedPreferences(USER_PREF_KEY, Context.MODE_Private)
//    }

    constructor(context: Context) {
        val jsonArray = readJson(context)
        readWriteJson(context)
        list = createTopics(jsonArray)
    }

    private fun readWriteJson(context: Context) {
        sharedPreferences = context.getSharedPreferences("USER_PREFERENCES_KEY", Context.MODE_PRIVATE)
    }

    // Used to get json values
    private fun readJson(context: Context): JSONArray {
        val json: String? = try {


            val inputStream = context.assets.open(JSON_FILE_NAME)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            null
        }


        return JSONArray(json)
    }

    private fun createTopics(json: JSONArray): List<String> {
        var listOfTopics = arrayListOf<Topic>()

        for (i in 0 until json.length()) {
            val quiz = json.get(i) as JSONObject
            val quizTitle = quiz.get(TITLE) as String
            val quizDesc = quiz.get(DESCRIPTION) as String
            val quizJson = quiz.get(QUESTIONS) as JSONArray

            val quizQuestions = createQuestions(quizJson)

            val quizTopic = Topic(quizTitle, "A short quiz!", quizDesc, quizQuestions)
            listOfTopics.add(quizTopic)
        }

        val newList: ArrayList<String> = ArrayList()
        for (obj in listOfTopics) {
            newList.add(obj.name)
        }
        return newList
    }

    private fun createQuestions(json: JSONArray): Array<QuizQuestion> {
        var questions = arrayListOf<QuizQuestion>()

        for (i in 0 until json.length()) {
            val question = json.get(i) as JSONObject

            val questionInfo = question.get(TEXT) as String
            val correctAns = question.get(ANSWER) as String
            val correctInt = (correctAns.toInt()) - 1

            val jsonAnswers = question.get(ANSWERS) as JSONArray
            val answers = createAnswers(jsonAnswers)

            questions.add(QuizQuestion(questionInfo, answers, correctInt))
        }
        return questions.toTypedArray()
    }

    private fun createAnswers(json: JSONArray): Array<String> {
        var answers = arrayListOf<String>()

        for (i in 0 until json.length()) {
            answers.add(json.get(i) as String)
        }
        return answers.toTypedArray()
    }

//    override fun listOfTopics(list: List<Topic>): List<String> {
//        var newList: ArrayList<String> = ArrayList()
//        for (obj in list) {
//            newList.add(obj.name)
//        }
//        return newList
//    }

}

data class QuizQuestion(val question: String, val quizQuestions: Array<String>, val correct: Int)
data class Topic(val name: String, val descriptionOne: String, val descriptionTwo: String, val questions: Array<QuizQuestion>)