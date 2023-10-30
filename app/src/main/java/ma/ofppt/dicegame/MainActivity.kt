package ma.ofppt.dicegame

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.random.Random

/**
 * Activité principale pour le jeu de dé
 * @author Omar
 * @property score le score du joueur
 * @return
 * @see toString
 */

class MainActivity : AppCompatActivity() {

    lateinit var score : TextView
    lateinit var computerDice : ImageView
    lateinit var playerDice : ImageView
    lateinit var dice1 : ImageView
    lateinit var dice2 : ImageView
    lateinit var dice3 : ImageView
    lateinit var dice4 : ImageView
    lateinit var dice5 : ImageView
    lateinit var dice6 : ImageView
    var playerScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        computerDice = findViewById(R.id.computerDice)
        playerDice = findViewById(R.id.playerDice)
        dice1 = findViewById(R.id.dice1)
        dice2 = findViewById(R.id.dice2)
        dice3 = findViewById(R.id.dice3)
        dice4 = findViewById(R.id.dice4)
        dice5 = findViewById(R.id.dice5)
        dice6 = findViewById(R.id.dice6)
        score = findViewById(R.id.score)
        val dices :List<ImageView> = listOf(dice1,dice2,dice3,dice4,dice5,dice6)
        for(d in dices){
            d.setOnClickListener{
                var job : Job = Job()
                job = GlobalScope.launch(Dispatchers.Main) {
                    val mediaPlayer  = MediaPlayer.create(applicationContext,R.raw.move)
                    mediaPlayer.isLooping=true
                    mediaPlayer.start()
                    for(j in 0..30){

                        val i = Random.nextInt(0,6)
                        computerDice.setImageDrawable(dices[i].drawable)
                        delay(20)
                    }
                    mediaPlayer.stop()
                    playerDice.setImageDrawable(d.drawable)
                    if(playerDice.drawable == computerDice.drawable){
                        playerScore++
                        score.text = "Score : $playerScore"
                }
                   // job.cancel()

                }
            }
        }


    }



}