package com.parquet.hadoop_schemaparquet.conf;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CreateSchema {
	public static int i=0;
	public static Map<String, Object> create(){
		Map<String, Object> map=new LinkedHashMap<String, Object>();
		map.put("id", 1+i++);
		map.put("name", "尚力力".getBytes());
		map.put("money", 10000L+i++);
		map.put("Man", true);
		map.put("weight", 70.08+i++);
		map.put("float", 20.32f);
		map.put("date", new Date(System.currentTimeMillis()));
		map.put("time", new Timestamp(System.currentTimeMillis()));
		map.put("new_time", new Time(System.currentTimeMillis()));
		map.put("short", new Short((short)100));
		map.put("bigDecimal", new BigDecimal("31231244343435"));
		return map;
	}
	
	public static ArrayList<String> createList(){
		Map<String, Object> map = CreateSchema.create();
		ArrayList<String> list=new ArrayList<>();
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for(Entry<String, Object> entry:entrySet) {
			Object value = entry.getValue();
			if(value instanceof Float) {
				list.add("float");
			}
			if(value instanceof Integer) {
				list.add("int");
			}
			if(value instanceof Date) {
				list.add("date");
			}
			if("[B".equalsIgnoreCase(entry.getValue().getClass().getName().toString())) {
				list.add("[B");
			}
			if(value instanceof String) {
				list.add("String");
			}
			if(value instanceof Long) {
				list.add("long");
			}
			if(value instanceof Double) {
				list.add("double");
			}
			if(value instanceof Boolean) {
				list.add("boolean");
			}
			if(value instanceof Short) {
				list.add("short");
			}
			if(value instanceof BigDecimal) {
				list.add("bigDecimal");
			}
		}
		return list;
	}
}
