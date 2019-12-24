package FPLbot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException{
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NjU4ODM0NTQwMjE0ODc4MjA5.XgFjTA.oMWdIDyzWegs4TUW7DbBtbmhNco";
        builder.setToken(token);
        builder.addEventListeners(new JDAListener());
        builder.build();
    }

}
