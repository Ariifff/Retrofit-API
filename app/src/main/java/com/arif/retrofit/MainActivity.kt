package com.arif.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arif.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val baseUrl= "https://jsonplaceholder.typicode.com/"

    var postList = ArrayList<Posts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showposts()

        }

    fun showposts(){
        val retrofit= Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI : RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call : Call<List<Posts>> =retrofitAPI.getallPosts()

        call.enqueue(object : Callback<List<Posts>>{
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if(!response.isSuccessful){
                    binding.textViewid.text="error"
                    binding.textViewtitle.text="error"
                    binding.textViewuserid.text="error"
                    binding.textViewbody.text="error"

                }

                postList=response.body() as ArrayList<Posts>

                binding.textViewid.text=postList[2].id.toString()
                binding.textViewuserid.text=postList[2].userId.toString()
                binding.textViewtitle.text=postList[2].title
                binding.textViewbody.text=postList[2].subtitle

            }

            override fun onFailure(p0: Call<List<Posts>>, t: Throwable) {

                Toast.makeText(applicationContext,t.localizedMessage,Toast.LENGTH_LONG).show()
            }


        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}