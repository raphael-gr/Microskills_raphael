package com.example.microskills.courseFolder

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.microskills.courseFolder.CourseActivity

private const val TAG = "Recycler"

//Passing the activity context into the Adapter is considered bad practice
//but I see no other way to modify the input interface when a message asks so

class MessagesRecyclerAdapter(context: Context, private var uid: String)  :
    RecyclerView.Adapter<MessagesRecyclerAdapter.MessageViewHolder>() {
    // uid: user id, corresponds to the person using the app
    // note that the two lines above are actually on the same level (the newline is for clarity)

    private var items: List<Message> = ArrayList()
    private val adapterCallback = (context as AdapterCallback)


    companion object {
        private const val SENT_TEXT = 0
        private const val SENT_IMAGE = 1
        private const val RECEIVED_TEXT = 10
        private const val RECEIVED_IMAGE = 11
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        Log.d(TAG, "MessagesAdapter: onCreateViewHolder.")
        val view = when (viewType) {
            SENT_TEXT -> {
                LayoutInflater.from(parent.context).inflate(R.layout.text_item_sent, parent, false)
            }
            RECEIVED_TEXT -> {
                LayoutInflater.from(parent.context).inflate(R.layout.text_item_received, parent, false)
            }
            SENT_IMAGE -> {
                LayoutInflater.from(parent.context).inflate(R.layout.image_item_sent, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context).inflate(R.layout.image_item_received, parent, false)
            }
        }
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        Log.d(TAG, "MessagesRecyclerAdapter: onBindViewHolder")
        val context = holder.itemView.context

        holder.bind(items[position])
        // If the bound view wasn't previously displayed on screen, it's animated
        if (!items[position].displayed) {
            val animation: Animation =
                AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down)
            holder.itemView.startAnimation(animation)
            items[position].displayed = true
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "MessagesRecyclerAdapter: getItemCount ; " + items.size.toString())
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.d(TAG, "MessagesAdapter: getItemViewType")
        // This function's code is redundant and ugly

        return if (items[position].sender?.uid!!.contentEquals(uid)) {
            if (items[position] is TextMessage) {
                SENT_TEXT
            } else {
                SENT_IMAGE
            }

        } else {
            if (items[position] is TextMessage) {
                RECEIVED_TEXT
            } else {
                RECEIVED_IMAGE
            }
        }
    }

    fun submitList(messagesList: List<Message>){
        items = messagesList
    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Do note that findViewById returns null if the view hasn't been inflated
        fun bind(message: Message) {

            if (message is TextMessage) {
                val messageText: TextView = itemView.findViewById(R.id.message_text)
                messageText.text = message.text

            } else if (message is ImageMessage) {
                val messageImage: ImageView = itemView.findViewById(R.id.message_image)
                messageImage.setImageResource(R.drawable.sofishticated)
            }

            if (message.expectNumInput) {
                adapterCallback.onHideButtonReceiveCallback()
                adapterCallback.onShowNumPad()
            }
        }
    }

    // The following functions are initialized in CourseActivity
    interface AdapterCallback {
        fun onHideButtonReceiveCallback()
        fun onShowButtonReceiveCallback()
        fun onShowNumPad()
    }
}