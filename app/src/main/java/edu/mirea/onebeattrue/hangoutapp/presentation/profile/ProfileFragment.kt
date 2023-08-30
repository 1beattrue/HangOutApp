package edu.mirea.onebeattrue.hangoutapp.presentation.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentProfileBinding
import edu.mirea.onebeattrue.hangoutapp.di.DaggerComponent
import edu.mirea.onebeattrue.hangoutapp.presentation.ViewModelFactory
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.AuthViewModel
import javax.inject.Inject

class ProfileFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    private val component = DaggerComponent.create()

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        Log.d("PROFILE", authViewModel.currentUser?.email.toString() + " " + authViewModel.currentUser?.displayName.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}