package FPLbot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JDAListener extends ListenerAdapter {
    private static final String PREFIX = "&";

    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()){
            return;
        }

        if(event.getMessage().getContentRaw().equals(PREFIX + "test")){
            event.getChannel().sendMessage("passed").queue();
        }
    }
}
