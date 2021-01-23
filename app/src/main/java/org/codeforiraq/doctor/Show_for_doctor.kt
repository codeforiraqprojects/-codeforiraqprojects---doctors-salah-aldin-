package org.codeforiraq.doctor

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_show_for_doctor.*
import kotlinx.android.synthetic.main.custom_tablayout.*

class Show_for_doctor : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{


    lateinit var doctor_listview: ListView
    lateinit var doctorList :MutableList<DoctorData>
    var myAuth = FirebaseAuth.getInstance()

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_for_doctor)

        setSupportActionBar(toolbar_main)

        login.setOnClickListener {
            var intent= Intent(this,Data_of_User_and_Docotr::class.java)
            startActivity(intent)
        }

        nav_view2.setNavigationItemSelectedListener(this)
        var actionToggle = ActionBarDrawerToggle(this,doctordrwer,toolbar_main,R.string.drawe_open,R.string.drawe_close)


        doctordrwer.addDrawerListener(actionToggle)

        actionToggle.syncState()

        doctor_listview =findViewById(R.id.doctor_listview)
        doctorList = mutableListOf()

        ref = FirebaseDatabase.getInstance().getReference("Users").child("Doctor")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                doctorList.clear()
                if (p0.exists()){

                    for (e in p0.children) {

                        var data = e.getValue(DoctorData::class.java)

                        doctorList.add(0,data!!)

                        }
                    }

                var adapter = Doctor_adapter(this@Show_for_doctor,doctorList)
                doctor_listview.adapter =adapter
            }


        })

        ref.keepSynced(true)


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
            R.id.signout->{

                Toast.makeText(this,"جاري تسجيل الخروج", Toast.LENGTH_LONG).show()
                myAuth.signOut()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
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

        doctordrwer.closeDrawer(Gravity.START)
    }

    override fun onBackPressed() {
        if (doctordrwer.isDrawerOpen(GravityCompat.START)) {

            closenavigation()
        }
        else{

            if (myAuth.currentUser !=null){

                finishAffinity()
            }else{

                super.onBackPressed()

            }

        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.menusearch, menu)


        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.search -> {
                var intent = Intent(this, Search::class.java)
                startActivity(intent)
                true
            }


            else -> super.onOptionsItemSelected(item)
        }

    }
}
