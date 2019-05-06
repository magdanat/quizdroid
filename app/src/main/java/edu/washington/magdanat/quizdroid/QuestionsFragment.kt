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

//         var correct  = arguments?.getInt("correct") // Amount of questions correct
         var current = arguments?.getInt("current") // Current question
//         var answered = arguments?.getInt("answered") // Amount of questions answered

        // Need to inflate
        val view = inflater.inflate(R.layout.fragment_question, container, false)
//        val questionSet = arguments?.getCharSequenceArray(current.toString())
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

        submit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val selectedOption = view.findViewById<RadioButton>(rGroup.checkedRadioButtonId)
                val isCorrect = selectedOption.text.toString().equals(questionSet?.get(5))

                arguments?.putInt("answered", (arguments?.getInt("answered")!!.plus(1)))
                if (isCorrect) {
                    arguments?.putInt("correct", (arguments?.getInt("correct")!!.plus(1)))
                }
                Log.i("","setOnClickListener found!")

                view.findViewById<Button>(R.id.button)?.setOnClickListener {
                    Log.i("","We in this view setOnClickListener")
                    it.findNavController().navigate(R.id.action_questionFragment_to_summaryFragment, arguments)
                }

            }
        })

        return view
    }


}