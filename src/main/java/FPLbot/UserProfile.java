package FPLbot;

import net.dv8tion.jda.api.EmbedBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;

public class UserProfile {

    //converts the JSON of the history of a user to a String
    public static String teamHistory(int teamID) throws IOException {
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

    //finds the gameweek info(rank and points) of a user given the team ID and gameweek number
    public static String gameweekInfo(int teamID, int gameweek) throws IOException {
        String data = teamHistory(teamID);
        JSONObject obj = new JSONObject(data);
        JSONArray current = (JSONArray) obj.get("current");
        int length = gameweek - 1;

        JSONObject main = (JSONObject) current.get(length);
        String gameweekInfo = "Your rank for gameweek " + gameweek + ": is " + main.get("rank").toString();

        main = (JSONObject) current.get(length);
        gameweekInfo = gameweekInfo + " and your points for the gameweek are: " + main.get("points").toString();
        return gameweekInfo;
    }

    public static EmbedBuilder teamInfo(int teamID) throws IOException {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Team Info");
        embed.setColor(Color.MAGENTA);
        embed.setColor(new Color(54, 12, 58));
        embed.setDescription(teamID + "'s team info");//temporary for now until I grab team names

        String data = teamHistory(teamID);
        JSONObject obj = new JSONObject(data);
        JSONArray current = (JSONArray) obj.get("current");
        int length = current.length() - 1;

        JSONObject main = (JSONObject) current.get(length);//get the last occurrence to get the total points
        String teamInfo = main.get("total_points").toString();
        embed.addField("Total points: ", teamInfo, false);

        main = (JSONObject) current.get(length);
        teamInfo = main.get("overall_rank").toString();
        embed.addField("Overall rank: ", teamInfo, false);

        main = (JSONObject) current.get(length);
        teamInfo = main.get("value").toString();
        DecimalFormat df = new DecimalFormat("#.#");
        //API shows team value as 1061 rather than 106.1 so divide by 10 to get actual team value
        double totalValue = Double.parseDouble(teamInfo.trim()) / 10.0;
        teamInfo = main.get("bank").toString();
        double bankValue = Double.parseDouble(teamInfo.trim()) / 10.0;
        double squadValue = totalValue - bankValue;
        String teamValue = squadValue + "";
        embed.addField("Squad value (Bank): ", df.format(squadValue) + " (" + bankValue + ")", false);

        main = (JSONObject) current.get(length);
        teamInfo = main.get("rank").toString();
        embed.addField("Last gameweek's rank (GW #" + length + "): ", teamInfo, false);

        main = (JSONObject) current.get(length);
        teamInfo = main.get("points").toString();
        embed.addField("Last gameweek's points (GW #" + length + "): ", teamInfo, false);

        return embed;
    }
}
