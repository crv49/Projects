package misc;

//SongLib by Haoran Lyu and Chris Velasquez

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import song.Song;
import song.SongComparator;

public class ListController {
	@FXML         
	ListView<Song> listView;  
	@FXML
	Text songTitle;
	@FXML
	Text songArtist;
	@FXML
	Text songAlbum;
	@FXML
	Text songYear;
	@FXML
	private ObservableList<Song> obsList;  

	public void start(Stage mainStage) {                
		ArrayList<Song> songs=readFromFile("src/misc/songs.txt");
		obsList = FXCollections.observableArrayList(songs); 
		listView.setItems(obsList); 

		listView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>(){

			@Override
			public ListCell<Song> call(ListView<Song> p) {

				ListCell<Song> cell = new ListCell<Song>(){

					@Override
					protected void updateItem(Song s, boolean b) {
						super.updateItem(s, b);
						if (s != null) {
							setText(s.getTitle());
						}
						else if (s == null)
						{
							setText(null);
						}
					}

				};

				return cell;
			}
		});


		if (!obsList.isEmpty())
			listView.getSelectionModel().select(0);

		showSong();

		listView
		.getSelectionModel()
		.selectedItemProperty()
		.addListener(
				(ods, oldVal, newVal) -> 
				showSong());

		mainStage.setOnCloseRequest(event -> {

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
		});

	}

	@FXML
	private void handleAdd(ActionEvent event) {
		Dialog<Song> dialog = new Dialog<>();
		dialog.setTitle("Add Song");
		dialog.setHeaderText("Add a New Song!");

		TextField titleField = new TextField();
		titleField.setPromptText("Enter song name:");
		TextField artistField = new TextField();
		artistField.setPromptText("Enter artist:");
		TextField albumField = new TextField();
		albumField.setPromptText("Enter album:");
		TextField yearField = new TextField();
		yearField.setPromptText("Enter year:");

		GridPane grid = new GridPane();
		grid.add(titleField, 1, 1);
		grid.add(artistField, 1, 2);
		grid.add(albumField, 1, 3);
		grid.add(yearField, 1, 4);

		dialog.getDialogPane().setContent(grid);

		ButtonType buttonTypeOk = new ButtonType("Add", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

		dialog.setResultConverter(new Callback<ButtonType, Song>() {
			@Override
			public Song call(ButtonType b) {
				if (b == buttonTypeOk) {

					if(!verifySong(titleField.getText().trim(),
							artistField.getText().trim(),
							yearField.getText().trim())) {
						showAlert("Add error","Something went wrong",
								"Check to verify the fields fit song criteria");
						return null;
					} else {
						return new Song(titleField.getText().trim(),
								artistField.getText().trim(),
								albumField.getText().trim(),
								yearField.getText().trim());
					}
				}
				return null;
			}
		});

		Optional<Song> result = dialog.showAndWait();

		if (result.isPresent()) {
			Song newSong = (Song) result.get();
			obsList.add(newSong);
			FXCollections.sort(obsList, new SongComparator());
			int newIndex = 0;
			for(Song s: obsList){
				if(s == newSong)
				{
					listView.getSelectionModel().select(newIndex);
				}
				newIndex++;
			}
		}

	}


	@FXML
	private void handleEdit(ActionEvent event) {
		if (obsList==null||obsList.isEmpty()) {
			showAlert("Edit error","Nothing in list","There are no songs to change.");
			return;
		}

		TextField titleField = new TextField(songTitle.getText());
		TextField artistField = new TextField(songArtist.getText());
		TextField albumField = new TextField(songAlbum.getText());
		TextField yearField = new TextField(songYear.getText());
		if(songTitle.getText().equals("")) titleField.setPromptText("Enter song name:");
		if(songArtist.getText().equals(""))artistField.setPromptText("Enter artist:");
		if(songAlbum.getText().equals(""))albumField.setPromptText("Enter album:");
		if(songYear.getText().equals(""))yearField.setPromptText("Enter year:");

		GridPane grid = new GridPane();
		grid.add(titleField, 1, 1);
		grid.add(artistField, 1, 2);
		grid.add(albumField, 1, 3);
		grid.add(yearField, 1, 4);
		
		Dialog<Boolean> dialog = new Dialog<>();
		dialog.setTitle("Edit Song");
		dialog.setHeaderText("Edit song info");
		dialog.getDialogPane().setContent(grid);

		ButtonType buttonTypeOk = new ButtonType("Confirm", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		int index = listView.getSelectionModel().getSelectedIndex();
		dialog.setResultConverter(new Callback<ButtonType, Boolean>() {
			@Override
			public Boolean call(ButtonType b) {
				if (b == buttonTypeOk) {
					if(!verifySong(titleField.getText(),
							artistField.getText(),
							yearField.getText())) {
						showAlert("Add error","Something went wrong",
								"Check to verify the fields fit song criteria");
						return false;
					} else {
						return true;
					}
				}
				return null;
			}
		});

		Optional<Boolean> result = dialog.showAndWait();
		if (result.isPresent()) {
			Song temp = new Song(titleField.getText(),artistField.getText(),
					albumField.getText(),yearField.getText());
			obsList.set(index,temp);
			listView.getSelectionModel().select(index);
			FXCollections.sort(obsList, new SongComparator());
			showSong();
		}
	}

	@FXML
	private void handleDelete(ActionEvent event) {
		if (obsList.isEmpty()) {
			showAlert("Deletion Error","No items are in the list","There is nothing to delete.");
			return;
		}

		Song item = listView.getSelectionModel().getSelectedItem();
		int index = listView.getSelectionModel().getSelectedIndex();

		obsList.remove(item);

		int newListSize=obsList.size();

		if (obsList.isEmpty()) {
			songTitle.setText("");
			songArtist.setText("");
			songAlbum.setText("");
			songYear.setText("");
		}
		else if(index != newListSize-1)
		{
			listView.getSelectionModel().select(index++);
		}
		else
		{
			listView.getSelectionModel().select(index--);
		}
		showSong();		
		showAlert("Delete Item","Deletion successful","Item deleted");

	}

	private void showSong() {
		if (listView.getSelectionModel().getSelectedIndex() < 0)
			return;

		Song selSong = listView.getSelectionModel().getSelectedItem();
		songAlbum.setText(selSong.getAlbum());
		songYear.setText(selSong.getYear());
		songTitle.setText(selSong.getTitle());
		songArtist.setText(selSong.getArtist());
		
	}


	private ArrayList<Song> readFromFile(String fPathName)
	{
		ArrayList <Song> regenSongList = new ArrayList<Song>();
		BufferedReader br;
		Path fPath = Paths.get(fPathName);
		try {

			if (!new File(fPathName).exists())
			{
				return regenSongList;
			}
			br = Files.newBufferedReader(fPath);
			
			String title;
			String artist;
			String album;
			String year;
			String next = br.readLine();
			while (next != null) { 
				title = next;
				artist = br.readLine();
				album = br.readLine();
				year = br.readLine();
				Song newSong = new Song(title, artist, album, year);
				regenSongList.add(newSong);
				next = br.readLine();
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(regenSongList, new SongComparator());
		return regenSongList;
	}

	private boolean songUnique(String title, String artist) {
		for (Song s : obsList) {

			if (s.getTitle().compareToIgnoreCase(title.trim())==0 &&
					s.getArtist().compareToIgnoreCase(artist.trim())==0)
			{
				return false;
			}

		}
		return true;
	}

	private boolean verifySong(String title, String artist, String year) {
		if (!year.trim().isEmpty()) {
			if (!year.trim().matches("[0-9]+"))
				return false;
			else if(Integer.parseInt(year.trim())<0) return false;
		}
		if (title.trim().isEmpty())
			return false;
		if (!songUnique(title, artist))
			return false;
		if (artist.trim().isEmpty())
			return false;

		return true;
	}

	private void showAlert(String title, String header, String body) {
		Alert alert = 
				new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(body);
		alert.showAndWait();
	}

}
