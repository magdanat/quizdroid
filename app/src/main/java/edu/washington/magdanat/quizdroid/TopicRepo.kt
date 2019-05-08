package edu.washington.magdanat.quizdroid

import android.os.Bundle
import androidx.core.os.bundleOf

interface TopicRepositoryInterface {
    fun startRepo()
    fun listOfTopics() : Array<String>
    fun getTopic(name: String): Topic?
    fun makeBundle(topic: Topic): Bundle
    fun getQuestions(topic: Topic): Array<QuizQuestion>
}

class TopicRepo : TopicRepositoryInterface {

    private var topicQuiz = arrayListOf<Topic>()
    private lateinit var list : ArrayList<String>
    private var test : Topic? = null
    private var newBundle : Bundle? = null

    override fun startRepo() {
        // Questions for math Topic
        val mathQOne = QuizQuestion("1 + 1 = ?", arrayOf("2", "-2", "0", "1"), 0)
        val mathQTwo = QuizQuestion("5 * 8 = ?", arrayOf("40", "-40", "20", "-20"), 0)
        val mathQThree = QuizQuestion("100 % 10 = ?", arrayOf("100", "10", "1", "0"), 3)
        val mathQFour = QuizQuestion("36 / 6 = ?", arrayOf("8", "2", "6", "4"), 2)

        // Questions for physics topic
        val physicsQOne = QuizQuestion("What is the first law of physics?", arrayOf(
            "An object will remain at rest or in uniform motion unless acted upon by an external force",
            "There is no first law",
            "E = mc^2",
            "None of the Above"), 0)
        val physicsQTwo = QuizQuestion("What is the acceleration of gravity?", arrayOf("10 m / s",
            "9.9 m / s",
            "9.81 m / s / s",
            "2 m / s / s"), 2)

        // Questions for Marvel
        val marvelQOne = QuizQuestion("What color is Iron Man?", arrayOf("Red", "Blue", "Yellow", "Silver"), 0)
        val marvelQTwo = QuizQuestion("How many infinity stones are there?", arrayOf("1", "6", "3", "100"), 1)
        val marvelQThree = QuizQuestion("What is Captain America's real name?", arrayOf("Bucky", "Stark", "Captain America", "Steve"), 3)


        topicQuiz.add(Topic("Math", "Math quiz", "This quiz will be a series of math questions", arrayOf(mathQOne, mathQTwo, mathQThree, mathQFour)))
        topicQuiz.add(Topic("Physics", "Physics quiz", "This quiz will be a series of physics questions", arrayOf(physicsQOne, physicsQTwo)))
        topicQuiz.add(Topic("Marvel Cinematic Universe!", "MCU Quiz", "This quiz be a series of questions about the Marvel" +
                "Cinematic Universe", arrayOf(marvelQOne, marvelQTwo, marvelQThree)))
    }

    override fun listOfTopics(): Array<String> {
        list = ArrayList()
        for (obj in topicQuiz) {
            list.add(obj.name)
        }
        return list.toTypedArray()
    }

    override fun getTopic(name: String): Topic? {
        for (obj in topicQuiz) {
            if (obj.name.equals(name)) {
                test = obj
            }
        }
        return test
    }

    // Turns topic into a bundle to pass as an argument
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

    override fun getQuestions(topic: Topic): Array<QuizQuestion> {
        return topic.questions
    }
}

data class QuizQuestion(val question: String, val quizQuestions: Array<String>, val correct: Int)
data class Topic(val name: String, val descriptionOne: String, val descriptionTwo: String, val questions: Array<QuizQuestion>)
