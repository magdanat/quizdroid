package edu.washington.magdanat.quizdroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.android.synthetic.main.item.view.*


class QuestionsFragment : Fragment() {

    private var correct: Int = 0 // Amount of questions correct
    private var current: Int = 1 // Current question
    private var answered: Int = 0 // Amount of questions answered

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Need to inflate
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        val questionSet = arguments!!.getCharSequenceArray(current.toString())

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

//        group?.setOnCheckedChangeListener { group, checkedId ->  }
//            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
//                // Only show the submit button if one of the options is selected.
////                Log.i("Change detected","Change Detected!")
//                Toast.makeText(context,"clicked!", Toast.LENGTH_SHORT).show()
//                submit.visibility = View.VISIBLE
//            }
//        })

//        rGroup.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener {
//            fun onCheckedChange(group: RadioGroup?, checkedId: Int) {
//                val checkedRadioButton : RadioButton = group!!.findViewById(checkedId)
//                val isChecked = checkedRadioButton.isChecked
//                if (isChecked) {
//                    submit.visibility = View.VISIBLE
//                }
//            }
//        })

//        submit.setOnClickListener {
//            val selected = view.findViewById<RadioButton>(group.checkedRadioButtonId)
//            val correctAnswer = questionSet[5]
//            if (correctAnswer.equals(selected.text.toString())) {
//                correct += 1
//            }
//
//            it.findNavController().navigate(R.id.action_questionFragment_to_summaryFragment)
//        }


        // For moving to next fragment
//        view.findViewById<Button>(R.id.button).setOnClickListener {
//            it.findNavController().navigate(R.id.action_detailFragment_to_question, questionsToUse)
//        }

        return view
    }


}