package com.hadoop.orcWriter.POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Pojo implements Serializable{
	private Map<String, Object> map;
	private ArrayList<String> array;
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public ArrayList<String> getArray() {
		return array;
	}
	public void setArray(ArrayList<String> array) {
		this.array = array;
	}
	@Override
	public String toString() {
		return "Pojo [map=" + map + ", array=" + array + "]";
	}
	
}
