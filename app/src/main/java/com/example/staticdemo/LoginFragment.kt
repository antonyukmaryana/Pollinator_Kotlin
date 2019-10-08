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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<Button>(R.id.executeLoginButton).setOnClickListener { v ->

            val userEmail = view.findViewById<EditText>(R.id.emailText).text.toString()
            val passwordStr = view.findViewById<EditText>(R.id.passwordText).text.toString()

            DataSource.login(userEmail, passwordStr,
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        showError()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        showSuccess(response.body()?.string()!!);
                    }

                })
//
        }
        return view
    }

    fun showSuccess(text: String) {
        activity?.runOnUiThread {
            Toast.makeText(context, "Response = " + text, Toast.LENGTH_LONG).show()
            if (text.equals("Success")) {
                view?.findNavController()?.navigate(R.id.action_loginFragment_to_quizFragment)
            }
        }
    }

    fun showError() {
        activity?.runOnUiThread {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        }
    }

}
