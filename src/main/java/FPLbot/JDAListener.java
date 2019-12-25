package FPLbot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class JDAListener extends ListenerAdapter {
    private static final String PREFIX = ".";

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

        //if user wants to know their total points
        if (input.startsWith("total ")) {
            try {
                String id = input.substring(input.indexOf(" "));
                int teamID = Integer.parseInt(id.trim()); //get rid of space and convert to integer
                channel.sendMessage("```" + UserProfile.total(teamID) + "```").queue();
            } catch (RuntimeException | IOException e) {
                channel.sendMessage("Invalid team ID or incorrect format. Use the command .help for more information").queue();
            }
        }

        //if user wants to know their rank for a certain gameweek
        if (input.startsWith("gw ") || input.startsWith("gameweek ")) {
            try {
                String id = input.substring(input.indexOf(" "));
                String[] inputs = id.trim().split("\\s+");
                int teamID = Integer.parseInt(inputs[0]);
                int gameweek = Integer.parseInt(inputs[1]);
                channel.sendMessage("```" + UserProfile.gameweekRank(teamID, gameweek) + "```").queue();
            } catch (RuntimeException | IOException e) {
                channel.sendMessage("Invalid team ID or incorrect format. Use the command .help for more information").queue();
            }
        }

        //ideas for upcoming updates
        if(input.equals("todo")){
            channel.sendMessage(".or/.overallrank command\nmake something like a registration thing where people " +
                    "can register their team id so if they do .profile it'll show their stats\ncommand for" +
                    " checking top 5/10 overall using .top\ncommand for checking what rank you're in " +
                    "in your mini league\nmaybe add team name\n.tv/teamvalue command for checking team value" +
                    "\ncommand for checking how many points you lost through negative transfers\ncommand for checking how " +
                    "many points you had on bench in total\ncommand for comparing 2 different teams points/rank etc").queue();
        }

        if (input.equals("help")) {
            channel.sendMessage(".total [team id]\n.gw [team id] [gameweek number]").queue();
        }
    }
}