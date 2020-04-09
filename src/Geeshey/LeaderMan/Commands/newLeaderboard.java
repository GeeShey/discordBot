package Geeshey.LeaderMan.Commands;

import java.util.List;

import Geeshey.LeaderMan.LeaderMan;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class newLeaderboard extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent event) {

		String[] args= event.getMessage().getContentRaw().split("\\s+");
		TextChannel leaderboardChannel = event.getGuild().getTextChannelsByName("leaderboard",true).get(0);
		String msgID="696808449924792421";
		List<Member> members =event.getGuild().getMembers();
		
		



		//leaderboardChannel.editMessageById(msgID, dummyData).queue();
		
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"new")&&args[1].equalsIgnoreCase("leaderboard")) {
			//resets the msg to @geeshey 0 0 0 0
			leaderboardChannel.editMessageById(msgID, "<@!433686004839481344>  0 0 0 0").queue();
			String newLeaderboardString="";
			for(int x=0;x<members.size();x++) {
				//get all the members info
				//System.out.println(members.get(x).toString());
				
				//\n at the end because the next line to be appended will be added below
				String userName = "<@!"+members.get(x).getUser().getId()+">";
				newLeaderboardString=newLeaderboardString+userName+" 0 0 0 0\n";
				
				



			}

			//System.out.println(newLeaderboardString);
			leaderboardChannel.editMessageById(msgID, newLeaderboardString).queue();

		}
	}

}
