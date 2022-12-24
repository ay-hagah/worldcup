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


class Team {
    public String country;
    public int goals;
    public int penalities;

    Team() {}

    public Team(String country, int goals, int penalities) {
        this.country = country;
        this.goals = goals;
        this.penalities = penalities;
    }


    public void print() {
        System.out.println("Country: " +  country);
        System.out.println("Goals: " + goals);
        System.out.println("Penalities: " + penalities);
    }
    
    public void setTeam(JSONObject data) {
        
    }
}
