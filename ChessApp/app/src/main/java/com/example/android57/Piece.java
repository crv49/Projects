package com.example.android57;

import java.util.ArrayList;

/** 
* Abstract class Piece
* Generalizes behavior for subclasses that extend this (Chess pieces)
* 
* @author Haoran Lyu
* @author Christopher Velasquez
* 
*/
public abstract class Piece {
	/** 
	* Color of a piece instance
	*/
	private String color;
	
	/** 
	* ID number of a piece instance
	*/
	private int id;
	
	/** 
	* Contains square of pawn that is able to be captured en Passant
	* Constantly changes throughout the state of the game
	*/
	public static Square enPassantSquare=null;
	
	/** 
	* Boolean checking whether the left White rook moved, making long castling illegal
	*/
	public static boolean leftWhiteRookMoved=false;
	
	/** 
	* Boolean checking whether the right White rook moved, making short castling illegal
	*/
	public static boolean rightWhiteRookMoved=false;
	
	/** 
	* Boolean checking whether the White king moved, making castling illegal
	*/
	public static boolean whiteKingMoved=false;
	
	/** 
	* Boolean checking whether the left Black rook moved, making long castling illegal
	*/
	public static boolean leftBlackRookMoved=false;
	
	/** 
	* Boolean checking whether the right Black rook moved, making short castling illegal
	*/
	public static boolean rightBlackRookMoved=false;
	
	/** 
	* Boolean checking whether the Black king moved, making castling illegal
	*/
	public static boolean blackKingMoved=false;
	
	/** 
	* Prints out a piece.
	* e.g. the White King would print "wK".
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public abstract void printPiece();
	
	/** 
	* Sets a piece's color.
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void setColor(String color) {
		this.color=color;
	}
	
	/** 
	* Sets the ID number of a piece.
	* 
	* Used to identify pieces of the same color and type from each other.
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void setId(int id) {
		this.id=id;
	}
	
	/** 
	* Gets a piece's color
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public String getColor() {
		return color;
	}
	
	/** 
	* Gets a piece's ID number
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return Piece ID Number
	* 
	*/
	public int getId() {
		return id;
	}
	
	/** 
	* Checks if there is a square of a certain rank and file
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param  rank number
	* @param  file number
	* @return boolean whether such a square exists
	* 
	*/
	public boolean squareExists(int rank, int file) {
		if(rank<0 || rank>7 || file<0 || file>7) return false;
		return true;
	}
	
	/** 
	* Checks if a piece can move to a certain square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return boolean whether the piece can move to the square
	* 
	*/
	public boolean checkMovableTo(Square[][] chessboard,int rank, int file) {
		if(rank<0 || rank>7 || file<0 || file>7) return false;
		if(chessboard[rank][file].getPiece()==null) return true;
		
		if(this.getColor().equals(chessboard[rank][file].getPiece().getColor())) return false;
		return true;
	}
	
	/** 
	* Checks if there exists a piece on certain square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return boolean whether there is a piece on the target square
	* 
	*/
	public static boolean pieceOnSquare(Square[][] chessboard,int rank, int file) {
		if(chessboard[rank][file].getPiece()!=null) return true;
		if(chessboard[rank][file].getPiece()==null) return false;
		return false;
		
	}
	
	/** 
	* Removes moves from a specified list with designated ID and Direction
	* as well as extensions greater than or equal to specified extension
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public static void removeMoves(ArrayList<Move> attackList,int id,int extension, String direction) {
		
		attackList.removeIf(a -> (a.getId()==id &&
					a.getDirection().equals(direction) &&
					a.getExtension()>=extension));
		
	}
	
	/** 
	* Removes moves from a specified attack list by cross-referencing it
	* with a specified defense list
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	*/
	// will be used to check whether checks are blockable
	public static void reviseMoveList(String attackingColor,
			Square[][] chessboard,ArrayList<Move> attackList, ArrayList<Move> defendList) {
		String defendingColor="";
		if (attackingColor.equals("White")) defendingColor="Black";
		else if (attackingColor.equals("Black")) defendingColor="White";
		Square presidentialSquare=findKing(defendingColor,chessboard);
		//remove all instances of King's square being defended by his own pieces
		defendList.removeIf(d -> (d.getRank()==presidentialSquare.getRankNum() &&
				d.getFile()==presidentialSquare.getFileNum()));
		
		ArrayList<Move> kingDefenseList=new ArrayList<Move>();

		presidentialSquare.getPiece().addAttacks(chessboard,kingDefenseList,
				presidentialSquare.getRankNum(), presidentialSquare.getFileNum());
		
		ArrayList<Move> justKidding=new ArrayList<Move>();
		for(Move k: kingDefenseList) {
			for(Move a: attackList) {
				if(k.getRank()==a.getRank() &&
						k.getFile()==a.getFile())
					justKidding.add(k);
			}
		}
		
		for(Move prank: justKidding) {
			removeMoves(defendList,prank.getId(),prank.getExtension(),prank.getDirection());
		}
		
		defendList.removeIf(d -> (d.getRank()==presidentialSquare.getRankNum() &&
				d.getFile()==presidentialSquare.getFileNum()));
		
		ArrayList<Move> toBeDeleted=new ArrayList<Move>();
		for(Move a: attackList) {
			for(Move d: defendList) {
				if(d.getRank()==a.getRank() &&
						d.getFile()==a.getFile()) {
					toBeDeleted.add(a);
				}
			}
		}
		
		for(Move d: toBeDeleted) {
			removeMoves(attackList,d.getId(),d.getExtension(),d.getDirection());
		}
		
		
	}
	
