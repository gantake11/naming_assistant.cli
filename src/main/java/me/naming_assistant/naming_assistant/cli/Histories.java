package me.naming_assistant.naming_assistant.cli;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Histories {
	@JsonProperty("Interface")
	private List<History> interfaceList= new ArrayList<>();

	@JsonProperty("Class")
	private List<History> classList= new ArrayList<>();

	@JsonProperty("Variable")
	private List<History> variableList= new ArrayList<>();

	@JsonProperty("Constant")
	private List<History> constantList= new ArrayList<>();

	@JsonProperty("Method")
	private List<History> methodList= new ArrayList<>();

	/**
	 * @return interfaceList
	 */
	public List<History> getInterfaceList() {
		return interfaceList;
	}

	/**
	 * @param interfaceList セットする interfaceList
	 */
	public void setInterfaceList(List<History> interfaceList) {
		this.interfaceList = interfaceList;
	}

	/**
	 * @return classList
	 */
	public List<History> getClassList() {
		return classList;
	}

	/**
	 * @param classList セットする classList
	 */
	public void setClassList(List<History> classList) {
		this.classList = classList;
	}

	/**
	 * @return variableList
	 */
	public List<History> getVariableList() {
		return variableList;
	}

	/**
	 * @param variableList セットする variableList
	 */
	public void setVariableList(List<History> variableList) {
		this.variableList = variableList;
	}

	/**
	 * @return constantList
	 */
	public List<History> getConstantList() {
		return constantList;
	}

	/**
	 * @param constantList セットする constantList
	 */
	public void setConstantList(List<History> constantList) {
		this.constantList = constantList;
	}

	/**
	 * @return methodList
	 */
	public List<History> getMethodList() {
		return methodList;
	}

	/**
	 * @param methodList セットする methodList
	 */
	public void setMethodList(List<History> methodList) {
		this.methodList = methodList;
	}

}
