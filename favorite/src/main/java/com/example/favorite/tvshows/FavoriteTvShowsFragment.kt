package com.example.favorite.tvshows

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
import com.example.favorite.databinding.FragmentFavoriteTvShowsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowsFragment : Fragment() {

    private var favoriteTvShowsBinding: FragmentFavoriteTvShowsBinding? = null
    private val binding get() = favoriteTvShowsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteTvShowsBinding = FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var showbizAdapter: ShowbizAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var sort = Queries.NEWEST

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showbizAdapter = ShowbizAdapter()

        binding.favshowsProgressBar.visibility = View.VISIBLE
        binding.notFoundText.visibility = View.GONE
        setList(sort)

        with(binding.favshowsRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = showbizAdapter
        }

        showbizAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("showbiz", selectedData)
            startActivity(intent)
        }
    }

    private fun setList(sort: String) {
        favoriteViewModel.getFavoriteTvShows(sort).observe(viewLifecycleOwner, tvShowsObserver)
    }

    private val tvShowsObserver = Observer<List<Showbiz>> { tvShows ->
        if (tvShows.isNullOrEmpty()){
            binding.favshowsProgressBar.visibility = View.GONE
            binding.notFoundText.visibility = View.VISIBLE
        } else {
            binding.favshowsProgressBar.visibility = View.GONE
            binding.notFoundText.visibility = View.GONE
        }
        showbizAdapter.setData(tvShows)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteTvShowsBinding = null
    }
}