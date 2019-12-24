package FPLbot;

import jdk.nashorn.internal.parser.JSONParser;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.*;


public class JDAListener extends ListenerAdapter {
    private static final String PREFIX = "&";

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

//    public String profile() throws IOException {
//        String data = "";
//
//        URL url = new URL("https://fantasy.premierleague.com/api/leagues-classic/408284/standings/");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.connect();
//        int responsecode = connection.getResponseCode();
//        System.out.println("Response code is: " + responsecode);
//
//        if (responsecode != 200)
//            throw new RuntimeException("HttpResponseCode: " + responsecode);
//        else {
//
//            Scanner sc = new Scanner(url.openStream());
//            while (sc.hasNext())
//                data += sc.nextLine();
//            sc.close();
//        }
//    }
}

