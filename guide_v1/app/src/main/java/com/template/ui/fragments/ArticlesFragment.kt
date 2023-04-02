package com.template.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.databinding.FragmentArticlesBinding
import com.template.ui.fragments.FragmentManager.backPressFragment
import com.template.utils.Constants.CHAPTER
import com.template.utils.Constants.DESCRIPTION
import com.template.utils.Constants.EXEPTION_NAME
import com.template.utils.Constants.HOME

class ArticlesFragment : Fragment() {

    private var _binding: FragmentArticlesBinding? = null
    private val binding: FragmentArticlesBinding
    get() = _binding ?: throw RuntimeException(EXEPTION_NAME)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)

        bindView()
        val nameFragment = getArgument(CHAPTER)
        backPressFragment(requireActivity() as AppCompatActivity, nameFragment)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getArgument(argKey: String) : String? {
        return requireArguments().getString(argKey)
    }

    private fun bindView() = with(binding){
        tvDescription.text = getArgument(DESCRIPTION)

        button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack( HOME, 0)
        }
    }

    companion object {
        fun newInstanceArticles(description: String, chapter: String) : ArticlesFragment{
            return ArticlesFragment().apply {
                arguments = Bundle().apply {
                    putString(CHAPTER, chapter)
                    putString(DESCRIPTION, description)
                }
            }
        }
    }
}