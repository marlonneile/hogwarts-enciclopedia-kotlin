package com.android.hogwartsenciclopedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.hogwartsenciclopedia.R
import com.android.hogwartsenciclopedia.adapter.CharacterAdapter
import com.android.hogwartsenciclopedia.adapter.CharacterClickListener
import com.android.hogwartsenciclopedia.databinding.AllCharactersFragmentBinding
import com.android.hogwartsenciclopedia.domain.CharacterModel
import com.android.hogwartsenciclopedia.viewmodel.AllCharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCharactersFragment : Fragment() {

    companion object {
        fun newInstance() = AllCharactersFragment()
    }

    private lateinit var viewModel: AllCharactersViewModel

    private lateinit var binding: AllCharactersFragmentBinding

    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.all_characters_fragment, container, false)

        initialize()

        return binding.root
    }

    private fun initialize() {
        initializeInstancesAndBinding()
        setObservers()
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModel.getAllCharacters()
    }

    private fun setObservers() {
        setCharactersObserver()
        setEventNavigateObserver()
    }

    private fun setEventNavigateObserver() {
        viewModel.eventNavigateToCharacter.observe(viewLifecycleOwner, Observer {
            it?.let {
                navigateToDetail(it)
                viewModel.onEventNavigateCompleted()
            }
        })
    }

    private fun setCharactersObserver() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            it?.let {
                @Suppress("UNCHECKED_CAST")
                adapter.submitList(it as List<CharacterModel>)
            }
        })
    }

    private fun navigateToDetail(characterId: String) {
        findNavController().navigate(AllCharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(characterId))
    }

    private fun initializeInstancesAndBinding() {
        initializeViewModel()
        initializeAdapter()
        initializeBinding()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(AllCharactersViewModel::class.java)
    }

    private fun initializeAdapter() {
        adapter = CharacterAdapter(CharacterClickListener { characterId ->
            viewModel.onCharacterClicked(characterId)
        })
    }

    private fun initializeBinding() {
        bindViewModel()
        bindAdapter()
        binding.lifecycleOwner = this
    }

    private fun bindViewModel() {
        binding.characterViewModel = viewModel
    }

    private fun bindAdapter() {
        binding.charactersGrid.adapter = adapter
        setAdapterManager()
    }

    private fun setAdapterManager() {
        val manager = GridLayoutManager(activity, 2)
        binding.charactersGrid.layoutManager = manager
    }
}