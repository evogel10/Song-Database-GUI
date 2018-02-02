import java.io.*;
import java.util.*;

/**
 * This class builds a song object for each of the songs read into an ArrayList
 * from the text file
 * 
 * @author Eric Vogel
 */
public class Song
{
    // Declare song element information
    private String songName;
    private String itemCode;
    private String description;
    private String artist;
    private String album;
    private String price;
    
    // Constructor to build each song object
    public Song( String songName, String itemCode, String description, String artist, String album, String price )
    {
        this.songName = songName;
        this.itemCode = itemCode;
        this.description = description;
        this.artist = artist;
        this.album = album;
        this.price = price;
        
    }
    
    /**
     * Method sets the song name
     * @param songName
     */
    public void setSongName( String songName )
    {
        this.songName = songName;
    }
    
    /**
     * Method gets the song name
     * @return song name
     */
    public String getSongName()
    {
        return songName;
    }
    
    /**
     * Method sets the item code
     * @param itemCode
     */
    public void setItemCode( String itemCode )
    {
        this.itemCode = itemCode;
    }
    
    /**
     * Method gets the item code
     * @return item code
     */
    public String getItemCode()
    {
        return itemCode;
    }
    
    /**
     * Method sets the song descripton
     * @param description
     */
    public void setDescription( String description )
    {
        this.description = description;
    }
    
    /**
     * Method gets the song description
     * @return song discription
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Method sets the song artist
     * @param artist
     */
    public void setArtist( String artist )
    {
        this.artist = artist;
    }
    
    /**
     * Method gets the song artist
     * @return song artist
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Method sets the song album
     * @param album
     */
    public void setAlbum( String album )
    {
        this.album = album;
    }
    
    /**
     * Method gets the song album
     * @return song album
     */
    public String getAlbum()
    {
        return album;
    }
    
    /**
     * Method sets the song price
     * @param price
     */
    public void setPrice( String price )
    {
        this.price = price;
    }
    
    /**
     * Method gets the song price
     * @return song price
     */
    public String getPrice()
    {
        return price;
    }
}