package com.example.android57;

import static com.example.android57.MainActivity.gameRecording;
import static com.example.android57.MainActivity.resultRecord;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.io.File;


public class HomePage extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";


    public static ListView gamesListView;
    public static ArrayList<Game> gamesRecorded=null;   // List which stores all recorded games
    public static String[] gameNamesAndDates;
    public static boolean noGames=true;

    private Button newGameButton;
    private Button dateSort;
    private Button titleSort;

    public static final String GAME_NAME = "name";
    public static final String GAME_DATE = "date";
    public static final String GAME_RESULT = "result";
    public static final String GAME_MOVES = "moves";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        gamesListView=(ListView)findViewById(R.id.recordedGamesList);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        newGameButton=(Button) findViewById(R.id.newGameButton);
        dateSort=(Button) findViewById(R.id.dateSort);
        titleSort=(Button) findViewById(R.id.titleSort);

        //new reader here to read data


        if(noGames) {
            gamesRecorded=new ArrayList<Game>();
            titleSort.setVisibility(View.GONE);
            dateSort.setVisibility(View.GONE);
        } else {
            titleSort.setVisibility(View.VISIBLE);
            dateSort.setVisibility(View.VISIBLE);
        }

        /*
            Trying to extract the data from the internal storage
         */


        if(gamesRecorded!=null&&gamesRecorded.size()>0) {
            gameNamesAndDates = new String[gamesRecorded.size()];

            for (int i = 0; i < gameNamesAndDates.length; i++) {
                gameNamesAndDates[i] = gamesRecorded.get(i).getName()+" - "+
                        gamesRecorded.get(i).getDate().getTime();
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gameNamesAndDates);
            gamesListView.setAdapter(arrayAdapter);
        }

        gamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Game gamePlayback=gamesRecorded.get(i);
                Intent intent=new Intent(HomePage.this, MainActivity.class);
                intent.putExtra("Mode","oldGame");
                gameRecording=gamePlayback.getMoves();
                resultRecord=gamePlayback.getResult();
                startActivity(intent);

            }
        });
    }

    public void handleNewGame(View v){
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("Mode","newGame");
        startActivity(i);
    }

        //android.R.layout.simple_list_item_2


    public void sortDates(View v){
            if(gamesRecorded != null) {
                Collections.sort(gamesRecorded, new sortByDate());
                String[] gamesByDate = new String[gamesRecorded.size()];
                for (int i = 0; i<gamesRecorded.size(); i++) {
                    gamesByDate[i] = gamesRecorded.get(i).getName()+" - "+
                            gamesRecorded.get(i).getDate().getTime();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HomePage.this,
                        android.R.layout.simple_list_item_1, gamesByDate);
                gamesListView.setAdapter(arrayAdapter);
            }
        }

    public void sortTitles(View v){
        if(gamesRecorded != null) {
            Collections.sort(gamesRecorded, new sortByTitle());
            String[] gamesByName = new String[gamesRecorded.size()];
            for (int i = 0; i<gamesRecorded.size(); i++) {
                gamesByName[i] = gamesRecorded.get(i).getName()+" - "+
                        gamesRecorded.get(i).getDate().getTime();
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HomePage.this,
                    android.R.layout.simple_list_item_1, gamesByName);
            gamesListView.setAdapter(arrayAdapter);
        }
    }



    class sortByTitle implements Comparator<Game> {
        public int compare(Game g1, Game g2) {
            if(g1 != null && g2 != null)
            {
                return g1.getName().compareToIgnoreCase(g2.getName());
            }
            return 0;

        }
    }
    class sortByDate implements Comparator<Game> {
        public int compare(Game g1, Game g2) {
            if(g1 != null && g2 != null)
            {
                GregorianCalendar date1 = g1.getDate();
                GregorianCalendar date2 = g2.getDate();

                return date2.compareTo(date1);
            }
            return 0;
        }
    }





}