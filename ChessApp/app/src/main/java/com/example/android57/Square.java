package com.example.android57;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.*;

/** 
* Class for squares
* Dictates what types of information a square needs to store
* 
* @author Haoran Lyu
* @author Christopher Velasquez
* 
*/
public class Square {
	
	/** 
	* Rank name of target square that piece will end up on in form of character data type
	*/
	private char rankName;
	
	/** 
	* File name of target square that piece will end up on in form of character data type
	*/
	private char fileName;
	
	/** 
	* Piece contained in square
	*/
	private Piece piece;
	
	/** 
	* Shade of square
	* Used for printing
	*/
	private String shade;
	
	/** 
	* Rank number of square
	*/
	private int rankNum;
	
	/** 
	* File number of square
	*/
	private int fileNum;
	
	/** 
	* Create square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param shade of square
	* 
	*/
	public Square(String shade,int rank,int file) {
		this.piece=null;
		this.shade=shade;
		this.rankNum=rank;
		this.fileNum=file;
	}
	
	/** 
	* Set square rank name
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void setRankName(char rankName) {
		this.rankName=rankName;
	}
	
	/** 
	* Set square file name
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void setFileName(char fileName) {
		this.fileName=fileName;
	}
	
	/** 
	* Set piece on square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void setPiece(Piece piece) {
		this.piece=piece;
	}
	
	/** 
	* Get square rank name
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return rank name
	* 
	*/
	public char getRankName() {
		return this.rankName;
	}
	
	/** 
	* Get square file name
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return file name
	* 
	*/
	public char getFileName() {
		return this.fileName;
	}
	
	/** 
	* Get piece on square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return square piece
	* 
	*/
	public Piece getPiece() {
		return this.piece;
	}
	
	/** 
	* Get square shade
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return square shade
	* 
	*/
	public String getShade() {
		return this.shade;
	}
	
	/** 
	* Get square rank number
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return square rank number
	* 
	*/
	public int getRankNum() {
		return this.rankNum;
	}
	
	/** 
	* Get square file number
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return square file number
	* 
	*/
	public int getFileNum() {
		return this.fileNum;
	}
	
