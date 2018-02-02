import javafx.application.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import java.io.*;
import javafx.scene.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.util.*;

/**
 * This class displays and processes commands of a song database manager in a GUI
 *
 * @author Eric Vogel
 */
public class SongDataBase extends Application
{
    // Declare Arraylist
    private static ArrayList< Song > songList;
    
    // Declare boolean to differentiate between adding or editing a song
    private static boolean isAdd = false;
    
    private static String fileName = "";
    
    /**
     * main method to program
     *
     * @param args
     */
    public static void main( String[] args )
    {
        // Instantiate Arraylist
        songList = new ArrayList< Song >();
        
        Scanner input = new Scanner( System.in );
        
        
        
        if( args.length > 0 )
        {
            fileName = args[0];
        }
        else
        {
            // Creates new file
            System.out.println( "Please enter a file name" );
            fileName = input.nextLine();
        }
        
        
        // Check to see if the file exists
        File f = new File(fileName);
        while (!f.exists())
        {
            System.out.println( "Invalid file, please enter another file name" );
            f = new File( input.next() );
        }
        
        // try and catch statement to read in text file
        try
        {
            Reader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            
            String strLine;
            while ( (strLine = br.readLine()) != null)
            {
                
                String[] strArray = strLine.split(";");
                
                // Method called to add song
                if (strArray.length == 6)
                {
                    songList.add( new Song( strArray[0], strArray[1], strArray[2], strArray[3], strArray[4], strArray[5] ) );
                }
            }
            
        }
        // Catch IOException if file not found
        catch ( FileNotFoundException e )
        {
            System.out.println( "I/O Error: " + e.getMessage() );
        }
        // Catch IOExceptions
        catch ( IOException e )
        {
            System.out.println( "I/O Error: " + e.getMessage() );
        }
     

        
        // Starts the SongDataBase application by calling launch() method
        launch( args );
    }
    
