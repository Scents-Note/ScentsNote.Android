package com.scentsnote.android.ui.detail.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.scentsnote.android.R
import com.scentsnote.android.databinding.FragmentDetailNoteBinding
import com.scentsnote.android.viewmodel.detail.PerfumeDetailViewModel

/**
 * 향수 상세 페이지 - 오른쪽 탭(시향 노트)
 *
 * 해당 향수의 시향 노트 리스트를 표시
 */
class DetailNoteFragment(val perfumeIdx: Int) : Fragment() {

    lateinit var binding: FragmentDetailNoteBinding
    lateinit var noteAdapter: DetailNoteAdapter
    private val viewModel: PerfumeDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_note, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initNoteList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPerfumeInfoWithReview(perfumeIdx)

        viewModel.perfumeDetailWithReviewData.observe(viewLifecycleOwner) { reviews ->
            noteAdapter.submitList(reviews.map { it.copy() })
        }

        viewModel.isValidNoteList.observe(viewLifecycleOwner) {
            if (it) {
                binding.run {
                    rvDetailNote.visibility = View.VISIBLE
                    txtDetailReviewList.visibility = View.GONE
                }
            } else {
                binding.run {
                    rvDetailNote.visibility = View.GONE
                    txtDetailReviewList.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initNoteList() {
        noteAdapter = DetailNoteAdapter(
            viewModel, parentFragmentManager, perfumeIdx
        ) { idx -> viewModel.postReviewLike(idx) }
        binding.rvDetailNote.run {
            adapter = noteAdapter
            itemAnimator = null
        }
    }
}