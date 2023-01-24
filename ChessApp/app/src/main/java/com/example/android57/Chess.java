package com.example.android57;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 
* Class for the game of Chess
* 
* @author Haoran Lyu
* @author Christopher Velasquez
* 
*/

public class Chess {

	/** 
	* Initiates a game of Chess
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	/*
    public static void main(String[] args)
        throws IOException
    {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
        // Reading data using readLine
        String moveInput="";
        String currentMove="White";
        String nextMove="Black";
        boolean successfulMove=false;
        boolean whiteOfferedDraw=false;
        boolean blackOfferedDraw=false;
        boolean inCheck=false;
        
        Square[][] chessboard=Square.createChessboard();
        Square.printChessboard(chessboard);
        System.out.println();
        while(!moveInput.equals("end")){
        	
        	if(successfulMove) { //checks to see if a move was successfully made the turn before
            	if(currentMove.equals("White")) {
            		currentMove="Black";
            		nextMove="White";
            		if(blackOfferedDraw) blackOfferedDraw=false;
            	}
            	else if(currentMove.equals("Black")) {
            		currentMove="White";
            		nextMove="Black";
            		if(whiteOfferedDraw) whiteOfferedDraw=false;
            	}
            	successfulMove=false;
            	if(inCheck) System.out.println("Check" );
            	inCheck=false;
            }
        	
        	if(currentMove.equals("White")) System.out.print("White's Move: ");
        	else System.out.print("Black's Move: ");
        	
        	
        	
        	moveInput = reader.readLine();
        	System.out.println();
        	if((whiteOfferedDraw && currentMove.equals("Black")) && !moveInput.equals("draw")) {
        		System.out.println("Illegal move, try again");
        		continue;
        	} else if ((blackOfferedDraw && currentMove.equals("White")) && !moveInput.equals("draw")) {
        		System.out.println("Illegal move, try again");
        		continue;
        	} else if ((whiteOfferedDraw || blackOfferedDraw) && moveInput.equals("draw")) {
        		System.out.println("draw");
        		return;
        	}
        	if(moveInput.length()>=6 && moveInput.equals("resign")) {
        		System.out.println();
        		System.out.println(nextMove+" wins");
        		return;
        	} else if(moveInput.length()>=6 && moveInput.substring(6).equals("draw?")) {
        		if(currentMove.equals("White")) {
        			whiteOfferedDraw=true;
        			
        		} else if(currentMove.equals("Black")) {
        			blackOfferedDraw=true;
        			
        		}
        		
        		
        		
        	}
        	
        	//prevents bad input
        	if(moveInput.length()<5 || 
        			Character.compare(moveInput.charAt(0),'a')<0 ||
        			Character.compare(moveInput.charAt(0),'h')>0 ||
        			Character.compare(moveInput.charAt(3),'a')<0 ||
        			Character.compare(moveInput.charAt(3),'h')>0 ||
        			Character.compare(moveInput.charAt(1),'1')<0 ||
        			Character.compare(moveInput.charAt(1),'8')>0 ||
        			Character.compare(moveInput.charAt(4),'1')<0 ||
        			Character.compare(moveInput.charAt(4),'8')>0) {
            	System.out.println("Illegal move, try again");
            	continue;
            }
        	
        	char srcFile=moveInput.charAt(0);
        	char srcRank=moveInput.charAt(1);
        	char destFile=moveInput.charAt(3);
        	char destRank=moveInput.charAt(4);
        	
        	
        	if(Square.getSquareFromCoords(chessboard, srcRank, srcFile).getPiece()==null){
        		System.out.println("Illegal move, try again");
        		continue;
        	}
        	
        	if(!currentMove.equals(Square.getSquareFromCoords(chessboard, srcRank, srcFile).getPiece().getColor())) {
        		System.out.println("Illegal move, try again");
        		continue;
        	}
        	
        	if(srcFile==destFile && srcRank==destRank) {
        		System.out.println("Illegal move, try again");
        		continue;
        	}
        	
        	
        	ArrayList<Move> currentAttackList=new ArrayList<Move>();
        	ArrayList<Move> currentDefendList=new ArrayList<Move>();
        	
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
        	//reviseMoveList(ArrayList<Move> attackList, ArrayList<Move> defendList);
        	Square originSquare=Square.getSquareFromCoords(chessboard, srcRank, srcFile);
        	Square targetSquare=Square.getSquareFromCoords(chessboard, destRank, destFile);
        	
        	//currentAttackList included because we need to know, 
        	//like in the case of castling, whether moves are invalid because a square is attacked.
        	ArrayList<Move> currentMoveList=originSquare.getPiece().
        			getMoveList(chessboard,
        			originSquare.getRankNum(),originSquare.getFileNum(),currentDefendList);
        	
        	if(!targetSquare.squareIsOnMoveList(currentMoveList)) {
        		System.out.println("Illegal move, try again");
        		continue;
        	}
        	
        	//we will check to see whether a move will put the King in check and render it invalid.
        	Square[][] dupChessboard=Square.duplicateChessboard(chessboard);
        	
        	Square dupOriginSquare=Square.getSquareFromCoords(dupChessboard, srcRank, srcFile);
        	Square dupTargetSquare=Square.getSquareFromCoords(dupChessboard, destRank, destFile);
        	
        	ArrayList<Move> dupMoveList=dupOriginSquare.getPiece().
        			getMoveList(dupChessboard,
        			dupOriginSquare.getRankNum(),dupOriginSquare.getFileNum(),currentDefendList);
        	
        	ArrayList<Move> dupAttackList=new ArrayList<Move>();
        	ArrayList<Move> dupDefendList=new ArrayList<Move>();
        	
        	//populate with initial attacks and defenses
        	for(int i=0;i<8;i++) {
        		for(int j=0;j<8;j++) {
        			if(Piece.pieceOnSquare(dupChessboard,i,j)) {
        				if(dupChessboard[i][j].getPiece().getColor().equals(nextMove)) {
        					dupChessboard[i][j].getPiece().addAttacks(dupChessboard,dupAttackList,i,j);
        				} else if (dupChessboard[i][j].getPiece().getColor().equals(currentMove)) {
        					dupChessboard[i][j].getPiece().addAttacks(dupChessboard,dupDefendList,i,j);
        				}
        			}
        		}
        	}
        	
        	dupOriginSquare.getPiece().movePiece(dupChessboard,dupOriginSquare,dupTargetSquare,dupMoveList);
        	
        	dupAttackList=new ArrayList<Move>();
        	dupDefendList=new ArrayList<Move>();
        	
        	//repopulate with attacks and defenses after simulation of move to see if it is legal
        	for(int i=0;i<8;i++) {
        		for(int j=0;j<8;j++) {
        			if(Piece.pieceOnSquare(dupChessboard,i,j)) {
        				if(dupChessboard[i][j].getPiece().getColor().equals(nextMove)) {
        					dupChessboard[i][j].getPiece().addAttacks(dupChessboard,dupAttackList,i,j);
        				} else if (dupChessboard[i][j].getPiece().getColor().equals(currentMove)) {
        					dupChessboard[i][j].getPiece().addAttacks(dupChessboard,dupDefendList,i,j);
        				}
        			}
        		}
        	}
        	
        	if(Piece.checkCheck(nextMove,dupChessboard,dupAttackList)) {
        		System.out.println("Illegal move, try again");
        		continue;
        	}
        	boolean promotion=false;
        	//handling pawn promotions
        	if(currentMove.equals("White") &&
        			originSquare.getPiece() instanceof Pawn &&
        			targetSquare.getRankNum()==0) {
        		promotion=true;
        		if(moveInput.length()<6) {
        			targetSquare.setPiece(new Queen(currentMove,originSquare.getPiece().getId()));
        		} else {
	        		char promoteTo=moveInput.charAt(6);
	        		if(promoteTo=='N') {
	        			targetSquare.setPiece(new Knight(currentMove,originSquare.getPiece().getId()));
	        		} else if(promoteTo=='Q') {
	        			targetSquare.setPiece(new Queen(currentMove,originSquare.getPiece().getId()));
	        		} else if(promoteTo=='R') {
	        			targetSquare.setPiece(new Rook(currentMove,originSquare.getPiece().getId()));
	        		} else if(promoteTo=='B') {
	        			targetSquare.setPiece(new Bishop(currentMove,originSquare.getPiece().getId()));
	        		} 
        		}
        		originSquare.setPiece(null);
        	} else if (currentMove.equals("Black") &&
        			originSquare.getPiece() instanceof Pawn &&
        			targetSquare.getRankNum()==7) {
        		promotion=true;
        		if(moveInput.length()<6) {
        			targetSquare.setPiece(new Queen(currentMove,originSquare.getPiece().getId()));
        		} else {
        			char promoteTo=moveInput.charAt(6);
	        		if(promoteTo=='N') {
	        			targetSquare.setPiece(new Knight(currentMove,originSquare.getPiece().getId()));
	        		} else if(promoteTo=='Q') {
	        			targetSquare.setPiece(new Queen(currentMove,originSquare.getPiece().getId()));
	        		} else if(promoteTo=='R') {
	        			targetSquare.setPiece(new Rook(currentMove,originSquare.getPiece().getId()));
	        		} else if(promoteTo=='B') {
	        			targetSquare.setPiece(new Bishop(currentMove,originSquare.getPiece().getId()));
	        		} 
        		}
        		originSquare.setPiece(null);
        		
        	}
        		
        	if(!promotion) originSquare.getPiece().movePiece(chessboard,originSquare,targetSquare,currentMoveList);
        	
        	successfulMove=true;
        	
        	//checking for castling rights
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
        	
        	if(Piece.checkCheck(currentMove,chessboard,currentAttackList)) {
        		inCheck=true;
        		boolean nowhereToGo=Piece.checkCheckmate(currentMove, chessboard, currentAttackList);
        		Piece.reviseMoveList(currentMove, chessboard,
        				currentAttackList, currentDefendList);
        		Piece.findKing(nextMove, chessboard).squareIsOnMoveList(dupDefendList);
        		boolean nowhereToHide=Piece.checkCheck(currentMove,chessboard,currentAttackList);
        		if (nowhereToGo && nowhereToHide) {
        			
        			Square.printChessboard(chessboard);
        			System.out.println();
        			System.out.println("Checkmate");
        			System.out.println(currentMove+" wins");
        			System.out.println();
        			return;
        		}
        		
        	}
        	Square.printChessboard(chessboard);
        	System.out.println();
        	
        // Printing the read line
        }
        return;
    } */
}