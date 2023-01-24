package com.example.android57;

import java.util.ArrayList;

/** 
* Class for king piece
* Dictates behavior for king piece
* 
* @author Haoran Lyu
* @author Christopher Velasquez
* 
*/
public class King extends Piece{
	
	/** 
	* Creates king instance
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public King(String color, int id) {
		setColor(color);
		setId(id);
	}
	
	/** 
	* Prints out color initial and piece initial for king
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void printPiece() {
		printColor();
		System.out.print("K");
	}
	
	/** 
	* Get move list of a specified piece on a given rank and file
	* while considering the opposing attack list
	* 
	* King by definition cannot more to any squares on the opposing attack list
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return list of valid moves for the current piece
	* 
	*/
	public ArrayList<Move> getMoveList(Square[][] chessboard,int rank, int file,ArrayList<Move> currentAttackList){
		
		ArrayList<Move> moveList=new ArrayList<Move>();
		
		if(this.checkMovableTo(chessboard,rank-1,file-1) && 
				!chessboard[rank-1][file-1].squareIsOnMoveList(currentAttackList)) 
			moveList.add(new Move(this.getId(),1,"nw",rank-1,file-1));
		
		if(this.checkMovableTo(chessboard,rank-1,file)&& 
				!chessboard[rank-1][file].squareIsOnMoveList(currentAttackList)) 
			moveList.add(new Move(this.getId(),1,"n",rank-1,file));
		
		if(this.checkMovableTo(chessboard,rank-1,file+1)&& 
				!chessboard[rank-1][file+1].squareIsOnMoveList(currentAttackList)) 
			moveList.add(new Move(this.getId(),1,"ne",rank-1,file+1));
		
		if(this.checkMovableTo(chessboard,rank,file-1)&& 
				!chessboard[rank][file-1].squareIsOnMoveList(currentAttackList)) 
			moveList.add(new Move(this.getId(),1,"w",rank,file-1));
		
		if(this.checkMovableTo(chessboard,rank,file+1)&& 
				!chessboard[rank][file+1].squareIsOnMoveList(currentAttackList)) 
			moveList.add(new Move(this.getId(),1,"e",rank,file+1));
		
		if(this.checkMovableTo(chessboard,rank+1,file-1)&& 
				!chessboard[rank+1][file-1].squareIsOnMoveList(currentAttackList)) 
			moveList.add(new Move(this.getId(),1,"sw",rank+1,file-1));
		
		if(this.checkMovableTo(chessboard,rank+1,file)&& 
				!chessboard[rank+1][file].squareIsOnMoveList(currentAttackList)) 
			moveList.add(new Move(this.getId(),1,"s",rank+1,file));
		
		if(this.checkMovableTo(chessboard,rank+1,file+1)&& 
				!chessboard[rank+1][file+1].squareIsOnMoveList(currentAttackList)) 
			moveList.add(new Move(this.getId(),1,"se",rank+1,file+1));
		
		//coding White long castles
		if(currentAttackList!=null) {
		if(this.getColor().equals("White") &&
				!leftWhiteRookMoved &&
				!whiteKingMoved &&
				chessboard[7][1].getPiece()==null &&
				chessboard[7][2].getPiece()==null &&
				chessboard[7][3].getPiece()==null &&
				!chessboard[7][2].squareIsOnMoveList(currentAttackList) &&
				!chessboard[7][3].squareIsOnMoveList(currentAttackList) &&
				!chessboard[7][4].squareIsOnMoveList(currentAttackList)) {
			moveList.add(new Move(this.getId(),1,"whiteLongCastle",7,2));
		}
		
		//coding White short castles
		if(this.getColor().equals("White") &&
				!rightWhiteRookMoved &&
				!whiteKingMoved &&
				chessboard[7][5].getPiece()==null &&
				chessboard[7][6].getPiece()==null &&
				!chessboard[7][4].squareIsOnMoveList(currentAttackList) &&
				!chessboard[7][5].squareIsOnMoveList(currentAttackList) &&
				!chessboard[7][6].squareIsOnMoveList(currentAttackList)) {
			moveList.add(new Move(this.getId(),1,"whiteShortCastle",7,6));
		}
		
		//coding Black long castles
		if(this.getColor().equals("Black") &&
				!leftBlackRookMoved &&
				!blackKingMoved &&
				chessboard[0][1].getPiece()==null &&
				chessboard[0][2].getPiece()==null &&
				chessboard[0][3].getPiece()==null &&
				!chessboard[0][2].squareIsOnMoveList(currentAttackList) &&
				!chessboard[0][3].squareIsOnMoveList(currentAttackList) &&
				!chessboard[0][4].squareIsOnMoveList(currentAttackList)) {
			moveList.add(new Move(this.getId(),1,"blackLongCastle",0,2));
		}
		
		//coding Black short castles
		if(this.getColor().equals("Black") &&
				!rightBlackRookMoved &&
				!blackKingMoved &&
				chessboard[0][5].getPiece()==null &&
				chessboard[0][6].getPiece()==null &&
				!chessboard[0][4].squareIsOnMoveList(currentAttackList) &&
				!chessboard[0][5].squareIsOnMoveList(currentAttackList) &&
				!chessboard[0][6].squareIsOnMoveList(currentAttackList)) {
			moveList.add(new Move(this.getId(),1,"blackShortCastle",0,6));
		}
		}
		
		return moveList;
	}
	
