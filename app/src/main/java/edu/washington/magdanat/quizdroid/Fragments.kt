package edu.washington.magdanat.quizdroid


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.item.view.*
import android.util.Log
import android.widget.*
import android.widget.Button
import android.widget.TextView


class ListFragment : Fragment() {

    private lateinit var recycler : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val list = listOf("Math","Physics","Marvel Super Heroes")

        val adapter = RecyclerViewAdapter(list)
        recycler = view.findViewById(R.id.myRecyclerView)
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.adapter = adapter
        recycler.setHasFixedSize(true)


        return view
    }



}

class RecyclerViewAdapter(var list: List<String>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val variableMath = bundleOf(
        "Topic" to "Math",
        "Description" to "This quiz will be a series of math questions",
        "Questions" to 5
    )

    private val variablePhysics = bundleOf(
        "Topic" to "Physics",
        "Description" to "This quiz will be a series of physics questions",
        "Questions" to 2
    )

    private val variableMCU = bundleOf(
        "Topic" to "Marvel Super Heroes",
        "Description" to "This quiz will be a series of questions about the Marvel Cinematic Universe",
        "Questions" to 4
    )

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
                if (itemView.content.text.toString().equals("Math")) {
                    it.findNavController().navigate(R.id.action_listFragment_to_detailFragment, variableMath)
                } else if (itemView.content.text.toString().equals("Physics")) {
                    it.findNavController().navigate(R.id.action_listFragment_to_detailFragment, variablePhysics)
                } else {
                    it.findNavController().navigate(R.id.action_listFragment_to_detailFragment, variableMCU)
                }
            }
        }


    }
}

class DetailFragment : Fragment() {
    private val questionsMath = bundleOf(
        "1" to arrayOf(
            "1 + 1 = ?", "2", "-2", "0", "1", "2"
        ),
        "2" to arrayOf(
            "5 * 8 = ?", "32", "48", "-40", "40", "40"
        ),
        "3" to arrayOf(
            "10 / 5 = ?", "2.5", "2", "10", "5", "2"
        ),
        "4" to arrayOf(
            "100 - 20 = ?", "79", "30", "80", "90", "80"
        ),
        "5" to arrayOf(
            "29 % 10 = ?", "9", "2.9", "1", "0", "9"
        ),
        "correct" to 0,
        "answered" to 0,
        "current" to 1,
        "given" to "",
        "correctAnswer" to ""
    )

    private val questionsPhysics = bundleOf(
        "1" to arrayOf(
            "What is the first law of physics?",
            "An object will remain at rest or in uniform motion unless acted upon by an external force",
            "There is no first law",
            "E = mc^2",
            "None of the Above",
            "An object will remain at rest or in uniform motion unless acted upon by an external force"
        ),
        "2" to arrayOf(
            "What is the acceleration of gravity?",
            "9.81 m/s",
            "10 mph",
            "10 m/s",
            "9.81 m/s/s",
            "9.81 m/s/s"
        ),
        "correct" to 0,
        "answered" to 0,
        "current" to 1,
        "given" to "",
        "correctAnswer" to ""
    )

    private val questionsMCU = bundleOf(
        "1" to arrayOf(
            "What is the first Marvel film of the MCU?",
            "Iron Man",
            "Iron Man 2",
            "The Incredible Hulk",
            "Batman vs. Superman",
            "Iron Man"
        ),
        "2" to arrayOf(
            "What color is Iron Man primarily?",
            "Red",
            "Blue",
            "Green",
            "Purple",
            "Red"
        ),
        "3" to arrayOf(
            "What is the real name of Captain America?",
            "Bucky Barnes",
            "Barnes Nobles",
            "Steve Dodgers",
            "Steve Rogers",
            "Steve Rogers"
        ),
        "4" to arrayOf(
            "Fill in the blank: Guardians of the [Blanks]",
            "Planet",
            "Universe",
            "World",
            "Galaxy",
            "Galaxy"
        ),
        "correct" to 0,
        "answered" to 0,
        "current" to 1,
        "given" to "",
        "correctAnswer" to ""
    )

