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

    public static String total(int teamID) throws IOException {
        String data = JSONtoString(teamID);

        JSONObject obj = new JSONObject(data);
        JSONArray current = (JSONArray) obj.get("current");
        JSONObject main = (JSONObject) current.get(current.length() - 1); //get the last occurrence to get the total points
        String totalPoints = "Your current total points is: " + main.get("total_points").toString();
        return totalPoints;
    }
}
