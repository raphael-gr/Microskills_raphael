package com.example.microskills.menuFolder

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.test.R
import kotlinx.android.synthetic.main.activity_menu_sections.*

class SectionsMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_sections)

        val constraintLayout = findViewById(R.id.constraintLayout) as LinearLayout

        val coursesnames = mutableMapOf(
            "Saving money" to "saving and hiding money", "Multiply money" to "gain and save", "Loan interest" to "loan definition and request", "Home budget" to "separate business and home", "Training 1" to "bla", "Training 2" to "blabla"
        )
        //We get from the website a dictionary with keys : courses names and values : description of the course.

        val values = coursesnames.values.toList()
        var i: Int = 0


        for (name in coursesnames.keys) {
            val btni = Button(this)
            btni.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            btni.text = name
            btni.height = 210
            btni.id= i + 1
            constraintLayout.addView(btni);
            //for each course, we create a button with on it the course name, with its parameters.

            val detail = values[i]
            //we get the description of the course now.

            btni.setOnClickListener() {
                val view = layoutInflater.inflate(R.layout.layout_popup, root_layout, false)

                val window = android.widget.PopupWindow(
                    view,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT

                )
                window.height = 500
                val code = view.findViewById<TextView>(R.id.textView3)
                val about = view.findViewById<TextView>(R.id.textView4)
                about.text = (getString(R.string.abt1) + " " + btni.text)
                code.text = (getString(R.string.introduction) + " " + btni.text + getString(R.string.abt2) + " " + detail + ".")

                //When the user clicks on the button, a pop up window appears with the first text : About "name course", and the next box is the description
                //of the course.

                code.setOnClickListener {
                    window.dismiss()
                }
                //when the user clicks on the second box, the message disappears.

                window.showAtLocation(
                    root_layout, // Location to display popup window
                    Gravity.CENTER, // Exact position of layout to display popup
                    0, // X offset
                    0
                ) // Y offset
                //we print the button at a certain location.
            }
            i++
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}
