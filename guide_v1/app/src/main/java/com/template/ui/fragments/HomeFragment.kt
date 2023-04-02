package com.template.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.databinding.FragmentHomeBinding
import com.template.domain.ArticlesGenerator
import com.template.domain.models.Chapters
import com.template.domain.models.ListForAdapter
import com.template.ui.adapter.chapter.ChapterAdapter
import com.template.ui.fragments.FragmentManager.launchFragment
import com.template.utils.Constants.EXEPTION_NAME


class HomeFragment : Fragment(), ChapterAdapter.ItemClickChapter {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException(EXEPTION_NAME)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val listForAdapter = ArticlesGenerator(requireContext()).forAdapterList

        setupRecyclerView(listForAdapter)
        finishFragment()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClickItemChapter(listNameItem: ListForAdapter) {

        when(listNameItem.nameChapter) {
            Chapters.ONE.nameChapter -> {
                launchFragment(
                    OneFragment.newInstanceOne(),
                    requireActivity() as AppCompatActivity,
                    Chapters.ONE.nameChapter
                )
            }
            Chapters.TWO.nameChapter-> {
                launchFragment(
                    TwoFragment.newInstanceTwo(),
                    requireActivity() as AppCompatActivity,
                    Chapters.TWO.nameChapter
                )
            }
            Chapters.THREE.nameChapter -> {
                launchFragment(
                    ThreeFragment.newInstanceThree(),
                    requireActivity() as AppCompatActivity,
                    Chapters.THREE.nameChapter
                )
            }
            Chapters.FOUR.nameChapter -> {
                launchFragment(
                    FourFragment.newInstanceFour(),
                    requireActivity() as AppCompatActivity,
                    Chapters.FOUR.nameChapter
                )
            }
            Chapters.FIVE.nameChapter -> {
                launchFragment(
                    FiveFragment.newInstanceFive(),
                    requireActivity() as AppCompatActivity,
                    Chapters.FIVE.nameChapter
                )
            }
        }
    }

    private fun setupRecyclerView(list: List<ListForAdapter>) = with(binding){

        val adapter = ChapterAdapter(this@HomeFragment)
        recyclerViewHome.adapter = adapter
        adapter.submitList(list)
    }

    private fun finishFragment(){
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    companion object {
        fun newInstanceHome() = HomeFragment()
    }
}