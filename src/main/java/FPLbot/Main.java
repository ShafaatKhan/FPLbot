package FPLbot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);

//        File folder = new File("C:/Users/HPPC/IdeaProjects/FPLbot/src/main/java/FPLbot");
//        String[] files = folder.list();
//        for (String file : files)
//            System.out.println(file);

        Path path = Paths.get("botToken");
        String botToken = Files.readString(path);
        builder.setToken(botToken);
        builder.addEventListeners(new JDAListener());
        builder.build();
    }

}
