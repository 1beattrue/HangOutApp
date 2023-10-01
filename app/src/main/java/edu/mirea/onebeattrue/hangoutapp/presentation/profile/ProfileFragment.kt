package edu.mirea.onebeattrue.hangoutapp.presentation.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentProfileBinding
import edu.mirea.onebeattrue.hangoutapp.presentation.HangOutApplication
import edu.mirea.onebeattrue.hangoutapp.presentation.ViewModelFactory
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.AuthViewModel
import javax.inject.Inject

class ProfileFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as HangOutApplication).component
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding = null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            authViewModel.logOut()
            launchLoginFragment()
        }
        Log.d("TAG", authViewModel.currentUser?.displayName.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchLoginFragment() {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
    }
}