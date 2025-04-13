import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Leaderboard {
    public static ArrayList<String> leaderboardEntry = new ArrayList<>();
    public static void leaderboardHandler(){
        leaderboardEntry.clear();
        load();
    }
    public static void load(){
        try {
            File file = new File(System.getProperty("user.dir") + "\\src\\leaderboard.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()){
                leaderboardEntry.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //stupid fking piece of shit code, but nvrmind if it works, it works.
    public static void addPlayer(String score){
        leaderboardEntry.add(score);
        String[] names = leaderboardEntry.toArray(new String[0]);
        Arrays.sort(names, (s1, s2) -> {
            String score1 = s1.substring(s1.indexOf("|") + 1);
            String score2 = s2.substring(s2.indexOf("|") + 1);
            int score11 = Integer.parseInt(score1);
            int score22 = Integer.parseInt(score2);
            return score11 - score22;
        });
        leaderboardEntry = new ArrayList<>(Arrays.asList(names));
        String[] nameList = new String[5];
        for (int i = 0; i < 5; i++) {
            nameList[i] = leaderboardEntry.get(i);
        }
        leaderboardEntry = new ArrayList<>(Arrays.asList(nameList));
        leaderboard();
    }

    public static void leaderboard(){
        StringBuilder leaderboard = new StringBuilder();
        for (String i: leaderboardEntry){
            leaderboard.append(i).append("\n");
        }
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "\\src\\leaderboard.txt");
            myWriter.write(leaderboard.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
