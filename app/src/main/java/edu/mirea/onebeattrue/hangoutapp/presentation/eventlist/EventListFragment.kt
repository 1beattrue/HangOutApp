package edu.mirea.onebeattrue.hangoutapp.presentation.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentEventListBinding

class EventListFragment: Fragment() {
    private var _binding: FragmentEventListBinding? = null
    private val binding: FragmentEventListBinding
        get() = _binding ?: throw RuntimeException("FragmentEventListBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventListBinding.inflate(layoutInflater, container, false)
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