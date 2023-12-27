package com.example.exam6.presentation.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exam6.databinding.FragmentHomeBinding
import com.example.exam6.presentation.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var buttonsRecyclerAdapter: ButtonsRecyclerAdapter

    override fun bind() {
        val data = viewModel.getButtonsData()
        buttonsRecyclerAdapter = ButtonsRecyclerAdapter()
        with(binding) {
            buttonRecycler.layoutManager = GridLayoutManager(context, 3)
            buttonsRecyclerAdapter.submitList(data)
            buttonRecycler.adapter = buttonsRecyclerAdapter
        }
    }

    override fun clickListeners() {
        checkPassword()
    }

    override fun bindObserves() {
        buttonsRecyclerAdapter.setOnItemClickListener {
            viewModel.password(it)
        }
    }

    fun checkPassword() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.passwordFlow.collect {
                    if (it == "0934") {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}