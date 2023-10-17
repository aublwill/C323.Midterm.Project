package com.example.c323midtermproject

import android.media.MediaPlayer
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.c323midtermproject.databinding.FragmentGameBinding

class GameFragment :Fragment(){
    private var _binding:FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = ScoreDatabase.getInstance(application).scoreDao

        val viewModelFactory = GameViewModelFactory( dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GameViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /*
        observer for navigating to main fragment after completing game
         */
        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer {navigate->
            if (navigate){
                val action = GameFragmentDirections.actionGameFragmentToMainFragment(viewModel.score.scoreName, viewModel.score.scoreScore)
                view.findNavController()
                    .navigate(action)
                viewModel.onNavigatedToMain()
            }
        })
        /*
        subtracts the current guess by one when minus button is pressed
         */
        binding.bMinus.setOnClickListener {
            if (binding.etGuess.text.isNotEmpty()) {
                val guess = binding.etGuess.text.toString().toInt()
                val num = viewModel.decrease(guess)
                binding.etGuess.text.replace(0, guess.toString().length, num.toString())
            }
        }
        /*
        adds the current guess by one when add button is pressed
         */
        binding.bAdd.setOnClickListener {
            if (binding.etGuess.text.isNotEmpty()) {
                val guess = binding.etGuess.text.toString().toInt()
                val num = viewModel.increase(guess)
                binding.etGuess.text.replace(0, guess.toString().length, num.toString())
            }
        }
        /*
        When ok button is pressed:
         - toast is shown
            - either wrong- with direction higher or lower and buzz sound
            - correct- saves score/navigate back to main
         */
        var num = 0
        binding.bOK.setOnClickListener {
            val guess = binding.etGuess.text.toString().toInt()
            val dir = viewModel.guess(guess)
            // Show Toast for "higher" or "lower"
            val toastText = when (dir) {
                "higher" -> "Aim Higher"
                "lower" -> "Aim Lower"
                else -> "Correct Guess!"
            }
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()

            if (dir != "win") {
                // Play alarm sound on incorrect guess
                val mediaPlayer = MediaPlayer.create(context, R.raw.wrong)
                mediaPlayer.start()
                viewModel.attempts()
                binding.tvAttempts.text = "Number of attempts:"+ ++num

            }
            else viewModel.saveScore()

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}