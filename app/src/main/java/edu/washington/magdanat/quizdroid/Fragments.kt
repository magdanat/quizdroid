package edu.washington.magdanat.quizdroid


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.item.view.*
import android.widget.*
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import edu.washington.magdanat.quizdroid.QuizApp.Companion.repo


class ListFragment : Fragment() {

    private lateinit var recycler : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val list = QuizApp.repo.listOfTopics().toList()

        val adapter = RecyclerViewAdapter(list)
        recycler = view.findViewById(R.id.myRecyclerView)
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.adapter = adapter
        recycler.setHasFixedSize(true)

        return view
    }



}

class RecyclerViewAdapter(var list: List<String>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): ViewHolder {
        // Creates ViewHolder to hold reference of the views
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(list[position], position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(listItem: String, position: Int) {
            itemView.content.text = listItem

            itemView.setOnClickListener {
                // Needs to send topic as a bundle
                val topicName = itemView.content.text.toString()
                val topicData = QuizApp.repo.getTopic(topicName)
                val args = QuizApp.repo.makeBundle(topicData!!)

                it.findNavController().navigate(R.id.action_listFragment_to_detailFragment, args)
            }
        }
    }

}



class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val topicName = arguments?.getString("Topic")
        val description = arguments?.getString("dTwo")
        val questionAmount  = arguments?.getInt("questionSize")

        // Need to inflate
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // Views
        val cTopic : TextView = view.findViewById(R.id.topic)
        val cDescription : TextView = view.findViewById(R.id.description)
        val cQuestions : TextView = view.findViewById(R.id.questions)

        // Change Text
        cTopic.text = topicName
        cDescription.text = description
        cQuestions.text = questionAmount.toString()

        // For moving to next fragment
        view.findViewById<Button>(R.id.button).setOnClickListener {
            it.findNavController().navigate(R.id.action_detailFragment_to_question, arguments)
        }

        return view
    }
}


class QuestionsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var current = arguments?.getInt("current") // Current question
        val questions = QuizApp.repo.getQuestions(QuizApp.repo.getTopic(arguments?.getString("Topic")!!)!!)

        // Need to inflate
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        val questionView : TextView = view.findViewById(R.id.questionQuiz)
        val answerOneView : RadioButton = view.findViewById(R.id.answer1)
        val answerTwoView : RadioButton = view.findViewById(R.id.answer2)
        val answerThreeView : RadioButton = view.findViewById(R.id.answer3)
        val answerFourView : RadioButton = view.findViewById(R.id.answer4)
        val submit : Button = view.findViewById(R.id.submit)
        val rGroup : RadioGroup = view.findViewById(R.id.group)

        questionView.text = questions[current!!].question
        answerOneView.text = questions[current!!].quizQuestions[0]
        answerTwoView.text = questions[current!!].quizQuestions[1]
        answerThreeView.text = questions[current!!].quizQuestions[2]
        answerFourView.text = questions[current!!].quizQuestions[3]

        rGroup.setOnCheckedChangeListener { group, checkedId ->
            submit.visibility = View.VISIBLE
        }

        view.findViewById<Button>(R.id.submit).setOnClickListener {
            val selectedOption = view.findViewById<RadioButton>(rGroup.checkedRadioButtonId)
            val currentQuestion = questions[current]
            val isCorrect = selectedOption.text.toString().equals(currentQuestion.quizQuestions[currentQuestion.correct])

            // Sets given to the answer selected by user
            arguments?.putString("given", selectedOption.text.toString())
            if (isCorrect) {
                // Update amount of correct questions
                arguments?.putInt("correct", (arguments?.getInt("correct")!!.plus(1)))
            }

            // Update current question
            arguments?.putInt("current", (current.plus(1)))
            it.findNavController().navigate(R.id.action_questionFragment_to_summaryFragment, arguments)
        }
        return view
    }
}


class SummaryFragment : Fragment () {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        // Current question set number
        var current = arguments?.getInt("current")

        // Current array of questions
        val questions = QuizApp.repo.getQuestions(QuizApp.repo.getTopic(arguments?.getString("Topic")!!)!!)

        // Current question of the set number
        val currentQuestion = questions[current!! - 1]

        val answerIGave : TextView = view.findViewById(R.id.yourAnswer)
        val correctAnswer : TextView = view.findViewById(R.id.correctAnswer)
        val correctSoFar : TextView = view.findViewById(R.id.correctSoFar)
        val button : Button = view.findViewById(R.id.button3)

        if ((arguments?.getInt("current"))!!.equals(arguments?.getInt("questionSize"))) {
            button.text = "Finish"
        }

        answerIGave.text = "Your Answer: " + this.arguments?.getString("given")
        correctAnswer.text = "Correct Answer: " + currentQuestion.quizQuestions[currentQuestion.correct]
        correctSoFar.text = "You have " + arguments?.getInt("correct").toString() + " out of " + (arguments?.getInt("questionSize")) + " correct"

        view.findViewById<Button>(R.id.button3)?.setOnClickListener {
            if (arguments?.getInt("current")!!.equals(arguments?.getInt("questionSize"))) {
                it.findNavController().navigate(R.id.action_summaryFragment_to_listFragment)
            } else {
                it.findNavController().navigate(R.id.action_summaryFragment_to_questionFragment, arguments)
            }
        }
        return view
    }
}

