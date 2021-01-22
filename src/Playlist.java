/*
 * CS2050 - Computer Science II - Spring 2020
 * Instructor: Thyago Mota
 * Description: Prg03 - PlayList class
 * Name(s):
 */

import java.io.*;
import java.util.*;

public class Playlist {

    public static final String FILE_NAME = "C:\\Users\\Reborn\\IdeaProjects\\20SCS2050\\prg_03_playlist\\playlist.csv";
    public static final int OPTION_PRINT = 1;
    public static final int OPTION_ADD   = 2;
    public static final int OPTION_KEY   = 3;
    public static final int OPTION_QUIT  = 4;
    public static Scanner sc = new Scanner(System.in);

    private BinaryTree<Song> binTree;
    private int key;

    public Playlist() throws FileNotFoundException {
        binTree = new BinaryTree<>();
        key = Song.TITLE_KEY;
        loadSongs();
    }

    // TODO: implement the loadSongs method
    public void loadSongs() throws FileNotFoundException {
        Scanner playListFile = new Scanner(new File(FILE_NAME));
        while (playListFile.hasNext()) {
            String songs = playListFile.nextLine();
            String[] songInfo = songs.split(",");
            Song newSong = new Song(songInfo[0], songInfo[1], Integer.parseInt(songInfo[2]));
            addSong(newSong);
        }
        playListFile.close();

    }

    // TODO: implement the saveSongs method
    public void saveSongs() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(FILE_NAME);
        Iterator<Song> _it = binTree.iterator();
        while(_it.hasNext())
        {
            Song current = _it.next();
            printWriter.println(
                    current.getTitle() + "," +
                            current.getArtist() + "," +
                            current.getRank());
        }
        printWriter.close();

    }

    // TODO: implement the addSong method
    public void addSong(Song song) {
        binTree.add(song);


    }

    // TODOd: implement the setKey method
    public void setKey(int key)
    {
        if(this.key == key) {
            return;
        }

        BinaryTree<Song> newTree = new BinaryTree<>();

        for (Song current : binTree) {

            current.setKey(key);
            newTree.add(current);

        }
        if(!binTree.isEmpty()) {

            binTree.clear();
        }

        binTree = newTree;
        this.key = key;
    }

    // TODO: implement the toString method
    @Override
    public String toString() {
        if(binTree.isEmpty()){
            return "None";
        }
        else{
            return binTree.toString();
        }
    }

    public static int getOption() {
        while (true) {
            System.out.println("Options: \n1:print \n2:add \n3:set key \n4:quit");
            System.out.print("? ");
            String line = sc.nextLine();
            try {
                int option = Integer.parseInt(line);
                if (option == OPTION_PRINT || option == OPTION_ADD || option == OPTION_KEY || option == OPTION_QUIT)
                    return option;
            }
            catch (NumberFormatException ex) {  }
            System.out.println("Error!");
        }
    }

    public static Song getSong() {
        System.out.print("Title? ");
        String title = sc.nextLine();
        System.out.print("Artist? ");
        String artist = sc.nextLine();
        System.out.print("Rank [" + Song.MIN_RANK + "-" + Song.MAX_RANK + "]? ");
        int rank = Song.MIN_RANK;
        try {
            rank = Integer.parseInt(sc.nextLine());
        }
        catch (NumberFormatException ex) {}
        return new Song(title, artist, rank);
    }

    public static int getKey() {
        System.out.println("Options: " + Song.TITLE_KEY + ":by title " + Song.ARTIST_KEY + ":by artist " + Song.RANK_KEY + ":by rank");
        System.out.print("? ");
        int key = Song.TITLE_KEY;
        try {
            key = Integer.parseInt(sc.nextLine());
        }
        catch (NumberFormatException ex) {}
        return key;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to my playlist!");
        Playlist playList = new Playlist();
        boolean quit = false;
        while (!quit) {
            int option = getOption();
            switch (option) {
                case OPTION_PRINT:
                    System.out.println(playList);
                    break;
                case OPTION_ADD:
                    Song song = getSong();
                    playList.addSong(song);
                    break;
                case OPTION_KEY:
                    int key = getKey();
                    playList.setKey(key);
                    break;
                case OPTION_QUIT:
                    System.out.println("Saving playlist changes...");
                    playList.saveSongs();
                    System.out.println("Done!");
                    quit = true;
            }
        }
        System.out.println("Bye!");
    }
}
