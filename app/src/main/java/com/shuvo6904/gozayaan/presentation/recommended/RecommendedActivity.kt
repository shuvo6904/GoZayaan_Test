package com.shuvo6904.gozayaan.presentation.recommended

import GridSpacingItemDecoration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shuvo6904.gozayaan.R
import com.shuvo6904.gozayaan.data.UiState
import com.shuvo6904.gozayaan.databinding.ActivityRecommendedBinding
import com.shuvo6904.gozayaan.presentation.details.DetailsActivity
import com.shuvo6904.gozayaan.presentation.home.HomeViewModel
import com.shuvo6904.gozayaan.presentation.recommended.adapter.RecommendedAdapter
import com.shuvo6904.gozayaan.utils.Constants
import com.shuvo6904.gozayaan.utils.Extensions.openActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecommendedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendedBinding
    private val viewModel: RecommendedViewModel by viewModels()
    private val recommendedAdapter by lazy {
        RecommendedAdapter { item ->
            openActivity<DetailsActivity> {
                putExtra(Constants.RECOMMENDED_ITEM, item)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservers()
        fetchData()
        initListeners()
    }

    private fun initViews() {
        val itemSpacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        binding.recommendedRV.apply {
            addItemDecoration(GridSpacingItemDecoration(2, itemSpacing, false))
            adapter = recommendedAdapter
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.uiStateRecommendedLocation.collect {
                when(it) {
                    is UiState.Loading -> {
                        binding.progressBar.show()
                    }
                    is UiState.Success -> {
                        binding.progressBar.hide()
                        recommendedAdapter.submitList(it.data)
                    }
                    is UiState.Error -> {
                        binding.progressBar.hide()
                        Toast.makeText(this@RecommendedActivity, it.errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun fetchData() {
        viewModel.getRecommendedLocation()
    }

    private fun initListeners() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}