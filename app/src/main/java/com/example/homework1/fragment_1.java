package com.example.homework1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;


public class fragment_1 extends Fragment   {

    private CountDownTimer countDownTimer;
    public fragment_1() {
        // Required empty public constructor
    }

    private Context mcontext;
    private ImageView resim;
    private ImageView resim2;
    private Button btn;
    private Random random = new Random();
    private TextView soru;


   // private MediaPlayer mp;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level1, container, false);

        resim = rootView.findViewById(R.id.level1_1);
        resim2 = rootView.findViewById(R.id.level1_2);

        soru = rootView.findViewById(R.id.textsoru);
        String dogru = Game.list.get(Game.position);

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
        ArrayList<String> siklar = new ArrayList();
        siklar.add(dogru);

        Game.textToSpeech.speak("find the "+dogru,TextToSpeech.QUEUE_FLUSH,null);
      //  Game.mp = MediaPlayer.create(getActivity(),getResources().getIdentifier(dogru,"raw",getActivity().getPackageName()));
       // Game.mp.start();

        // btn = rootView.findViewById(R.id.button_listen);
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(getActivity(),R.);
                mp.start();
            }
        });
        /*
        mp =MediaPlayer.create(getActivity(),getResources().getIdentifier(dogru, "raw", getActivity().getPackageName() ));
        System.out.println("--------------------------"+dogru);
        mp.start();
*/
        /*String path = "android.resource://"+"com.example.homework1"+"/raw/"+dogru;
        mp = new MediaPlayer();
        try {
            mp.setDataSource(getActivity(), Uri.parse(path));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        while (true){
            int rnd = random.nextInt(15);
            if (!(Game.list.get(rnd)).equals(dogru)){
                siklar.add(Game.list.get(rnd));
                break;
            }
        }
        Collections.shuffle(siklar);

        int imageresource = getResources().getIdentifier(siklar.get(0), "drawable", getActivity().getPackageName());
        resim.setImageResource(imageresource);
        int imageresource2 = getResources().getIdentifier(siklar.get(1), "drawable", getActivity().getPackageName());
        resim2.setImageResource(imageresource2);


        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Game.mp.release();
                countDownTimer.cancel();

                check(siklar.get(0));
            }

        });

        resim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                countDownTimer.cancel();

                check(siklar.get(1));
            }
        });



        return rootView;
    }

    public void check(String cevap){
        if(Game.list.get(Game.position).equals(cevap) ){
//            Game.mp.release();
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
           // Game.mp.release();
            //String str = String.valueOf(Game.point);
            Intent intent = new Intent(getActivity(), ResultAct.class);
            intent.putExtra("point",Game.point);
            startActivity(intent);

         // startActivity(new Intent(getActivity(),ResultAct.class));
        }


    }

    public void refresh(){
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_tutucu, new fragment_1())
                .remove(fragment_1.this)
                .commit();
    }


    public void replace(){
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_tutucu, new fragment2())
                .remove(fragment_1.this)
                .commit();
    }

    public void callcancel (){
        countDownTimer.cancel();
    }


}