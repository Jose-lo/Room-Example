package com.example.maroom.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.maroom.R
import com.example.maroom.data.local.AppDatabase
import com.example.maroom.data.local.LocalDataSource
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity
import com.example.maroom.databinding.FragmentSecondGradeBinding
import com.example.maroom.presentation.SecondScreenViewModel
import com.example.maroom.presentation.VMFactory
import com.example.maroom.repository.RepositoryImpl
import com.example.maroom.ui.adapters.SecondGradeAdapter
import com.example.maroom.utils.Resource

class SecondGradeFragment : Fragment(R.layout.fragment_second_grade), SecondGradeAdapter.OnUserClickListener,SearchView.OnQueryTextListener {

    private lateinit var binding : FragmentSecondGradeBinding
    private val viewmodel by viewModels<SecondScreenViewModel> {
        VMFactory(
            RepositoryImpl(
                LocalDataSource(AppDatabase.getDatabase(requireContext()).userDao())
            )
        )
    }

    private lateinit var secondGradeAdapter: SecondGradeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondGradeAdapter = SecondGradeAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondGradeBinding.bind(view)
        setHasOptionsMenu(true)
        binding.rvSecond.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvSecond.adapter = secondGradeAdapter
        binding.btnSecondGuardar.setOnClickListener {
            insertData()
        }

        getUserInfo()
    }
    private fun inputCheck(
        firstName: String,
        lastName: String,
        phone: String,
        español: String,
        Matematicas: String,
        Historia: String
    ): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            phone
        ) && TextUtils.isEmpty(español) && TextUtils.isEmpty(Matematicas) && TextUtils.isEmpty(Historia))
    }

    private fun insertData() {

        val name = binding.edtSecondName.text.toString()
        val email = binding.edtSecondEmail.text.toString()
        val phone = binding.edtSecondPhone.text.toString()
        val español = binding.edtSecondSpanish.text.toString()
        val matematicas = binding.edtSecondMatematicas.text.toString()
        val historia = binding.edtSecondHitoria.text.toString()

        if (inputCheck(name,email,phone,español,matematicas,historia)){

            val user = UserSecondEntity(0,
                "2",
                name,
                email,
                phone,
                español,
                matematicas,
                historia,
                "",
                "",
                0.0)
            viewmodel.insertSecondGradeUser(user)
            binding.edtSecondName.setText("")
            binding.edtSecondEmail.setText("")
            binding.edtSecondPhone.setText("")
            binding.edtSecondSpanish.setText("")
            binding.edtSecondMatematicas.setText("")
            binding.edtSecondHitoria.setText("")

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun getUserInfo() {
        viewmodel.fetchSecondGradeList().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    secondGradeAdapter.setProductList(it.data)
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Hubo error: ${it.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onUserClick(user: UserSecondEntity, position: Int) {
        viewmodel.deleteSecondGradeUser(user)
    }

    override fun onUSerUpdateClick(user: UserSecondEntity) {
        val action = SecondGradeFragmentDirections.actionSecondGradeFragmentToDetailSecondFragment(
            user.id,
            user.grade,
            user.name,
            user.email,
            user.phone,
            user.español,
            user.matematicas,
            user.Historia,
            user.geografia,
            user.Ingles,
            user.promedio.toLong()
        )
        findNavController().navigate(action)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.main_menu,menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchview = search?.actionView as? SearchView
        searchview?.isSubmitButtonEnabled = true
        searchview?.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query"

        viewmodel.searchSeconGradeDatabase(searchQuery).observe(viewLifecycleOwner, Observer {
            it.let {
                secondGradeAdapter.setProductList(it)
            }
        })
    }
}