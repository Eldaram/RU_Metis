package src.main.java.command;

import com.google.common.collect.Lists;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import src.main.java.errors.NotValidDice;
import src.main.java.tools.Parsing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputeCom implements TemplateCom{
    private static final String name = "roll";

    public ComputeCom() {
    }

    private static StringBuilder diceRoll(StringBuilder str, Integer pos) throws NotValidDice {
        // get num of dice
        int start = pos;
        while (start > 0 && str.charAt(start - 1) >= '0' && str.charAt(start - 1) <= '9') {
            start -= 1;
        }
        if (start == pos) {
            throw new NotValidDice(pos);
        }
        int numDices = Integer.parseInt(str.substring(start, pos));
        // get type of dices
        int end = pos;
        while (end + 1 < str.length() && str.charAt(end + 1) >= '0' && str.charAt(end + 1) <= '9') {
            end += 1;
        }
        if (end == pos)
            throw new NotValidDice(pos);
        end += 1;
        int diceType = Integer.parseInt(str.substring(pos + 1, end));
        // replace all with the result
        Random rand = new Random(); // Instance a seed of randomness
        Integer finalResult = 0;
        for (int i = 0; i < numDices; i++) {
            finalResult += rand.nextInt(diceType) + 1;
        }
        str.replace(start, end, finalResult.toString());
        return str;
    }

    public static String diceRoller(String str) throws NotValidDice {
        StringBuilder builder = new StringBuilder(str);
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'd' || str.charAt(i) == 'D')
                pos.add(i);
        }
        for (Integer e: Lists.reverse(pos)) {
            builder = diceRoll(builder, e);
        }
        return builder.toString();
    }

    public static String compute(String str) {
        if (str == null || str.contentEquals(""))
            return "0";
        try {
            str = diceRoller(str);
            Expression expr = new ExpressionBuilder(str).build();
            return Long.toString(Math.round(expr.evaluate()));
        } catch (NotValidDice e) {
            return "Les dès n'ont pas été lancé correctement...";
        } catch (ArithmeticException e) {
            return "Division par zero !";
        } catch (IllegalArgumentException e) {
            return "La formule n'est pas correcte";
        }
    }

    private static MessageEmbed buildMessage(String content) {
        EmbedBuilder mesage = new EmbedBuilder();
        if (content == null) {
            mesage.addField("Resultat :", "Le message n'est pas correct", false);
        }
        else {
            mesage.addField("Resultat :", compute(content), false);
        }
        mesage.setColor(new Color(255, 153, 0));
        return mesage.build();
    }

    @Override
    public void slash(SlashCommandInteractionEvent event) {
        event.reply("En cours...")
                .flatMap(v -> event.getHook().editOriginalEmbeds(
                        buildMessage(event.getOption("content").getAsString()))
                ).queue(); // Queue both reply and edit
    }

    @Override
    public void msg(MessageReceivedEvent event, List<String> parsed) {
        MessageChannel channel = event.getChannel();
        String content = Parsing.fuse(parsed,1);
        channel.sendMessageEmbeds(new EmbedBuilder().addField("En cours...", "", false).build())
                .queue(response /* => Message */ -> {
                    response.editMessageEmbeds(buildMessage(content)).queue();
                });
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CommandListUpdateAction addSlashCom(CommandListUpdateAction list) {
        list.addCommands(Commands.slash(name, "Effectue un lancer de dès")
                .addOption(OptionType.STRING, "Tirage", "Le tirage a faire"));
        return list;
    }
}