    /**
     * Overrides the start() method to display and process commands in GUI
     *
     * @param myStage stage provided by run-time system and is the primary stage
     */
    public void start( Stage myStage )
    {
        
        // Set title of stage to Song Database Manager
        myStage.setTitle( "Song Database Manager" );
        
        // Set layout
        GridPane grid = new GridPane();
        grid.setPadding( new Insets( 10, 10, 10, 10 ) );
        grid.setVgap( 8 );
        grid.setHgap( 10 );
        
        // Create a scene
        Scene myScene = new Scene( grid, 525, 225 );
        
        // Set the scene on a stage
        myStage.setScene( myScene );
        
        // Create labels for all fields
        Label selectSongLabel = new Label( "Select Song:" );
        Label itemCodeLabel = new Label( "Item Code:" );
        Label descriptionLabel = new Label( "Description:" );
        Label artistLabel = new Label( "Artist: " );
        Label albumLabel = new Label( "Album:" );
        Label priceLabel = new Label( "Price" );
        
        // Create an observableList of songs for the combo box
        ObservableList<String> songNames = FXCollections.observableArrayList();
        for (Song s : songList)
        {
            songNames.add(s.getSongName());
        }
        
        // Create a combo box for songs from database
        ComboBox<String> cbSongNames = new ComboBox<String>( songNames );
        
        // Set a default song name
        cbSongNames.setValue( songList.get(0).getSongName() );
        
        // Create text fields for all fields
        TextField itemCodeTField = new TextField();
        TextField descriptionTField = new TextField();
        TextField artistTField = new TextField();
        TextField albumTField = new TextField();
        TextField priceTField = new TextField();
        
        // Set the text inside all fields
        itemCodeTField.setText( songList.get(0).getItemCode() );
        descriptionTField.setText( songList.get(0).getDescription() );
        artistTField.setText( songList.get(0).getArtist() );
        albumTField.setText( songList.get(0).getAlbum() );
        priceTField.setText( songList.get(0).getPrice() );
        
        // Set preferred column count for all fields
        itemCodeTField.setPrefColumnCount( 7 );
        descriptionTField.setPrefColumnCount( 25 );
        artistTField.setPrefColumnCount( 25 );
        albumTField.setPrefColumnCount( 25 );
        priceTField.setPrefColumnCount( 7 );
        
        // Create buttons to manage song data base
        Button add = new Button( "Add" );
        Button edit = new Button( "Edit" );
        Button delete = new Button( "Delete" );
        Button accept = new Button( "Accept" );
        Button cancel = new Button( "Cancel" );
        Button exit = new Button( "Exit" );
        
        // Set editable and disable options for textfields, and buttons
        itemCodeTField.setEditable( false );
        descriptionTField.setEditable( false );
        artistTField.setEditable( false );
        albumTField.setEditable( false );
        priceTField.setEditable( false );
        
        accept.setDisable( true );
        cancel.setDisable( true );
        
        /**
         * Inner class that listens for action events on songname combo box
         *
         * @param ActionEvent describes type of event to be handled by handler
         */
        cbSongNames.setOnAction( new EventHandler<ActionEvent>()
                                {
                                    
                                    /**
                                     * Method used to handle when user scolls through combo box of
                                     * different song names
                                     *
                                     * @param ae type of action event
                                     */
                                    public void handle( ActionEvent ae )
                                    {
                                        for (Song s : songList)
                                        {
                                            if( cbSongNames.getValue().equals( s.getSongName() ) )
                                            {
                                                itemCodeTField.setText( s.getItemCode() );
                                                descriptionTField.setText( s.getDescription() );
                                                artistTField.setText( s.getArtist() );
                                                albumTField.setText( s.getAlbum() );
                                                priceTField.setText( s.getPrice() );
                                            }
                                        }
                                    }
                                } );
        
        /**
         * Inner class that listens for action events for the add button
         *
         * @param ActionEvent handles action events generated by the add button
         */
        add.setOnAction( new EventHandler<ActionEvent>()
                        {
                            
                            /**
                             * Method used to handle when user pushes the add button
                             *
                             * @param ae type of action event
                             */
                            public void handle( ActionEvent ae )
                            {
                                isAdd = true;
                                
                                // Clears textfields
                                itemCodeTField.clear();
                                descriptionTField.clear();
                                artistTField.clear();
                                albumTField.clear();
                                priceTField.clear();

                                // Enables textfields to be edited
                                itemCodeTField.setEditable( true );
                                descriptionTField.setEditable( true );
                                artistTField.setEditable( true );
                                albumTField.setEditable( true );
                                priceTField.setEditable( true );
                                
                                // Enables and disables buttons
                                add.setDisable( true);
                                edit.setDisable( true );
                                delete.setDisable( true );
                                accept.setDisable( false );
                                cancel.setDisable( false );
                                
                                songNames.add("New Song");
                                cbSongNames.setValue("New Song");
                                
                            }
                        } );
        
        /**
         * Inner class that listens for action events for the edit button
         *
         * @param ActionEvent handles action events generated by the edit button
         */
        edit.setOnAction( new EventHandler<ActionEvent>()
                         {
                             
                            /**
                             * Method used to handle when user pushes the edit button
                             *
                             * @param ae type of action event
                             */
                            public void handle( ActionEvent ae )
                            {
                                isAdd = false;
                                
                                // Enables textfields to be edited except the item code
                                descriptionTField.setEditable( true );
                                artistTField.setEditable( true );
                                albumTField.setEditable( true );
                                priceTField.setEditable( true );
                                
                                // Enables and disables buttons
                                add.setDisable( true );
                                edit.setDisable( true );
                                delete.setDisable( true );
                                accept.setDisable( false );
                                cancel.setDisable( false );
                            }
                         } );
        
        /**
         * Inner class that listens for action events for the delete button
         *
         * @param ActionEvent handles action events generated by the delete button
         */
        delete.setOnAction( new EventHandler<ActionEvent>()
                           {
                               
                               /**
                                * Method used to handle when user pushes the delete button
                                *
                                * @param ae type of action event
                                */
                                public void handle( ActionEvent ae )
                                {
                                    for ( int i = 0 ; i < songList.size() ; i++ )
                                    {
                                        if( cbSongNames.getValue().equals( songList.get(i).getSongName() ) )
                                        {
                                            
                                            // Removes song from list
                                            songList.remove(i);
                                            if( songList.size() > 0 )
                                            {
                                            cbSongNames.setValue(songList.get(0).getSongName());
                                            songNames.remove(i);
                                            }
                                            else
                                            {
                                                cbSongNames.setValue(null);
                                                songNames.remove(i);
                                                itemCodeTField.setText( "" );
                                                descriptionTField.setText( "" );
                                                artistTField.setText( "" );
                                                albumTField.setText( "" );
                                                priceTField.setText( "" );
                                                
                                            }
                                        }
                                    }
                                }
                           } );
        
        /**
         * Inner class that listens for action events for the accept button
         *
         * @param ActionEvent handles action events generated by the accept button
         */
        accept.setOnAction( new EventHandler<ActionEvent>()
                           {
                               /**
                                * Method used to handle when user pushes the accept button
                                *
                                * @param ae type of action event
                                */
                               public void handle( ActionEvent ae )
                               {
                                   
                                   // if statement used to see if user selected add or edit button
                                   if( isAdd == true )
                                   {
                                       String error = "";
                                       String itemCode = itemCodeTField.getText();
                                       String description = descriptionTField.getText();
                                       String artist = artistTField.getText();
                                       String album = albumTField.getText();
                                       String price = priceTField.getText();
                                       
                                       // if statement used to check for blank textfields and if a numberic
                                       // value was used for price
                                       if( itemCode.length() == 0 )
                                       {
                                           error = error + "Item Code cannot be blank\n";
                                       }
                                       if( description.length() == 0 )
                                       {
                                           error = error + "Description cannot be blank\n";
                                       }
                                       if( artist.length() == 0 )
                                       {
                                           error = error + "Artist cannot be blank\n";
                                       }
                                       if( album.length() == 0 )
                                       {
                                            album = "None";
                                       }
                                       try
                                       {
                                           Double.parseDouble(price);
                                       }
                                       catch( NumberFormatException e)
                                       {
                                           error = error + "Price needs to be a numeric value";
                                       }
                                       
                                       // If no errors a new song is added
                                       if( error.length() == 0)
                                       {
                                           songList.add( new Song ( descriptionTField.getText(),
                                                                   itemCodeTField.getText(),
                                                                   descriptionTField.getText(),
                                                                   artistTField.getText(),
                                                                   albumTField.getText(),
                                                                   priceTField.getText() ));
                                           songNames.set(songList.size() -1, descriptionTField.getText());
                                       }
                                       else
                                       {
                                           
                                           // Creates pop-up message of error/errors
                                           Alert alert = new Alert(AlertType.INFORMATION);
                                           alert.setTitle("Error Dialog");
                                           alert.setHeaderText(null);
                                           alert.setContentText( error );
                                           
                                           alert.showAndWait();
                                           songNames.remove(songNames.size() -1);
                                           cbSongNames.setValue( songList.get(0).getSongName() );
                                       }
                                   }
                                   else
                                   {
                                       String error = "";
                                       String description = descriptionTField.getText();
                                       String artist = artistTField.getText();
                                       String album = albumTField.getText();
                                       String price = priceTField.getText();
                                       
                                       // if statement used to check for blank textfields and if a numberic
                                       // value was used for price
                                       if( description.length() == 0 )
                                       {
                                           error = error + "Description cannot be blank\n";
                                       }
                                       if( artist.length() == 0 )
                                       {
                                           error = error + "Artist cannot be blank\n";
                                       }
                                       if( album.length() == 0 )
                                       {
                                           album = "None";
                                       }
                                       try
                                       {
                                           Double.parseDouble(price);
                                       }
                                       catch( NumberFormatException e)
                                       {
                                           error = error + "Price needs to be a numeric value";
                                       }
                                       
                                       // If no errors the song is edited and saved
                                       if( error.length() == 0)
                                       {
                                           for (Song s : songList)
                                           {
                                               if( cbSongNames.getValue().equals( s.getSongName() ) )
                                               {
                                                   s.setDescription( descriptionTField.getText() );
                                                   s.setArtist( artistTField.getText() );
                                                   s.setAlbum( albumTField.getText() );
                                                   s.setPrice( priceTField.getText() );

                                               }
                                           }
                                       }
                                       else
                                       {
                                           
                                           // Creates pop-up message of error/errors
                                           Alert alert = new Alert(AlertType.INFORMATION);
                                           alert.setTitle("Error Dialog");
                                           alert.setHeaderText(null);
                                           alert.setContentText( error );
                                           
                                           alert.showAndWait();
                                           songNames.remove(songNames.size() -1);
                                           cbSongNames.setValue( songList.get(0).getSongName() );
                                       }
                                   }
                                   add.setDisable( false );
                                   edit.setDisable( false );
                                   delete.setDisable( false );
                                   accept.setDisable( true );
                                   cancel.setDisable( true );
                               }
                           } );
        
        /**
         * Inner class that listens for action events for the cancel button
         *
         * @param ActionEvent handles action events generated by the cancel button
         */
        cancel.setOnAction( new EventHandler<ActionEvent>()
                           {
                               
                           /**
                            * Method used to handle when user pushes the cancel button
                            *
                            * @param ae type of action event
                            */
                            public void handle( ActionEvent ae )
                            {
                                // Set editable and disable options for textfields, and buttons
                                itemCodeTField.setEditable( false );
                                descriptionTField.setEditable( false );
                                artistTField.setEditable( false );
                                albumTField.setEditable( false );
                                priceTField.setEditable( false );
                                
                                add.setDisable( false );
                                edit.setDisable( false );
                                delete.setDisable( false );
                                accept.setDisable( true );
                                cancel.setDisable( true );
                                
                                // Removes "New Song" from combo box
                                if ( isAdd )
                                {
                                    songNames.remove(songNames.size() -1);
                                    cbSongNames.setValue( songList.get(0).getSongName() );
                                }
                                
                                add.setDisable( false );
                                edit.setDisable( false );
                                delete.setDisable( false );
                                accept.setDisable( true );
                                cancel.setDisable( true );

                            }
                           } );
        
        /**
         * Inner class that listens for action events for the exit button
         *
         * @param ActionEvent handles action events generated by the exit button
         */
        exit.setOnAction( new EventHandler<ActionEvent>()
                         {
                             /**
                              * Method used to handle when user pushes the exit button
                              *
                              * @param ae type of action event
                              */
                               public void handle( ActionEvent ae )
                               {
                                   // Stops application
                                   Platform.exit();
                               }
                         } );
        
        // Set up grid layout of all the elements
        GridPane.setConstraints( selectSongLabel, 1, 1);
        GridPane.setConstraints( cbSongNames, 2, 1);
        GridPane.setConstraints( itemCodeLabel, 1, 2);
        GridPane.setConstraints( itemCodeTField, 2, 2);
        GridPane.setConstraints( descriptionLabel, 1, 3);
        GridPane.setConstraints( descriptionTField, 2, 3);
        GridPane.setConstraints( artistLabel, 1, 4);
        GridPane.setConstraints( artistTField, 2, 4);
        GridPane.setConstraints( albumLabel, 1, 5);
        GridPane.setConstraints( albumTField, 2, 5);
        GridPane.setConstraints( priceLabel, 1, 6);
        GridPane.setConstraints( priceTField, 2, 6);
        GridPane.setConstraints( add, 3, 1);
        GridPane.setConstraints( edit, 3, 2);
        GridPane.setConstraints( delete, 3, 3);
        GridPane.setConstraints( accept, 3, 4);
        GridPane.setConstraints( cancel, 3, 5);
        GridPane.setConstraints( exit, 3, 6);

        
        
        // Add the labels, combo boxes, textfields, and buttons to the scene graph
        grid.getChildren().addAll( selectSongLabel, cbSongNames,
                                      itemCodeLabel, itemCodeTField,
                                      descriptionLabel, descriptionTField,
                                      artistLabel, artistTField,
                                      albumLabel, albumTField,
                                      priceLabel, priceTField,
                                      add, edit, delete, accept, cancel, exit );
        
        
        // Show the stage and its scene
        myStage.show();
    }
    
    /**
     * Overrides the stop() method to end the program
     */
    public void stop()
    {
        try
        {
            // Writes songs to new file
            PrintWriter writer = new PrintWriter( fileName );
            
            for (Song s : songList)
            {
                    writer.println( s.getSongName() + ";" +
                                   s.getItemCode() + ";" +
                                   s.getDescription() + ";" +
                                   s.getArtist() + ";" +
                                   s.getAlbum() + ";" +
                                   s.getPrice() );
            }
            writer.close();
            
            System.out.println("Goodbye.");
        }
        // Catch IOException if file not found
        catch ( FileNotFoundException e )
        {
            System.out.println( "I/O Error: " + e.getMessage() );
        }
    }
}