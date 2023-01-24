package com.example.android57;

import java.util.ArrayList;

/** 
* Class for knight piece
* Dictates behavior for knight piece
* 
* @author Haoran Lyu
* @author Christopher Velasquez
* 
*/
public class Knight extends Piece{
	
	/** 
	* Creates knight instance
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public Knight(String color, int id) {
		setColor(color);
		setId(id);
	}
	
	/** 
	* Prints out color initial and piece initial for knight
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void printPiece() {
		printColor();
		System.out.print("N");
	}	
	
	/** 
	* Get move list of a specified knight on a given rank and file
	* while considering the opposing attack list
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return list of valid moves for the current piece
	* 
	*/
	public ArrayList<Move> getMoveList(Square[][] chessboard,int rank, int file,ArrayList<Move> currentAttackList){
		ArrayList<Move> moveList=new ArrayList<Move>();
		if(this.checkMovableTo(chessboard,rank-2,file-1))
			moveList.add(new Move(this.getId(),1,"knightJump1",rank-2,file-1));
		
		if(this.checkMovableTo(chessboard,rank-2,file+1))
			moveList.add(new Move(this.getId(),1,"knightJump2",rank-2,file+1));
		
		if(this.checkMovableTo(chessboard,rank-1,file-2))
			moveList.add(new Move(this.getId(),1,"knightJump3",rank-1,file-2));
		
		if(this.checkMovableTo(chessboard,rank-1,file+2))
			moveList.add(new Move(this.getId(),1,"knightJump4",rank-1,file+2));
		
		if(this.checkMovableTo(chessboard,rank+1,file-2))
			moveList.add(new Move(this.getId(),1,"knightJump5",rank+1,file-2));
		
		if(this.checkMovableTo(chessboard,rank+1,file+2))
			moveList.add(new Move(this.getId(),1,"knightJump6",rank+1,file+2));
		
		if(this.checkMovableTo(chessboard,rank+2,file-1))
			moveList.add(new Move(this.getId(),1,"knightJump7",rank+2,file-1));
		
		if(this.checkMovableTo(chessboard,rank+2,file+1))
			moveList.add(new Move(this.getId(),1,"knightJump8",rank+2,file+1));
		
		return moveList;
	}
	
	/** 
	* Adds squares a knight attacks and can block from to a certain list given the knight's rank and file
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param rank of piece
	* @param file of piece
	* 
	*/
	public void addAttacks(Square[][] chessboard,ArrayList<Move> attackList,int rank, int file) {
		
		if(this.checkMovableTo(chessboard,rank-2,file-1))
			attackList.add(new Move(this.getId(),1,"knightJump1",rank-2,file-1));
		
		if(this.checkMovableTo(chessboard,rank-2,file+1))
			attackList.add(new Move(this.getId(),1,"knightJump2",rank-2,file+1));
		
		if(this.checkMovableTo(chessboard,rank-1,file-2))
			attackList.add(new Move(this.getId(),1,"knightJump3",rank-1,file-2));
		
		if(this.checkMovableTo(chessboard,rank-1,file+2))
			attackList.add(new Move(this.getId(),1,"knightJump4",rank-1,file+2));
		
		if(this.checkMovableTo(chessboard,rank+1,file-2))
			attackList.add(new Move(this.getId(),1,"knightJump5",rank+1,file-2));
		
		if(this.checkMovableTo(chessboard,rank+1,file+2))
			attackList.add(new Move(this.getId(),1,"knightJump6",rank+1,file+2));
		
		if(this.checkMovableTo(chessboard,rank+2,file-1))
			attackList.add(new Move(this.getId(),1,"knightJump7",rank+2,file-1));
		
		if(this.checkMovableTo(chessboard,rank+2,file+1))
			attackList.add(new Move(this.getId(),1,"knightJump8",rank+2,file+1));
	}
	
	/** 
	* Moves knight on a specified chessboard from its origin square to a destination square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void movePiece(Square[][] chessboard,Square origin, Square destination,
			ArrayList<Move> moveList) {

		destination.setPiece(origin.getPiece());
		origin.setPiece(null);
		enPassantSquare=null;
	}

	public void movePiece(Square[][] chessboard,Square origin, Square destination,
								   Move move){

		destination.setPiece(origin.getPiece());
		origin.setPiece(null);
		enPassantSquare=null;
	}
}
