package app;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 

public class Main {

    public static void main(String[] args) throws IOException {
        // Read world cup json data
        JSONArray data;
        // read json data
        data = readData();
        // read highlights
        BufferedReader highlights = new BufferedReader(
            new FileReader("links.txt"));
        String line;

        LinkedList matches = new LinkedList();
        line = highlights.readLine();
        for (int i = 0; i < data.length(); i++) {
            JSONObject home = data.
                    getJSONObject(i)
                    .getJSONObject("home_team");
            JSONObject away = data
                    .getJSONObject(i)
                    .getJSONObject("away_team");
            Match m = new Match(
                    data.getJSONObject(i).getInt("id"),
                    new Team(
                            home.getString("name"),
                            home.getInt("goals"),
                            home.getInt("penalties")
                    ),
                    new Team(
                            away.getString("name"),
                            away.getInt("goals"),
                            away.getInt("penalties")
                    ),
                    data.getJSONObject(i).getString("datetime"),
                    data.getJSONObject(i).getString("location"),
                    data.getJSONObject(i).getString("winner")
            );
            m.setHighlights(line);
            line = highlights.readLine();
            matches.insert(m);
        }
        
        Scanner scn = new Scanner(System.in);
        System.out.println("**World Cup Data**");
        System.out.println("1) Search matches using match id");
        System.out.println("2) Show all matches");
        System.out.println("3) Exit");
        System.out.print("==> ");

        String input = scn.nextLine();

        switch (input) {
            case "1":
                searchMatch(matches);
                break;
            case "2":
                matches.printAllMatches();
                break;
            case "3":
                System.exit(0);
                break;
            default:
                exitFailed();
        }
    }

    public static void searchMatch(LinkedList list) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Match ID [from 0 to 64]: ");
        int id = scn.nextInt();

        if (list.search(id)) {
            System.out.println("Match Found");
            System.out.println("----- MATCH DATA -----");
            Match m = list.getNth(id);
            m.print();
            System.out.println("----------------------");

        } else {
            System.out.println("Match is not found.");
            return;
        }

    }

    private static void exitFailed() {
        System.err.println("Unrecognized option");
        System.exit(1);
    }

    public static JSONArray readData() {
        JSONArray matches = null;
        try {
            String content;
            content = Files.readString(Paths.get("matches.json"));
            matches = new JSONArray(content);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matches;

    }

}
