package app;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.*;




public class Main {

        
    public static void main(String[] args) throws IOException {
        // Read world cup json data
        JSONArray data;
        data = readData();
        int keys[] = new int[64];
        
        LinkedList matches = new LinkedList();
        
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
            //matches.insert(m);
            //m.print();
            //matches.print();
            

            // store keys in array
            for (int j = 0; j < keys.length; j++) keys[i] = m.id;
            
            
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
                matches.print();
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
            System.exit(1);
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
