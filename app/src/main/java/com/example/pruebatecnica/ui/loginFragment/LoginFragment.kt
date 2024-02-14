package com.example.pruebatecnica.ui.loginFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private lateinit var navController: NavController
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
    }

    private fun initialize(view:View) {

        navController =Navigation.findNavController(view)
        binding.btnLogin.setOnClickListener {
            if (binding.username.text.toString().isEmpty() || binding.password.text.toString()
                    .isEmpty()
            ) {
                binding.username.error = "Ingrese un usuario"
                binding.password.error = "Ingrese una contraseña"
            } else {
                if (binding.username.text.toString() == "admin" && binding.password.text.toString() == "Password*123") {

                   navController.navigate(R.id.action_loginFragment_to_movieListFragment)

                } else {
                    Toast.makeText(requireContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}