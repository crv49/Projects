package com.example.android57;

import android.os.Parcel;
import android.os.Parcelable;

/**
* Class for moves
* Dictates what types of information a move needs to store
* 
* @author Haoran Lyu
* @author Christopher Velasquez
* 
*/
public class Move implements Parcelable {
	
	/** 
	* ID number of piece performing a move
	*/
	private int id;
	
	/** 
	* Extension of a move i.e. how far it goes from its origin square
	*/
	private int extension;
	
	/** 
	* String containing any special information about the move and/or direction that a piece will move
	*/
	private String direction;
	
	/** 
	* Rank number of target square that piece will end up on
	*/
	private int rank;
	
	/** 
	* File number of target square that piece will end up on
	*/
	private int file;
	
	/** 
	* Creates move instance
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @param rank number of moving piece
	* @param file number of moving piece
	* 
	*/
	public Move(int id,int extension, String direction,int rank,int file) {
		this.id=id;
		this.extension=extension;
		this.direction=direction;
		this.rank=rank;
		this.file=file;
	}

	public Move(int id,String direction,int rank,int file) {
		this.id=id;
		this.extension=0;
		this.direction=direction;
		this.rank=rank;
		this.file=file;
	}
	
	/** 
	* Gets moving piece's ID number
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return moving piece ID number
	* 
	*/
	public int getId(){
		return id;
	}
	
	/** 
	* Gets move's extension
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return move extension number
	* 
	*/
	public int getExtension(){
		return extension;
	}
	
	/** 
	* Gets move's direction
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return move direction
	* 
	*/
	public String getDirection(){
		return direction;
	}
	
	/** 
	* Gets rank of moving piece
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return moving piece rank number
	* 
	*/
	public int getRank() {
		return rank;
	}
	
	/** 
	* Gets file of moving piece
	* 
	* @author Haoran Lyu
	* @author Christopher Velasquez
	* @return moving piece file number
	* 
	*/
	public int getFile() {
		return file;
	}

	/**
	 * Gets move's direction
	 *
	 * @author Haoran Lyu
	 * @author Christopher Velasquez
	 * @return move direction
	 *
	 */
	public void setDirection(String direction){
		this.direction=direction;
		return;
	}

	public Move(Parcel in){
		String[] data = new String[3];

		in.readStringArray(data);
		// the order needs to be the same as in writeToParcel() method
		this.id = Integer.parseInt(data[0]);
		this.direction = data[1];
		this.rank=Integer.parseInt(data[2]);
		this.file=Integer.parseInt(data[3]);
	}

	public int describeContents(){
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] {this.getId()+"",
				this.getDirection()+"",
				this.getRank()+"",this.getFile()+""});
	}
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Move createFromParcel(Parcel in) {
			return new Move(in);
		}

		public Move[] newArray(int size) {
			return new Move[size];
		}
	};
}
	

