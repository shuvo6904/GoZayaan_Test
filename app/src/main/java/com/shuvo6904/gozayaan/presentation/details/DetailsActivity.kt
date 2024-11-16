package com.shuvo6904.gozayaan.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.shuvo6904.gozayaan.R
import com.shuvo6904.gozayaan.data.model.RecommendedItem
import com.shuvo6904.gozayaan.databinding.ActivityDetailsBinding
import com.shuvo6904.gozayaan.databinding.ItemImageCarouselBinding
import com.shuvo6904.gozayaan.utils.Constants
import com.shuvo6904.gozayaan.utils.Extensions.getParcelableExtraCompat
import com.shuvo6904.gozayaan.utils.Extensions.toCapitalized
import com.shuvo6904.gozayaan.utils.Extensions.toCurrencySign
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initListeners()
    }

    private fun initViews() {
        val recommendedIem =
            intent?.getParcelableExtraCompat<RecommendedItem>(Constants.RECOMMENDED_ITEM)
        binding.apply {
            recommendedIem?.let { data ->
                setupImageCarousel(data.detailsImages)
                propertyName.text = data.propertyName ?: ""
                rating.text = data.rating?.toString() ?: "0.0"
                location.text = data.location ?: ""
                description.text = data.description ?: ""
                val currencySign = data.currency?.toCurrencySign() ?: ""
                val fare = data.fare?.toString() ?: "0.0"
                fareWithCurrency.text =
                    getString(R.string.fare_with_currency_sign, currencySign, fare)
                fateUnit.text = getString(R.string.per_day, data.fareUnit?.toCapitalized() ?: "")
            }
        }
    }

    private fun setupImageCarousel(detailsImages: List<String?>?) {
        binding.imageCarousel.apply {
            carouselListener = object : CarouselListener {
                override fun onCreateViewHolder(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup
                ): ViewBinding {
                    return ItemImageCarouselBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                }

                override fun onBindViewHolder(
                    binding: ViewBinding,
                    item: CarouselItem,
                    position: Int
                ) {
                    val currentBinding = binding as ItemImageCarouselBinding
                    currentBinding.image.apply {
                        setImage(item)
                        scaleType = ImageView.ScaleType.FIT_XY
                    }
                }
            }
            registerLifecycle(this@DetailsActivity)
            setIndicator(binding.customIndicator)

            val carouselList = mutableListOf<CarouselItem>()
            detailsImages?.forEach {
                carouselList.add(CarouselItem(it))
            }
            binding.imageCarousel.setData(carouselList)
        }
    }

    private fun initListeners() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }

            bookNowButton.setOnClickListener {
                // Book Now Click Functionality
            }
        }
    }
}