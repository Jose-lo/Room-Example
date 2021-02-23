package com.example.maroom.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.maroom.R
import com.example.maroom.data.local.AppDatabase
import com.example.maroom.data.local.LocalDataSource
import com.example.maroom.data.model.UserEntity
import com.example.maroom.databinding.FragmentHomeBinding
import com.example.maroom.presentation.FirstScreenViewModel
import com.example.maroom.presentation.VMFactory
import com.example.maroom.repository.RepositoryImpl


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.firstGradeContainer.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_firstGradeFragment) }
        binding.secondGradeContainer.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_secondGradeFragment) }
    }

}