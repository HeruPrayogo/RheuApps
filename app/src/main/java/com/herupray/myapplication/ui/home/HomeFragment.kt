package com.herupray.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.herupray.core.data.source.Resource
import com.herupray.core.ui.MovieAdapter
import com.herupray.myapplication.R
import com.herupray.myapplication.databinding.FragmentHomeBinding
import com.herupray.myapplication.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class   HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupAdapter()
        favOnClick()
    }

    private fun favOnClick() {
        binding.favBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }

    private fun setupAdapter() {
        movieAdapter = MovieAdapter {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                Bundle().apply { putParcelable(DetailFragment.MOVIE, it) })
        }
        binding.rvMovie.apply {
            layoutManager =
                GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun observeData() {
        viewModel.getMovie.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.apply {
                        rvMovie.visibility = View.GONE
                        pb.visibility = View.VISIBLE
                    }
                }

                is Resource.Success -> {
                    binding.apply {
                        rvMovie.visibility = View.VISIBLE
                        pb.visibility = View.GONE
                    }
                    movieAdapter.differ.submitList(it.data)
                }

                is Resource.Error -> {
                    binding.apply {
                        rvMovie.visibility = View.GONE
                        pb.visibility = View.GONE
                    }
                }
            }
        }
    }


}