# C323.Midterm.Project
This project functions as a guessing number game.

## Functionality 
The following **required** functionality is completed:
* [] user sees a screen with a welcome message and two buttons
* [] "Play game" button directs user to game screen
* [] "highscore" button directs user to highscore screen
* [] user can input their name and guess within the game screen
* [] user can use +/- buttons to increase/decrease their guess
* [] once user presses "ok" button, dialog pops up to either guess higher or lower, or that it was correct
* [] user repeats until correct and directs back to main screen
* [] main screen now shows updated welcome text with their last score
* [] highscore screen shows all previous saved games with their name and score
* [] user can press "x" to delete that game/score
* [] user can press "<-" to navigate back to home screen

The folowing **extensions** are implemented:

* import android.app.AlertDialog
* import android.app.Dialog
* import android.content.Context
* import android.os.Bundle
* import android.util.Log
* import androidx.fragment.app.DialogFragment
* import android.os.Bundle
* import android.view.LayoutInflater
* import android.view.View
* import android.view.ViewGroup
* import androidx.fragment.app.Fragment
* import androidx.lifecycle.Observer
* import androidx.lifecycle.ViewModelProvider
* import androidx.navigation.findNavController
* import androidx.lifecycle.LiveData
* import androidx.lifecycle.MutableLiveData
* import androidx.lifecycle.ViewModel
* import androidx.lifecycle.viewModelScope
* import kotlinx.coroutines.launch
* import java.lang.IllegalArgumentException
* import androidx.appcompat.app.AppCompatActivity
* import androidx.room.ColumnInfo
* import androidx.room.Entity
* import androidx.room.PrimaryKey
* import androidx.room.Dao
* import androidx.room.Delete
* import androidx.room.Insert
* import androidx.room.Query
* import androidx.room.Update
* import androidx.room.Database
* import androidx.room.Room
* import androidx.room.RoomDatabase
* import androidx.recyclerview.widget.DiffUtil
* import androidx.recyclerview.widget.ListAdapter
* import androidx.recyclerview.widget.RecyclerView
  
## Video Walkthrough 



https://github.com/aublwill/C323.Midterm.Project/assets/143005409/ae6e0346-585d-4907-9eb9-8cb148530f94



## Notes
* Does not show 'no high scores yet' text

## License
Copyright [2023] [Aubrey Williams]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