	/** 
	* Get square given its rank name and file name
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param rank name
	* @param file name
	* @return square rank number
	* 
	*/
	public static Square getSquareFromCoords(Square[][] chessboard, char rank, char file) {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(chessboard[i][j].getRankName()==rank&&
						chessboard[i][j].getFileName()==file)
					return chessboard[i][j];
			}
		}
		return null;
	}

	public static Square getSquareFromCoords(Square[][] chessboard, String name) {
		return getSquareFromCoords(chessboard, name.charAt(1),name.charAt(0));
	}
	
	/** 
	* Create a populated chessboard with pieces set up
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return populated chessboard
	* 
	*/
	public static Square[][] createChessboard() {
		Square[][] chessboard=new Square[8][8];
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if((i+j)%2==0) chessboard[i][j]=new Square("Light",i,j);
				else if((i+j)%2==1) chessboard[i][j]=new Square("Dark",i,j);
				
				if(i==0) chessboard[i][j].setRankName('8');
				if(i==1) chessboard[i][j].setRankName('7');
				if(i==2) chessboard[i][j].setRankName('6');
				if(i==3) chessboard[i][j].setRankName('5');
				if(i==4) chessboard[i][j].setRankName('4');
				if(i==5) chessboard[i][j].setRankName('3');
				if(i==6) chessboard[i][j].setRankName('2');
				if(i==7) chessboard[i][j].setRankName('1');
				
				if(j==0) chessboard[i][j].setFileName('a');
				if(j==1) chessboard[i][j].setFileName('b');
				if(j==2) chessboard[i][j].setFileName('c');
				if(j==3) chessboard[i][j].setFileName('d');
				if(j==4) chessboard[i][j].setFileName('e');
				if(j==5) chessboard[i][j].setFileName('f');
				if(j==6) chessboard[i][j].setFileName('g');
				if(j==7) chessboard[i][j].setFileName('h');
				
			}
		}
		chessboard[0][0].setPiece(new Rook("Black",0));
		chessboard[0][1].setPiece(new Knight("Black",1));
		chessboard[0][2].setPiece(new Bishop("Black",2));
		chessboard[0][3].setPiece(new Queen("Black",3));
		chessboard[0][4].setPiece(new King("Black",4));
		chessboard[0][5].setPiece(new Bishop("Black",5));
		chessboard[0][6].setPiece(new Knight("Black",6));
		chessboard[0][7].setPiece(new Rook("Black",7));
		chessboard[1][0].setPiece(new Pawn("Black",8));
		chessboard[1][1].setPiece(new Pawn("Black",9));
		chessboard[1][2].setPiece(new Pawn("Black",10));
		chessboard[1][3].setPiece(new Pawn("Black",11));
		chessboard[1][4].setPiece(new Pawn("Black",12));
		chessboard[1][5].setPiece(new Pawn("Black",13));
		chessboard[1][6].setPiece(new Pawn("Black",14));
		chessboard[1][7].setPiece(new Pawn("Black",15));
		
		chessboard[7][0].setPiece(new Rook("White",100));
		chessboard[7][1].setPiece(new Knight("White",101));
		chessboard[7][2].setPiece(new Bishop("White",102));
		chessboard[7][3].setPiece(new Queen("White",103));
		chessboard[7][4].setPiece(new King("White",104));
		chessboard[7][5].setPiece(new Bishop("White",105));
		chessboard[7][6].setPiece(new Knight("White",106));
		chessboard[7][7].setPiece(new Rook("White",107));
		chessboard[6][0].setPiece(new Pawn("White",108));
		chessboard[6][1].setPiece(new Pawn("White",109));
		chessboard[6][2].setPiece(new Pawn("White",110));
		chessboard[6][3].setPiece(new Pawn("White",111));
		chessboard[6][4].setPiece(new Pawn("White",112));
		chessboard[6][5].setPiece(new Pawn("White",113));
		chessboard[6][6].setPiece(new Pawn("White",114));
		chessboard[6][7].setPiece(new Pawn("White",115));
		
		return chessboard;
	}
	
	/** 
	* Create duplicate of given chessboard
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param chessboard to be duplicated
	* @return new duplicate of chessboard
	* 
	*/
	@NonNull
	public static Square[][] duplicateChessboard(Square[][] chessboard) {
		Square[][] dupChessboard=new Square[8][8];
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if((i+j)%2==0) dupChessboard[i][j]=new Square("Light",i,j);
				else if((i+j)%2==1) dupChessboard[i][j]=new Square("Dark",i,j);
				
				if(i==0) dupChessboard[i][j].setRankName('8');
				if(i==1) dupChessboard[i][j].setRankName('7');
				if(i==2) dupChessboard[i][j].setRankName('6');
				if(i==3) dupChessboard[i][j].setRankName('5');
				if(i==4) dupChessboard[i][j].setRankName('4');
				if(i==5) dupChessboard[i][j].setRankName('3');
				if(i==6) dupChessboard[i][j].setRankName('2');
				if(i==7) dupChessboard[i][j].setRankName('1');
				
				if(j==0) dupChessboard[i][j].setFileName('a');
				if(j==1) dupChessboard[i][j].setFileName('b');
				if(j==2) dupChessboard[i][j].setFileName('c');
				if(j==3) dupChessboard[i][j].setFileName('d');
				if(j==4) dupChessboard[i][j].setFileName('e');
				if(j==5) dupChessboard[i][j].setFileName('f');
				if(j==6) dupChessboard[i][j].setFileName('g');
				if(j==7) dupChessboard[i][j].setFileName('h');
				
				dupChessboard[i][j].setPiece(chessboard[i][j].getPiece());
			}
		}
		
		return dupChessboard;
	}
	
	/** 
	* Print square contents
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void printSquare() {
		if(this.getPiece()==null) {
			if(this.getShade().equals("Dark")) System.out.print("##");
			else if(this.getShade().equals("Light")) System.out.print("  ");
		} else {
			this.getPiece().printPiece();
		}
	}
	
	/** 
	* Print each square of a chessboard in its proper orientation
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public static void printChessboard(Square[][] chessboard) {

		for(int i = 0 ; i < 8; i++){
			for(int j=0;j < 8; j++){
				if(!Piece.pieceOnSquare(chessboard,i, j)) {
					MainActivity.imageBoard[i][j].setImageResource(0);
				} else {
					if (chessboard[i][j].getPiece().getColor().equals("White")) {
						if (chessboard[i][j].getPiece() instanceof Knight) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.w_knight);

						} else
						if (chessboard[i][j].getPiece() instanceof Bishop) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.w_bishop);

						} else
						if (chessboard[i][j].getPiece() instanceof Queen) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.w_queen);

						} else
						if (chessboard[i][j].getPiece() instanceof King) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.w_king);

						} else
						if (chessboard[i][j].getPiece() instanceof Pawn) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.w_pawn);

						} else
						if (chessboard[i][j].getPiece() instanceof Rook) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.w_rook);

						}
					} else if (chessboard[i][j].getPiece().getColor().equals("Black")) {
						if (chessboard[i][j].getPiece() instanceof Knight) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.b_knight);

						} else
						if (chessboard[i][j].getPiece() instanceof Bishop) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.b_bishop);

						} else
						if (chessboard[i][j].getPiece() instanceof Queen) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.b_queen);

						} else
						if (chessboard[i][j].getPiece() instanceof King) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.b_king);

						} else
						if (chessboard[i][j].getPiece() instanceof Pawn) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.b_pawn);

						} else
						if (chessboard[i][j].getPiece() instanceof Rook) {
							MainActivity.imageBoard[i][j].setImageResource(R.drawable.b_rook);

						}
					}
				}
			}
		}
	}
	
	/** 
	* Checks whether a square is on a move list
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return boolean whether square is on a move list
	* 
	*/
	public boolean squareIsOnMoveList(ArrayList<Move> moveList) {
		for(Move m: moveList) {
			if(m.getRank()==this.getRankNum() &&
					m.getFile()==this.getFileNum())
				return true;
		}
		return false;
	}
	
	/** 
	* Retrieves square from a move list
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return target square from move list
	* 
	*/
	public Move retrieveMoveFromList(ArrayList<Move> moveList) {
		for(Move m: moveList) {
			if(m.getRank()==this.getRankNum() &&
					m.getFile()==this.getFileNum())
				return m;
		}
		return null;
	}

	/**
	 * Retrieves square from a move list given the piece
	 *
	 * @author Haoran Lyu
	 * @author Christopher Velasquez
	 * @return target square from move list
	 *
	 */
	public Move retrievePieceMoveFromList(Piece piece, ArrayList<Move> moveList) {
		if(piece==null) return null;

		for(Move m: moveList) {
			if(piece.getId()==m.getId() &&
			m.getRank()==this.getRankNum() &&
					m.getFile()==this.getFileNum())
				return m;
		}
		return null;
	}
	
	/** 
	* Checks whether two squares are refer to the same square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param square in question
	* @return boolean whether squares are the same
	* 
	*/
	public boolean equals(Square square) {
		if(square==null) return false;
		if(this.getRankNum()==square.getRankNum() &&
				this.getFileNum()==square.getFileNum())
			return true;
		return false;
	}

	public static Square findPieceSquareFromId(Square[][]chessboard,int id){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(Piece.pieceOnSquare(chessboard, i,j)){
					if(chessboard[i][j].getPiece().getId()==id)
						return chessboard[i][j];
				}
			}
		}
		return null;
	}
}
