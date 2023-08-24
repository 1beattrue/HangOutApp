package edu.mirea.onebeattrue.hangoutapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import edu.mirea.onebeattrue.hangoutapp.R
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigationMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBottomNavigationMenu() {
        val navigationView = binding.bottomNavigationView
        val fragmentContainerId = R.id.fragment_container
        val navigationController = (
                childFragmentManager.findFragmentById(fragmentContainerId) as NavHostFragment
                ).navController
        NavigationUI.setupWithNavController(navigationView, navigationController)
    }
}