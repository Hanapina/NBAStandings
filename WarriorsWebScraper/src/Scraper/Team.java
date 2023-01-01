package Scraper;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Team implements Serializable{

	// **************************************************
	// Fields
	// **************************************************
	private String name;
	private String record;
	private String rank;
	private String conference;
	private String lastGame;
	private int hiddenNumRank; // Read the Get/Set for more documentation.





	// **************************************************
	// Constructors
	// **************************************************
	/**
	 * Typical constructor for teams
	 * @param name
	 * @param record
	 * @param rank
	 * @param conference
	 * @param hiddenNumRank
	 */
	public Team(String name, String record, String rank, String conference, int hiddenNumRank) {
		super();
		this.name = name;
		this.record = record;
		this.rank = rank;
		this.conference = conference;
		this.hiddenNumRank = hiddenNumRank;
	}





	// **************************************************
	// Getters and Setters
	// **************************************************

	/**
	 * Gets and sets the name of the team.
	 * @return
	 * Returns the name for the team. 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets and sets the record for each team.
	 * @return
	 * Returns said record.
	 */
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}


	/**
	 * Gets and set the rank for display for each team.
	 * @return
	 * Returns the rank.
	 */
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}	


	/**
	 * Gets and sets the conference for each team. Can only be Western or Eastern.
	 * Used to separate the 2 conferences for sorting later.
	 * @return
	 * Returns said rank. 
	 */
	public String getConference() {
		return conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}


	/**
	 * Used to sanitize and compare easier without dealing with characters.
	 * Gets and sets the sanitized numbers for each team. Eg: 13th to 13.
	 * @return
	 * Returns the hidden number for comparison later on.
	 */
	public int getHiddenNumRank() {
		return hiddenNumRank;
	}

	public void sethiddenNumRank(int hiddenNumRank) {
		this.hiddenNumRank = hiddenNumRank;
	}


	/**
	 * Used to find the last game and the result.
	 * Prints in (W/L) + Score + vs. 'Team'
	 * @return
	 * Returns a team's last game. 
	 */
	public String getLastGame() {
		return lastGame;
	}

	public void setLastGame(String lastGame) {
		this.lastGame = lastGame;
	}

}
