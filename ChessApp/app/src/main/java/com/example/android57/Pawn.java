package com.example.android57;

import java.util.ArrayList;

/** 
* Class for pawn piece
* Dictates behavior for pawn piece
* 
* @author Haoran Lyu
* @author Christopher Velasquez
* 
*/
public class Pawn extends Piece{
	
	/** 
	* Creates pawn instance
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public Pawn(String color, int id) {
		setColor(color);
		setId(id);
	}
	
	/** 
	* Prints out color initial and piece initial for pawn
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void printPiece() {
		printColor();
		System.out.print("P");
	}
	
	/** 
	* Get move list of a specified pawn on a given rank and file
	* while considering the opposing attack list
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return list of valid moves for the current piece
	* 
	*/
	public ArrayList<Move> getMoveList(Square[][] chessboard,int rank, int file,ArrayList<Move> currentAttackList){
		ArrayList<Move> moveList=new ArrayList<Move>();
		if(this.getColor().equals("White")) {
			if(chessboard[rank-1][file].getPiece()==null) {
				moveList.add(new Move(this.getId(),1,"walk",rank-1,file));
				if(rank==6 && chessboard[rank-2][file].getPiece()==null) {
					moveList.add(new Move(this.getId(),1,"dash",rank-2,file));
					
				}
			}
			if(squareExists(rank, file-1) && chessboard[rank][file-1].equals(enPassantSquare)) {
				moveList.add(new Move(this.getId(),1,"enPassantNW",rank-1,file-1));
			}
			
			if(squareExists(rank, file+1) && chessboard[rank][file+1].equals(enPassantSquare)) {
				moveList.add(new Move(this.getId(),1,"enPassantNE",rank-1,file+1));
			}	
			
			if(squareExists(rank-1,file-1) && 
					chessboard[rank-1][file-1].getPiece()!=null &&
					chessboard[rank-1][file-1].getPiece().getColor().equals("Black")) {
				moveList.add(new Move(this.getId(),1,"attackNW",rank-1,file-1));
				
			}
			
			if(squareExists(rank-1,file+1) && 
					chessboard[rank-1][file+1].getPiece()!=null &&
					chessboard[rank-1][file+1].getPiece().getColor().equals("Black")) {
				moveList.add(new Move(this.getId(),1,"attackNE",rank-1,file+1));
				
			}
			
			
		}	
		
		
		if(this.getColor().equals("Black")) {
			if(squareExists(rank+1, file) && chessboard[rank+1][file].getPiece()==null) {
				moveList.add(new Move(this.getId(),1,"walk",rank+1,file));
				if(rank==1 && chessboard[rank+2][file].getPiece()==null) {
					moveList.add(new Move(this.getId(),1,"dash",rank+2,file));
					
				}
			}
			if(squareExists(rank, file-1) && chessboard[rank][file-1].equals(enPassantSquare)) {
				moveList.add(new Move(this.getId(),1,"enPassantSW",rank+1,file-1));
			}
			
			if(squareExists(rank, file+1) && chessboard[rank][file+1].equals(enPassantSquare)) {
				moveList.add(new Move(this.getId(),1,"enPassantSE",rank+1,file+1));
			}	
			
			if(squareExists(rank+1,file-1) && 
					chessboard[rank+1][file-1].getPiece()!=null &&
					chessboard[rank+1][file-1].getPiece().getColor().equals("White")) {
				moveList.add(new Move(this.getId(),1,"attackSW",rank+1,file-1));
				
			}
			
			if(squareExists(rank+1,file+1) && 
					chessboard[rank+1][file+1].getPiece()!=null &&
					chessboard[rank+1][file+1].getPiece().getColor().equals("White")) {
				moveList.add(new Move(this.getId(),1,"attackSE",rank+1,file+1));
				
			}
			
			
		}	
		
		return moveList;
		
	}
	
