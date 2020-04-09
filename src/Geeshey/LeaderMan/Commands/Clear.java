package Geeshey.LeaderMan.Commands;

import java.util.List;

import Geeshey.LeaderMan.LeaderMan;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Clear extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent event) {
		
		String[] args= event.getMessage().getContentRaw().split("\\s+");
		
		
		//to check if control comes here
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"test clear")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("clear command seems to be working").queue();
			event.getChannel().sendMessage("clear command can be used by typing !clear [no. of msgs to delete]").queue();
			
		}

		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"clear")) {
			
			String s= String.join(" ", args);
			TextChannel logChannel = event.getGuild().getTextChannelsByName("log", true).get(0);
			String user = event.getMessage().getAuthor().toString();
			String channel = event.getChannel().getName().toString();
			logChannel.sendMessage(user + " Typed "+ s +" in #" + channel).queue();
			
			if(args.length<2) {
				EmbedBuilder usage = new EmbedBuilder();
				usage.setColor(0xff3923);
				usage.setTitle("Specify amount to delete");
				usage.setDescription("This is how you use this command: "+	LeaderMan.prefix+"clear [number]");
				event.getChannel().sendMessage(usage.build()).queue();
			}
			else {
				
				try {
					List<Message> messages = event.getChannel().getHistory().retrievePast((Integer.parseInt(args[1]))+2).complete();
					
					for(int x=1;x<messages.size();x++) {
						
						//System.out.println(messages.get(x));
						event.getChannel().deleteMessageById(messages.get(x).getId()).queue();
						
					}
					//success
					EmbedBuilder success = new EmbedBuilder();
					success.setColor(0x22ff2a);
					success.setTitle("Successfully Deleted "+args[1]+" messages.");
					event.getChannel().sendMessage(success.build()).queue();
					
				}
				catch(IllegalArgumentException e) {
					if(e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
						//too many messages
						EmbedBuilder err = new EmbedBuilder();
						err.setColor(0xff3923);
						err.setTitle("Too many messages selected");
						err.setDescription("You can only choose less than 100 messages to be cleared, you dumbass");
						event.getChannel().sendMessage(err.build()).queue();
						
					}
				
					
					
				}
				
				
				
			}
				
			
		}
		
	}
}
