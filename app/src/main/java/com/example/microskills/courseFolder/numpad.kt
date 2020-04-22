package com.example.microskills.courseFolder

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import com.example.test.R

// This file was adapted from:
// https://stackoverflow.com/questions/9577304/how-can-you-make-a-custom-keyboard-in-android

class Numpad @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0):
    LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    // keyboard keys (buttons)
    private lateinit var mButton1: Button
    private lateinit var mButton2: Button
    private lateinit var mButton3: Button
    private lateinit var mButton4: Button
    private lateinit var mButton5: Button
    private lateinit var mButton6: Button
    private lateinit var mButton7: Button
    private lateinit var mButton8: Button
    private lateinit var mButton9: Button
    private lateinit var mButton0: Button

    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    private var keyValues: SparseArray<String> = SparseArray()
    // Our communication link to the EditText
    private lateinit var inputConnection: InputConnection
    init{
        if (attrs != null) {
            init(context, attrs)
        }
    }
    private fun init(context:Context, attrs:AttributeSet) {
        // initialize buttons
        LayoutInflater.from(context).inflate(R.layout.numpad, this, true)
        mButton1 = findViewById<Button>(R.id.button_1)
        mButton2 = findViewById<Button>(R.id.button_2)
        mButton3 = findViewById<Button>(R.id.button_3)
        mButton4 = findViewById<Button>(R.id.button_4)
        mButton5 = findViewById<Button>(R.id.button_5)
        mButton6 = findViewById<Button>(R.id.button_6)
        mButton7 = findViewById<Button>(R.id.button_7)
        mButton8 = findViewById<Button>(R.id.button_8)
        mButton9 = findViewById<Button>(R.id.button_9)
        mButton0 = findViewById<Button>(R.id.button_0)
        val mButtonDelete = findViewById<Button>(R.id.button_delete)
        val mButtonEnter = findViewById<Button>(R.id.button_dot)
        // set button click listeners
        mButton1.setOnClickListener(this)
        mButton2.setOnClickListener(this)
        mButton3.setOnClickListener(this)
        mButton4.setOnClickListener(this)
        mButton5.setOnClickListener(this)
        mButton6.setOnClickListener(this)
        mButton7.setOnClickListener(this)
        mButton8.setOnClickListener(this)
        mButton9.setOnClickListener(this)
        mButton0.setOnClickListener(this)
        mButtonDelete.setOnClickListener(this)
        mButtonEnter.setOnClickListener(this)
        // map buttons IDs to input strings
        keyValues.put(R.id.button_1, "1")
        keyValues.put(R.id.button_2, "2")
        keyValues.put(R.id.button_3, "3")
        keyValues.put(R.id.button_4, "4")
        keyValues.put(R.id.button_5, "5")
        keyValues.put(R.id.button_6, "6")
        keyValues.put(R.id.button_7, "7")
        keyValues.put(R.id.button_8, "8")
        keyValues.put(R.id.button_9, "9")
        keyValues.put(R.id.button_0, "0")
        keyValues.put(R.id.button_dot, "\n")
    }

    override fun onClick(v:View) {
        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return
        // Delete text or input key value
        // All communication goes through the InputConnection
        if (v.id === R.id.button_delete)
        {
            val selectedText = inputConnection.getSelectedText(0)
            if (TextUtils.isEmpty(selectedText))
            {
                // no selection, so delete previous character
                inputConnection.deleteSurroundingText(1, 0)
            }
            else
            {
                // delete the selection
                inputConnection.commitText("", 1)
            }
        }
        else
        {
            val value = keyValues.get(v.id)
            inputConnection.commitText(value, 1)
        }
    }
    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    fun setInputConnection(ic:InputConnection) {
        this.inputConnection = ic
    }
}