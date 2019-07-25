package com.ls.dareman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class DashFragment: DMFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(com.ls.dareman.R.layout.fragment_menu, container, false)
    }

    override fun onStart() {
        super.onStart()

        val buttonOne = activity!!.findViewById(com.ls.dareman.R.id.menu_one) as Button
        buttonOne.setOnClickListener {
            Toast.makeText(activity, "hoge!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, SplashActivity::class.java)
            startActivity(intent)
        }

        val buttonTwo = activity!!.findViewById(com.ls.dareman.R.id.menu_two) as Button
        buttonTwo.setOnClickListener {
            Toast.makeText(activity, "Contents preparing...", Toast.LENGTH_SHORT).show()
        }
    }
}