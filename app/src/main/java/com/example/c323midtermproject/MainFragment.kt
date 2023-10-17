package com.example.c323midtermproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.c323midtermproject.databinding.FragmentMainBinding

class MainFragment:Fragment() {
    val TAG = "MainFragment"
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //variables
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ScoreDatabase.getInstance(application).scoreDao
        val viewModelFactory = MainViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //retrieve passed variables
        val args: MainFragmentArgs by navArgs()
        val scoreName = args.scoreName
        //update welcome to screen to show previous stats
        if (scoreName!="1") {
            val score = args.scoreScore
            val name = args.scoreName
            val text = getString(R.string.welcome_message)
            val out = text.format(name, score)
            binding.mainWelcome.text = out
        }

        /*
        observes when to navigate to play game screen
         */
        viewModel.navigateToGame.observe(viewLifecycleOwner, Observer { scoreId->
            scoreId?.let {
                val action = MainFragmentDirections
                    .actionMainFragmentToGameFragment()
                this.findNavController().navigate(action)
                viewModel.onGameNavigated()
            }
        })

        /*
        observes when to navigate to highscore screen
         */
        viewModel.navigateToScores.observe(viewLifecycleOwner, Observer { scoreId->
            scoreId?.let {
                val action = MainFragmentDirections
                    .actionMainFragmentToHighscoreFragment()
                this.findNavController().navigate(action)
                viewModel.onScoresNavigated()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}