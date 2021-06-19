package com.example.homework1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class fragment3 extends Fragment {


    public fragment3() {
        // Required empty public constructor
    }

    private Button bt;
    private ImageView resim;
    private ImageView resim2;
    private ImageView resim3;
    private ImageView resim4;
    private CountDownTimer countDownTimer;


    private Random random = new Random();
    private TextView soru;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level3, container, false);

        resim = rootView.findViewById(R.id.level3_4);
        resim2 = rootView.findViewById(R.id.level3_3);
        resim3 = rootView.findViewById(R.id.level3_2);
        resim4 = rootView.findViewById(R.id.level3_1);
        soru = rootView.findViewById(R.id.textsoru3);

        String dogru = Game.list.get(Game.position);

        ArrayList<String> siklar = new ArrayList();
        siklar.add(dogru);
        countDownTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                soru.setText("TIME: "+String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {

                countDownTimer.cancel();
                startActivity(new Intent(getActivity(),ResultAct.class));
                getActivity().finish();
            }
        }.start();
        Game.textToSpeech.speak("find the "+dogru, TextToSpeech.QUEUE_FLUSH,null);
      //  Game.mp = MediaPlayer.create(getActivity(),getResources().getIdentifier(dogru,"raw",getActivity().getPackageName()));
      //  Game.mp.start();
        while(siklar.size()<4){
            int rnd = random.nextInt(15);
            while(!(Game.list.get(rnd)).equals(dogru) && !siklar.contains(Game.list.get(rnd))){
                siklar.add(Game.list.get(rnd));
                break;
            }

        }
        Collections.shuffle(siklar);
        int imageresource = getResources().getIdentifier(siklar.get(0), "drawable", getActivity().getPackageName());
        resim.setImageResource(imageresource);
        int imageresource2 = getResources().getIdentifier(siklar.get(1), "drawable", getActivity().getPackageName());
        resim2.setImageResource(imageresource2);
        int imageresource3 = getResources().getIdentifier(siklar.get(2), "drawable", getActivity().getPackageName());
        resim3.setImageResource(imageresource3);
        int imageresource4 = getResources().getIdentifier(siklar.get(3), "drawable", getActivity().getPackageName());
        resim4.setImageResource(imageresource4);
        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(0));
                countDownTimer.cancel();

            }
        });
        resim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(1));
                countDownTimer.cancel();

            }
        });
        resim3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                countDownTimer.cancel();

                check(siklar.get(2));
            }
        });
        resim4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // Game.mp.release();
                // countDownTimer.cancel();

                check(siklar.get(3));
            }
        });
        return rootView;
    }

    public void check(String cevap){

        if(Game.list.get(Game.position).equals(cevap) ){
          //  Game.mp.release();
            Game.position++;
            Game.counter++;
            Game.point++;

            if (Game.counter==3){
                Game.level++;
                replace();
                Game.counter=0;
            }
            else {
                refresh();
            }
        }
        else {
            //Game.mp.release();
            Intent intent = new Intent(getActivity(), ResultAct.class);
            intent.putExtra("point",Game.point);
            startActivity(intent);

//            startActivity(new Intent(getActivity(),ResultAct.class));
        }
    }

    public void refresh(){
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_tutucu, new fragment3())
                .remove(fragment3.this)
                .commit();
    }

    public void replace(){
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_tutucu, new fragment4())
                .remove(fragment3.this)
                .commit();
    }
}