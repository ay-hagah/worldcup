package app;
import org.json.JSONObject;

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
        System.out.println("  Country: " +  country);
        System.out.println("  Goals: " + goals);
        System.out.println("  Penalities: " + penalities);
    }
    
    public void setTeam(JSONObject data) {
        
    }
}
