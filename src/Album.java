/************************************************************
 *  CSCI 470/680      ASSIGNMENT3     SPRING 2018           *
 *  TEAM:    NAGA SATISH KRISHNA REDDY NALLAMILLI (Z1806140)*
 *  		 SAI SEETA RAM BOPPANA (Z1804843)              *
 *  DUE DATE:4/4/2018                                      *
 *  PURPOSE:    This program is to write a Java application
 *  to download XML, parse it, and display the results.    *
 ***********************************************************/

public class Album {

    private String artistName; 
    private String albumName;
    private String genre;


    Album(){}

    Album(String artistName, String albumName, String genre){

        this.artistName = artistName;
        this.albumName = albumName;
        this.genre = genre;

    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
