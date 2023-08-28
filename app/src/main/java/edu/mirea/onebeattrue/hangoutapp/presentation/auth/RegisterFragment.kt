package edu.mirea.onebeattrue.hangoutapp.presentation.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentRegisterBinding
import edu.mirea.onebeattrue.hangoutapp.di.DaggerComponent
import javax.inject.Inject

class RegisterFragment : Fragment() {
//    private val viewModel: AuthViewModel by lazy {
//        ViewModelProvider(this)[AuthViewModel::class.java]
//    }
    @Inject
    lateinit var viewModel: AuthViewModel
    private val component = DaggerComponent.create()

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = _binding ?: throw RuntimeException("FragmentRegisterBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)

        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        addTextChangedListeners()
        observeViewModel()

        binding.btnRegister.setOnClickListener {
            viewModel.signUp(
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
        with(viewModel) {
            shouldFinishAuthorization.observe(viewLifecycleOwner) {
                launchLoginFragment()
            }
            authError.observe(viewLifecycleOwner) { message ->
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addTextChangedListeners() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputEmail()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputPassword()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputUsername()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun launchLoginFragment() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }
}