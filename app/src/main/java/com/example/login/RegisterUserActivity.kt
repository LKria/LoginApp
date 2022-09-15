package com.example.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import com.example.login.databinding.ActivityMainBinding

class RegisterUserActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private lateinit var binding : ActivityMainBinding

    private var genderIndex: Int =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterUser.setOnClickListener(this)
        binding.rgSex.setOnCheckedChangeListener(this)

    }

    override fun onClick(p0: View?) {

        if (binding.edtUsername.text.isEmpty()) {
            binding.edtUsername.error = "Required"
            return
        }
        if (binding.edtPassword.text.isEmpty()) {
            binding.edtPassword.error = "Required"
            return
        }
        if (binding.edtLastName.text.isEmpty()) {
            binding.edtLastName.error = "Required"
            return
        }
        if (binding.edtAge.text.isEmpty()) {
            binding.edtAge.error = "Required"
        }

        if (binding.edtPassword.text.toString() != binding.edtConfirmPassword.text.toString()) {
            binding.edtPassword.error = "Password does not match"
            binding.edtConfirmPassword.error = "Password does not match"
            return
        }

        if (genderIndex == -1) {
            Toast.makeText(this, "Please specify your sex", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, ConfirmUserDetailsActivity::class.java)
        intent.putExtra("username", binding.edtUsername.text.toString())
        intent.putExtra("password", binding.edtPassword.text.toString())
        intent.putExtra("firstname", binding.edtFirstName.text.toString())
        intent.putExtra("lastname", binding.edtLastName.text.toString())
        intent.putExtra("age", binding.edtAge.text.toString())
        intent.putExtra("gender Index", genderIndex)
        startActivity(intent)

    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val rb = binding.rgSex.findViewById<View>(p1)
        genderIndex=binding.rgSex.indexOfChild(rb)
    }

}