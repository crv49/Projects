package com.example.android57;

import java.util.ArrayList;

/** 
* Class for queen piece
* Dictates behavior for queen piece
* 
* @author Haoran Lyu
* @author Christopher Velasquez
* 
*/
public class Queen extends Piece {

	/** 
	* Creates queen instance
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	*/
	public Queen(String color, int id) {
		setColor(color);
		setId(id);
	}
	
	/** 
	* Prints out color initial and piece initial for queen
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/
	public void printPiece() {
		printColor();
		System.out.print("Q");
	}
	
	/** 
	* Get move list of a specified queen on a given rank and file
	* while considering the opposing attack list
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return list of valid moves for the current piece
	* 
	*/
	public ArrayList<Move> getMoveList(Square[][] chessboard, int rank, int file, ArrayList<Move> currentAttackList) {
		boolean moreMoves = true;
		int rankCheck = rank;
		int fileCheck = file;
		int extension = 0;
		ArrayList<Move> moveList = new ArrayList<Move>();
		while (moreMoves) {
			rankCheck++;
			fileCheck++;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (moreMoves)
				moveList.add(new Move(this.getId(), extension, "se", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck))
				moreMoves = false;
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck--;
			fileCheck++;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (moreMoves)
				moveList.add(new Move(this.getId(), extension, "ne", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck))
				moreMoves = false;
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck--;
			fileCheck--;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (moreMoves)
				moveList.add(new Move(this.getId(), extension, "nw", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck))
				moreMoves = false;
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck++;
			fileCheck--;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (moreMoves)
				moveList.add(new Move(this.getId(), extension, "sw", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck))
				moreMoves = false;
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck--;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (moreMoves)
				moveList.add(new Move(this.getId(), extension, "n", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck))
				moreMoves = false;
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			fileCheck--;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (moreMoves)
				moveList.add(new Move(this.getId(), extension, "w", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck))
				moreMoves = false;
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			fileCheck++;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (moreMoves)
				moveList.add(new Move(this.getId(), extension, "e", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck))
				moreMoves = false;
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck++;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (moreMoves)
				moveList.add(new Move(this.getId(), extension, "s", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck))
				moreMoves = false;
		}

		return moveList;
	}
	
	/** 
	* Adds squares a queen attacks and can block from to a certain list given the queen's rank and file
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param rank of piece
	* @param file of piece
	* 
	*/
	public void addAttacks(Square[][] chessboard, ArrayList<Move> attackList, int rank, int file) {
		boolean moreMoves = true;
		int rankCheck = rank;
		int fileCheck = file;
		int extension = 0;
		while (moreMoves) {
			rankCheck++;
			fileCheck++;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (squareExists(rankCheck, fileCheck))
				attackList.add(new Move(this.getId(), extension, "se", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck)) {
				moreMoves = false;
				if (chessboard[rankCheck][fileCheck].getPiece() instanceof King
						&& !chessboard[rankCheck][fileCheck].getPiece().getColor().equals(this.getColor()))
					moreMoves = true;
			}
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck--;
			fileCheck++;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (squareExists(rankCheck, fileCheck))
				attackList.add(new Move(this.getId(), extension, "ne", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck)) {
				moreMoves = false;
				if (chessboard[rankCheck][fileCheck].getPiece() instanceof King
						&& !chessboard[rankCheck][fileCheck].getPiece().getColor().equals(this.getColor()))
					moreMoves = true;
			}
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck--;
			fileCheck--;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (squareExists(rankCheck, fileCheck))
				attackList.add(new Move(this.getId(), extension, "nw", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck)) {
				moreMoves = false;
				if (chessboard[rankCheck][fileCheck].getPiece() instanceof King
						&& !chessboard[rankCheck][fileCheck].getPiece().getColor().equals(this.getColor()))
					moreMoves = true;
			}
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck++;
			fileCheck--;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (squareExists(rankCheck, fileCheck))
				attackList.add(new Move(this.getId(), extension, "sw", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck)) {
				moreMoves = false;
				if (chessboard[rankCheck][fileCheck].getPiece() instanceof King
						&& !chessboard[rankCheck][fileCheck].getPiece().getColor().equals(this.getColor()))
					moreMoves = true;
			}
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck--;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (squareExists(rankCheck, fileCheck))
				attackList.add(new Move(this.getId(), extension, "n", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck)) {
				moreMoves = false;
				if (chessboard[rankCheck][fileCheck].getPiece() instanceof King
						&& !chessboard[rankCheck][fileCheck].getPiece().getColor().equals(this.getColor()))
					moreMoves = true;
			}
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			fileCheck--;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (squareExists(rankCheck, fileCheck))
				attackList.add(new Move(this.getId(), extension, "w", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck)) {
				moreMoves = false;
				if (chessboard[rankCheck][fileCheck].getPiece() instanceof King
						&& !chessboard[rankCheck][fileCheck].getPiece().getColor().equals(this.getColor()))
					moreMoves = true;
			}
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			fileCheck++;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (squareExists(rankCheck, fileCheck))
				attackList.add(new Move(this.getId(), extension, "e", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck)) {
				moreMoves = false;
				if (chessboard[rankCheck][fileCheck].getPiece() instanceof King
						&& !chessboard[rankCheck][fileCheck].getPiece().getColor().equals(this.getColor()))
					moreMoves = true;
			}
		}

		moreMoves = true;
		rankCheck = rank;
		fileCheck = file;
		extension = 0;
		while (moreMoves) {
			rankCheck++;
			extension++;
			moreMoves = this.checkMovableTo(chessboard, rankCheck, fileCheck);
			if (squareExists(rankCheck, fileCheck))
				attackList.add(new Move(this.getId(), extension, "s", rankCheck, fileCheck));
			if (!squareExists(rankCheck, fileCheck)) {
				moreMoves = false;
				continue;
			}
			if (pieceOnSquare(chessboard, rankCheck, fileCheck)) {
				moreMoves = false;
				if (chessboard[rankCheck][fileCheck].getPiece() instanceof King
						&& !chessboard[rankCheck][fileCheck].getPiece().getColor().equals(this.getColor()))
					moreMoves = true;
			}
		}
	}

	/** 
	* Moves Queen on a specified chessboard from its origin square to a destination square
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* 
	*/	
	public void movePiece(Square[][] chessboard, Square origin, Square destination, ArrayList<Move> moveList) {
		destination.setPiece(origin.getPiece());
		origin.setPiece(null);
		enPassantSquare = null;
	}

	public void movePiece(Square[][] chessboard,Square origin, Square destination,
								   Move move) {
		destination.setPiece(origin.getPiece());
		origin.setPiece(null);
		enPassantSquare = null;
	}
}