    private lateinit var questionsToUse : Bundle


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val topic = arguments?.getString("Topic")
        val description = arguments?.getString("Description")
        val questions  = arguments?.getInt("Questions")
        when {
            topic.equals("Math") -> questionsToUse = questionsMath
            topic.equals("Physics") -> questionsToUse = questionsPhysics
            topic.equals("Marvel Super Heroes") -> questionsToUse = questionsMCU
        }

        // Need to inflate
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // Views
        val cTopic : TextView = view.findViewById(R.id.topic)
        val cDescription : TextView = view.findViewById(R.id.description)
        val cQuestions : TextView = view.findViewById(R.id.questions)

        // Change Text
        cTopic.text = topic
        cDescription.text = description
        cQuestions.text = questions.toString()


        // For moving to next fragment
        view.findViewById<Button>(R.id.button).setOnClickListener {
            it.findNavController().navigate(R.id.action_detailFragment_to_question, questionsToUse)
        }

        return view
    }


}


class QuestionsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var current = arguments?.getInt("current") // Current question

        // Need to inflate
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        val questionSet  = arguments!!.getStringArray(current.toString())

        val questionView : TextView = view.findViewById(R.id.questionQuiz)
        val answerOneView : RadioButton = view.findViewById(R.id.answer1)
        val answerTwoView : RadioButton = view.findViewById(R.id.answer2)
        val answerThreeView : RadioButton = view.findViewById(R.id.answer3)
        val answerFourView : RadioButton = view.findViewById(R.id.answer4)
        val submit : Button = view.findViewById(R.id.submit)
        val rGroup : RadioGroup = view.findViewById(R.id.group)

        questionView.text = questionSet[0]
        answerOneView.text = questionSet[1]
        answerTwoView.text = questionSet[2]
        answerThreeView.text = questionSet[3]
        answerFourView.text = questionSet[4]

        rGroup.setOnCheckedChangeListener { group, checkedId ->
            submit.visibility = View.VISIBLE
        }

        view.findViewById<Button>(R.id.submit).setOnClickListener {
            val selectedOption = view.findViewById<RadioButton>(rGroup.checkedRadioButtonId)
            val isCorrect = selectedOption.text.toString().equals(questionSet?.get(5))
            // Stores the correct answer for summary fragment
            arguments?.putString("correctAnswer", questionSet[5].toString())

            // Amount of questions answered so far
            arguments?.putInt("answered", (arguments?.getInt("answered")!!.plus(1)))
            // Sets given to the answer selected by user
            arguments?.putString("given", selectedOption.text.toString())
            if (isCorrect) {
                // Update amount of correct questions
                arguments?.putInt("correct", (arguments?.getInt("correct")!!.plus(1)))
            }

            // Update current question
            arguments?.putInt("current", (arguments?.getInt("current")!!.plus(1)))
            it.findNavController().navigate(R.id.action_questionFragment_to_summaryFragment, arguments)
        }
        return view
    }
}


class SummaryFragment : Fragment () {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        val answerIGave : TextView = view.findViewById(R.id.yourAnswer)
        val correctAnswer : TextView = view.findViewById(R.id.correctAnswer)
        val correctSoFar : TextView = view.findViewById(R.id.correctSoFar)
        val button : Button = view.findViewById(R.id.button3)

        answerIGave.text = "Your Answer: " + this.arguments?.getString("given")
        correctAnswer.text = "Correct Answer: " + this.arguments?.getString("correctAnswer")
        correctSoFar.text = "You have " + arguments?.getInt("correct").toString() + " out of " + (arguments?.size()?.minus(5)) + " correct"

        if (arguments?.getInt("answered")!!.equals(arguments?.size()?.minus(5))) {
            button.text = "Finish"
        }

        view.findViewById<Button>(R.id.button3)?.setOnClickListener {
            if (arguments?.getInt("answered")!!.equals(arguments?.size()?.minus(5))) {
                it.findNavController().navigate(R.id.action_summaryFragment_to_listFragment)
            } else {
                it.findNavController().navigate(R.id.action_summaryFragment_to_questionFragment, arguments)
            }
        }
        return view
    }
}