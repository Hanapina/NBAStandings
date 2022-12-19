package Scraper;

public class Team {
	private String name;
	private String record;
	private String rank;
	private String conference;
	private String lastGame;
	
	// hiddenNumRank is a sanitized number for comparison. I like having the 'th's at the end for display.
	private int hiddenNumRank;

	public Team(String name, String record, String rank, String conference, int hiddenNumRank) {
		super();
		this.name = name;
		this.record = record;
		this.rank = rank;
		this.conference = conference;
		this.hiddenNumRank = hiddenNumRank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}	
	
	public String getConference() {
		return conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}
	
	public int getHiddenNumRank() {
		return hiddenNumRank;
	}
	
	public void sethiddenNumRank(int hiddenNumRank) {
		this.hiddenNumRank = hiddenNumRank;
	}
	
	public String getLastGame() {
		return lastGame;
	}

	public void setLastGame(String lastGame) {
		this.lastGame = lastGame;
	}

}
