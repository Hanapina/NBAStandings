package Scraper;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Scraper {
    public static void main(final String[] args) {
    	List<Team> westernTeamList = new LinkedList<>();
    	List<Team> easternTeamList = new LinkedList<>();
    	// Western Conference Initialized
    	System.out.println("Initializing Western Conference.\n");
    	
    	initializeTeam("Golden State Warriors", "https://www.basketball-reference.com/teams/GSW/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Denver Nuggets", "https://www.basketball-reference.com/teams/DEN/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Utah Jazz", "https://www.basketball-reference.com/teams/UTA/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Portland Trail Blazers", "https://www.basketball-reference.com/teams/POR/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Minnesota Timberwolves", "https://www.basketball-reference.com/teams/MIN/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Oklahoma City Thunder", "https://www.basketball-reference.com/teams/OKC/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Phoenix Suns", "https://www.basketball-reference.com/teams/PHO/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Sacramento Kings", "https://www.basketball-reference.com/teams/SAC/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Los Angeles Clippers", "https://www.basketball-reference.com/teams/LAC/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Los Angeles Lakers", "https://www.basketball-reference.com/teams/LAL/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("New Orleans Pelicans", "https://www.basketball-reference.com/teams/NOP/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Memphis Grizzlies", "https://www.basketball-reference.com/teams/MEM/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Dallas Mavericks", "https://www.basketball-reference.com/teams/DAL/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Houston Rockets", "https://www.basketball-reference.com/teams/HOU/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("San Antonio Spurs", "https://www.basketball-reference.com/teams/SAS/2023.html", westernTeamList, easternTeamList);	
    	System.out.println("Done.");
    	System.out.println();
    	
    	// Eastern Conference Initialized
    	System.out.println("Initializing Eestern Conference.\n");
    	initializeTeam("Boston Celtics", "https://www.basketball-reference.com/teams/BOS/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Brooklyn Nets", "https://www.basketball-reference.com/teams/BRK/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Toronto Raptors", "https://www.basketball-reference.com/teams/TOR/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Philadelphia 76ers", "https://www.basketball-reference.com/teams/PHI/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("New York Knicks", "https://www.basketball-reference.com/teams/NYK/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Milwaukee Bucks", "https://www.basketball-reference.com/teams/MIL/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Cleveland Cavaliers", "https://www.basketball-reference.com/teams/CLE/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Indiana Pacers", "https://www.basketball-reference.com/teams/IND/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Chicago Bulls", "https://www.basketball-reference.com/teams/CHI/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Detroit Pistons", "https://www.basketball-reference.com/teams/DET/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Atlanta Hawks", "https://www.basketball-reference.com/teams/ATL/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Miami Heat", "https://www.basketball-reference.com/teams/MIA/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Washington Wizards", "https://www.basketball-reference.com/teams/WAS/2023.html", westernTeamList, easternTeamList);
    	initializeTeam("Charlotte Hornets", "https://www.basketball-reference.com/teams/CHO/2023.html", westernTeamList, easternTeamList);	
    	initializeTeam("Orlando Magic", "https://www.basketball-reference.com/teams/ORL/2023.html", westernTeamList, easternTeamList);
    	System.out.println("Done.");
    	System.out.println();
    	
    	// Doing the sorts
    	Collections.sort(westernTeamList, Comparator.comparing(Team::getHiddenNumRank));
    	Collections.sort(easternTeamList, Comparator.comparing(Team::getHiddenNumRank));
    	
    	// Just a small test comment
    	boolean token = false;
    	while (!token) { 
    		System.out.println("Please choose which conference you want to look at.");
        	System.out.println("1. Western Conference");
        	System.out.println("2. Eastern Conference");
        	System.out.println();
        	String input;
        	boolean numCheck = false;
        	int actualNum = -1;
        	while (!numCheck) {
        		Scanner userInput = new Scanner(System.in);
            	input = userInput.next();
            	try {
            		actualNum = Integer.parseInt(input);
            		numCheck = true;
            	} catch (NumberFormatException e) {
            		System.out.println("You did not enter a number.");
            	}
        	}
        	switch(actualNum) {
	    		case 1: 
	    			for (Team team : westernTeamList) {
	    				printInfo(team);
	    			}
	    			break;
	    		case 2:
	    			for (Team team: easternTeamList) {
	    				printInfo(team);
	    			}
	    			break;
	    		case 3:
	    			token = true;
	    			System.out.println("Exiting.");
	    			break;
	        	default: 
	        		System.out.println("You did not pick 1 or 2.");
	        }
    	}
    }
    
    public static void initializeTeam(String team, String link, List<Team> westList, List<Team> eastList) {
    	HtmlPage page = null;
    	Team teamObj = new Team(team, "", "", "", 0);
    	WebClient webClient = new WebClient(BrowserVersion.CHROME);
    	
    	// Sets of useless errors
    	webClient.getOptions().setCssEnabled(false);
    	webClient.getOptions().setJavaScriptEnabled(false);
    	
    	try {
    		page = webClient.getPage(link);
    		webClient.getCurrentWindow().getJobManager().removeAllJobs();
    		webClient.close();
    	} catch (IOException e) {
    		System.out.println("An error has occured: " + e);
    	}
    	extractInformation(teamObj, page);
    	if (teamObj.getConference().equals("Western Conference")) {
    		westList.add(teamObj);
    	} else {
    		eastList.add(teamObj);
    	}
    	//printInfo(teamObj);
    }
    
    public static void extractInformation(Team team, HtmlPage page) {
    	List<DomText> items = page.getByXPath("//p//text()");
    	
    	//Testing Statements for now
    	/* 
    	int i = 0;
    	for (DomText domText: items) {
    		System.out.println(i + ": " + domText.toString());
    		i++;
    	}
    	*/
    	
    	
    	// Extract the first line for Record and Rank
    	String initialString = items.get(4).toString();
    	String[] sArray = initialString.split(" ");
    	int numRank = Integer.parseInt(sArray[1].replaceAll("[^0-9]", ""));
    	
    	String record = sArray[0].replaceAll(",", "");
    	String conf = items.get(6).toString();
    	
    	// Setting the Team fields
    	team.setRank(sArray[1]);
    	team.setRecord(record);
    	team.setConference(conf);
    	team.sethiddenNumRank(numRank);
    }
    
    public static void printInfo(Team team) {
    	System.out.println(team.getName());
    	System.out.println("Rank: " + team.getRank());
    	System.out.println("Record: " + team.getRecord());
    	System.out.println("Conference: " + team.getConference());
    	System.out.println("Hidden Rank: " + team.getHiddenNumRank());
    	System.out.println();
    }
}
