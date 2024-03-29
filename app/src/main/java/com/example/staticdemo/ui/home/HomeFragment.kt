package com.example.staticdemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.staticdemo.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        val loginButton: Button = root.findViewById(R.id.loginButton)
        loginButton.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_navigation_home_to_loginFragment)
        }

        val registerButton: Button = root.findViewById(R.id.registerButton)
        registerButton.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_navigation_home_to_registerFragment)
        }
        return root
    }
}