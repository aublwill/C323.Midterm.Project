package com.example.c323midtermproject

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.c323midtermproject.databinding.FragmentHighscoreBinding

class ScoresFragment : Fragment(){
    val TAG = "ScoresFragment"
    private var _binding:FragmentHighscoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //variables
        _binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ScoreDatabase.getInstance(application).scoreDao
        val viewModelFactory = ScoresViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ScoresViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //observes when to navigate back to main screen
        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer {navigate->
            if (navigate){
                view.findNavController()
                    .navigate(R.id.action_highscoreFragment_to_mainFragment)
                viewModel.onNavigatedToMain()
            }
        })

        /*
        @param scoreId:Long current scores id
        triggers delete method using id
         */
        fun yesPressed(scoreId:Long){
            viewModel.deleteById(scoreId)
        }
        /*
        @param scoreId:Long current scores id
        shows delete dialog
         */
        fun deleteClicked(scoreId:Long){
            ConfirmDeleteDialogFragment(scoreId, ::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }

        val adapter = ScoreItemAdapter(::deleteClicked)
        binding.rvHighScore.adapter = adapter

        //observes scores to submit data
        viewModel.scores.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}