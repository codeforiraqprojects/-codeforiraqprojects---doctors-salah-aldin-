package org.codeforiraq.doctor

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_not_login_search.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.drow_search
import kotlinx.android.synthetic.main.activity_search.nav_search_fordoctor
import kotlinx.android.synthetic.main.custom_tablayout.*

class Not_login_search : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{


    lateinit var ref: DatabaseReference
    lateinit var doctorlist :MutableList<DoctorData>
    lateinit var searchlistview : ListView
    lateinit var searchview: EditText
    lateinit var searchbutton: Button

    lateinit var spinner_search :Spinner
    var myAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_login_search)

        setSupportActionBar(toolbar_main)
        supportActionBar!!.title="البحث"



        nav_search_foruser.setNavigationItemSelectedListener(this)
            var actionToggle = ActionBarDrawerToggle(this,drow_search_not_login,toolbar_main,R.string.drawe_open,R.string.drawe_close)
            drow_search_not_login.addDrawerListener(actionToggle)

            actionToggle.syncState()





        doctorlist = mutableListOf()
        searchlistview = findViewById(R.id.searchlistview)
        searchview = findViewById(R.id.searchview)
        searchbutton = findViewById(R.id.searchbutton_not_login)

        spinner_search = findViewById(R.id.spinner_search)


        var spinnertext = arrayOf("اختر نوع البحث", "عن الاسم", "عن الاختصاص")

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnertext)
        spinner_search.adapter = adapter

        ref = FirebaseDatabase.getInstance().getReference("Users").child("Doctor")

        spinner_search.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {


            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                when {


                    p2 == 1 -> {

                        searchbutton.setOnClickListener {

                            var pro = ProgressDialog.show(this@Not_login_search," ","جاري البحث ...",true)


                            pro.show()



                            var textsearch = searchview.text.toString()

                            var fireQuery = ref.orderByChild("doctor_name").startAt(textsearch)
                                .endAt(textsearch + "\uf8ff")
                            fireQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {


                                }


                                override fun onDataChange(p0: DataSnapshot) {
                                    doctorlist.clear()
                                    if (p0.exists()){

                                        for (messageSnapshot in p0.children) {

                                            val name = messageSnapshot.getValue(DoctorData::class.java)

                                            doctorlist.add(name!!)

                                            var adapter = Doctor_adapter(this@Not_login_search, doctorlist)
                                            searchlistview.adapter = adapter
                                            pro.dismiss()

                                        }


                                    }else{

                                        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.KITKAT){

                                            pro.dismiss()

                                            val builder = AlertDialog.Builder(this@Not_login_search)
                                            with(builder) {
                                                setTitle("عملية البحث")
                                                setMessage("لا يوجد ما تبحث عنه")


                                                setIcon(resources.getDrawable(R.drawable.ic_error_black_24dp, theme))
                                            }
                                            val alertDialog = builder.create()
                                            alertDialog.show()

                                        }else{

                                            pro.dismiss()

                                            val builder = AlertDialog.Builder(this@Not_login_search)
                                            with(builder) {
                                                setTitle("عملية البحث")
                                                setMessage("لا يوجد ما تبحث عنه")

                                               setIcon(android.R.drawable.stat_notify_error)
                                            }
                                            val alertDialog = builder.create()
                                            alertDialog.show()
                                        }

                                    }

                                }

                            })


                        }

                    }

                    p2 == 2 -> {

                        searchbutton.setOnClickListener {

                            var pro = ProgressDialog.show(this@Not_login_search," ","جاري البحث ...",true)


                            pro.show()



                            var textsearch = searchview.text.toString()

                            var fireQuery = ref.orderByChild("doctor_department").startAt(textsearch)
                                .endAt(textsearch + "\uf8ff")
                            fireQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {


                                }


                                override fun onDataChange(p0: DataSnapshot) {
                                    doctorlist.clear()
                                    if (p0.exists()){

                                        for (messageSnapshot in p0.children) {

                                            val name = messageSnapshot.getValue(DoctorData::class.java)

                                            doctorlist.add(name!!)

                                            var adapter = Doctor_adapter(this@Not_login_search, doctorlist)
                                            searchlistview.adapter = adapter
                                            pro.dismiss()

                                        }


                                    }else{

                                        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.KITKAT){

                                            pro.dismiss()

                                            val builder = AlertDialog.Builder(this@Not_login_search)
                                            with(builder) {
                                                setTitle("عملية البحث")
                                                setMessage("لا يوجد ما تبحث عنه")


                                                setIcon(resources.getDrawable(R.drawable.ic_error_black_24dp, theme))
                                            }
                                            val alertDialog = builder.create()
                                            alertDialog.show()

                                        }else{

                                            pro.dismiss()

                                            val builder = AlertDialog.Builder(this@Not_login_search)
                                            with(builder) {
                                                setTitle("عملية البحث")
                                                setMessage("لا يوجد ما تبحث عنه")

                                                setIcon(android.R.drawable.stat_notify_error)
                                            }
                                            val alertDialog = builder.create()
                                            alertDialog.show()
                                        }
                                    }

                                }

                            })


                        }

                    }



                }
            }


        }
    }

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

        drow_search_not_login.closeDrawer(Gravity.START)
    }

    override fun onBackPressed() {
        if (drow_search_not_login.isDrawerOpen(GravityCompat.START)) {

            closenavigation()
        }
        else{
            super.onBackPressed()

        }

    }
}
