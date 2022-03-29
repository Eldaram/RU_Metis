package src.main.java;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import src.main.java.listener.Bot;
import src.main.java.ready.ReadyListener;

import javax.security.auth.login.LoginException;

public class Metis {
    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault("token"); //Add token here
        builder.addEventListeners(new ReadyListener())
                .addEventListeners(new Bot());
        try {
            JDA jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        System.out.print("This is a pure test.");
    }
}