	/** 
	* Adds squares a king attacks from to a certain list given the king's rank and file
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param rank of piece
	* @param file of piece
	* 
	*/
	public void addAttacks(Square[][] chessboard,ArrayList<Move> attackList,int rank, int file) {
		
		if(this.checkMovableTo(chessboard,rank-1,file-1)) 
			attackList.add(new Move(this.getId(),1,"nw",rank-1,file-1));
		
		if(this.checkMovableTo(chessboard,rank-1,file)) 
			attackList.add(new Move(this.getId(),1,"n",rank-1,file));
		
		if(this.checkMovableTo(chessboard,rank-1,file+1)) 
			attackList.add(new Move(this.getId(),1,"ne",rank-1,file+1));
		
		if(this.checkMovableTo(chessboard,rank,file-1)) 
			attackList.add(new Move(this.getId(),1,"w",rank,file-1));
		
		if(this.checkMovableTo(chessboard,rank,file+1)) 
			attackList.add(new Move(this.getId(),1,"e",rank,file+1));
		
		if(this.checkMovableTo(chessboard,rank+1,file-1))
			attackList.add(new Move(this.getId(),1,"sw",rank+1,file-1));
		
		if(this.checkMovableTo(chessboard,rank+1,file)) 
			attackList.add(new Move(this.getId(),1,"s",rank+1,file));
		
		if(this.checkMovableTo(chessboard,rank+1,file+1))
			attackList.add(new Move(this.getId(),1,"se",rank+1,file+1));
	}
	
	/** 
	* Moves piece on a specified chessboard from its origin square to a destination square
	* Move list used to check whether castling is legal
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void movePiece(Square[][] chessboard,Square origin, Square destination,
			ArrayList<Move> moveList) {
		
			destination.setPiece(origin.getPiece());
			Move pieceMove=destination.retrieveMoveFromList(moveList);
			if(pieceMove.getDirection().length()>3) {

				if(pieceMove.getDirection().equals("whiteLongCastle")) {
					chessboard[7][3].setPiece(chessboard[7][0].getPiece());
					chessboard[7][0].setPiece(null);
					chessboard[7][2].setPiece(chessboard[7][4].getPiece());
					whiteKingMoved=true;
					leftWhiteRookMoved=true;
					rightWhiteRookMoved=true;
				}
				if(pieceMove.getDirection().equals("whiteShortCastle")) {
					chessboard[7][5].setPiece(chessboard[7][7].getPiece());
					chessboard[7][7].setPiece(null);
					chessboard[7][6].setPiece(chessboard[7][4].getPiece());
					whiteKingMoved=true;
					leftWhiteRookMoved=true;
					rightWhiteRookMoved=true;
				}
				if(pieceMove.getDirection().equals("blackLongCastle")) {
					chessboard[0][3].setPiece(chessboard[0][7].getPiece());
					chessboard[0][0].setPiece(null);
					chessboard[0][2].setPiece(chessboard[0][4].getPiece());
					blackKingMoved=true;
					leftBlackRookMoved=true;
					rightBlackRookMoved=true;
				}
				if(pieceMove.getDirection().equals("blackShortCastle")) {
					chessboard[0][5].setPiece(chessboard[0][7].getPiece());
					chessboard[0][7].setPiece(null);
					chessboard[0][6].setPiece(chessboard[0][4].getPiece());
					blackKingMoved=true;
					leftBlackRookMoved=true;
					rightBlackRookMoved=true;
				}
				
			}
			origin.setPiece(null);
			enPassantSquare=null;

	}

	public void movePiece(Square[][] chessboard,Square origin, Square destination,
								   Move pieceMove){

		destination.setPiece(origin.getPiece());
		if(pieceMove.getDirection().length()>3) {

			if(pieceMove.getDirection().equals("whiteLongCastle")) {
				chessboard[7][3].setPiece(chessboard[7][0].getPiece());
				chessboard[7][0].setPiece(null);
				chessboard[7][2].setPiece(chessboard[7][4].getPiece());
				whiteKingMoved=true;
				leftWhiteRookMoved=true;
				rightWhiteRookMoved=true;
			}
			if(pieceMove.getDirection().equals("whiteShortCastle")) {
				chessboard[7][5].setPiece(chessboard[7][7].getPiece());
				chessboard[7][7].setPiece(null);
				chessboard[7][6].setPiece(chessboard[7][4].getPiece());
				whiteKingMoved=true;
				leftWhiteRookMoved=true;
				rightWhiteRookMoved=true;
			}
			if(pieceMove.getDirection().equals("blackLongCastle")) {
				chessboard[0][3].setPiece(chessboard[0][7].getPiece());
				chessboard[0][0].setPiece(null);
				chessboard[0][2].setPiece(chessboard[0][4].getPiece());
				blackKingMoved=true;
				leftBlackRookMoved=true;
				rightBlackRookMoved=true;
			}
			if(pieceMove.getDirection().equals("blackShortCastle")) {
				chessboard[0][5].setPiece(chessboard[0][7].getPiece());
				chessboard[0][7].setPiece(null);
				chessboard[0][6].setPiece(chessboard[0][4].getPiece());
				blackKingMoved=true;
				leftBlackRookMoved=true;
				rightBlackRookMoved=true;
			}

		}
		origin.setPiece(null);
		enPassantSquare=null;
	}
}
