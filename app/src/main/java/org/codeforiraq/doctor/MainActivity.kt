package org.codeforiraq.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var signup: TextView
    lateinit var login: FloatingActionButton
    lateinit var emaillogin: EditText
    lateinit var passwordlogin: EditText

    var ref = FirebaseDatabase.getInstance().getReference("Users").child("Doctor")
    var ref2 = FirebaseDatabase.getInstance().getReference("Users").child("User")

    var myAuth = FirebaseAuth.getInstance()

    //lateinit var id :DataSnapshot






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signup = findViewById(R.id.signup)
        login = findViewById(R.id.login)
        emaillogin = findViewById(R.id.emaillogin)
        passwordlogin = findViewById(R.id.passwordlogin)


        signup.setOnClickListener {
            var intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }


        login.setOnClickListener {

                view ->

            var email = emaillogin.text.toString()

            var password = passwordlogin.text.toString().trim()

            if (email.isEmpty()){
                emaillogin.error = "ادخل الاميل"
                return@setOnClickListener
            }
            if (password.isEmpty()){
                passwordlogin.error = "ادخل كلمة المرور"
                return@setOnClickListener
            }
            login1(view, email, password)

        }

    }

    fun login1(view: View, email: String, password: String) {

        Toast.makeText(this, "جاري تسجيل الدخول ...", Toast.LENGTH_LONG).show()

        myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->

            if (task.isSuccessful) {

             var intent =Intent(this@MainActivity,Show_for_doctor::class.java)
                startActivity(intent)
                this.finish()


                Toast.makeText(this, "تم تسجيل الدخول ...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "يرجى التأكد من المعلومات او التأكد من الاتصال بالانترنت!", Toast.LENGTH_LONG).show()
            }
        })


    }


}
