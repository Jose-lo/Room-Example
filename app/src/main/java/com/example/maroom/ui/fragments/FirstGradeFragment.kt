package com.example.maroom.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
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
import com.example.maroom.databinding.FragmentFirstGradeBinding
import com.example.maroom.presentation.FirstScreenViewModel
import com.example.maroom.presentation.VMFactory
import com.example.maroom.repository.RepositoryImpl
import com.example.maroom.ui.adapters.SecondGradeAdapter
import com.example.maroom.ui.adapters.UserAdapter
import com.example.maroom.utils.Resource


class FirstGradeFragment : Fragment(R.layout.fragment_first_grade), UserAdapter.OnUserClickListener, SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentFirstGradeBinding
    private val viewmodel by viewModels<FirstScreenViewModel> {
        VMFactory(
            RepositoryImpl(
                LocalDataSource(AppDatabase.getDatabase(requireContext()).userDao())
            )
        )
    }

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userAdapter = UserAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstGradeBinding.bind(view)
        setHasOptionsMenu(true)
        binding.rvFirst.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvFirst.adapter = userAdapter
        binding.btnFirstGuardar.setOnClickListener {
            insertData()
        }

        getUserInfo()
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

    private fun insertData() {

        val name = binding.edtFirstName.text.toString()
        val email = binding.edtFirstEmail.text.toString()
        val phone = binding.edtFirstPhone.text.toString()
        val español = binding.edtFirstSpanish.text.toString()
        val matematicas = binding.edtFirstMatematicas.text.toString()

        if (inputCheck(name,email,phone,español,matematicas)){

            val user = UserEntity(0,
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
            viewmodel.insertUser(user)
            binding.edtFirstName.setText("")
            binding.edtFirstEmail.setText("")
            binding.edtFirstPhone.setText("")
            binding.edtFirstSpanish.setText("")
            binding.edtFirstMatematicas.setText("")

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun getUserInfo() {
        viewmodel.getUserInfo().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    userAdapter.setProductList(it.data)
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

    override fun onUserClick(user: UserEntity, position: Int) {
        viewmodel.deleteUser(user)
    }

    override fun onUSerUpdateClick(user: UserEntity) {
        val action = FirstGradeFragmentDirections.actionFirstGradeFragmentToDetailFragment(
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

        viewmodel.searchDatabase(searchQuery).observe(viewLifecycleOwner, Observer {
            it.let {
                userAdapter.setProductList(it)
            }
        })
    }
}