	/** 
	* Adds squares a pawn attacks and can block from to a certain list given the pawn's rank and file
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param rank of piece
	* @param file of piece
	* 
	*/
	public void addAttacks(Square[][] chessboard,ArrayList<Move> attackList,int rank, int file) {
		if(this.getColor().equals("White")) {
			if(squareExists(rank-1,file) && chessboard[rank-1][file].getPiece()==null) {
				attackList.add(new Move(this.getId(),1,"walk",rank-1,file));
				if(rank==6 && chessboard[rank-2][file].getPiece()==null) {
					attackList.add(new Move(this.getId(),1,"dash",rank-2,file));
					
				}
			}
			if(squareExists(rank, file-1) && chessboard[rank][file-1].equals(enPassantSquare)) {
				attackList.add(new Move(this.getId(),1,"enPassantNW",rank-1,file-1));
			}
			
			if(squareExists(rank, file+1) && chessboard[rank][file+1].equals(enPassantSquare)) {
				attackList.add(new Move(this.getId(),1,"enPassantNE",rank-1,file+1));
			}	
			
			if(squareExists(rank-1,file-1) && 
					chessboard[rank-1][file-1].getPiece()!=null &&
					chessboard[rank-1][file-1].getPiece().getColor().equals("Black")) {
				attackList.add(new Move(this.getId(),1,"attackNW",rank-1,file-1));
				
			}
			
			if(squareExists(rank-1,file+1) && 
					chessboard[rank-1][file+1].getPiece()!=null &&
					chessboard[rank-1][file+1].getPiece().getColor().equals("Black")) {
				attackList.add(new Move(this.getId(),1,"attackNE",rank-1,file+1));
				
			}
		}	
		
		
		if(this.getColor().equals("Black")) {
			if(squareExists(rank+1, file) && chessboard[rank+1][file].getPiece()==null) {
				attackList.add(new Move(this.getId(),1,"walk",rank+1,file));
				if(rank==1 && chessboard[rank+2][file].getPiece()==null) {
					attackList.add(new Move(this.getId(),1,"dash",rank+2,file));
					
				}
			}
			if(squareExists(rank, file-1) && chessboard[rank][file-1].equals(enPassantSquare)) {
				attackList.add(new Move(this.getId(),1,"enPassantSW",rank+1,file-1));
			}
			
			if(squareExists(rank, file+1) && chessboard[rank][file+1].equals(enPassantSquare)) {
				attackList.add(new Move(this.getId(),1,"enPassantSE",rank+1,file+1));
			}	
			
			if(squareExists(rank+1,file-1) && 
					chessboard[rank+1][file-1].getPiece()!=null &&
					chessboard[rank+1][file-1].getPiece().getColor().equals("White")) {
				attackList.add(new Move(this.getId(),1,"attackSW",rank+1,file-1));
				
			}
			
			if(squareExists(rank+1,file+1) && 
					chessboard[rank+1][file+1].getPiece()!=null &&
					chessboard[rank+1][file+1].getPiece().getColor().equals("White")) {
				attackList.add(new Move(this.getId(),1,"attackSE",rank+1,file+1));
				
			}
			
			
		}	
		
	}
	
	/** 
	* Moves piece on a specified chessboard from its origin square to a destination square
	* Move list is used to check conditions for en passant
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void movePiece(Square[][] chessboard,Square origin, Square destination,
			ArrayList<Move> moveList) {
		
			destination.setPiece(origin.getPiece());
			Move pieceMove=destination.retrieveMoveFromList(moveList);
			if(pieceMove.getDirection().length()==4 &&
					pieceMove.getDirection().equals("dash")) {
				enPassantSquare=destination;
			}
			else if(pieceMove.getDirection().length()>=9 &&
					pieceMove.getDirection().substring(0,9).equals("enPassant")) {
				if(this.getColor().equals("White")) {
					for(int i=0;i<8;i++) {
						for(int j=0;j<8;j++) {
							if(chessboard[i][j].equals(destination)) {
								chessboard[i+1][j].setPiece(null);
							}
						}
					}
				}
				if(this.getColor().equals("Black")) {
					for(int i=0;i<8;i++) {
						for(int j=0;j<8;j++) {
							if(chessboard[i][j].equals(destination)) {
								chessboard[i-1][j].setPiece(null);
							}
						}
					}
				}			
				enPassantSquare=null;
			} else {
				enPassantSquare=null;
			}
			origin.setPiece(null);
		
	}
	/*
		Will account for different styles of promotion
	 */
	public void movePiece(Square[][] chessboard,Square origin, Square destination,
								   Move pieceMove) {
		/*
			Promoting the different pieces
		 */
		if(pieceMove.getDirection().length() > 9 &&
				pieceMove.getDirection().startsWith("promotion")){
			if(pieceMove.getDirection().charAt(9)=='Q'){
				destination.setPiece(new Queen(
						this.getColor(),this.getId()));
				origin.setPiece(null);
			} else if(pieceMove.getDirection().charAt(9)=='B'){
				destination.setPiece(new Bishop(
						this.getColor(),this.getId()));
				origin.setPiece(null);
			} else if(pieceMove.getDirection().charAt(9)=='N'){
				destination.setPiece(new Knight(
						this.getColor(),this.getId()));
				origin.setPiece(null);
			} else if(pieceMove.getDirection().charAt(9)=='R'){
				destination.setPiece(new Rook(
						this.getColor(),this.getId()));
				origin.setPiece(null);
			}
			return;
		}




		destination.setPiece(origin.getPiece());
		if (pieceMove.getDirection().length() == 4 &&
				pieceMove.getDirection().equals("dash")) {
			enPassantSquare = destination;
		} else if (pieceMove.getDirection().length() >= 9 &&
				pieceMove.getDirection().startsWith("enPassant")) {
			if (this.getColor().equals("White")) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (chessboard[i][j].equals(destination)) {
							chessboard[i + 1][j].setPiece(null);
						}
					}
				}
			}
			if (this.getColor().equals("Black")) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (chessboard[i][j].equals(destination)) {
							chessboard[i - 1][j].setPiece(null);
						}
					}
				}
			}
			enPassantSquare = null;
		} else {
			enPassantSquare = null;
		}
		origin.setPiece(null);
	}
}
