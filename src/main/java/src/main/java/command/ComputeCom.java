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
import src.main.java.tools.RollResult;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputeCom implements TemplateCom{
    private static final String name = "roll";

    public ComputeCom() {
    }

    private static RollResult diceRoll(RollResult roll, Integer pos) throws NotValidDice {
        // get num of dice
        int start = pos;
        while (start > 0 && roll.stringBuilder.charAt(start - 1) >= '0' &&
                roll.stringBuilder.charAt(start - 1) <= '9') {
            start -= 1;
        }
        if (start == pos) {
            throw new NotValidDice(pos);
        }
        int numDices = Integer.parseInt(roll.stringBuilder.substring(start, pos));
        // get type of dices
        int end = pos;
        while (end + 1 < roll.stringBuilder.length() && roll.stringBuilder.charAt(end + 1) >= '0' &&
                roll.stringBuilder.charAt(end + 1) <= '9') {
            end += 1;
        }
        if (end == pos)
            throw new NotValidDice(pos);
        end += 1;
        int diceType = Integer.parseInt(roll.stringBuilder.substring(pos + 1, end));
        // replace all with the result
        Random rand = new Random(); // Instance a seed of randomness
        Integer finalResult = 0;
        Integer tmp = 0;
        for (int i = 0; i < numDices; i++) {
            tmp = rand.nextInt(diceType) + 1;
            finalResult += tmp;
            roll.totalResult += (float) tmp / diceType;
        }
        roll.nmbDices += numDices;
        roll.stringBuilder.replace(start, end, finalResult.toString());
        return roll;
    }

    public static RollResult diceRoller(String str) throws NotValidDice {
        StringBuilder builder = new StringBuilder(str);
        RollResult roll = new RollResult(builder, null, 0, 0, false, null);
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'd' || str.charAt(i) == 'D')
                pos.add(i);
        }
        for (Integer e: Lists.reverse(pos)) {
            roll = diceRoll(roll, e);
        }
        return roll;
    }

    public static RollResult compute(String str) {
        if (str == null || str.contentEquals(""))
            return null;
        try {
            RollResult roll = diceRoller(str);
            str = roll.stringBuilder.toString();
            Expression expr = new ExpressionBuilder(str).build();
            roll.result = Long.toString(Math.round(expr.evaluate()));
            return roll;
        } catch (NotValidDice e) {
            RollResult roll = new RollResult("Les dès n'ont pas été lancé correctement...");
            return roll;
        } catch (ArithmeticException e) {
            RollResult roll = new RollResult("Division par zero !");
            return roll;
        } catch (IllegalArgumentException e) {
            RollResult roll = new RollResult("La formule n'est pas correcte");
            return roll;
        }
    }

    private static Color determineColor(float aFloat)
    {
        int a = (int) (255 * aFloat);
        return new Color(a,a, 255);
    }

    private static MessageEmbed buildMessage(String content) {
        EmbedBuilder mesage = new EmbedBuilder();
        Color color = new Color(255, 153, 0);
        if (content == null) {
            mesage.addField("Resultat :", "Le message n'est pas correct", false);
        }
        else {
            RollResult roll = compute(content);
            mesage.addField("Resultat :", roll.stringBuilder.toString(), false);
            color = determineColor((float) roll.totalResult / roll.nmbDices);
        }
        mesage.setColor(color);
        return mesage.build();
    }

    @Override
    public void slash(SlashCommandInteractionEvent event) {
        event.reply("En cours...")
                .flatMap(v -> event.getHook().editOriginalEmbeds(
                        buildMessage(event.getOption("content").getAsString()))
                ).flatMap(v -> event.getHook().editOriginal("")
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
