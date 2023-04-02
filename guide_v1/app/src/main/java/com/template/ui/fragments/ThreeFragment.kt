package com.template.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.databinding.FragmentThreeBinding
import com.template.domain.ArticlesGenerator
import com.template.domain.models.Chapters
import com.template.domain.models.ListArticles
import com.template.ui.adapter.arcticles.ArticlesAdapter
import com.template.utils.Constants


class ThreeFragment : Fragment(), ArticlesAdapter.ItemClickArticle {

    private var _binding: FragmentThreeBinding? = null
    private val binding: FragmentThreeBinding
        get() = _binding ?: throw RuntimeException(Constants.EXEPTION_NAME)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)

        val listArticles = ArticlesGenerator(requireContext()).getListForAdapter(Chapters.THREE)
        setupRecyclerView(listArticles)
        FragmentManager.backPressFragment(requireActivity() as AppCompatActivity, Constants.HOME)

        return binding.root
    }

    override fun onClickItemArticle(listNameItem: ListArticles) {
        val fragment = ArticlesFragment.newInstanceArticles(
                listNameItem.description, Chapters.THREE.nameChapter
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
        binding.recyclerViewThree.adapter = adapter
        adapter.submitList(list)
    }

    companion object{
        fun newInstanceThree() = ThreeFragment()
    }
}