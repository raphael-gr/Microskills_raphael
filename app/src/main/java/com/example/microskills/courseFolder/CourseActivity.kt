package com.example.microskills.courseFolder

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R


private const val TAG = "Recycler"

//courseActivity implements an interface that is written at the end of this file.
class CourseActivity : AppCompatActivity(), MessagesRecyclerAdapter.AdapterCallback {
    private lateinit var messagesRecycler: RecyclerView
    private lateinit var messageAdapter: MessagesRecyclerAdapter
    private val data = generateData()
    private var messageList = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        //Note: the Log didn't work outside of onCreate
        Log.d(TAG, "CourseActivity: onCreate.")

        initRecyclerView()
        messageAdapter.submitList(messageList)

        initNumpadInput()

        // One message is automatically loaded, then they are through key press.
        addMessage()
        val receiveButton = findViewById<Button>(R.id.button_chatbox_receive)
        receiveButton.setOnClickListener{
            addMessage()
        }
        /*
            If we wanted to make messages appear automatically with time instead of having
            to press button_chatbox_receive, we'd probably just have to time calls of addMessage().
         */

    }

    private fun addMessage(){
        val message = data[0]
        data.removeAt(0)
        messageList.add(message)
        messageAdapter.notifyItemInserted(messageList.size - 1)
        messagesRecycler.scrollToPosition(messageAdapter.itemCount -1);

        Log.d(TAG, "CourseActivity: addMessage() done.")
    }

    private fun initRecyclerView(){
        messagesRecycler = findViewById<RecyclerView>(R.id.recyclerview_message_list)

        messagesRecycler.apply {
            //The following two lines must be in this precise order
            messageAdapter =
                MessagesRecyclerAdapter(context,"raphael")
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = messageAdapter
        }

        Log.d(TAG, "CourseActivity: initRecyclerView() done.")
    }

    private fun initNumpadInput(){
        val numpadInput = findViewById<EditText>(R.id.numpad_input)
        val numpad = findViewById<Numpad>(R.id.numpad)
        numpadInput.setRawInputType(InputType.TYPE_CLASS_TEXT)
        numpadInput.setTextIsSelectable(true)
        val ic = numpadInput.onCreateInputConnection(EditorInfo())
        numpad.setInputConnection(ic)
    }

    //======================================
    // The following methods are "callbacks": they are called in MessagesRecyclerAdapter
    // through a Kotlin interface
    //======================================

    override fun onHideButtonReceiveCallback(){
        val receiveButton = findViewById<Button>(R.id.button_chatbox_receive)
        if (receiveButton != null) {
            receiveButton.visibility = View.GONE
        }
    }

    override fun onShowButtonReceiveCallback(){
        val receiveButton = findViewById<Button>(R.id.button_chatbox_receive)
        if (receiveButton != null) {
            receiveButton.visibility = View.VISIBLE
        }
    }


    override fun onShowNumPad(){
        val numpad = findViewById<Numpad>(R.id.numpad)
        if (numpad != null) {
            numpad.visibility = View.VISIBLE
        }

    }
}