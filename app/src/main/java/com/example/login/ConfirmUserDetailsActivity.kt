package com.example.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.login.databinding.ActivityConfirmBinding

class ConfirmUserDetailsActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding : ActivityConfirmBinding
    private lateinit var sharedPreferences: SharedPreferences

    private var username: String = ""
    private var firstname: String = ""
    private var lastname: String = ""
    private var genderIndex: Int = -1
    private var age: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE)

        if(intent !=null) {
            username = intent.getStringExtra("username")!!
            firstname = intent.getStringExtra("firstname")!!
            lastname = intent.getStringExtra("lastname")!!
            genderIndex = intent.getIntExtra("genderIndex", 2)
            age = intent.getStringExtra("age")!!
            password = intent.getStringExtra("password")!!
        }
        when(genderIndex){
            (0)-> {
                binding.txtSex.text = "Male"
            }
            (1)->{
                binding.txtSex.text = "Female"
            }
            (2)->{
                binding.txtSex.text="Prefer not to say"
            }
        }

        binding.txtUserName.text=username
        binding.txtName.text=String.format("%s %s",firstname,lastname)
        binding.txtAge.text=age
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_confirm) -> {
                val editor = sharedPreferences.edit()
                editor.putString(USER_NAME, username)
                editor.putString(USER_PASSWORD, password)
                editor.putString(USER_FIRST_NAME, firstname)
                editor.putString(USER_LAST_NAME, lastname)
                editor.putString(USER_AGE, age)
                editor.putInt(USER_GENDER, genderIndex)
                editor.apply()

            }
            (R.id.btn_redo)->{
                finish()
            }
        }
    }
}