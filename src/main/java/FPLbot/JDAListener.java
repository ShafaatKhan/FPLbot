package FPLbot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

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
        if (input.startsWith("total ")) {
            try {
                String id = input.substring(input.indexOf(" "));
                int teamID = Integer.parseInt(id.trim()); //get rid of space and convert to integer
                channel.sendMessage("```" + UserProfile.total(teamID) + "```").queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}