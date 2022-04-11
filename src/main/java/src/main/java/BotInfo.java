package src.main.java;

import src.main.java.command.*;

import java.util.Arrays;
import java.util.List;

public class BotInfo {
    //les commandes en fonction sur le bot
    static public final List<TemplateCom> listCom = Arrays.asList(new PingCom(), new ComputeCom(), new sendFromWebhooksCom(),
            new createWebhooksCom());
    static public final String welcomeMessage = "Bienvenue sur Roliste Universe.";
    static public final List<String> roleInteractionId = Arrays.asList();
    static public final List<String> roleInteractionMsg = Arrays.asList();
    static public String mainId = null;
}
