package me.naming_assistant.naming_assistant.cli;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class History {
	private String name;

	@JsonProperty("package")
	private String packageName;

	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd")  // 日付フォーマットの指定
    private LocalDate timestamp;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName セットする packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description セットする description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return timestamp
	 */
	public LocalDate getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp セットする timestamp
	 */
	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
	
}
