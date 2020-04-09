package Geeshey.LeaderMan.Commands;

import java.util.List;

import Geeshey.LeaderMan.LeaderMan;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class addPlayer extends ListenerAdapter {
	public void onMessageReceived(MessageReceivedEvent event) {
//TODO still need to check if the player to be added is already in the leader board
		String[] args = event.getMessage().getContentRaw().split("\\s+");

		if (args[0].equalsIgnoreCase(LeaderMan.prefix + "add")) {
			List<Member> members =event.getGuild().getMembers();
			if(args.length==2) {
				boolean userIdExists= false;

				//checking if the @userId of the person to be added is in the server or not
				for(int x=0;x<members.size();x++) {
					String s="<@!"+(members.get(x).getUser().getId()+">");
					//System.out.println(s);	
					if(args[1].equalsIgnoreCase(s)) {
						 
						userIdExists=true;
						break;
					}
					
				}
				
				if(userIdExists) {
					
					TextChannel leaderboardChannel = event.getGuild().getTextChannelsByName("leaderboard", true).get(0);
					List<Message> messages = leaderboardChannel.getHistory().retrievePast(1).complete();
					String[] rows=messages.get(0).getContentRaw().split("\n");
					int noOfRows=rows.length;
					boolean userIdExistsInLeaderboard=false;
					//checking if the entered user is in the leaderboard already
					for(int i =0;i<noOfRows;i++) {
						String[] arguments= rows[i].split(" ");
						if(arguments[0].equalsIgnoreCase(args[1])) {
							userIdExistsInLeaderboard =true;
							System.out.println("This guy already exists in the leaderboard");
							break;
						}
						
						
					}
					
					
				if(userIdExistsInLeaderboard) {
					event.getChannel().sendMessage("This person is already there in the leaderboard u dumb bum").queue();
					
				}
				else {
				//logging
				String s= String.join(" ", args);
				TextChannel logChannel = event.getGuild().getTextChannelsByName("log", true).get(0);
				String user = event.getMessage().getAuthor().toString();
				String channel = event.getChannel().getName().toString();
				logChannel.sendMessage(user + " Typed "+ s +" in #" + channel).queue();
				
				
				
				String oldLeaderBoard = messages.get(0).getContentRaw();
				
				
				String msgID = (messages.get(0).getId());
				String newUser=args[1];
				String newLeaderBoard = oldLeaderBoard+"\n"+newUser+" 0 0 0 0";
				leaderboardChannel.editMessageById(msgID, newLeaderBoard).queue();
				}
					}
					else {
					//the name the user has entered is not in the server
					String userName=event.getAuthor().getName();
					event.getChannel().sendMessage(userName+" please stop being a dumbass, sincerely - LeaderBot").queue();
					event.getChannel().sendMessage("To use !add command type !add @[playername] ").queue();
					event.getChannel().sendMessage("The player name that you entered is not part of the discord server").queue();
					
				}
			
			
			}
			else {
				String userName=event.getAuthor().getName();
				event.getChannel().sendMessage(userName+" please stop being a dumbass, sincerely - LeaderBot").queue();
				event.getChannel().sendMessage("To use !add command type !add @[playername] ").queue();
			}
			
			
		
		}
	}
}
