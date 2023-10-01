package edu.mirea.onebeattrue.hangoutapp.presentation.auth

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import edu.mirea.onebeattrue.hangoutapp.data.Resource
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentRegisterBinding
import edu.mirea.onebeattrue.hangoutapp.presentation.HangOutApplication
import edu.mirea.onebeattrue.hangoutapp.presentation.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as HangOutApplication).component
    }

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = _binding ?: throw RuntimeException("FragmentRegisterBinding = null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = authViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        addTextChangedListeners()
        observeViewModel()

        binding.btnRegister.setOnClickListener {
            authViewModel.signup(
                username = binding.etUsername.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }

        binding.tvRegisterToLogin.setOnClickListener {
            launchLoginFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                authViewModel.signupFlow.collect {
                    binding.progressBar.visibility = View.GONE
                    binding.btnRegister.isEnabled = true
                    when (it) {
                        is Resource.Failure -> {
                            Toast.makeText(
                                requireActivity(),
                                it.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.btnRegister.isEnabled = false
                        }

                        is Resource.Success -> {
                            launchLoginFragment()
                        }

                        null -> {
                        }
                    }
                }
            }
        }
    }

    private fun addTextChangedListeners() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                authViewModel.resetErrorInputEmail()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                authViewModel.resetErrorInputPassword()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                authViewModel.resetErrorInputUsername()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun launchLoginFragment() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }
}