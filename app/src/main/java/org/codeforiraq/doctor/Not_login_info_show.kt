package org.codeforiraq.doctor

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_not_login_info_show.*
import kotlinx.android.synthetic.main.activity_show_doctor_info.*
import kotlinx.android.synthetic.main.activity_show_doctor_info.address_info
import kotlinx.android.synthetic.main.activity_show_doctor_info.cityinfo
import kotlinx.android.synthetic.main.activity_show_doctor_info.deprat_info
import kotlinx.android.synthetic.main.activity_show_doctor_info.doctor_image_info
import kotlinx.android.synthetic.main.activity_show_doctor_info.mohafadainfo
import kotlinx.android.synthetic.main.activity_show_doctor_info.name_info
import kotlinx.android.synthetic.main.activity_show_doctor_info.phonenumber_info
import kotlinx.android.synthetic.main.activity_show_doctor_info.phonenumber_info_call
import kotlinx.android.synthetic.main.activity_show_doctor_info.signnumberinfo
import kotlinx.android.synthetic.main.activity_show_for_doctor.*
import kotlinx.android.synthetic.main.custom_tablayout.*

class Not_login_info_show : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var myAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_login_info_show)
        setSupportActionBar(toolbar_main)

        nav_info_not_login.setNavigationItemSelectedListener(this)
        var actionToggle = ActionBarDrawerToggle(this,drow_info_not_login,toolbar_main,R.string.drawe_open,R.string.drawe_close)


        drow_info_not_login.addDrawerListener(actionToggle)

        actionToggle.syncState()

        var name =intent.extras!!.getString("name")
        var phone =intent.extras!!.getString("phone")
        var depart =intent.extras!!.getString("depart")
        var image =intent.extras!!.getString("image")
        var address =intent.extras!!.getString("addres")
        var signnumber =intent.extras!!.getString("signnumber")
        var mohafada =intent.extras!!.getString("mohafada")
        var city =intent.extras!!.getString("city")




        name_info.text = "الاسم :$name"
        phonenumber_info.text = "رقم الحجز :$phone"
        deprat_info.text = "الاختصاص :$depart"
        address_info.text = "العنوان :$address"
        signnumberinfo.text = "رقم الاجازة :$signnumber"
        mohafadainfo.text = "المحافظة :$mohafada"
        cityinfo.text = "المدينة :$city"


        Glide.with(this).load(image).into(doctor_image_info)


        phonenumber_info_call.setOnClickListener {

            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            startActivity(intent)
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

        drow_info_not_login.closeDrawer(Gravity.START)
    }

    override fun onBackPressed() {
        if (drow_info_not_login.isDrawerOpen(GravityCompat.START)) {

            closenavigation()
        }
        else{
            super.onBackPressed()

        }

    }
}
