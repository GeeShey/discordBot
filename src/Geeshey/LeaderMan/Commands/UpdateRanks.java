package Geeshey.LeaderMan.Commands;

import java.util.List;

import Geeshey.LeaderMan.LeaderMan;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UpdateRanks extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent event) {

		String[] args= event.getMessage().getContentRaw().split("\\s+");


		//to check if control comes here
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"update")) {
			
			TextChannel logChannel = event.getGuild().getTextChannelsByName("log", true).get(0);
			String user = event.getMessage().getAuthor().toString();
			String channel = event.getChannel().getName().toString();
			String s = String.join(" ", args);
			logChannel.sendMessage(user + " Typed "+s+" in #" + channel).queue();
			TextChannel leaderboardChannel = event.getGuild().getTextChannelsByName("leaderboard", true).get(0);
			String msgID="696808449924792421";

			
			List<Message> messages = leaderboardChannel.getHistory().retrievePast(1).complete();
			String oldLeaderBoard = messages.get(0).getContentRaw();
			String[] rows=oldLeaderBoard.split("\n");
			
			//this assigns random ranks to each row in the leaderboard 
			for(int i=0;i<rows.length;i++) {
				//the 5th argument in rows is the rank
				String[] individualElements = rows[i].split(" ");
				individualElements[4]=Integer.toString((i+1));	
				rows[i]=individualElements[0]+" "+individualElements[1]+" "+individualElements[2]+" "+individualElements[3]+" "+individualElements[4]+"\n";
				//System.out.println(rows[i]);

			}
			
			//assigning random values to the old LeaderBoard and storing it in one String
			
			oldLeaderBoard=String.join("", rows);
			
			//add gowthas code here
			String str = oldLeaderBoard;
	        String lines[] = str.split("\n");
	        for(int i=0;i<lines.length;i++){
	            for(int j=0;j<lines.length - 1;j++){
	                String line1[] = lines[j].split(" ");
	                String line2[] = lines[j+1].split(" ");
	                int score1 = Integer.parseInt(line1[3]);
	                int score2 = Integer.parseInt(line2[3]);
	                String l1 = "";
	                String l2 = "";
	                if(score1 < score2){
	                    l1 = line2[0] + " " + line2[1] + " " + line2[2] + " " + line2[3] + " " + line2[4];
	                    l2 = line1[0] + " " + line1[1] + " " + line1[2] + " " + line1[3] + " " + line1[4];
	                }
	                else if(score1 == score2){
	                    int win1 = Integer.parseInt(line1[1]);
	                    int win2 = Integer.parseInt(line2[1]);
	                    if(win1 < win2){
	                        l1 = line2[0] + " " + line2[1] + " " + line2[2] + " " + line2[3] + " " + line2[4];
	                        l2 = line1[0] + " " + line1[1] + " " + line1[2] + " " + line1[3] + " " + line1[4];
	                    }
	                    else{
	                        l2 = line2[0] + " " + line2[1] + " " + line2[2] + " " + line2[3] + " " + line2[4];
	                        l1 = line1[0] + " " + line1[1] + " " + line1[2] + " " + line1[3] + " " + line1[4];
	                    }
	                }
	                else{
	                    l2 = line2[0] + " " + line2[1] + " " + line2[2] + " " + line2[3] + " " + line2[4];
	                    l1 = line1[0] + " " + line1[1] + " " + line1[2] + " " + line1[3] + " " + line1[4];
	                }
	                lines[j] = l1;
	                lines[j+1] = l2;
	            }
	        }
	        String final_str = "";
	        for(int i=0;i<lines.length;i++){
	            int ind = lines[i].lastIndexOf(" ");
	            lines[i] = lines[i].substring(0,ind);
	            lines[i] = lines[i] + " " + String.valueOf(i+1);
	            final_str = final_str + lines[i]+"\n";
	        }
	        
	        //System.out.println("Sorted Leaderboard\n"+final_str);
	        leaderboardChannel.editMessageById(msgID,final_str).queue();
	        logChannel.sendMessage("Successfully updated leaderboards !!!").queue();
			
			
			
		}
	}
}