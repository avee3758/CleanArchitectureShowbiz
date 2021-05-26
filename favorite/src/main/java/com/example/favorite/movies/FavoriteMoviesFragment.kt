package com.example.favorite.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.ui.ShowbizAdapter
import com.example.cleanarchitecture.core.utils.Queries
import com.example.cleanarchitecture.detail.DetailActivity
import com.example.favorite.FavoriteViewModel
import com.example.favorite.databinding.FragmentFavoriteMoviesBinding

import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {

    private var favoriteMoviesBinding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = favoriteMoviesBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteMoviesBinding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var showbizAdapter: ShowbizAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var sort = Queries.NEWEST

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showbizAdapter = ShowbizAdapter()

        binding.favmoviesProgressBar.visibility = View.VISIBLE
        binding.notFoundText.visibility = View.GONE
        setList(sort)

        with(binding.favmoviesRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = showbizAdapter
        }

        showbizAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("showbiz", selectedData)
            startActivity(intent)
        }
    }


    private fun setList(sort: String) {
        favoriteViewModel.getFavoriteMovies(sort).observe(viewLifecycleOwner, showbizObserver)
    }

    private val showbizObserver = Observer<List<Showbiz>> { showbiz ->
        if (showbiz.isNullOrEmpty()) {
            binding.favmoviesProgressBar.visibility = View.GONE
            binding.notFoundText.visibility = View.VISIBLE
        } else {
            binding.favmoviesProgressBar.visibility = View.GONE
            binding.notFoundText.visibility = View.GONE
        }
        showbizAdapter.setData(showbiz)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteMoviesBinding = null
    }
}