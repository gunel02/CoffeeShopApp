package com.example.coffeeshop.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityDetailBinding
import com.example.coffeeshop.databinding.ActivityItemsListBinding
import com.example.coffeeshop.databinding.ActivitySplashScreenBinding
import com.example.coffeeshop.domain.models.PopularModel
import com.example.coffeeshop.helper.ManagmentCart

class DetailActivity : AppCompatActivity() {


    lateinit var binding: ActivityDetailBinding
    private lateinit var item:PopularModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        bundle()
        initSizeList()

    }

    private fun initSizeList() {
            binding.apply {

                smallBtn.setOnClickListener{
                    smallBtn.setBackgroundResource(R.drawable.background_brown_stroke)
                    mediumBtn.setBackgroundResource(0)
                    largeBtn.setBackgroundResource(0)
                }
                mediumBtn.setOnClickListener{
                    smallBtn.setBackgroundResource(0)
                    mediumBtn.setBackgroundResource(R.drawable.background_brown_stroke)
                    largeBtn.setBackgroundResource(0)
                }
                largeBtn.setOnClickListener{
                    smallBtn.setBackgroundResource(0)
                    mediumBtn.setBackgroundResource(0)
                    largeBtn.setBackgroundResource(R.drawable.background_brown_stroke)
                }
            }
    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as PopularModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$"+item.price
            ratingTxt.text = item.rating.toString()

            addToCartBtn.setOnClickListener{
                item.numberInCart = Integer.valueOf(
                    numberInCartTxt.text.toString()
                )
                managmentCart.insertItems(item)
            }

            backBtn.setOnClickListener{finish()}

            plusBtn.setOnClickListener{
                numberInCartTxt.text = (item.numberInCart+1).toString()
                item.numberInCart++
            }

            minusBtn.setOnClickListener{
                if (item.numberInCart > 0){
                    numberInCartTxt.text = (item.numberInCart-1).toString()
                    item.numberInCart--
                }
            }
        }
    }
}