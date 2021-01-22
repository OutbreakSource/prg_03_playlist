/*
 * CS2050 - Computer Science II - Spring 2020
 * Instructor: Thyago Mota
 * Description: Prg03 - Song class
 * Name(s):
 */

import java.util.InputMismatchException;

public class Song implements Comparable<Song> {

    private String title;
    private String artist;
    private int    rank;
    private int    key;

    public static final int MIN_RANK  = 1;
    public static final int MAX_RANK  = 5;
    public static final int TITLE_KEY  = 1;
    public static final int ARTIST_KEY = 2;
    public static final int RANK_KEY   = 3;

    // TODOd: implement the constructor
    public Song(String title, String artist, int rank) {

        if(rank > MAX_RANK || rank < MIN_RANK){
            throw new InputMismatchException();
        }
        else{
            this.rank = rank;
            this.title = title;
            this.artist = artist;
        }

    }

    // TODOd: implement all getter methods

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getRank() {
        return rank;
    }

    public int getKey() {
        return key;
    }


    // TODOd: implement the setter method


    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setKey(int key) {
        this.key = key;
    }

    // TODOd: implement the compareTo override based on the current key value (note that rank should be from higher to lower rank)
    @Override
    public int compareTo(Song other) {
        switch(getKey()){
            case ARTIST_KEY:
                return this.artist.compareTo(other.artist);
            case TITLE_KEY:
                return this.title.compareTo(other.title);
            default:
                return this.rank - other.rank;
        }

    }

    // TODOd: implement the toString override
    @Override
    public String toString() {
        return title + " by " + artist + " - " + rank + "\n";

    }
}
