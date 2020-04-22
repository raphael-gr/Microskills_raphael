package com.example.microskills.mainFolder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.microskills.courseFolder.CourseActivity
import com.example.microskills.menuFolder.SectionsMenuActivity
import com.example.test.R
import com.example.test.R.id.action_FirstFragment_to_SecondFragment


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //First button goes to pop up activity
        val btn=view.findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            val intent = Intent(activity, SectionsMenuActivity:: class.java)
            startActivity(intent)
        }

        val goButton = view.findViewById<Button>(R.id.gotonext)
        val codeEditText = view.findViewById<EditText>(R.id.editText)

        //Direction after typing a code
        goButton.setOnClickListener {
            val text = codeEditText.text

            if (text.toString() == "a") {
                findNavController().navigate(action_FirstFragment_to_SecondFragment)
            }
            else if (text.toString() == "b") {
                findNavController().navigate(R.id.action_FirstFragment_to_ThirdFragment)
            }
            else {
                goButton.text = "wrong code, try again"

                //By defaults starts the course activity (temporary)
                val intent = Intent(activity, CourseActivity::class.java)
                startActivity(intent)
            }
        }
    }
}


