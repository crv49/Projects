package com.example.android57;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.*;


public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    public static final String GAME_NAME = "name";
    public static final String GAME_DATE = "date";
    public static final String GAME_RESULT = "result";
    public static final String GAME_MOVES = "moves";
    public static String currentMove="White";
    public static String nextMove="Black";

    public static Square originSquare=null;
    public static Square targetSquare=null;

    public static boolean whiteInCheck=false;
    public static boolean blackInCheck=false;

    public static boolean whiteCheckmated=false;
    public static boolean blackCheckmated=false;

    public static int nextMoveCount;

    public static boolean originSquareSelected=false;

    public static Square[][] dupChessboard = null;

    public static Square dupOriginSquare = null;
    public static Square dupTargetSquare = null;

    public static ArrayList<Move> dupMoveList=null;

    public static ArrayList<Move> dupAttackList;
    public static ArrayList<Move> dupDefendList;

    public static ArrayList<Move> currentAttackList;
    public static ArrayList<Move> currentDefendList;

    private Button resignButton;

    public static ArrayList<Move> gameRecording=null;

    public static ImageButton[][] imageBoard;

    Square[][] chessboard=Square.createChessboard();

    GregorianCalendar gCal;

    String checkString="";

    public static String mode="";

    public boolean successfulMove=false;

    public boolean promotion=false;

    public static Game processedGame;

    public static String resultRecord="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mode=getIntent().getStringExtra("Mode");
        nextMoveCount=0;
        if(mode.equals("newGame")) {
            gCal = new GregorianCalendar();
            currentMove="White";
            nextMove="Black";

            findViewById(R.id.randomButton).setClickable(true);
            findViewById(R.id.nextButton).setClickable(false);
            findViewById(R.id.nextButton).setVisibility(View.GONE);
            findViewById(R.id.drawButton).setClickable(false);
            findViewById(R.id.drawButton).setVisibility(View.GONE);
            findViewById(R.id.undoButton).setClickable(false);
            findViewById(R.id.undoButton).setVisibility(View.GONE);
            findViewById(R.id.resignButton).setClickable(false);
            findViewById(R.id.resignButton).setVisibility(View.GONE);

            ((TextView) findViewById(R.id.textView)).setText(
                    currentMove + " to move");


            gameRecording = new ArrayList<Move>();

            imageBoard = new ImageButton[8][8];

            currentMove = "White";
            nextMove = "Black";

            originSquare = null;
            targetSquare = null;

            whiteInCheck = false;
            blackInCheck = false;

            whiteCheckmated = false;
            blackCheckmated = false;

            nextMoveCount = 0;

            originSquareSelected = false;

            dupChessboard = null;

            dupOriginSquare = null;
            dupTargetSquare = null;

            dupMoveList = null;

            dupAttackList = null;
            dupDefendList = null;

            currentAttackList = new ArrayList<Move>();
            currentDefendList = new ArrayList<Move>();

            gameRecording = new ArrayList<Move>();

        } else if (mode.equals("oldGame")){
            findViewById(R.id.randomButton).setClickable(false);
            findViewById(R.id.randomButton).setVisibility(View.GONE);
            findViewById(R.id.nextButton).setClickable(true);
            findViewById(R.id.nextButton).setVisibility(View.VISIBLE);
            findViewById(R.id.drawButton).setClickable(false);
            findViewById(R.id.drawButton).setVisibility(View.GONE);
            findViewById(R.id.undoButton).setClickable(false);
            findViewById(R.id.undoButton).setVisibility(View.GONE);
            findViewById(R.id.resignButton).setClickable(false);
            findViewById(R.id.resignButton).setVisibility(View.GONE);

            ((TextView)findViewById(R.id.textView)).setText("Game Playback");
        }

        chessboard=Square.createChessboard();
        //don't forget calendar

        imageBoard[0][0]=(ImageButton) findViewById(R.id.a8);
        imageBoard[0][1]=(ImageButton) findViewById(R.id.b8);
        imageBoard[0][2]=(ImageButton) findViewById(R.id.c8);
        imageBoard[0][3]=(ImageButton) findViewById(R.id.d8);
        imageBoard[0][4]=(ImageButton) findViewById(R.id.e8);
        imageBoard[0][5]=(ImageButton) findViewById(R.id.f8);
        imageBoard[0][6]=(ImageButton) findViewById(R.id.g8);
        imageBoard[0][7]=(ImageButton) findViewById(R.id.h8);

        imageBoard[1][0]=(ImageButton) findViewById(R.id.a7);
        imageBoard[1][1]=(ImageButton) findViewById(R.id.b7);
        imageBoard[1][2]=(ImageButton) findViewById(R.id.c7);
        imageBoard[1][3]=(ImageButton) findViewById(R.id.d7);
        imageBoard[1][4]=(ImageButton) findViewById(R.id.e7);
        imageBoard[1][5]=(ImageButton) findViewById(R.id.f7);
        imageBoard[1][6]=(ImageButton) findViewById(R.id.g7);
        imageBoard[1][7]=(ImageButton) findViewById(R.id.h7);

        imageBoard[2][0]=(ImageButton) findViewById(R.id.a6);
        imageBoard[2][1]=(ImageButton) findViewById(R.id.b6);
        imageBoard[2][2]=(ImageButton) findViewById(R.id.c6);
        imageBoard[2][3]=(ImageButton) findViewById(R.id.d6);
        imageBoard[2][4]=(ImageButton) findViewById(R.id.e6);
        imageBoard[2][5]=(ImageButton) findViewById(R.id.f6);
        imageBoard[2][6]=(ImageButton) findViewById(R.id.g6);
        imageBoard[2][7]=(ImageButton) findViewById(R.id.h6);

        imageBoard[3][0]=(ImageButton) findViewById(R.id.a5);
        imageBoard[3][1]=(ImageButton) findViewById(R.id.b5);
        imageBoard[3][2]=(ImageButton) findViewById(R.id.c5);
        imageBoard[3][3]=(ImageButton) findViewById(R.id.d5);
        imageBoard[3][4]=(ImageButton) findViewById(R.id.e5);
        imageBoard[3][5]=(ImageButton) findViewById(R.id.f5);
        imageBoard[3][6]=(ImageButton) findViewById(R.id.g5);
        imageBoard[3][7]=(ImageButton) findViewById(R.id.h5);

        imageBoard[4][0]=(ImageButton) findViewById(R.id.a4);
        imageBoard[4][1]=(ImageButton) findViewById(R.id.b4);
        imageBoard[4][2]=(ImageButton) findViewById(R.id.c4);
        imageBoard[4][3]=(ImageButton) findViewById(R.id.d4);
        imageBoard[4][4]=(ImageButton) findViewById(R.id.e4);
        imageBoard[4][5]=(ImageButton) findViewById(R.id.f4);
        imageBoard[4][6]=(ImageButton) findViewById(R.id.g4);
        imageBoard[4][7]=(ImageButton) findViewById(R.id.h4);

        imageBoard[5][0]=(ImageButton) findViewById(R.id.a3);
        imageBoard[5][1]=(ImageButton) findViewById(R.id.b3);
        imageBoard[5][2]=(ImageButton) findViewById(R.id.c3);
        imageBoard[5][3]=(ImageButton) findViewById(R.id.d3);
        imageBoard[5][4]=(ImageButton) findViewById(R.id.e3);
        imageBoard[5][5]=(ImageButton) findViewById(R.id.f3);
        imageBoard[5][6]=(ImageButton) findViewById(R.id.g3);
        imageBoard[5][7]=(ImageButton) findViewById(R.id.h3);

        imageBoard[6][0]=(ImageButton) findViewById(R.id.a2);
        imageBoard[6][1]=(ImageButton) findViewById(R.id.b2);
        imageBoard[6][2]=(ImageButton) findViewById(R.id.c2);
        imageBoard[6][3]=(ImageButton) findViewById(R.id.d2);
        imageBoard[6][4]=(ImageButton) findViewById(R.id.e2);
        imageBoard[6][5]=(ImageButton) findViewById(R.id.f2);
        imageBoard[6][6]=(ImageButton) findViewById(R.id.g2);
        imageBoard[6][7]=(ImageButton) findViewById(R.id.h2);

        imageBoard[7][0]=(ImageButton) findViewById(R.id.a1);
        imageBoard[7][1]=(ImageButton) findViewById(R.id.b1);
        imageBoard[7][2]=(ImageButton) findViewById(R.id.c1);
        imageBoard[7][3]=(ImageButton) findViewById(R.id.d1);
        imageBoard[7][4]=(ImageButton) findViewById(R.id.e1);
        imageBoard[7][5]=(ImageButton) findViewById(R.id.f1);
        imageBoard[7][6]=(ImageButton) findViewById(R.id.g1);
        imageBoard[7][7]=(ImageButton) findViewById(R.id.h1);

        //to keep track of game start time
        //Calendar timeOfCurrentGame = Calendar.getInstance();

        Square.printChessboard(chessboard);
        // Inflate the menu from xml
        /*
        Intent i=new Intent(this,SaveGame.class);
        startActivity(i);*/

    }

    public void onClick(View v){

        /*
            Conditions for choosing origin square
            i.e. the square whose piece is being moved
         */

        /*
            If originSquare is not set
         */
        checkString="";
        if(whiteInCheck&&currentMove.equals("White")) checkString="White in check!\n";
        if(blackInCheck&&currentMove.equals("Black")) checkString="Black in check!\n";

        if(!originSquareSelected) {
            if (Square.getSquareFromCoords(chessboard, (String) v.getTag()).getPiece() == null) {
                ((TextView)findViewById(R.id.textView)).setText(
                        checkString+currentMove+" to move");
                return;
            } else if (!currentMove.equals(Square.
                    getSquareFromCoords(chessboard, (String) v.getTag()).getPiece().getColor())) {
                ((TextView)findViewById(R.id.textView)).setText(
                        checkString+currentMove+" to move");
                return;
            } else if (currentMove.equals(Square.
                    getSquareFromCoords(chessboard, (String) v.getTag()).getPiece().getColor())) {
                ((TextView)findViewById(R.id.textView)).setText(
                        checkString+currentMove+" to move\n"+(String) v.getTag()+" selected"); //maybe consider putting piece in textView below
                originSquare = Square.
                        getSquareFromCoords(chessboard, (String) v.getTag());
                Square.printChessboard(chessboard);
                originSquareSelected=true;
                return;
                //tag
            }
        }
        /*
            Continues here if originSquare is indeed set
         */

        /*
            If player's own piece is selected, reselect origin square to that piece
         */

        targetSquare = Square.getSquareFromCoords(chessboard, (String) v.getTag());

            if (Piece.pieceOnSquare(chessboard,
                    targetSquare.getRankNum(),
                    targetSquare.getFileNum()) &&
                    currentMove.equals(Square.
                    getSquareFromCoords(chessboard, (String) v.getTag()).getPiece().getColor())) {
                 //maybe consider putting piece in textView below
                originSquare = Square.getSquareFromCoords(chessboard, (String) v.getTag());
                ((TextView)findViewById(R.id.textView)).setText(
                        checkString+currentMove+" to move\n"+originSquare.getFileName()
                                +originSquare.getRankName()+" selected");
                return;
            }

            currentAttackList = new ArrayList<Move>();
            currentDefendList = new ArrayList<Move>();

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Piece.pieceOnSquare(chessboard, i, j)) {
                        if (chessboard[i][j].getPiece().getColor().equals(currentMove)) {
                            chessboard[i][j].getPiece().addAttacks(chessboard, currentAttackList, i, j);
                        } else if (chessboard[i][j].getPiece().getColor().equals(nextMove)) {
                            chessboard[i][j].getPiece().addAttacks(chessboard, currentDefendList, i, j);
                        }
                    }
                }
            }

            //currentAttackList included because we need to know,
            //like in the case of castling, whether moves are invalid because a square is attacked.
            ArrayList<Move> currentMoveList = originSquare.getPiece().
                    getMoveList(chessboard,
                            originSquare.getRankNum(), originSquare.getFileNum(), currentDefendList);

            if (!targetSquare.squareIsOnMoveList(currentMoveList)) {
                ((TextView) findViewById(R.id.textView)).setText(
                        currentMove + " to move");
                return;
            }

            //we will check to see whether a move will put the King in check and render it invalid.
            dupChessboard = Square.duplicateChessboard(chessboard);

            dupOriginSquare = Square.getSquareFromCoords(dupChessboard,
                    originSquare.getRankName(), originSquare.getFileName());
            dupTargetSquare = Square.getSquareFromCoords(dupChessboard,
                    targetSquare.getRankName(), targetSquare.getFileName());

            dupMoveList = dupOriginSquare.getPiece().
                    getMoveList(dupChessboard,
                            dupOriginSquare.getRankNum(),
                            dupOriginSquare.getFileNum(), currentDefendList);

            dupOriginSquare.getPiece().movePiece(dupChessboard, dupOriginSquare, dupTargetSquare, dupMoveList);

            dupAttackList = new ArrayList<Move>();
            dupDefendList = new ArrayList<Move>();
            /*
                repopulate with attacks and defenses
                after simulation of move to see if it is legal
            */
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Piece.pieceOnSquare(dupChessboard, i, j)) {
                        if (dupChessboard[i][j].getPiece().getColor().equals(nextMove)) {
                            dupChessboard[i][j].getPiece().addAttacks(dupChessboard, dupAttackList, i, j);
                        } else if (dupChessboard[i][j].getPiece().getColor().equals(currentMove)) {
                            dupChessboard[i][j].getPiece().addAttacks(dupChessboard, dupDefendList, i, j);
                        }
                    }
                }
            }

            if (Piece.checkCheck(nextMove, dupChessboard, dupAttackList)) {
                ((TextView) findViewById(R.id.textView)).setText(
                        currentMove + " to move\n" + "Illegal move, try again");
                return;
            }

            /*
            handle promotions
             */

        if(whiteInCheck) whiteInCheck=false;
        if(blackInCheck) blackInCheck=false;

        int inheritedId=originSquare.getPiece().getId();
        promotion=false;
        if(currentMove.equals("White") &&
            originSquare.getPiece().getColor().equals("White") &&
            originSquare.getPiece() instanceof Pawn &&
                originSquare.getRankName()=='7' &&
                targetSquare.getRankName()=='8'
        ) // return true or false, also try {!myObjectReference.myField == true}
        {
            promotion=true;
            successfulMove=true;
            PopupMenu popup = new PopupMenu(this, v);
            // Inflate the menu from xml
            popup.inflate(R.menu.menu_main);
            // Setup menu item selection
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Move addedPromo;
                    switch (item.getItemId()) {
                        case R.id.menu_queen:
                            addedPromo=targetSquare.retrievePieceMoveFromList(
                                    originSquare.getPiece(),currentAttackList);
                            addedPromo.setDirection("promotionQ");
                            originSquare.setPiece(null);
                            targetSquare.setPiece(new Queen("White",inheritedId));
                            Square.printChessboard(chessboard);
                            setCheckmateFlags();
                            return true;
                        case R.id.menu_rook:
                            addedPromo=targetSquare.retrievePieceMoveFromList(
                                    originSquare.getPiece(),currentAttackList);
                            addedPromo.setDirection("promotionR");
                            originSquare.setPiece(null);
                            targetSquare.setPiece(new Rook("White",inheritedId));
                            Square.printChessboard(chessboard);
                            setCheckmateFlags();
                            return true;
                        case R.id.menu_knight:
                            addedPromo=targetSquare.retrievePieceMoveFromList(
                                    originSquare.getPiece(),currentAttackList);
                            addedPromo.setDirection("promotionN");
                            originSquare.setPiece(null);
                            targetSquare.setPiece(new Knight("White",inheritedId));
                            Square.printChessboard(chessboard);
                            setCheckmateFlags();
                            return true;
                        case R.id.menu_bishop:
                            addedPromo=targetSquare.retrievePieceMoveFromList(
                                    originSquare.getPiece(),currentAttackList);
                            addedPromo.setDirection("promotionB");
                            originSquare.setPiece(null);
                            targetSquare.setPiece(new Bishop("White",inheritedId));
                            Square.printChessboard(chessboard);
                            setCheckmateFlags();
                            return true;

                        default:
                            return false;
                    }
                }//need to append the move as well
            });
            // Show the menu
            popup.show();
        }
        else if(currentMove.equals("Black") &&
                originSquare.getPiece().getColor().equals("Black") &&
                originSquare.getPiece() instanceof Pawn &&
                originSquare.getRankName()=='2' &&
                targetSquare.getRankName()=='1')
        {
            promotion=true;
            successfulMove=true;

            PopupMenu popup = new PopupMenu(this, v);
            // Inflate the menu from xml
            popup.inflate(R.menu.menu_main);
            // Setup menu item selection
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Move addedPromo;
                    switch (item.getItemId()) {
                        case R.id.menu_queen:
                            addedPromo=targetSquare.retrievePieceMoveFromList(
                                    originSquare.getPiece(),currentAttackList);
                            addedPromo.setDirection("promotionQ");
                            gameRecording.add(addedPromo);
                            originSquare.setPiece(null);
                            targetSquare.setPiece(new Queen("Black",inheritedId));
                            Square.printChessboard(chessboard);
                            setCheckmateFlags();
                            return true;
                        case R.id.menu_rook:
                            addedPromo=targetSquare.retrievePieceMoveFromList(
                                    originSquare.getPiece(),currentAttackList);
                            addedPromo.setDirection("promotionR");
                            gameRecording.add(addedPromo);
                            originSquare.setPiece(null);
                            targetSquare.setPiece(new Rook("Black",inheritedId));
                            Square.printChessboard(chessboard);
                            setCheckmateFlags();
                            return true;
                        case R.id.menu_knight:
                            addedPromo=targetSquare.retrievePieceMoveFromList(
                                    originSquare.getPiece(),currentAttackList);
                            addedPromo.setDirection("promotionN");
                            gameRecording.add(addedPromo);
                            originSquare.setPiece(null);
                            targetSquare.setPiece(new Knight("Black",inheritedId));
                            Square.printChessboard(chessboard);
                            setCheckmateFlags();
                            return true;
                        case R.id.menu_bishop:
                            addedPromo=targetSquare.retrievePieceMoveFromList(
                                            originSquare.getPiece(),currentAttackList);
                            addedPromo.setDirection("promotionB");
                            gameRecording.add(addedPromo);//need to tell to append promotion value
                            originSquare.setPiece(null);
                            targetSquare.setPiece(new Bishop("Black",inheritedId));
                            Square.printChessboard(chessboard);

                            setCheckmateFlags();
                            return true;
                        default:
                            return false;
                    }
                }
            });
            // Show the menu
            popup.show();

        }

        if(!promotion) {
            gameRecording.add(targetSquare.
                    retrievePieceMoveFromList(originSquare.getPiece(), currentAttackList));
            originSquare.getPiece().movePiece(chessboard,originSquare,targetSquare,currentMoveList);
        }
        /*
            Checking for castling rights
         */
        int srcRank=originSquare.getRankNum();
        int srcFile=originSquare.getFileNum();
        int destRank=targetSquare.getRankNum();
        int destFile=targetSquare.getFileNum();

        if( (srcRank==7 && srcFile==0) || destRank==7 && destFile==0 )
            Piece.leftWhiteRookMoved=true;
        if( (srcRank==7 && srcFile==7) || destRank==7 && destFile==7 )
            Piece.rightWhiteRookMoved=true;
        if( (srcRank==0 && srcFile==0) || destRank==0 && destFile==0 )
            Piece.leftBlackRookMoved=true;
        if( (srcRank==0 && srcFile==7) || destRank==0 && destFile==7 )
            Piece.rightBlackRookMoved=true;
        if( (srcRank==0 && srcFile==4) || destRank==0 && destFile==4 )
            Piece.blackKingMoved=true;
        if( (srcRank==7 && srcFile==4) || destRank==7 && destFile==4 )
            Piece.whiteKingMoved=true;

        checkString="";
            Square.printChessboard(chessboard);
        if(whiteInCheck&&nextMove.equals("White")) checkString="White in check!\n";
        if(blackInCheck&&nextMove.equals("Black")) checkString="Black in check!\n";

        if(promotion) successfulMove=false;
        else successfulMove=true;
        setCheckmateFlags();
            // Printing the read line

        return;
    }


    public void handleDraw(View v){
        ((TextView) findViewById(R.id.textView)).setText(
                "The game has ended in a draw.");

        findViewById(R.id.randomButton).setClickable(false);
        findViewById(R.id.randomButton).setVisibility(View.GONE);
        findViewById(R.id.nextButton).setClickable(false);
        findViewById(R.id.nextButton).setVisibility(View.GONE);
        findViewById(R.id.drawButton).setClickable(false);
        findViewById(R.id.drawButton).setVisibility(View.GONE);
        findViewById(R.id.undoButton).setClickable(false);
        findViewById(R.id.undoButton).setVisibility(View.GONE);
        findViewById(R.id.resignButton).setClickable(false);
        findViewById(R.id.resignButton).setVisibility(View.GONE);

        Intent intent = new Intent(MainActivity.this,SaveGame.class);

        processedGame=new Game("",
                gCal,"The game has ended in a draw.",gameRecording);
        startActivity(intent);

    }

    public void handleResign(View v){
        ((TextView) findViewById(R.id.textView)).setText(
                nextMove+" wins by resignation!");

        findViewById(R.id.randomButton).setClickable(false);
        findViewById(R.id.randomButton).setVisibility(View.GONE);
        findViewById(R.id.nextButton).setClickable(false);
        findViewById(R.id.nextButton).setVisibility(View.GONE);
        findViewById(R.id.drawButton).setClickable(false);
        findViewById(R.id.drawButton).setVisibility(View.GONE);
        findViewById(R.id.undoButton).setClickable(false);
        findViewById(R.id.undoButton).setVisibility(View.GONE);
        findViewById(R.id.resignButton).setClickable(false);
        findViewById(R.id.resignButton).setVisibility(View.GONE);

        Intent intent = new Intent(MainActivity.this,SaveGame.class);

        processedGame=new Game("",
                gCal,nextMove+" wins by resignation!",gameRecording);
        startActivity(intent);
    }

    public void handleRandom(View v) {
        successfulMove = false;
        int pieceRandomizer;
        int moveRandomizer;

        ArrayList<Square>pieceMoveCandidateList;
        Move candidateMove=null;

        while(!successfulMove){
            currentAttackList = new ArrayList<Move>();
            currentDefendList = new ArrayList<Move>();
            pieceMoveCandidateList = new ArrayList<Square>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Piece.pieceOnSquare(chessboard, i, j)) {
                        if (chessboard[i][j].getPiece().getColor().equals(currentMove)) {
                            pieceMoveCandidateList.add(chessboard[i][j]);
                            chessboard[i][j].getPiece().addAttacks(chessboard, currentAttackList, i, j);
                        } else if (chessboard[i][j].getPiece().getColor().equals(nextMove)) {
                            chessboard[i][j].getPiece().addAttacks(chessboard, currentDefendList, i, j);
                        }
                    }
                }
            }
            ArrayList<Move> currentMoveList=new ArrayList<Move>();
            do {
                pieceRandomizer = (int) (Math.random() * (pieceMoveCandidateList.size()));
                originSquare = pieceMoveCandidateList.get(pieceRandomizer);


                //currentAttackList included because we need to know,
                //like in the case of castling, whether moves are invalid because a square is attacked.
                currentMoveList = originSquare.getPiece().
                        getMoveList(chessboard,
                                originSquare.getRankNum(), originSquare.getFileNum(), currentDefendList);
            } while (currentMoveList.isEmpty());
            moveRandomizer= (int)(Math.random()*currentMoveList.size());
            candidateMove=currentMoveList.get(moveRandomizer);
            targetSquare=chessboard[candidateMove.getRank()][candidateMove.getFile()];

            //we will check to see whether a move will put the King in check and render it invalid.
            dupChessboard = Square.duplicateChessboard(chessboard);

            dupOriginSquare = Square.getSquareFromCoords(dupChessboard,
                    originSquare.getRankName(), originSquare.getFileName());
            dupTargetSquare = Square.getSquareFromCoords(dupChessboard,
                    targetSquare.getRankName(), targetSquare.getFileName());

            dupMoveList = dupOriginSquare.getPiece().
                    getMoveList(dupChessboard,
                            dupOriginSquare.getRankNum(),
                            dupOriginSquare.getFileNum(), currentDefendList);

            dupOriginSquare.getPiece().movePiece(dupChessboard, dupOriginSquare, dupTargetSquare, dupMoveList);

            dupAttackList = new ArrayList<Move>();
            dupDefendList = new ArrayList<Move>();
                /*
                    repopulate with attacks and defenses
                    after simulation of move to see if it is legal
                */
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Piece.pieceOnSquare(dupChessboard, i, j)) {
                        if (dupChessboard[i][j].getPiece().getColor().equals(nextMove)) {
                            dupChessboard[i][j].getPiece().addAttacks(dupChessboard, dupAttackList, i, j);
                        } else if (dupChessboard[i][j].getPiece().getColor().equals(currentMove)) {
                            dupChessboard[i][j].getPiece().addAttacks(dupChessboard, dupDefendList, i, j);
                        }
                    }
                }
            }

            if (Piece.checkCheck(nextMove, dupChessboard, dupAttackList)) {
                successfulMove = false;
            }else successfulMove=true;
            //should return to top of loop
        }
        if(currentMove.equals("White") &&
                originSquare.getPiece().getColor().equals("White") &&
                originSquare.getPiece() instanceof Pawn &&
                originSquare.getRankName()=='7' &&
                targetSquare.getRankName()=='8'){
            candidateMove.setDirection("promotionQ");
            gameRecording.add(candidateMove);
            targetSquare.setPiece(new Queen("White",originSquare.getPiece().getId()));
            originSquare.setPiece(null);
        }
        else if(currentMove.equals("Black") &&
                originSquare.getPiece().getColor().equals("Black") &&
                originSquare.getPiece() instanceof Pawn &&
                originSquare.getRankName()=='2' &&
                targetSquare.getRankName()=='1'){
            candidateMove.setDirection("promotionQ");
            gameRecording.add(candidateMove);
            targetSquare.setPiece(new Queen("Black",originSquare.getPiece().getId()));
            originSquare.setPiece(null);
        } else {//deals with 'normal' piece movement
            originSquare.getPiece().movePiece(chessboard,
                    originSquare, targetSquare, candidateMove);
            gameRecording.add(candidateMove);
        }
        successfulMove=true;
        Square.printChessboard(chessboard);
        setCheckmateFlags();



    }

    public void handleNext(View v){
        Move currentMove=gameRecording.get(nextMoveCount);
        nextMoveCount++;

        Square srcSquare=Square.findPieceSquareFromId(chessboard,currentMove.getId());
        srcSquare.getPiece().movePiece(
                chessboard,srcSquare, chessboard[currentMove.getRank()][currentMove.getFile()],
                currentMove);

        Square.printChessboard(chessboard);
        if(nextMoveCount==gameRecording.size()){
            findViewById(R.id.nextButton).setClickable(false);
            findViewById(R.id.nextButton).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.textView)).setText(resultRecord);
        }
    }

    public void handleUndo(View v){
        gameRecording.remove(gameRecording.size()-1);
        chessboard=Square.createChessboard();
        for(Move m: gameRecording){
            Square srcSquare=Square.findPieceSquareFromId(chessboard,m.getId());
            srcSquare.getPiece().movePiece(
                    chessboard,srcSquare, chessboard[m.getRank()][m.getFile()],
                    m);
        }

        successfulMove=true;
        setCheckmateFlags();
        findViewById(R.id.undoButton).setClickable(false);
        findViewById(R.id.undoButton).setVisibility(View.GONE);


        Square.printChessboard(chessboard);
    }

    public void setCheckmateFlags(){
        findViewById(R.id.undoButton).setVisibility(View.VISIBLE);
        findViewById(R.id.undoButton).setClickable(true);

        currentAttackList=new ArrayList<Move>();
        currentDefendList=new ArrayList<Move>();

        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                if(Piece.pieceOnSquare(chessboard,i,j)) {
                    if(chessboard[i][j].getPiece().getColor().equals(currentMove)) {
                        chessboard[i][j].getPiece().addAttacks(chessboard,currentAttackList,i,j);
                    } else if (chessboard[i][j].getPiece().getColor().equals(nextMove)) {
                        chessboard[i][j].getPiece().addAttacks(chessboard,currentDefendList,i,j);
                    }
                }
            }
        }
        if(currentMove.equals("White")) {
            whiteInCheck=false;
        }
        else {
            blackInCheck=false;
        }
        checkString="";


        if(Piece.checkCheck(currentMove,chessboard,currentAttackList)) {
            if(currentMove.equals("White")) blackInCheck=true;
            else whiteInCheck=true;
            boolean nowhereToGo=Piece.checkCheckmate(currentMove, chessboard, currentAttackList);
            Piece.reviseMoveList(currentMove, chessboard,
                    currentAttackList, currentDefendList);
            Piece.findKing(nextMove, chessboard).squareIsOnMoveList(dupDefendList);
            boolean nowhereToHide=Piece.checkCheck(currentMove,chessboard,currentAttackList);
            if (nowhereToGo && nowhereToHide) {
                if(whiteInCheck) whiteCheckmated=true;
                if(blackInCheck) blackCheckmated=true;
                Square.printChessboard(chessboard);
                findViewById(R.id.randomButton).setClickable(false);
                findViewById(R.id.nextButton).setClickable(false);
                findViewById(R.id.drawButton).setClickable(false);
                findViewById(R.id.undoButton).setClickable(false);
                findViewById(R.id.resignButton).setClickable(false);

                findViewById(R.id.randomButton).setVisibility(View.GONE);
                findViewById(R.id.nextButton).setVisibility(View.GONE);
                findViewById(R.id.drawButton).setVisibility(View.GONE);
                findViewById(R.id.undoButton).setVisibility(View.GONE);
                findViewById(R.id.resignButton).setVisibility(View.GONE);

                for(int i=0;i<8;i++){
                    for (int j=0;j<8;j++){
                        imageBoard[i][j].setClickable(false);
                    }
                }
            }
        }

        if(whiteInCheck&&nextMove.equals("White")) checkString="White in check!\n";
        if(blackInCheck&&nextMove.equals("Black")) checkString="Black in check!\n";


        ((TextView) findViewById(R.id.textView)).setText(
                checkString+nextMove + " to move");
        if(successfulMove) {
            String temp = currentMove;
            currentMove = nextMove;
            nextMove = temp;

        }

        if(whiteCheckmated){
            ((TextView) findViewById(R.id.textView)).setText(
                    "Checkmate!\nBlack wins");
            processedGame=new Game("",gCal,
                    "Black wins by checkmate!",gameRecording);

            Intent intent = new Intent(this,SaveGame.class);
            startActivity(intent);
        }
        else if(blackCheckmated){
            ((TextView) findViewById(R.id.textView)).setText(
                    "Checkmate!\nWhite wins");
            processedGame=new Game("",gCal,
                    "White wins by checkmate!",gameRecording);

            Intent intent = new Intent(this,SaveGame.class);
            startActivity(intent);
        }

        originSquareSelected=false;
        originSquare=null;



        findViewById(R.id.resignButton).setClickable(true);
        findViewById(R.id.resignButton).setVisibility(View.VISIBLE);
        findViewById(R.id.drawButton).setClickable(true);
        findViewById(R.id.drawButton).setVisibility(View.VISIBLE);
        successfulMove=false;
        return;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}