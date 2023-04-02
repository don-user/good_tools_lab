package com.template.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.databinding.FragmentOneBinding
import com.template.domain.ArticlesGenerator
import com.template.domain.models.Chapters
import com.template.domain.models.ListArticles
import com.template.ui.adapter.arcticles.ArticlesAdapter
import com.template.ui.fragments.FragmentManager.backPressFragment
import com.template.utils.Constants
import com.template.utils.Constants.EXEPTION_NAME

class OneFragment : Fragment(), ArticlesAdapter.ItemClickArticle {

    private var _binding: FragmentOneBinding? = null
    private val binding: FragmentOneBinding
    get() = _binding ?: throw RuntimeException(EXEPTION_NAME)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)

        val listArticles = ArticlesGenerator(requireContext()).getListForAdapter(Chapters.ONE)
        setupRecyclerView(listArticles)

        backPressFragment(requireActivity() as AppCompatActivity, Constants.HOME)
        return binding.root

    }

    override fun onClickItemArticle(listNameItem: ListArticles) {
        val fragment =  ArticlesFragment.newInstanceArticles(
            listNameItem.description, Chapters.ONE.nameChapter
        )
        FragmentManager.launchFragment(
            fragment,
            requireActivity() as AppCompatActivity,
            listNameItem.nameArticle
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecyclerView(list: List<ListArticles>) {

        val adapter = ArticlesAdapter(this)
        binding.recyclerViewOne.adapter = adapter
        adapter.submitList(list)
    }

    companion object{
        fun newInstanceOne() = OneFragment()
    }
}