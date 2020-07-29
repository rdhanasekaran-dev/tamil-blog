package com.dogood.tamilblogs.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.dogood.tamilblogs.R
import com.dogood.tamilblogs.databinding.FragmentHomeBinding
import com.dogood.tamilblogs.model.LoginViewModel
import com.dogood.tamilblogs.model.UserViewModel

class HomeFragment : Fragment() {

    private val viewModel:UserViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val args=HomeFragmentArgs.fromBundle(arguments!!)

        val binding=FragmentHomeBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewmodel=viewModel

        viewModel.session_id=args.sessionId

        viewModel.getDetails()

        binding.showMovies.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToDiscoverFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}