package edu.mirea.onebeattrue.hangoutapp.presentation.eventlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import edu.mirea.onebeattrue.hangoutapp.databinding.FragmentEventListBinding
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem
import edu.mirea.onebeattrue.hangoutapp.presentation.HangOutApplication
import edu.mirea.onebeattrue.hangoutapp.presentation.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: EventListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EventListViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as HangOutApplication).component
    }

    private var _binding: FragmentEventListBinding? = null
    private val binding: FragmentEventListBinding
        get() = _binding ?: throw RuntimeException("FragmentEventListBinding = null")

    private val adapter = EventListAdapter()

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

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
        setupRecycler()
        observeViewModel()

        binding.btnAddItem.setOnClickListener {
            viewModel.addEvent(
                EventItem(
                    "aboba", "01/01/23", "12:00", "aboba location"
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.listFlow.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun setupRecycler() {
        binding.rvEventList.adapter = adapter

        setupClickListener()
        setupSwipeListener(binding.rvEventList)
    }

    private fun setupSwipeListener(rvEventList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteEvent(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvEventList)
    }

    private fun setupClickListener() {
        adapter.onEventItemClickListener = {
            Log.d("EventListFragment", "$it")
        }
    }
}