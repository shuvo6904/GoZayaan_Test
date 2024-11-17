package com.shuvo6904.gozayaan.presentation.home.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shuvo6904.gozayaan.data.UiState
import com.shuvo6904.gozayaan.databinding.FragmentHomeBinding
import com.shuvo6904.gozayaan.presentation.details.DetailsActivity
import com.shuvo6904.gozayaan.presentation.home.HomeViewModel
import com.shuvo6904.gozayaan.presentation.home.adapter.HomeRecommendedAdapter
import com.shuvo6904.gozayaan.presentation.home.adapter.PopularCategoriesAdapter
import com.shuvo6904.gozayaan.presentation.recommended.adapter.RecommendedAdapter
import com.shuvo6904.gozayaan.presentation.recommended.RecommendedActivity
import com.shuvo6904.gozayaan.utils.Constants
import com.shuvo6904.gozayaan.utils.Extensions.openActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val recommendedAdapter by lazy {
        HomeRecommendedAdapter{ item ->
            context?.let { context ->
                context.openActivity<DetailsActivity> {
                    putExtra(Constants.RECOMMENDED_ITEM, item)
                }
            }
        }
    }
    private val popularCategoriesAdapter by lazy {
        PopularCategoriesAdapter{ item ->
            // Handle Popular Category item click
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
        fetchData()
        initListeners()
    }

    private fun initViews() {
        binding.apply {
            recommendedRV.adapter = recommendedAdapter
            popularCategoriesRV.adapter = popularCategoriesAdapter
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiStateRecommendedLocation.collect {
                when(it) {
                    is UiState.Loading -> {

                    }
                    is UiState.Success -> {
                        recommendedAdapter.submitList(it.data)
                    }
                    is UiState.Error -> {

                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiStatePopularCategories.collect {
                when(it) {
                    is UiState.Loading -> {

                    }
                    is UiState.Success -> {
                        popularCategoriesAdapter.submitList(it.data)
                    }
                    is UiState.Error -> {

                    }
                }
            }
        }
    }

    private fun fetchData() {
        viewModel.getRecommendedLocation()
        viewModel.getPopularCategories()
    }

    private fun initListeners() {
        binding.seeAll.setOnClickListener {
            context?.openActivity<RecommendedActivity> {  }
        }
    }
}