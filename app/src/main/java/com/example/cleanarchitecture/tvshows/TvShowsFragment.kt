package com.example.cleanarchitecture.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.core.data.Resource
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.ui.ShowbizAdapter
import com.example.cleanarchitecture.core.utils.Queries
import com.example.cleanarchitecture.databinding.ShowsFragmentBinding
import com.example.cleanarchitecture.detail.DetailActivity
import com.example.cleanarchitecture.home.HomeActivity
import com.example.cleanarchitecture.home.HomeViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class TvShowsFragment : Fragment() {

    private var fragmentTvShowsBinding: ShowsFragmentBinding? = null
    private val binding get() = fragmentTvShowsBinding!!
    private val viewModel: TvShowsViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var showbizAdapter: ShowbizAdapter
    private lateinit var searchView: MaterialSearchView
    private var sort = Queries.NEWEST

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowsBinding = ShowsFragmentBinding.inflate(inflater, container, false)
        val toolbar: Toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        searchView = (activity as HomeActivity).findViewById(R.id.search_view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showbizAdapter = ShowbizAdapter()
        setList(sort)
        observeSearchQuery()
        setSearchList()

        with(binding.showsRecyclerView) {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)
    }

    private fun setList(sort: String) {
        viewModel.getTvShows(sort).observe(viewLifecycleOwner, tvShowsObserver)
    }

    private val tvShowsObserver = Observer<Resource<List<Showbiz>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow) {
                is Resource.Loading -> {
                    binding.showsProgressBar.visibility = View.VISIBLE
                    binding.notFoundText.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.showsProgressBar.visibility = View.GONE
                    binding.notFoundText.visibility = View.GONE
                    showbizAdapter.setData(tvShow.data)
                }
                is Resource.Error -> {
                    binding.showsProgressBar.visibility = View.GONE
                    binding.notFoundText.visibility = View.VISIBLE
                    Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeSearchQuery() {
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    homeViewModel.setSearchQuery(it)
                }
                return true
            }

        })
    }

    private fun setSearchList() {
        homeViewModel.tvShowResult.observe(viewLifecycleOwner, { tvShows ->
            if (tvShows.isNullOrEmpty()) {
                binding.showsProgressBar.visibility = View.GONE
                binding.notFoundText.visibility = View.VISIBLE
            } else {
                binding.showsProgressBar.visibility = View.GONE
                binding.notFoundText.visibility = View.GONE
            }
            showbizAdapter.setData(tvShows)
        })
        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                binding.showsProgressBar.visibility = View.GONE
                binding.notFoundText.visibility = View.GONE
            }

            override fun onSearchViewClosed() {
                binding.showsProgressBar.visibility = View.GONE
                binding.notFoundText.visibility = View.GONE
                setList(sort)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentTvShowsBinding = null
    }

}