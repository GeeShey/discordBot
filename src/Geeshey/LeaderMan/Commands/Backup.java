package Geeshey.LeaderMan.Commands;

import java.util.List;

import Geeshey.LeaderMan.LeaderMan;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Backup extends ListenerAdapter {
	public void onMessageReceived(MessageReceivedEvent event) {

		String[] args = event.getMessage().getContentRaw().split("\\s+");

		if (args[0].equalsIgnoreCase(LeaderMan.prefix + "backup")) {
			
			
				//logging
				String s= String.join(" ", args);
				TextChannel logChannel = event.getGuild().getTextChannelsByName("log", true).get(0);
				String user = event.getMessage().getAuthor().toString();
				String channel = event.getChannel().getName().toString();
				logChannel.sendMessage(user + " Typed "+ s +" in #" + channel).queue();
				
			TextChannel leaderboardChannel = event.getGuild().getTextChannelsByName("leaderboard", true).get(0);
		
			List<Message> messages = leaderboardChannel.getHistory().retrievePast(1).complete();
			String oldLeaderBoard = messages.get(0).getContentRaw();
			TextChannel backupChannel = event.getGuild().getTextChannelById("697157324430770258");
			backupChannel.sendMessage(oldLeaderBoard).queue();
			logChannel.sendMessage("Backup created").queue();
			
		}
	}
}
