package com.shuvo6904.gozayaan.presentation.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shuvo6904.gozayaan.R
import com.shuvo6904.gozayaan.data.model.RecommendedItem
import com.shuvo6904.gozayaan.databinding.ActivityDetailsBinding
import com.shuvo6904.gozayaan.utils.Constants
import com.shuvo6904.gozayaan.utils.Extensions.getParcelableExtraCompat

class DetailsActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initListeners()
    }

    private fun initViews() {
        val recommendedIem = intent?.getParcelableExtraCompat<RecommendedItem>(Constants.RECOMMENDED_ITEM)
        binding.apply {
            recommendedIem?.let {

            }
        }
    }

    private fun initListeners() {

    }
}