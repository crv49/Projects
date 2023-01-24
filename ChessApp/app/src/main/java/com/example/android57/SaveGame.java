package com.example.android57;

import static com.example.android57.HomePage.gamesRecorded;
import static com.example.android57.MainActivity.processedGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class SaveGame extends AppCompatActivity {
    public static final String FILE_NAME = "example.txt";
    public static final String GAME_NAME = "name";
    public static final String GAME_DATE = "date";
    public static final String GAME_RESULT = "result";
    public static final String GAME_MOVES = "moves";

    public static long gameDate;
    public static String gameResult;
    public static ArrayList<Move> gameMoves;

    public static TextInputEditText userInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_game);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        GregorianCalendar date=processedGame.getDate();
        String result=processedGame.getResult();
        ArrayList<Move> moves=processedGame.getMoves();
        TextView gameResult = findViewById(R.id.gameResult);
        gameResult.setText(result);


    }

    public void handleSave(View v){
        userInput = (TextInputEditText) findViewById(R.id.saveInputText);

        String fileName=(String) userInput.getText().toString();
        if(fileName.isEmpty()) return;

        HomePage.noGames=false;
        MainActivity.processedGame.setName(fileName);

        gamesRecorded.add(MainActivity.processedGame);
        MainActivity.processedGame=null;
        Intent intent=new Intent(this,HomePage.class);
        startActivity(intent);

        /*
        PrintWriter writer;
        try {
            File file = new File ("src/misc/songs.txt");
            file.createNewFile();
            writer = new PrintWriter(file);
            for(Song s: obsList)
            {
                writer.println(s.getTitle());
                writer.println(s.getArtist());
                writer.println(s.getAlbum());
                writer.println(s.getYear());

            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        //PrintWriter stuff here
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}