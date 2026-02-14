package me.naming_assistant.naming_assistant.cli.dto.management;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Convention {
	@JsonProperty("Interface")
	private String interfaces = "PascalCase";

	@JsonProperty("Class")
	private String classes = "PascalCase";

	@JsonProperty("Variable")
	private String variables = "camelCase";

	@JsonProperty("Constant")
	private String constants = "UPPER_SNAKE_CASE";

	@JsonProperty("Method")
	private String methods = "camelCase";

	/**
	 * @return interfaces
	 */
	public String getInterfaces() {
		return interfaces;
	}

	/**
	 * @param interfaces セットする interfaces
	 */
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}

	/**
	 * @return classes
	 */
	public String getClasses() {
		return classes;
	}

	/**
	 * @param classes セットする classes
	 */
	public void setClasses(String classes) {
		this.classes = classes;
	}

	/**
	 * @return variables
	 */
	public String getVariables() {
		return variables;
	}

	/**
	 * @param variables セットする variables
	 */
	public void setVariables(String variables) {
		this.variables = variables;
	}

	/**
	 * @return constants
	 */
	public String getConstants() {
		return constants;
	}

	/**
	 * @param constants セットする constants
	 */
	public void setConstants(String constants) {
		this.constants = constants;
	}

	/**
	 * @return methods
	 */
	public String getMethods() {
		return methods;
	}

	/**
	 * @param methods セットする methods
	 */
	public void setMethods(String methods) {
		this.methods = methods;
	}

}
