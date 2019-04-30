package edu.washington.magdanat.quizdroid


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.item.view.*


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
        )
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
            "9.81 m/s/s/"
        )
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
        )
    )

    private lateinit var questionsToUse : Bundle


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val topic = arguments?.getString("Topic")
        val description = arguments?.getString("Description")
        val questions  = arguments?.getInt("Questions")
        if (topic.equals("Math")) {
            questionsToUse = questionsMath
        } else if (topic.equals("Physics")) {
            questionsToUse = questionsPhysics
        } else if (topic.equals("Marvel Super Heroes")) {
            questionsToUse = questionsMCU
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