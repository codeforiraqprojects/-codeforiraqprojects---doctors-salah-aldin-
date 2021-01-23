package org.codeforiraq.doctor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_seplash_screen.*
import org.codeforiraq.doctor.Fragments.*

class SeplashScreen : AppCompatActivity() {

    lateinit var sp :SharedPreferences
    var myAuth =FirebaseAuth.getInstance()

    lateinit var viewpager : ViewPager

     var fragment1 = FirstScreen()
     var fragment2 = FirstScreen2()
     var fragment3 = FirstScreen3()
     var fragment4 = FirstScreen4()

lateinit var adapter :Mypageadapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seplash_screen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            try {
                pro1.setTextColor(getColor(R.color.procolorg))
                pro2.setTextColor(getColor(R.color.procolorb))
                pro3.setTextColor(getColor(R.color.procolorb))
                pro4.setTextColor(getColor(R.color.procolorb))

            } catch (Ex: Exception) {


            }

            sp = getSharedPreferences("notShow", Context.MODE_PRIVATE)

            if (myAuth.currentUser != null) {
                if (!sp.getBoolean("notshow", true)) {
                    var intent = Intent(this@SeplashScreen, Show_for_doctor::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {

                if (!sp.getBoolean("notshow", true)) {
                    var intent = Intent(this@SeplashScreen, Show_for_user::class.java)
                    startActivity(intent)
                    finish()
                }

            }

            //شيريد بريفرنسس حتى يتم حفظ انه تم راية الصفحة وتحويله مباشرتا الى صفحة العرض


            skip.setOnClickListener {

                var intent = Intent(this@SeplashScreen, Show_for_user::class.java)
                startActivity(intent)
                var spedit = sp.edit()

                spedit.putBoolean("notshow", false)
                spedit.apply()
                finish()
            }
            viewpager = findViewById(R.id.viewpager)

            //عرض البيانات في الفراكمنت
            fragment1.settitle_and_image("مرحبا بكم في تطبيق", "دليل اطباء صلاح الدين", "")

            fragment2.settitle_and_image(
                "يستطيع الطبيب اضافة بياناته",
                "وايضا يستطيع اضافة رقم تسجيل الاجازة",
                ""
            )

            fragment3.settitle_and_image(
                "يستطيع المواطن تصفح المعلومات",
                "ويستطيع ايضا اضافة بيانات اطباء يعرفهم",
                ""
            )

            fragment4.settitle_and_image(
                "هذا التطبيق من اعداد",
                "البرمجة من اجل العراق",
                "اعداد فريق صلاح الدين"
            )


            adapter = Mypageadapter(supportFragmentManager)

            adapter.list.add(fragment1)
            adapter.list.add(fragment2)
            adapter.list.add(fragment3)
            adapter.list.add(fragment4)

            viewpager.adapter = adapter

            next.setOnClickListener {
                viewpager.currentItem++
            }

            viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {


                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                @RequiresApi(Build.VERSION_CODES.M)
                override fun onPageSelected(position: Int) {
                    if (position == adapter.list.size - 1) {
                        //last page
                        next.text = "انتهى"

                        next.setOnClickListener {
                            var intent = Intent(this@SeplashScreen, Show_for_user::class.java)
                            startActivity(intent)

                            finish()
                            var spedit = sp.edit()

                            spedit.putBoolean("notshow", false)
                            spedit.apply()

                        }
                    } else {
                        //has next

                        next.text = "التالي"
                        next.setOnClickListener {
                            viewpager.currentItem++
                        }
                    }

                    try {


                        when (viewpager.currentItem) {

                            0 -> {
                                pro1.setTextColor(getColor(R.color.procolorg))
                                pro2.setTextColor(getColor(R.color.procolorb))
                                pro3.setTextColor(getColor(R.color.procolorb))
                                pro4.setTextColor(getColor(R.color.procolorb))
                            }
                            1 -> {
                                pro1.setTextColor(getColor(R.color.procolorb))
                                pro2.setTextColor(getColor(R.color.procolorg))
                                pro3.setTextColor(getColor(R.color.procolorb))
                                pro4.setTextColor(getColor(R.color.procolorb))
                            }

                            2 -> {
                                pro1.setTextColor(getColor(R.color.procolorb))
                                pro2.setTextColor(getColor(R.color.procolorb))
                                pro3.setTextColor(getColor(R.color.procolorg))
                                pro4.setTextColor(getColor(R.color.procolorb))
                            }
                            3 -> {
                                pro1.setTextColor(getColor(R.color.procolorb))
                                pro2.setTextColor(getColor(R.color.procolorb))
                                pro3.setTextColor(getColor(R.color.procolorb))
                                pro4.setTextColor(getColor(R.color.procolorg))
                            }
                        }


                    } catch (Ex: Exception) {


                    }

                }


            })
        }

        else{

            var intent = Intent(this,Show_for_user::class.java)
            startActivity(intent)
            this.finish()
        }
    }
    }




class Mypageadapter (manager:FragmentManager):FragmentPagerAdapter(manager){

    var list : MutableList<Fragment> = ArrayList()
    override fun getItem(position: Int): Fragment {

return list[position]

    }

    override fun getCount(): Int {

return list.size

    }


}
