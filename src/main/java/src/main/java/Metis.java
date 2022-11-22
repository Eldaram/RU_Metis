package src.main.java;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import src.main.java.command.*;
import src.main.java.listener.MetisComListener;
import src.main.java.listener.ReadyListener;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.List;

public class Metis {

    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault("Add token here");
        builder.addEventListeners(new ReadyListener())
                .addEventListeners(new MetisComListener())
                .setActivity(Activity.playing("Tests en cours..."));
        JDA jda = null;
        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        //ToolsSlash.initSlash(jda, listCom);
        System.out.print("This is a pure test.\n");
    }
}
