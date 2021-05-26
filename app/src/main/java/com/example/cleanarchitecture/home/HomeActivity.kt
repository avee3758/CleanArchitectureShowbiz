package com.example.cleanarchitecture.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityHomeBinding
import com.example.cleanarchitecture.movies.MoviesFragment
import com.example.cleanarchitecture.tvshows.TvShowsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity() {
    private val className: String get() = "com.example.favorite.FavoriteFragment"
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFrag(MoviesFragment())
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_movies ->
                    setFrag(MoviesFragment())
                R.id.nav_shows ->
                    setFrag(TvShowsFragment())
                R.id.nav_fav ->
                    instantiateFragment(className)?.let { it1 -> setFrag(it1) }
            }
            true
        }
    }

    private fun instantiateFragment(className: String): Fragment? {
        return try {
            Class.forName(className).newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun setFrag(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
        }.commit()
        closeSearch()
    }

    fun closeSearch(){
        if (binding.searchView.isSearchOpen()) {
            binding.searchView.closeSearch()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        closeSearch()
    }
}