	/** 
	* Identifies if two pieces are the same
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param piece being analyzed
	* @return boolean expressing whether the pieces are the same
	* 
	*/
	public boolean equals(Piece piece) {
		return this.getId()==piece.getId();
	}
	
	/** 
	* Get move list of a specified piece on a given rank and file
	* while considering the opposing attack list
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return list of valid moves for the current piece
	* 
	*/
	public abstract ArrayList<Move> getMoveList(Square[][] chessboard,int rank, int file,ArrayList<Move> currentAttackList);
	
	/** 
	* Adds squares being attacked to a certain list given a piece's rank and file
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param rank of piece
	* @param file of piece
	* 
	*/
	public abstract void addAttacks(Square[][] chessboard,ArrayList<Move> attackList,int rank, int file);
	
	/** 
	* Moves piece on a specified chessboard from its origin square to a destination square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public abstract void movePiece(Square[][] chessboard,Square origin, Square destination,
			ArrayList<Move> moveList);

	public abstract void movePiece(Square[][] chessboard,Square origin, Square destination,
								   Move move);

	
	/** 
	* Adds squares being attacked to a certain list given a piece's rank and file
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param color of King to be found
	* @return square containing king of specified color
	* 
	*/
	public static Square findKing(String color,Square[][] chessboard) {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(chessboard[i][j].getPiece()!=null &&
						chessboard[i][j].getPiece() instanceof King &&
						chessboard[i][j].getPiece().getColor().equals(color))
					return chessboard[i][j];
			}
		}
		return null;
	}
	
	/** 
	* Checks to see if opposing King is checkmated assuming King is in check
	* Utilizes a revised AttackList which shows all attacks that are unblockable by opposing pieces
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return boolean declaring whether checkmate has occurred
	* 
	*/
	public static boolean checkCheckmate(String attackingColor, 
			Square[][] chessboard,ArrayList<Move> revisedAttackList) { 
		
		String defendingColor="";
		if (attackingColor.equals("White")) defendingColor="Black";
		else if (attackingColor.equals("Black")) defendingColor="White";
		Square presidentSquare=findKing(defendingColor,chessboard);
		int rank=presidentSquare.getRankNum();
		int file=presidentSquare.getFileNum();
		King president=(King) presidentSquare.getPiece();
				
		if(!presidentSquare.squareIsOnMoveList(revisedAttackList)) return false;
		
		ArrayList<Square> kingEscapes=new ArrayList<Square>();
		
		if(president.checkMovableTo(chessboard,rank-1,file-1)) 
			kingEscapes.add(chessboard[rank-1][file-1]);
		
		if(president.checkMovableTo(chessboard,rank-1,file)) 
			kingEscapes.add(chessboard[rank-1][file]);
		
		if(president.checkMovableTo(chessboard,rank-1,file+1)) 
			kingEscapes.add(chessboard[rank-1][file+1]);
		
		if(president.checkMovableTo(chessboard,rank,file-1)) 
			kingEscapes.add(chessboard[rank][file-1]);
		
		if(president.checkMovableTo(chessboard,rank,file+1)) 
			kingEscapes.add(chessboard[rank][file+1]);
		
		if(president.checkMovableTo(chessboard,rank+1,file-1)) 
			kingEscapes.add(chessboard[rank+1][file-1]);
		
		if(president.checkMovableTo(chessboard,rank+1,file)) 
			kingEscapes.add(chessboard[rank+1][file]);
		
		if(president.checkMovableTo(chessboard,rank+1,file+1)) 
			kingEscapes.add(chessboard[rank+1][file+1]);
		
		for(Square s: kingEscapes) {
			if(!s.squareIsOnMoveList(revisedAttackList)) return false;
		}
		
		return true;
	}
	
	/** 
	* Checks to see if opposing King is in check given a list of attacks
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return boolean declaring whether check has occurred
	* 
	*/
	public static boolean checkCheck(String attackingColor, 
			Square[][] chessboard,ArrayList<Move> attackList) { //checks to see if King is in check;
		String defendingColor="";
		if (attackingColor.equals("White")) defendingColor="Black";
		else if (attackingColor.equals("Black")) defendingColor="White";
		return findKing(defendingColor,chessboard).squareIsOnMoveList(attackList);
		
	}
	
	/** 
	* Prints a piece's color
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void printColor() {
		if(color.equals("White")) System.out.print("w");
		else if(color.equals("Black")) System.out.print("b");
	}
	
}
