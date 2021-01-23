package org.codeforiraq.doctor

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.listview_de_doctor.view.*

class Doctor_adapter (context:Context , var playlist:MutableList<DoctorData>)
    :ArrayAdapter<DoctorData> (context,0,playlist){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view =LayoutInflater.from(context).inflate(R.layout.listview_de_doctor,parent,false)
        var database : DoctorData? = playlist[position]


        var imageview :ImageView = view.findViewById(R.id.personal_image_show)
        var doctor_name = database!!.doctor_name
        var doctor_addres = database.doctor_addres
        var doctor_phone = database.doctor_phonenum

        view.doctor_name_listview.text = "الاسم: $doctor_name"
        view.doctor_addres_listview.text= "العنوان: $doctor_addres"
        view.doctor_phone_listview.text = "رقم الهاتف: $doctor_phone"



view.doctor_edit_listview.setOnClickListener {

    update(database)

}


        view.doctor_info_listview.setOnClickListener {

            var intent = Intent(this.context, Show_doctor_info::class.java)

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

        Glide.with(context).load(database.image_doctor).into(imageview)

        return view
    }
    fun update (database1:DoctorData){

        val bulder = AlertDialog.Builder(context)
        var inflater =LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.edit_doctor,null)


        var name1 = view.findViewById<EditText>(R.id.nameedit)
        var ektesas = view.findViewById<EditText>(R.id.department_doctoredit)
        var address = view.findViewById<EditText>(R.id.addres_doctoredit)
        var phonenumber = view.findViewById<EditText>(R.id.phonenum_doctoredit)
        var signcode = view.findViewById<EditText>(R.id.signup_doctoredit)
        var city = view.findViewById<EditText>(R.id.cityedit)


        name1.setText(database1.doctor_name)
        ektesas.setText(database1.doctor_department)
        address.setText(database1.doctor_addres)
        phonenumber.setText(database1.doctor_phonenum)
        signcode.setText(database1.doctor_signup_num)
        city.setText(database1.city)

        bulder.setView(view)
        bulder.setPositiveButton("تعديل البيانات",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                var ref = FirebaseDatabase.getInstance().getReference("Users").child("Doctor")

                var name2 = name1.text.toString()
                var ektesas2 = ektesas.text.toString()
                var address2 = address.text.toString()
                var phonenumber2 = phonenumber.text.toString()
                var signcode2 = signcode.text.toString()
                var city2 = city.text.toString()


                if (name2.isEmpty()
                    && ektesas2.isEmpty()
                    && address2.isEmpty()
                    && phonenumber2.isEmpty()
                    && signcode2.isEmpty()
                    && city2.isEmpty()
                ) {

                    name1.error = "ادخل الاسم"
                    ektesas.error = "ادخل الاختصاص"
                    address.error = "ادخل العنوان"
                    phonenumber.error = "ادخل رقم هاتف الحجز"
                    signcode.error = "ادخل رقم الاجازة"
                    city.error = "ادخل المدينة"



                    return
                }


                var data =DoctorData(database1.doctor_id, name2,ektesas2 ,address2, phonenumber2,signcode2,city2 , database1.mohafada,
                    database1.image_doctor,database1.typeofuser)
                ref.child(database1.doctor_id).setValue(data)

                Toast.makeText(context,"تم تعديل البيانات ...", Toast.LENGTH_LONG).show()


            }
        })

        bulder.setNegativeButton("الغاء",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {


            }
        })

        val alert = bulder.create()
        alert.show()



    }
}