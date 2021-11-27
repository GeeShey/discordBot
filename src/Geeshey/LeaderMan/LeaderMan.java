package Geeshey.LeaderMan;

import javax.security.auth.login.LoginException;

import Geeshey.LeaderMan.Commands.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
//import techtoolbox.Bot.events.GuildMessageReceived;
//import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;


public class LeaderMan {
	public static JDA jda;
	public static String prefix="!";
	
	//main method
	public static void main(String[] args) throws LoginException {
		
		//ENTER DISCORD BOT TOKEN ID HERE
		jda = new JDABuilder(AccountType.BOT)
				.setToken("ENTER TOKEN ID HERE")
				.build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setGame(Game.watching("Sai code me"));
		
		

		jda.addEventListener(new Rank());
		jda.addEventListener(new Victory());
		jda.addEventListener(new BasicCommands());
		jda.addEventListener(new Clear());
		jda.addEventListener(new newLeaderboard());
		jda.addEventListener(new Loss());
		jda.addEventListener(new UpdateRanks());
		jda.addEventListener(new addPlayer());
		jda.addEventListener(new Backup());
		
		
		
	}

}
