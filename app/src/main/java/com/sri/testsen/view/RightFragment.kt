package com.sri.testsen.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sri.testsen.R

class RightFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Fragment is not destroyed if retainInstance is true
        retainInstance = true
        return inflater.inflate(R.layout.right_fragment, container, false)
    }
}