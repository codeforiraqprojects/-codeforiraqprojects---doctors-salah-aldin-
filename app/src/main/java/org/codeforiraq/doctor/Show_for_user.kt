package org.codeforiraq.doctor

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_data__user__docotr.*
import kotlinx.android.synthetic.main.activity_show_for_user.*
import kotlinx.android.synthetic.main.custom_tablayout.*
import java.util.*

class Show_for_user : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    lateinit var doctor_listview: ListView
    lateinit var doctorList :MutableList<DoctorData>
    var myAuth = FirebaseAuth.getInstance()
    lateinit var ref: DatabaseReference

    var notificationHelper:NotificationHelper?=null

    var id1 =0
    var data :DoctorData?=null


    lateinit var sp :SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_for_user)
        setSupportActionBar(toolbar_main)

        sp=getSharedPreferences("notify",Context.MODE_PRIVATE)



            fabuser.setOnClickListener {

                Toast.makeText(this, "يجب عليك تسجيل الدخول", Toast.LENGTH_LONG).show()

                var intent = Intent(this@Show_for_user, MainActivity::class.java)
                startActivity(intent)
            }

            nav_view.setNavigationItemSelectedListener(this)
            var actionToggle =
                ActionBarDrawerToggle(this, drower_usershow, toolbar_main, R.string.drawe_open, R.string.drawe_close)


            drower_usershow.addDrawerListener(actionToggle)

            actionToggle.syncState()




            doctor_listview = findViewById(R.id.doctor_listview_for_user)
            doctorList = mutableListOf()


            ref = FirebaseDatabase.getInstance().getReference("Users").child("Doctor")


            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    doctorList.clear()
                    if (p0.exists()) {

                        for (e in p0.children) {

                            data = e.getValue(DoctorData::class.java)

                            doctorList.add(0, data!!)



                        }


                    }


                    var adapter = User_adapter(this@Show_for_user, doctorList)
                    doctor_listview.adapter = adapter

                }

            })

        ref.keepSynced(true)


        fabuser.setOnClickListener {

            Toast.makeText(this, "يجب عليك تسجيل الدخول", Toast.LENGTH_LONG).show()

            var intent = Intent(this@Show_for_user, MainActivity::class.java)
            startActivity(intent)
        }




    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        menuInflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//
//            R.id.signup -> {
//                myAuth.signOut()
//                Toast.makeText(this, "جار تسجيل الخروج ...", Toast.LENGTH_LONG).show()
//
//                var intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                this.finish()
//                return true
//
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }


    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        var item = menu.itemId
        when(item){


            R.id.signin->{

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                closenavigation()


            }
            R.id.signup->{

                var intent = Intent(this, Signup::class.java)
                startActivity(intent)
                closenavigation()

            }

            R.id.about_code_for_iraq->{

                var intent = Intent(this, About_Code_For_Iraq::class.java)
                startActivity(intent)
                closenavigation()

            }

            R.id.getout->{

                finishAffinity()

            }
            R.id.about_team->{

                var intent = Intent(this, About_team::class.java)
                startActivity(intent)
                closenavigation()

            }
            R.id.about_app->{

                var intent = Intent(this, About_app::class.java)
                startActivity(intent)
                closenavigation()

            }
        }

        return true
    }

    @SuppressLint("WrongConstant")
    fun closenavigation (){

        drower_usershow.closeDrawer(Gravity.START)
    }

    override fun onBackPressed() {
        if (drower_usershow.isDrawerOpen(GravityCompat.START)) {

            closenavigation()
        }
        else{
            super.onBackPressed()

        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.menusearch, menu)


        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.search -> {
                var intent = Intent(this, Not_login_search::class.java)
                startActivity(intent)
                true
            }


            else -> super.onOptionsItemSelected(item)
        }

    }



    }
