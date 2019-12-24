package FPLbot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException{
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NjU4ODM0NTQwMjE0ODc4MjA5.XgFjTA.oMWdIDyzWegs4TUW7DbBtbmhNco";
        builder.setToken(token);
        builder.build();
    }
}
