package com.afume.afume_android.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import com.afume.afume_android.data.vo.RecommendPerfumeListData
import com.afume.afume_android.databinding.FragmentHomeBinding
import com.afume.afume_android.home.adapter.RecommendListAdapter


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var recommendAdapter : RecommendListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecommendList()
    }

    private fun initRecommendList(){
        recommendAdapter = RecommendListAdapter(requireContext())
        binding.rvHomeRecommend.adapter = recommendAdapter

        recommendAdapter.data = mutableListOf(
            RecommendPerfumeListData(
                image = null,
                brand = "1번 브랜드",
                name = "1번향수",
                tags = listOf("#세련됨")
            ),
            RecommendPerfumeListData(
                image = null,
                brand = "2번 브랜드",
                name = "2번향수",
                tags = listOf("#세련됨", "#시원함")
            ),
            RecommendPerfumeListData(
                image = null,
                brand = "3번 브랜드",
                name = "3번향수",
                tags = listOf("#시원함", "#여성스러운")
            ),
            RecommendPerfumeListData(
                image = null,
                brand = "4번 브랜드",
                name = "4번향수",
                tags = listOf("#세련됨")
            )
        )
        recommendAdapter.notifyDataSetChanged()

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvHomeRecommend)
    }

}