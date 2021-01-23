package org.codeforiraq.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_about_app.*
import kotlinx.android.synthetic.main.custom_tablayout.*

class About_app : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)


        setSupportActionBar(toolbar_main)
        supportActionBar!!.title="عن التطبيق"

        textaboutapp.text = "تطبيق يتيح للمستخدم الاستدلال و معرفة عناوين الاطباء في المحافظات و يمكن اضافة الاطباء عن طريق المستخدم العادي"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menuback, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.back -> {
                onBackPressed()
                this.finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
