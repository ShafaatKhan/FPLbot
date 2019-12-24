package FPLbot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class JDAListener extends ListenerAdapter {
    private static final String PREFIX = "&";

    public static void profile() throws IOException {
        String data = "";

        URL url = new URL("https://fantasy.premierleague.com/api/entry/123867/history/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responsecode = connection.getResponseCode();
        System.out.println("Response code is: " + responsecode);

        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext())
                data += sc.nextLine();
            sc.close();
        }
        JSONObject obj = new JSONObject(data);
        JSONArray current = (JSONArray) obj.get("current");
        JSONObject main = (JSONObject) current.get(0);
        System.out.println(main.get("total_points").toString());
    }

    public static void main(String[] args) throws IOException {
        try {
            profile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String input = "";

        if (event.getAuthor().isBot()) {
            return;
        }

        if (message.getContentRaw().startsWith(PREFIX)) {
            input = message.getContentRaw().substring(message.getContentRaw().indexOf(PREFIX) + 1).toLowerCase();
        }
        if (input.equals("test")) {
            channel.sendMessage("passed").queue();
        }

//        if (input.equals("profile")) {
//            try {
//                //channel.sendMessage(String.valueOf(profile())).queue();
//                //channel.sendMessage(profile()).queue();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}

