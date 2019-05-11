package edu.washington.magdanat.quizdroid

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.os.bundleOf
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

interface TopicRepositoryInterface {
    fun getTopic(name: String): Topic?
    fun makeBundle(topic: Topic): Bundle
    fun getQuestions(topic: Topic): Array<QuizQuestion>
}

class TopicRepo : TopicRepositoryInterface{
    private lateinit var sharedPreferences: SharedPreferences
    var list: List<Topic>
    private var newBundle : Bundle? = null
    private var test : Topic? = null

    companion object {

        const val JSON_FILE_NAME = "questions.json"

        const val TITLE = "title"
        const val DESCRIPTION = "desc"
        const val QUESTIONS = "questions"

        const val TEXT = "text"
        const val ANSWER = "answer"
        const val ANSWERS = "answers"
    }


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

    private fun createTopics(json: JSONArray): List<Topic> {
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

        return listOfTopics
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

    override fun makeBundle(topic: Topic): Bundle {
        newBundle = bundleOf("Topic" to topic.name,
            "dOne" to topic.descriptionOne,
            "dTwo" to topic.descriptionTwo,
            "questionSize" to topic.questions.size,
            // Current Question of array
            "current" to 0,
            // Answer Given
            "given" to "",
            // Correct answers so far
            "correct" to 0
        )
        return newBundle as Bundle
    }

    override fun getTopic(name: String): Topic? {
        for (obj in list) {
            if (obj.name.equals(name)) {
                test = obj
            }
        }
        return test
    }

    override fun getQuestions(topic: Topic): Array<QuizQuestion> {
        return topic.questions
    }

}

data class QuizQuestion(val question: String, val quizQuestions: Array<String>, val correct: Int)
data class Topic(val name: String, val descriptionOne: String, val descriptionTwo: String, val questions: Array<QuizQuestion>)