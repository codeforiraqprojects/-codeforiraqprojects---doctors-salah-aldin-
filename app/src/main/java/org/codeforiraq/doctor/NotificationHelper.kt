package org.codeforiraq.doctor

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created by hussienalrubaye on 9/30/17.
 */
@RequiresApi(Build.VERSION_CODES.O)
class  NotificationHelper(conext:Context): ContextWrapper(conext) {

    val  manager:NotificationManager by lazy{
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {
        //first channel
        val chan1= NotificationChannel(FIRST_CHANNEL,"First channel",NotificationManager.IMPORTANCE_DEFAULT)
        chan1.lightColor=Color.GREEN
        chan1.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        manager.createNotificationChannel(chan1)




    }


    fun  getNotification1(title:String,body:String):Notification.Builder{

        return Notification.Builder(applicationContext,FIRST_CHANNEL)
            .setContentText(body)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setAutoCancel(true)
    }



    fun Notify(id:Int,notification: Notification.Builder){
        manager.notify(id,notification.build())
    }


    companion object {
        val FIRST_CHANNEL="first"
        val SECOND_CHANNEL="second"
        val THIRD_CHANNEL="third"
    }
}