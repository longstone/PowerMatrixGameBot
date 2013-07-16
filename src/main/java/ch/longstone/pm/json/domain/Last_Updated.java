package ch.longstone.pm.json.domain;

/*
 * Data Object for serialisation
 */
public class Last_Updated {
	private long world;
	private long inbox;
	private long achievements;
	private long trades;
	private long syndicate;
	private long nspp;
	private Last_Updated_Stats stats;

	public long getWorld() {
		return world;
	}

	public void setWorld(long world) {
		this.world = world;
	}

	public long getInbox() {
		return inbox;
	}

	public void setInbox(long inbox) {
		this.inbox = inbox;
	}

	public long getAchievements() {
		return achievements;
	}

	public void setAchievements(long achievements) {
		this.achievements = achievements;
	}

	public long getTrades() {
		return trades;
	}

	public void setTrades(long trades) {
		this.trades = trades;
	}

	public long getSyndicate() {
		return syndicate;
	}

	public void setSyndicate(long syndicate) {
		this.syndicate = syndicate;
	}

	public long getNspp() {
		return nspp;
	}

	public void setNspp(long nspp) {
		this.nspp = nspp;
	}

	public Last_Updated_Stats getStats() {
		return stats;
	}

	public void setStats(Last_Updated_Stats stats) {
		this.stats = stats;
	}

	@Override
	public String toString() {
		return "{" + "\"world\": " + world + " " + "\"inbox\": " + inbox + " " + "\"achievements\": " + achievements + " "
				+ "\"trades\": " + trades + " " + "\"syndicate\": " + syndicate + " " + "\"nspp\":" + nspp + " " + "\"stats\" "
				+ stats + "}";
	}
}
