package FPLbot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UserProfile {
    public static String JSONtoString(int teamID) throws IOException {
        String data = "";
        URL url = new URL("https://fantasy.premierleague.com/api/entry/" + teamID + "/history/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();

        if (responseCode != 200)
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext())
                data += sc.nextLine();
            sc.close();
        }
        return data;
    }

    //method for finding the total points of a user given the team id
    public static String total(int teamID) throws IOException {
        String data = JSONtoString(teamID);
        JSONObject obj = new JSONObject(data);
        JSONArray current = (JSONArray) obj.get("current");
        JSONObject main = (JSONObject) current.get(current.length() - 1); //get the last occurrence to get the total points
        String totalPoints = "Your current total points is: " + main.get("total_points").toString();
        return totalPoints;
    }

    //method for finding the gameweek rank of a user given the team id and gameweek number
    public static String gameweekRank(int teamID, int gameweek) throws IOException {
        String data = JSONtoString(teamID);
        JSONObject obj = new JSONObject(data);
        JSONArray current = (JSONArray) obj.get("current");
        JSONObject main = (JSONObject) current.get(gameweek - 1); //subtract by 1 to get actual gameweek since array index starts from 0
        String gameweekRank = "Your rank for gameweek " + gameweek + ": is " + main.get("rank").toString();
        return gameweekRank;
    }
}
