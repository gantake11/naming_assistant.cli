package me.naming_assistant.naming_assistant.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NamingContext {
	@JsonProperty("project_name")
	private String projectName;
	
	private String language = "Java";
	
	private Convention conventions = new Convention();
	
	private Histories histories = new Histories();

	/**
	 * @return projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName セットする projectName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language セットする language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return conventions
	 */
	public Convention getConventions() {
		return conventions;
	}

	/**
	 * @param conventions セットする conventions
	 */
	public void setConventions(Convention conventions) {
		this.conventions = conventions;
	}

	/**
	 * @return histories
	 */
	public Histories getHistories() {
		return histories;
	}

	/**
	 * @param histories セットする histories
	 */
	public void setHistories(Histories histories) {
		this.histories = histories;
	}
}
