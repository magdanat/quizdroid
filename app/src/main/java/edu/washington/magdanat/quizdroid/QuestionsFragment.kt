package edu.washington.magdanat.quizdroid

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


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
            // Only show the submit button if one of the options is selected.
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
                Log.i("test","Correct answer!")
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