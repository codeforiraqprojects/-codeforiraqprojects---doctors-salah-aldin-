package org.codeforiraq.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {

    lateinit var email_doctor: EditText
    lateinit var password_doctor: EditText
    lateinit var repassword_doctor: EditText

    lateinit var sava_data_doctor: FloatingActionButton

    var myAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        email_doctor=findViewById(R.id.email_doctor)
        password_doctor=findViewById(R.id.password_doctor)
        repassword_doctor=findViewById(R.id.repasword)

        sava_data_doctor=findViewById(R.id.save_data_doctor)


        sava_data_doctor.setOnClickListener {

            var email   =email_doctor.text.toString().trim()
            var password =password_doctor.text.toString()
            var repassword =repassword_doctor.text.toString()


            if (repassword == password) {


                myAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->


                    if (task.isSuccessful) {

                        Toast.makeText(this, "تم انشاء الحساب بنجاح", Toast.LENGTH_LONG).show()


                        var intent = Intent(this, Show_for_doctor::class.java)


                        startActivity(intent)
                    }
                    else{

                        Toast.makeText(this,"هنالك خطأ يرجى التأكد من الاتصال بالانترنت",Toast.LENGTH_LONG).show()
                    }
                }
            }

                if (repassword != password){

                    Toast.makeText(this,"كلمات المرور غير متطابقة يرجى التأكد منها",Toast.LENGTH_LONG).show()

                    repassword_doctor.error = "يرجى التأكد"
                    password_doctor.error = "يرجى التأكد"

                }

        }


    }
}

