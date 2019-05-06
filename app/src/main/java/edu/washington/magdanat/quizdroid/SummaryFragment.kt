//package edu.washington.magdanat.quizdroid
//
//import android.content.Context
//import android.os.Bundle
//import android.provider.Settings.Global.putInt
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.navigation.findNavController
//
//class OriginalSummaryFragment : Fragment () {
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_summary, container, false)
//
//        val answerIGave : TextView = view.findViewById(R.id.yourAnswer)
//        val correctAnswer : TextView = view.findViewById(R.id.correctAnswer)
//        val correctSoFar : TextView = view.findViewById(R.id.correctSoFar)
//        val button : Button = view.findViewById(R.id.button3)
//
//        answerIGave.text = "Your Answer: " + this.arguments?.getString("given")
//        correctAnswer.text = "Correct Answer: " + this.arguments?.getString("correctAnswer")
//        correctSoFar.text = "You have " + arguments?.getInt("correct").toString() + " out of " + (arguments?.size()?.minus(5)) + " correct"
//
//        if (arguments?.getInt("answered")!!.equals(arguments?.size()?.minus(5))) {
//            button.text = "Finish"
//        }
//
//        view.findViewById<Button>(R.id.button3)?.setOnClickListener {
//            if (arguments?.getInt("answered")!!.equals(arguments?.size()?.minus(5))) {
//                it.findNavController().navigate(R.id.action_summaryFragment_to_listFragment)
//            } else {
//                it.findNavController().navigate(R.id.action_summaryFragment_to_questionFragment, arguments)
//            }
//        }
//        return view
//    }
//}