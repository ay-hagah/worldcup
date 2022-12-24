package app;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.*;




public class Main {

        
    public static void main(String[] args) throws IOException {
        // Read world cup json data
        JSONArray data;
        data = readData();
        
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
            matches.insert(m);
            m.print();
            matches.print();
        }
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
