package org.codeforiraq.doctor.Fragments

import android.icu.text.CaseMap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.fragment_first_screen.*
import org.codeforiraq.doctor.R


class FirstScreen : Fragment() {
    var title1 =""
    var title2 =""
    var title3 =""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        f_title1.text=title1
        f_title2.text=title2
        f_title3.text=title3
    }

    fun settitle_and_image (title11: String, title22:String, title33:String ){

        title1 = title11
        title2 = title22
        title3 = title33

    }
}



