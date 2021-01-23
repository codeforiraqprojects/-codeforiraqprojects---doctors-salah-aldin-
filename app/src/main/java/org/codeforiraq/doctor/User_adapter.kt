package org.codeforiraq.doctor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.listview_de_doctor.view.*

class User_adapter (context:Context, var playlist:MutableList<DoctorData>)
    :ArrayAdapter<DoctorData> (context,0,playlist){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view =LayoutInflater.from(context).inflate(R.layout.listview_de_doctor_for_user,parent,false)
        var database : DoctorData? = playlist[position]

        var doctor_name = database!!.doctor_name
        var doctor_addres = database.doctor_addres
        var doctor_phone = database.doctor_phonenum

        view.doctor_name_listview.text = "الاسم: $doctor_name"
        view.doctor_addres_listview.text= "العنوان: $doctor_addres"
        view.doctor_phone_listview.text = "رقم الهاتف: $doctor_phone"

        Glide.with(context).load(database.image_doctor).into(view.personal_image_show)

        view.doctor_info_listview.setOnClickListener {

            var intent = Intent(this.context, Not_login_info_show::class.java)

            intent.putExtra("name", database.doctor_name)
            intent.putExtra("depart", database.doctor_department)
            intent.putExtra("addres", database.doctor_addres)
            intent.putExtra("phone", database.doctor_phonenum)
            intent.putExtra("image", database.image_doctor)
            intent.putExtra("signnumber",database.doctor_signup_num)
            intent.putExtra("mohafada",database.mohafada)
            intent.putExtra("city",database.city)



            context.startActivity(intent)

        }

        return view
    }
}