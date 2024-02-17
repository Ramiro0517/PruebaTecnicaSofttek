package com.example.pruebatecnica.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.FragmentLoginBinding
import com.example.pruebatecnica.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        observer()
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
    }

    private fun initialize(view:View) {

        navController =Navigation.findNavController(view)

        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                binding.username.error = "Ingrese un usuario"
                binding.password.error = "Ingrese una contraseña"
            } else {
                loginViewModel.authenticate(username, password)
            }

        }

    }
    private fun observer() {
        loginViewModel.authenticationResult.observe(viewLifecycleOwner) { isAuthenticated ->
            if (isAuthenticated) {
                navController.navigate(R.id.action_loginFragment_to_movieListFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Usuario o contraseña incorrecta",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}