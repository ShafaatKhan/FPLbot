package FPLbot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.io.IOException;

public class Commands extends ListenerAdapter {
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

        //displays the rank of a user for a specified gameweek
        if (input.startsWith("gw ") || input.startsWith("gameweek ")) {
            try {
                String id = input.substring(input.indexOf(" "));
                String[] inputs = id.trim().split("\\s+");//get rid of whitespace
                int teamID = Integer.parseInt(inputs[0]);//convert first number(team ID) to integer
                int gameweek = Integer.parseInt(inputs[1]);//convert second number(gameweek number) to integer
                channel.sendMessage("```" + UserProfile.gameweekInfo(teamID, gameweek) + "```").queue();
            } catch (RuntimeException | IOException e) {
                channel.sendMessage("Invalid team ID or incorrect format. Use the command .help for more information").queue();
            }
        }

        //displays overall rank and total points (so far)
        if(input.startsWith("info ")){
            try {
                String id = input.substring(input.indexOf(" "));
                int teamID = Integer.parseInt(id.trim()); //get rid of space and convert to integer
                channel.sendMessage(UserProfile.teamInfo(teamID).build()).queue();
            } catch (RuntimeException | IOException e) {
                channel.sendMessage("Invalid team ID or incorrect format. Use the command .help for more information").queue();
            }
        }

        //ideas for upcoming updates
        if(input.equals("todo")){
            channel.sendMessage("- make something like a registration thing where people " +
                    "can register their team id so if they do .profile it'll show their stats\n- command for" +
                    " checking top 5/10 overall using .top\n- command for checking what rank you're in " +
                    "in your mini league\nmaybe add team name" +
                    "\n- command for checking how many points you lost through negative transfers\n- command for checking how " +
                    "many points you had on bench in total\n- command for comparing 2 different teams points/rank etc\n" +
                    "- make it so that user can just copy paste their team link and extract team id from it? maybe\n" +
                    "- maybe make one command .profile that shows their rank, points etc").queue();
        }

        //info on how to use the bot
        if (input.equals("help")) {
            channel.sendMessage(".total [teamID]\n.gw or .gameweek [teamID] [gameweek number]" +
                    "\n.or or .overallrank [teamID]").queue();
        }
    }
}