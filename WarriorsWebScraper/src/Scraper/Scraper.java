package Scraper;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class Scraper {
	/**
	 * Current main function. May be separated out later for easier reading.
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) throws InterruptedException, IOException, ClassNotFoundException {
		List<Team> westernTeamList = new LinkedList<>();
		List<Team> easternTeamList = new LinkedList<>();

		File checkFile = new File("C:\\Users\\Jesse\\Documents\\TestStuff\\westernConf.txt");
		File checkFileTwo = new File("C:\\Users\\Jesse\\Documents\\TestStuff\\easternConf.txt");
		if (!(checkFile.exists() && checkFileTwo.exists())) {
			createStats(westernTeamList, easternTeamList);
			try {
				FileOutputStream fos = new FileOutputStream("C:\\Users\\Jesse\\Documents\\TestStuff\\westernConf.txt", false);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(westernTeamList);
				oos.close();
				fos.close();
				FileOutputStream fos2 = new FileOutputStream("C:\\Users\\Jesse\\Documents\\TestStuff\\easternConf.txt", false);
				ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
				oos2.writeObject(easternTeamList);
				oos2.close();
				fos2.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		} 
		
		// MENU LOGIC
		boolean token = false;
		while (!token) { 
			System.out.println("Please choose which conference you want to look at.");
			System.out.println("1. Western Conference");
			System.out.println("2. Eastern Conference");
			System.out.println("3. Update your files");
			System.out.println("4. Exit");
			System.out.println();
			String input;
			boolean numCheck = false;
			int actualNum = -1;

			// Sanitizing our input to make sure its an integer only. 
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

			// Switch statement to handle the input.
			switch(actualNum) {
			case 1: 
				List<Team> westList = new LinkedList<Team>();
				FileInputStream westFis = new FileInputStream("C:\\Users\\Jesse\\Documents\\TestStuff\\westernConf.txt");
				ObjectInputStream westOis = new ObjectInputStream(westFis);
				westList = (List<Team>) westOis.readObject();
				for (Team team : westList) {
					printInfo(team);
				}
				westFis.close();
				westOis.close();
				break;
			case 2:
				List<Team> eastList = new LinkedList<Team>();
				FileInputStream eastFis = new FileInputStream("C:\\Users\\Jesse\\Documents\\TestStuff\\easternConf.txt");
				ObjectInputStream eastOis = new ObjectInputStream(eastFis);
				eastList = (List<Team>) eastOis.readObject();
				for (Team team: eastList) {
					printInfo(team);
				}
				eastFis.close();
				eastOis.close();
				break;
			case 3:
				// Clearing list for reinitialization of files.
				westernTeamList.clear();
				easternTeamList.clear();
				createStats(westernTeamList, easternTeamList);
				try {
					FileOutputStream fos = new FileOutputStream("C:\\Users\\Jesse\\Documents\\TestStuff\\westernConf.txt", false);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(westernTeamList);
					oos.close();
					fos.close();
					FileOutputStream fos2 = new FileOutputStream("C:\\Users\\Jesse\\Documents\\TestStuff\\easternConf.txt", false);
					ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
					oos2.writeObject(easternTeamList);
					oos2.close();
					fos2.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				System.out.println("Files Updated.\n");
				break;
			case 4:
				token = true;
				System.out.println("Exiting.");
				break;
			default: 
				System.out.println("You did not pick a choice number.");
			}
		}
	}





	// **************************************************
	// Private methods
	// **************************************************

	/**
	 * Function that makes goes through the site, puts said information into two lists, and sorts the two lists after by rank
	 * The two String arrays are paired teams and sites so make sure they are in the right order if adding in more teams/sites
	 * @calls initializeTeam
	 * @param westernTeamList
	 * @param easternTeamList
	 * @throws InterruptedException
	 */
	private static void createStats(List<Team> westernTeamList, List<Team> easternTeamList) throws InterruptedException {
		System.out.println("Initializing Western Conference.");
		String[] westernTeamArray = {"Golden State Warriors", "Denver Nuggets", "Utah Jazz", "Portland Trail Blazers", "Minnesota Timberwolves",
				"Oklahoma City Thunder", "Phoenix Suns", "Sacramento Kings", "Los Angeles Clippers", "Los Angeles Lakers", "New Orleans Pelicans",
				"Memphis Grizzlies", "Dallas Mavericks", "Houston Rockets", "San Antonio Spurs"};
		String[] westernTeamSites = {"https://www.basketball-reference.com/teams/GSW/2023.html", "https://www.basketball-reference.com/teams/DEN/2023.html",
				"https://www.basketball-reference.com/teams/UTA/2023.html", "https://www.basketball-reference.com/teams/POR/2023.html", "https://www.basketball-reference.com/teams/MIN/2023.html",
				"https://www.basketball-reference.com/teams/OKC/2023.html", "https://www.basketball-reference.com/teams/PHO/2023.html", "https://www.basketball-reference.com/teams/SAC/2023.html", 
				"https://www.basketball-reference.com/teams/LAC/2023.html", "https://www.basketball-reference.com/teams/LAL/2023.html", "https://www.basketball-reference.com/teams/NOP/2023.html", 
				"https://www.basketball-reference.com/teams/MEM/2023.html", "https://www.basketball-reference.com/teams/DAL/2023.html", "https://www.basketball-reference.com/teams/HOU/2023.html", 
		"https://www.basketball-reference.com/teams/SAS/2023.html"};
		for (int i = 0, j = 0; i < westernTeamArray.length; i++) {
			initializeTeam(westernTeamArray[i], westernTeamSites[j++], westernTeamList, easternTeamList);
		}
		System.out.println("Done.");
		System.out.println();

		
		// Added a sleep to avoid rate limiting
		System.out.println("Sleeping 1 minute to avoid rate limits.");
		TimeUnit.MINUTES.sleep(1);
		System.out.println("Done.");
		System.out.println();

		
		// Eastern Conference Initialized
		System.out.println("Initializing Eestern Conference.\n");
		String[] easternTeamArray = {"Boston Celtics", "Brooklyn Nets", "Toronto Raptors", "Philadelphia 76ers", "New York Knicks",
				"Milwaukee Bucks", "Cleveland Cavaliers", "Indiana Pacers", "Chicago Bulls", "Detroit Pistons", "Atlanta Hawks",
				"Miami Heat", "Washington Wizards", "Charlotte Hornets", "Orlando Magic"};
		String[] easternTeamSites = {"https://www.basketball-reference.com/teams/BOS/2023.html", "https://www.basketball-reference.com/teams/BRK/2023.html",
				"https://www.basketball-reference.com/teams/TOR/2023.html", "https://www.basketball-reference.com/teams/PHI/2023.html", "https://www.basketball-reference.com/teams/NYK/2023.html",
				"https://www.basketball-reference.com/teams/MIL/2023.html", "https://www.basketball-reference.com/teams/CLE/2023.html", "https://www.basketball-reference.com/teams/IND/2023.html", 
				"https://www.basketball-reference.com/teams/CHI/2023.html", "https://www.basketball-reference.com/teams/DET/2023.html", "https://www.basketball-reference.com/teams/ATL/2023.html", 
				"https://www.basketball-reference.com/teams/MIA/2023.html", "https://www.basketball-reference.com/teams/WAS/2023.html", "https://www.basketball-reference.com/teams/CHO/2023.html", 
		"https://www.basketball-reference.com/teams/ORL/2023.html"};
		for (int i = 0, j = 0; i < easternTeamArray.length; i++) {
			initializeTeam(easternTeamArray[i], easternTeamSites[j++], westernTeamList, easternTeamList);
		}
		System.out.println("Done.");
		System.out.println();
		
		// Sorting each conference array by rank. 
		Collections.sort(westernTeamList, Comparator.comparing(Team::getHiddenNumRank));
		Collections.sort(easternTeamList, Comparator.comparing(Team::getHiddenNumRank));
	}
	
	
	/**
	 * Initializes Teams and each web page. Sorts them into the right conference.  
	 * Information is extracted from another function.
	 * @calls extractInformation
	 * @param team: The Team that is being initiated
	 * @param link: The link for each team used
	 * @param westList: a list to contain the western team
	 * @param eastList: a list to contain the eastern team
	 */
	private static void initializeTeam(String team, String link, List<Team> westList, List<Team> eastList) {
		HtmlPage page = null;
		Team teamObj = new Team(team, "", "", "", 0);
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		// Sets of useless errors
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);

		// Gets the current page with link.
		try {
			page = webClient.getPage(link);
			webClient.getCurrentWindow().getJobManager().removeAllJobs();
			webClient.close();
		} catch (IOException e) {
			System.out.println("An error has occured: " + e);
		}

		// Information extracted.
		extractInformation(teamObj, page);

		// Sorts into the right list for sorting later.
		if (teamObj.getConference().equals("Western Conference")) {
			westList.add(teamObj);
		} else {
			eastList.add(teamObj);
		}
	}


	/**
	 * Extracts information for the Websites listed. 
	 * All extracted Websites are in the same format. 
	 * numRank seems repetitive but allows for easier sorting.
	 * @param team: The team the information is extracted into
	 * @param page: The page we are extracting information from
	 */
	private static void extractInformation(Team team, HtmlPage page) {
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

		// Grabbing the rank
		String rank = sArray[1];
		// numRank is a parsed integer to be used for easier sorting.
		int numRank = Integer.parseInt(sArray[1].replaceAll("[^0-9]", "")); 

		// Grabbing records, conferences, and the wins.
		String record = sArray[0].replaceAll(",", "");
		String conf = items.get(6).toString();
		String winningLine = items.get(10).toString();

		// Setting the Team fields
		team.setRank(rank);
		team.setRecord(record);
		team.setConference(conf);
		team.sethiddenNumRank(numRank);
		team.setLastGame(winningLine);
	}


	/**
	 * Basic function used to print out the information from each team.
	 * @param team: The team we are printing out.
	 */
	private static void printInfo(Team team) {
		System.out.println(team.getName());
		System.out.println("Rank: " + team.getRank());
		System.out.println("Record: " + team.getRecord());
		System.out.println("Conference: " + team.getConference());
		System.out.println("Last game: " + team.getLastGame());	
		System.out.println();
	}

}
