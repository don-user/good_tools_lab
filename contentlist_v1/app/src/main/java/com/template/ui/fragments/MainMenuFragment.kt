package com.template.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.template.base.bookmarkBase.BookmarkRepositoryImpl
import com.template.base.viewPagerBase.ViewPagerRepositoryImpl
import com.template.databinding.FragmentMainMenuBinding
import com.template.domain.bookmark.SavePageUseCase
import com.template.domain.viewPager.GetAssetsListUseCase
import com.template.ui.adapters.AdapterViewPager
import com.template.ui.dialog.AboutUsDialog
import com.template.utils.Constants.MESSAGE
import com.template.utils.Constants.PAGE
import com.template.utils.Constants.SHARED_PREFS


class MainMenuFragment : BaseFragment() {

    private lateinit var binding: FragmentMainMenuBinding
    private lateinit var adapter: AdapterViewPager

    private val sharedPreferences by lazy {
        requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainMenuBinding.inflate(inflater, container, false)

        val repositoryBookmark = BookmarkRepositoryImpl(sharedPreferences)
        val repositoryViewPager = ViewPagerRepositoryImpl(requireContext())

        val assetsList = GetAssetsListUseCase(repositoryViewPager).getAssetsList()

        setupViewPageAdapter(assetsList)
        openViewPage()
        buttonPageClick()
        addBookmark(repositoryBookmark)
        showDialog()

        return binding.root
    }

    private fun setupViewPageAdapter(list: List<String>) = with(binding){
        adapter = AdapterViewPager(this@MainMenuFragment, list)
        viewPager.adapter = adapter

        viewPager.isUserInputEnabled = false
    }

    private fun openViewPage(){

        requireArguments().getInt(PAGE).let{
            binding.viewPager.setCurrentItem(it, false)
        }
    }

    private fun buttonPageClick()= with(binding){

        buttonNext.setOnClickListener {

            val currentPage = viewPager.currentItem
            viewPager.setCurrentItem(currentPage + 1, true)
        }
        buttonPrevious.setOnClickListener {

            val currentPage = viewPager.currentItem
            viewPager.setCurrentItem(currentPage - 1, true)
        }
    }

    private fun addBookmark(repository: BookmarkRepositoryImpl) = with(binding){
        buttonBookmark.setOnClickListener {

            val page = viewPager.currentItem

            SavePageUseCase(repository).savePage(page)

            Toast.makeText(requireContext(), MESSAGE, Toast.LENGTH_SHORT).show()

        }
    }

    private fun showDialog(){

        binding.buttonAboutUs.setOnClickListener {

            AboutUsDialog.showDialog(requireActivity())
        }
    }

    companion object{

        fun newInstanceViewPage(page: Int) : MainMenuFragment{
            return MainMenuFragment().apply {
                arguments = Bundle().apply {
                    putInt(PAGE, page)
                }
            }
        }
    }
}