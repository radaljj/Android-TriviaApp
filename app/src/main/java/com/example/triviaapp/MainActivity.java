 package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triviaapp.R;
import com.example.triviaapp.data.AnswerInter;
import com.example.triviaapp.data.QuestionBank;
import com.example.triviaapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView glavni;
    private TextView high;
    int brojacrez=0;
    int highscorerez;
    private TextView trenutnirez;
    int prvoPitanje=0;
    private Button tacno;
    private Button netacno;
    private TextView brojac;
    private ImageButton sledece;
    private ImageButton prethodno;
    List <Question> listapitanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView cardView=findViewById(R.id.cardView);
        trenutnirez=findViewById(R.id.trenutni);
        high=findViewById(R.id.highscore);
        getSupportActionBar().hide();
        netacno=findViewById(R.id.button);
        tacno=findViewById(R.id.button2);
        glavni=findViewById(R.id.textView3);
        brojac=findViewById(R.id.textView2);
        sledece=findViewById(R.id.imageButton);
        sledece.setOnClickListener(this);
        prethodno=findViewById(R.id.imageButton2);
        prethodno.setOnClickListener(this);
        tacno.setOnClickListener(this);
        netacno.setOnClickListener(this);


       listapitanja= new QuestionBank().getQuestions(new AnswerInter() {
            @Override
            public void processFinished(ArrayList<Question> questions) {
                  glavni.setText( questions.get(prvoPitanje).getPitanje());
                Log.d("Poziv","glavna"+questions);
                brojac.setText(prvoPitanje+"/"+listapitanja.size());

                SharedPreferences sp = getSharedPreferences("highscore", Activity.MODE_PRIVATE);
                int myIntValue = sp.getInt("kljuc", 0);
                high.setText(""+myIntValue);


            }
        });

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.imageButton:
                prvoPitanje=(prvoPitanje+1)%listapitanja.size();
                update();


                break;

                case R.id.imageButton2:

                    if(prvoPitanje>0){
                        prvoPitanje=(prvoPitanje-1)%listapitanja.size();
                        update();
                    }

                break;

            case R.id.button:
                proveraTacnost(false);
                break;


            case R.id.button2:
                proveraTacnost(true);
                break;

        }

    }

    private void proveraTacnost(boolean provera) {

        boolean isTrue=listapitanja.get(prvoPitanje).isTacnost();
        if(provera==isTrue){
            brojacrez=brojacrez+10;
            trenutnirez.setText(""+ brojacrez);
            if(highscorerez<=brojacrez){
                highscorerez=brojacrez;

                update();
            }
            prvoPitanje=(prvoPitanje+1)%listapitanja.size();
            SharedPreferences sp = getSharedPreferences("highscore", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("kljuc", highscorerez);
            editor.apply();
            fadeAnimation();

update();
        }

        else{
if(brojacrez>0){
    brojacrez=brojacrez-10;
    trenutnirez.setText(""+ brojacrez);
}
            prvoPitanje=(prvoPitanje+1)%listapitanja.size();
            update();
        shakeAnimation();
update();

        }

    }

    public void update(){
        String pitanje = listapitanja.get(prvoPitanje).getPitanje();
        glavni.setText(pitanje);
        brojac.setText(prvoPitanje+"/"+listapitanja.size());

    }

    private void shakeAnimation(){
        Animation shake= AnimationUtils.loadAnimation(this,R.anim.shake);
        CardView cardView=findViewById(R.id.cardView);
        cardView.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setBackgroundColor(Color.RED);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setBackgroundColor(Color.WHITE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void fadeAnimation(){
        Animation fade=AnimationUtils.loadAnimation(this,R.anim.fade);
        CardView cardView=findViewById(R.id.cardView);
        cardView.setAnimation(fade);
        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}