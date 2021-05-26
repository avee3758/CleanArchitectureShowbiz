package com.example.cleanarchitecture.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Showbiz>("showbiz")
        detailMovie?.let { populateDetail(it) }
        binding.detailBack.setOnClickListener { onBackPressed() }

    }

    private fun populateDetail(showbiz: Showbiz) {
        with(binding) {
            detailMovieName.text = showbiz.title
            detailMovieDesc.text = showbiz.overview
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, showbiz.posterPath))
                .into(detailPoster)

            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, showbiz.posterPath))
                .into(detailImage)

            var favoriteState = showbiz.favorite
            setFavoriteState(favoriteState)
            binding.detailFavBtn.setOnClickListener {
                favoriteState = !favoriteState
                detailViewModel.setFavoriteMovie(showbiz, favoriteState)
                setFavoriteState(favoriteState)
            }
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.detailFavBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.detailFavBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite
                )
            )
        }
    }
}