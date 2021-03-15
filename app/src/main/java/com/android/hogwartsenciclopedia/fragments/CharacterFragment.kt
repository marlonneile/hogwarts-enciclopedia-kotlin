package com.android.hogwartsenciclopedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hogwartsenciclopedia.R
import com.android.hogwartsenciclopedia.databinding.CharacterFragmentBinding
import com.android.hogwartsenciclopedia.domain.FullCharacterModel
import com.android.hogwartsenciclopedia.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterFragment()
    }

    private lateinit var viewModel: CharacterViewModel

    private lateinit var binding: CharacterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.character_fragment, container, false)

        initialize()

        return binding.root
    }

    private fun initialize() {
        initializeInstancesAndBinding()
        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            it?.let{ updateUI(it) }
        })
    }

    private fun updateUI(character: FullCharacterModel) {
        binding.character = character
        viewModel.onUpdateTheme(character.house)
    }

    private fun initializeInstancesAndBinding() {
        initializeViewModel()
        initializeBinding()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
    }

    private fun initializeBinding() {
        binding.lifecycleOwner = this
    }
}