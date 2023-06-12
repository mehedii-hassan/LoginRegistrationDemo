package com.example.petpatandroidapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.petpatandroidapp.R
import com.example.petpatandroidapp.databinding.FragmentLoginBinding
import com.example.petpatandroidapp.models.response.BaseResponse
import com.example.petpatandroidapp.models.response.LoginResponseModel
import com.example.petpatandroidapp.utils.SessionManager
import com.example.petpatandroidapp.viewmodels.LoginViewmodel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewmodel by viewModels<LoginViewmodel>()
    var token = SessionManager.getToken(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)


        if (!token.isNullOrBlank()) {
            Navigation.findNavController(binding.root).navigate(R.id.registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            doLogin()
        }

        viewmodel.loginResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }



        return binding.root
    }

    fun doLogin() {
        val phone = binding.etPhoneNumber.text.toString()
        val pwd = binding.etPassword.text.toString()
        viewmodel.userLogin(phone, pwd)
        Toast.makeText(requireContext(), "successfully logged in", Toast.LENGTH_LONG).show()

    }

    fun showLoading() {
        binding.progressBarId.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.progressBarId.visibility = View.GONE
    }

    fun processLogin(data: LoginResponseModel?) {
        showToast("Success:" + data?.message)
        if (!data?.data?.access_token.isNullOrEmpty()) {
            data?.data?.access_token?.let { SessionManager.saveAuthToken(requireContext(), it) }
            navigateToHome()
        }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToHome() {
        /*
            val intent = Intent(requireContext(), LogoutActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)*/

    }

    /* private fun userLogin() {
         val email: String = binding.etEmail.text.toString().trim()
         val password: String = binding.etPassword.text.toString().trim()

         //check email is empty or not
         if (email.isEmpty()) {
             Toast.makeText(requireContext(), "Enter your email pls !", Toast.LENGTH_LONG).show()
             return
         }
         //check valid email  or not
         if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
             Toast.makeText(requireContext(), "Please enter a valid  Email", Toast.LENGTH_LONG)
                 .show();
             return
         }
         //check password is empty or not
         if (password.isEmpty()) {
             Toast.makeText(requireContext(), "Enter your password pls !", Toast.LENGTH_LONG).show()
             return
         }
         //check password length less then 6  or not
         if (password.length < 6) {
             Toast.makeText(
                 requireContext(),
                 "Password length should be at least 6",
                 Toast.LENGTH_LONG
             ).show()
             return
         }

         auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

             if (it.isSuccessful) {

                 Toast.makeText(requireContext(), "Successfully Login", Toast.LENGTH_SHORT).show()
             } else {
                 Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()

             }
         }

     }*/

}