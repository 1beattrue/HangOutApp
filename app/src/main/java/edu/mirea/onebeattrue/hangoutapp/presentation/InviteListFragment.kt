package edu.mirea.onebeattrue.hangoutapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentInviteListBinding

class InviteListFragment: Fragment() {
    private var _binding: FragmentInviteListBinding? = null
    private val binding: FragmentInviteListBinding
        get() = _binding ?: throw RuntimeException("FragmentInviteListBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInviteListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}