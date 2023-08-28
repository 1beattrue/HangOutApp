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
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentLoginBinding
import edu.mirea.onebeattrue.hangoutapp.di.DaggerComponent
import javax.inject.Inject

class LoginFragment : Fragment() {
//    private val viewModel: AuthViewModel by lazy {
//        ViewModelProvider(this)[AuthViewModel::class.java]
//    }
    @Inject
    lateinit var viewModel: AuthViewModel // временная неправильная реализация для проверки работы с dagger
    private val component = DaggerComponent.create()

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this) // я - убожество, не забудь убрать меня!

        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        addTextChangedListeners()
        observeViewModel()

        binding.btnLogin.setOnClickListener {
            viewModel.logIn(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }

        binding.tvLoginToRegister.setOnClickListener {
            launchRegisterFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.currentUser != null) {
            launchMainFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        with(viewModel) {
            shouldFinishAuthorization.observe(viewLifecycleOwner) {
                launchMainFragment()
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
    }

    private fun launchMainFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
    }

    private fun launchRegisterFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }
}