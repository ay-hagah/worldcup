package app;

public class Match {
    int id;
    Team home;
    Team away;
    String date;
    String location;
    String winner;

    public Match(int id, Team home, Team away, String date, String location, String winner) {
        this.id = id;
        this.home = home;
        this.away = away;
        this.date = date;
        this.location = location;
        this.winner = winner;
    }

    public void print() {
        System.out.println("Match ID: " + id);
        System.out.println("Home:");
        home.print();
        System.out.println("Away");
        away.print();
        System.out.println("Information: ");
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);
        System.out.println("Winner: " + winner);
    }
    
    Match() {}
}
