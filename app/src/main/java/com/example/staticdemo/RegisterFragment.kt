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
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view.findViewById<Button>(R.id.executeRegisterButton).setOnClickListener { v ->

            val userEmail = view.findViewById<EditText>(R.id.userEmail).text.toString()
            val passwordStr = view.findViewById<EditText>(R.id.password).text.toString()
            val confirmPassword = view.findViewById<EditText>(R.id.confirmPassword).text.toString()
            DataSource.register(userEmail, passwordStr, confirmPassword,
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
            if (text.equals("Success")){
                view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
    }

    fun showError() {
        activity?.runOnUiThread {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        }
    }


}
