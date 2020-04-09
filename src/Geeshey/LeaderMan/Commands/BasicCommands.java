package Geeshey.LeaderMan.Commands;


import Geeshey.LeaderMan.LeaderMan;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BasicCommands extends ListenerAdapter {
	
public void onMessageReceived(MessageReceivedEvent event) {
		
		String[] args= event.getMessage().getContentRaw().split("\\s+");
		
		//to check if control comes here
		
		//TODO
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"yo")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("poda patti").queue();
			//event.getChannel().sendMessage("The leaderboard command can be used by typing !leaderboar or !leaderboards").queue();
			
			
		}
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"hemu")) {
			
			//event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Are you talking about Hemu the legend ?").queue();
			//event.getChannel().sendMessage("The leaderboard command can be used by typing !leaderboar or !leaderboards").queue();
			
			
		}
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"jaga")) {
			
			//event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("The real Hoysala King").queue();
			//event.getChannel().sendMessage("The leaderboard command can be used by typing !leaderboar or !leaderboards").queue();
			
			
		}

		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"test basiccommands")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("The basiccommands seems to be working").queue();
			event.getChannel().sendMessage("!yo !hemu !jaga are some of the commands").queue();
			
			
		}
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"shelly")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("MLG Rocket League Grand Champion").queue();
			
			
		}
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"geeshey")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Man's a mystery").queue();
			
			
		}
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"Indy")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Probably playing DOTA").queue();
			
			
		}
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"Dev")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Only being to beat Hemu,qualifying him to God Status").queue();
			
			
		}
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"shanks")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("He be playing with them TTs").queue();
			
			
		}
		if(args[0].equalsIgnoreCase(LeaderMan.prefix+"vedant")) {
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Mans bangin'").queue();
			
			
		}
		
		
															}
							}
