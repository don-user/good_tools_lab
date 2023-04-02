package com.template.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.template.utils.Constants.EXTRA_LEVEL
import com.template.utils.Constants.EXTRA_LONG
import com.template.utils.Constants.LONG_ZERO
import com.template.utils.Constants.formattedTimer
import com.template.databinding.FragmentFinishBinding
import com.template.domain.model.Level

class FinishFragment : Fragment() {

    private var _binding: FragmentFinishBinding? = null
    private val binding: FragmentFinishBinding
        get() = _binding ?: throw RuntimeException(EXCEPTION_MESSAGE_BINDING)

    private var level: Level? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)
        parsArguments()
        clickRestartOrHome()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parsArguments(){

       requireArguments().getParcelable<Level>(EXTRA_LEVEL).let {
           level = it
       }

        requireArguments().getLong(EXTRA_LONG, LONG_ZERO).let {
            binding.tvTime.text = formattedTimer(it)
        }
    }

    private fun clickRestartOrHome() = with(binding){
        restartButton.setOnClickListener {
            requireActivity().finish()
            val intent = GameActivity.newIntentInstance(requireContext(), level!!)
            startActivity(intent)
        }
        homeButton.setOnClickListener {
            requireActivity().finish()
        }
    }

    companion object {
        private const val EXCEPTION_MESSAGE_BINDING = "Param screen name is absent"

        fun newInstance(time: Long, level: Level) = FinishFragment().apply {
                arguments = Bundle().apply {
                    putLong(EXTRA_LONG, time)
                    putParcelable(EXTRA_LEVEL, level)
                }
            }
    }
}