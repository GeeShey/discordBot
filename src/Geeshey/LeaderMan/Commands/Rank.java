package Geeshey.LeaderMan.Commands;

import java.util.List;

import Geeshey.LeaderMan.LeaderMan;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Rank extends ListenerAdapter{
	public void onMessageReceived(MessageReceivedEvent event) {

		String[] args= event.getMessage().getContentRaw().split("\\s+");
		//logging

		TextChannel leaderboardChannel = event.getGuild().getTextChannelsByName("leaderboard",true).get(0);

		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"rank")) {
			
					String s= String.join(" ", args);
					TextChannel logChannel = event.getGuild().getTextChannelsByName("log", true).get(0);
					String user = event.getMessage().getAuthor().toString();
					String channel = event.getChannel().getName().toString();
					logChannel.sendMessage(user + " Typed "+ s +" in #" + channel).queue();
			
			//user wants to check their own rank
			if(args.length<2) {
				
				List<Message> messages = leaderboardChannel.getHistory().retrievePast(1).complete();
				//the messageId for the leaderboard message is 696808449924792421

				//to get the msgID in case someone deletes the old one
				//System.out.println(messages.get(0).getId()+" is the id for\n"+messages.get(0).getContentRaw());
				String oldLeaderBoard= messages.get(0).getContentRaw().toString();
				
				//splitting the leaderboard into different 
				//each array index contains one row
				String[] str=oldLeaderBoard.split("\n");
				String userId=event.getAuthor().getId();
				String userName=event.getAuthor().getName();
				//searching for the row with the same userID as that of the command giver
				int wins = 0;
				int loss=0;
				int score=0;
				int rank=0;
				for(int x=0;x<str.length;x++) {
					//splitting the row into each word
					String[] elements=str[x].split("\\s+");

					//thats how the user id is formatted when extracting it from the message
					if(elements[0].equals("<@!"+userId+">")) {
						
						System.out.println(elements[0]);
						wins=Integer.parseInt(elements[1]);
						loss=Integer.parseInt(elements[2]);
						score=Integer.parseInt(elements[3]);
						rank=Integer.parseInt(elements[4]);
						break;
						
						
					}
					
				}
				String msg="Name: "+userName+" | Wins: "+wins+"| Loss: "+loss+" | Score: "+score+" | Rank: "+rank;
				event.getChannel().sendMessage(msg).queue();
				
			}
			
			
		}
	}
}

