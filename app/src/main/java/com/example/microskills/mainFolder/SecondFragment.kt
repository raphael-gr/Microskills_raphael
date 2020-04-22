package com.example.microskills.mainFolder

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test.R


class SecondFragment : Fragment() {
    //The following value is what you should search in the Logcat to access this file's log
    private val TAG = "SecondFragment"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    /* onAttach is called when the fragment is attached to its parent activity (here MainActivity).
    Writing in this function guarantees that context is non-null.     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        /* The following code opens the json file from the assets folder, and prints its content in
       Logcat > Debug > "MainActivity" (TAG)
       jsonString is what we want to treat in JsonTreatment */
        try {
            val jsonString = activity!!.assets.open("Covid_best_practices.json")
                .bufferedReader().use{
                    it.readText()
                }
            Log.d(TAG, jsonString)
        } catch (e:Exception){
            Log.d(TAG, e.toString())
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }
    }

