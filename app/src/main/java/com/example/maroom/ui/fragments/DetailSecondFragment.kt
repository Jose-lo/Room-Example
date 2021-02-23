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
import com.example.maroom.databinding.FragmentDetailSecondBinding
import com.example.maroom.presentation.DetailSecondScreenViewModel
import com.example.maroom.presentation.FirstScreenViewModel
import com.example.maroom.presentation.VMFactory
import com.example.maroom.repository.RepositoryImpl

class DetailSecondFragment : Fragment(R.layout.fragment_detail_second) {

    private lateinit var binding: FragmentDetailSecondBinding
    private val args by navArgs<DetailSecondFragmentArgs>()
    private val viewmodel by viewModels<DetailSecondScreenViewModel> {
        VMFactory(
            RepositoryImpl(
                LocalDataSource(AppDatabase.getDatabase(requireContext()).userDao())
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailSecondBinding.bind(view)
        binding.edtSecondUpdateName.setText(args.name)
        binding.edtSecondUpdateEmail.setText(args.email)
        binding.edtSecondUpdatePhone.setText(args.phone)
        binding.edtSecondUpdateSpanish.setText(args.espaOl)
        binding.edtSecondUpdateMatematicas.setText(args.matematicas)
        binding.edtSecondUpdateHitoria.setText(args.historia)

        binding.btnSecondGuardar.setOnClickListener {
            updateInfo()
        }
    }
    private fun inputCheck(
        firstName: String,
        lastName: String,
        phone: String,
        español: String,
        Matematicas: String,
        historia: String
    ): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            phone
        ) && TextUtils.isEmpty(español) && TextUtils.isEmpty(Matematicas) && TextUtils.isEmpty(historia))
    }

    private fun updateInfo(){
        val name = binding.edtSecondUpdateName.text.toString()
        val email = binding.edtSecondUpdateEmail.text.toString()
        val phone = binding.edtSecondUpdatePhone.text.toString()
        val español = binding.edtSecondUpdateSpanish.text.toString()
        val matematicas = binding.edtSecondUpdateMatematicas.text.toString()
        val historia = binding.edtSecondUpdateHitoria.text.toString()


        if (inputCheck(name,email,phone,español,matematicas,historia)){

            val user = UserSecondEntity(args.id,
                "1",
                name,
                email,
                phone,
                español,
                matematicas,
                historia,
                "",
                "",
                0.0)
            viewmodel.updateSeconGradeUSer(user)
            findNavController().navigate(R.id.action_detailSecondFragment_to_secondGradeFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }
}