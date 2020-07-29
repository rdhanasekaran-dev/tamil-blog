package com.dogood.tamilblogs.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.dogood.tamilblogs.R
import com.dogood.tamilblogs.databinding.FragmentDiscoverBinding
import com.dogood.tamilblogs.model.DiscoverViewModel
import com.dogood.tamilblogs.model.UserViewModel

class DiscoverFragment : Fragment() {


    private val viewModel: DiscoverViewModel by lazy {
        ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding=FragmentDiscoverBinding.inflate(inflater)
        binding.setLifecycleOwner (this)
        binding.viewmodel=viewModel


        return binding.root
    }
}