package com.dogood.tamilblogs.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.dogood.tamilblogs.R
import com.dogood.tamilblogs.databinding.FragmentLoginBinding
import com.dogood.tamilblogs.model.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private val viewModel:LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding=FragmentLoginBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewmodel=viewModel

        viewModel.isClickedLogin.observe(this, Observer {
            if(it){
                binding.pBar.visibility=View.VISIBLE
            }else{
                binding.pBar.visibility=View.GONE
            }
        })

        viewModel.isLoggedIn.observe(this, Observer {
            if(it){
                LoggingSuccess()
            }
        })
        return binding.root
    }
    private fun LoggingSuccess(){
        val action=LoginFragmentDirections.actionLoginFragmentToHomeFragment(viewModel.session_id_value.toString())
        findNavController().navigate(action)
    }
}