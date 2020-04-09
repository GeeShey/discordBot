package Geeshey.LeaderMan.Commands;

import java.util.List;

import Geeshey.LeaderMan.LeaderMan;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Loss extends ListenerAdapter {
	public void onMessageReceived(MessageReceivedEvent event) {

		String[] args = event.getMessage().getContentRaw().split("\\s+");

		if (args[0].equalsIgnoreCase(LeaderMan.prefix + "loss")) {

			TextChannel logChannel = event.getGuild().getTextChannelsByName("log", true).get(0);
			String user = event.getMessage().getAuthor().toString();
			String channel = event.getChannel().getName().toString();
			logChannel.sendMessage(user + " Typed !loss in #" + channel).queue();

			TextChannel leaderboardChannel = event.getGuild().getTextChannelsByName("leaderboard", true).get(0);

			// sample input
			// !victory sai harssha gowtham
			// !victory[0] sai[1] harssha[2] gowtham[3]
			if (args.length != 4) {
				//updating log
				String s = String.join(" ", args);
				logChannel.sendMessage(user + " Typed "+s+" in #" + channel).queue();
				
				event.getChannel().sendMessage("Please enter 3 names in front of !loss").queue();
				//System.out.println(args[0]);
			} else {
				//log keeping
				logChannel.sendMessage(user + " Typed !victory "+args[1]+" "+args[2]+" "+args[3]+" in #" + channel).queue();
				// gets the entire table from #leaderboards

				// replace 90 with the no. of entries in the text channel
				
				List<Message> messages = leaderboardChannel.getHistory().retrievePast(1).complete();
				// the messageId for the leaderboard message is 696808449924792421

				// use this to get the msgID of the leaderboard message in case it changes
				// System.out.println(messages.get(0).getId()+" is the id
				// for\n"+messages.get(0).getContentRaw());
				String msgID = (messages.get(0).getId());

				// checking if the msg id has changed
				// System.out.println("Check if "+msgID+"(new msgID) = 696808449924792421(old
				// msgID)");
				String oldLeaderBoard = messages.get(0).getContentRaw();


				

				// Gowtha's Code starts here
				String str = oldLeaderBoard;
				String command = args[0]+" "+args[1]+" "+args[2]+" "+args[3];
				String s = "";
				String final_str = "";
				String leaderboardRows[] = str.split("\n");
				String losers[] = command.split(" ");
				//System.out.println(losers);
				for (int i = 1; i < losers.length; i++) {
					boolean nameExistsInLeaderboard = false;
					for (int j = 0; j < leaderboardRows.length; j++) {
						if (leaderboardRows[j].startsWith(losers[i])) {
							
							//System.out.println("Bingo !!!");
							// @asdas wins loss score rank
							String line[] = leaderboardRows[j].split(" ");
							// loss + 1
							int temp = Integer.parseInt(line[2]) + 1;
							line[2] = String.valueOf(temp);
							// score-2
							
							//TODO check if the subtraction is working
							temp = Integer.parseInt(line[3]) - 1;
							line[3] = String.valueOf(temp);

							leaderboardRows[j] = line[0] + " " + line[1] + " " + line[2] + " " + line[3] + " "
									+ line[4];
							nameExistsInLeaderboard = true;
						}
					}
					// if the !victory command contains a name which isnt found in the leaderboard
					if (nameExistsInLeaderboard == false) {
						System.out.println(leaderboardRows[i]+" does not exist in the leaderboard");
						//event.getChannel().sendMessage(leaderboardRows[i]+" does not exist in the leaderboard").queue();
						
						
					}
					else {
						//System.out.println("Successfully updated leaderboards");
						//event.getChannel().sendMessage(leaderboardRows[i]+"Successfully updated leaderboards").queue();
					}
				}
				for (int i = 0; i < leaderboardRows.length; i++) {
					final_str = final_str + leaderboardRows[i] + "\n";
				}
				final_str = final_str + s;
				System.out.println("Updated Leaderboard\n"+final_str);
				leaderboardChannel.editMessageById(msgID, final_str).queue();

			}
			
			
			

		}

	}

}