package com.example.maroom.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maroom.R
import com.example.maroom.data.local.AppDatabase
import com.example.maroom.data.local.LocalDataSource
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity
import com.example.maroom.databinding.FragmentDetailBinding
import com.example.maroom.presentation.DetailScreenViewModel
import com.example.maroom.presentation.VMFactory
import com.example.maroom.repository.RepositoryImpl


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding : FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewmodel by viewModels<DetailScreenViewModel> { VMFactory(RepositoryImpl(
        LocalDataSource(AppDatabase.getDatabase(requireContext()).userDao())
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        binding.edtUpdateFirstName.setText(args.name)
        binding.edtUpdateFirstEmail.setText(args.email)
        binding.edtUpdateFirstPhone.setText(args.phone)
        binding.edtUpdateFirstSpanish.setText(args.espaOl)
        binding.edtUpdateFirstMatematicas.setText(args.matematicas)

        binding.btnUpdateFirstGuardar.setOnClickListener {
            updateInfo()
        }

    }

    private fun inputCheck(
        firstName: String,
        lastName: String,
        phone: String,
        español: String,
        Matematicas: String
    ): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            phone
        ) && TextUtils.isEmpty(español) && TextUtils.isEmpty(Matematicas))
    }

    private fun updateInfo(){
        val name = binding.edtUpdateFirstName.text.toString()
        val email = binding.edtUpdateFirstEmail.text.toString()
        val phone = binding.edtUpdateFirstPhone.text.toString()
        val español = binding.edtUpdateFirstSpanish.text.toString()
        val matematicas = binding.edtUpdateFirstMatematicas.text.toString()


        if (inputCheck(name,email,phone,español,matematicas)){

                val user = UserEntity(args.id,
                    "1",
                    name,
                    email,
                    phone,
                    español,
                    matematicas,
                    "",
                    "",
                    "",
                    0.0)
                viewmodel.updateUser(user)
                findNavController().navigate(R.id.action_detailFragment_to_firstGradeFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

}