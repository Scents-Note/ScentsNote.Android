package com.afume.afume_android.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.afume.afume_android.databinding.FragmentHomeBinding
import com.afume.afume_android.ui.home.adapter.NewListAdapter
import com.afume.afume_android.ui.home.adapter.PopularListAdapter
import com.afume.afume_android.ui.home.adapter.RecentListAdapter
import com.afume.afume_android.ui.home.adapter.RecommendListAdapter


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var recommendAdapter : RecommendListAdapter
    lateinit var popularAdapter : PopularListAdapter
    lateinit var recentAdapter : RecentListAdapter
    lateinit var newAdapter : NewListAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = homeViewModel

        homeViewModel.setUserInfo()

        binding.btnHomeMore.setOnClickListener {
            val moreNewPerfumeIntent = Intent(context,MoreNewPerfumeActivity::class.java)

            startActivity(moreNewPerfumeIntent)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecommendList()
        initPopularList()
        initRecentList()
        initNewList()
    }

    private fun initRecommendList(){
        recommendAdapter =
            RecommendListAdapter(
                requireContext()
            )

        binding.vpHomeRecommend.adapter = recommendAdapter

        binding.indicatorHome.setViewPager(binding.vpHomeRecommend)
    }

    private fun initPopularList(){
        popularAdapter =
            PopularListAdapter(
                requireContext()
            )
        binding.rvHomePopular.adapter = popularAdapter

        popularAdapter.notifyDataSetChanged()

    }

    private fun initRecentList(){
        recentAdapter =
            RecentListAdapter(
                requireContext()
            )
        binding.rvHomeRecent.adapter = recentAdapter

        recentAdapter.notifyDataSetChanged()

    }

    private fun initNewList(){
        newAdapter =
            NewListAdapter(requireContext())
        binding.rvHomeNew.adapter = newAdapter

        newAdapter.notifyDataSetChanged()
    }

}