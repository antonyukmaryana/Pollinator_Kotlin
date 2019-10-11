package com.example.staticdemo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.staticdemo.data.DataSource
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class CreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create, container, false)
        val questionTextView: EditText = view.findViewById(R.id.questionText)
        val answerAView: EditText = view.findViewById(R.id.answerA)
        val answerBView: EditText = view.findViewById(R.id.answerB)
        val answerCView: EditText = view.findViewById(R.id.answerC)


        view.findViewById<Button>(R.id.addQuestionButton).setOnClickListener { v ->
            DataSource.createQuiz(questionTextView.text.toString(),
                answerAView.text.toString(),
                answerBView.text.toString(),
                answerCView.text.toString(),
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        showError()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        showSuccess(response.body()?.string()!!);
                    }

                })

        }
        return view
    }

    fun showSuccess(text: String) {
        activity?.runOnUiThread {
            Toast.makeText(context, "Response = " + text, Toast.LENGTH_LONG).show()
            if (text.equals("Success")) {
                view?.findNavController()?.navigate(R.id.action_createFragment_to_quizFragment)
            }
        }
    }

    fun showError() {
        activity?.runOnUiThread {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        }
    }
}
