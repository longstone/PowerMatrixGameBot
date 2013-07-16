package ch.longstone.pm.json.domain;

/*
 * Data Object for serialisation
 */
public class Last_Updated_Stats {
	private long credits;
	private long tp;
	private long citizens;
	private long pp;
	private long weather;
	private long wind;
	private long sun;
	private String daytime;

	public long getCredits() {
		return credits;
	}

	public void setCredits(long credits) {
		this.credits = credits;
	}

	public long getTp() {
		return tp;
	}

	public void setTp(long tp) {
		this.tp = tp;
	}

	public long getCitizens() {
		return citizens;
	}

	public void setCitizens(long citizens) {
		this.citizens = citizens;
	}

	public long getPp() {
		return pp;
	}

	public void setPp(long pp) {
		this.pp = pp;
	}

	public long getWeather() {
		return weather;
	}

	public void setWeather(long weather) {
		this.weather = weather;
	}

	public long getWind() {
		return wind;
	}

	public void setWind(long wind) {
		this.wind = wind;
	}

	public long getSun() {
		return sun;
	}

	public void setSun(long sun) {
		this.sun = sun;
	}

	public String getDaytime() {
		return daytime;
	}

	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}

	@Override
	public String toString() {
		return "{\"stats\": {" + "\"credits\": " + credits + "," + "\"tp\": " + tp + "," + "\"citizens\": " + citizens + ","
				+ "\"pp\": " + pp + "," + "\"weather\": " + weather + "," + "\"wind\": " + wind + "," + "\"sun\": " + sun + ","
				+ "\"daytime\": \"" + daytime + "\"" + "}";
	}
}
