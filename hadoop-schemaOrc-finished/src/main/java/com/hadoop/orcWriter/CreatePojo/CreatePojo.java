package com.hadoop.orcWriter.CreatePojo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.hive.metastore.api.Decimal;

import com.hadoop.orcWriter.POJO.Pojo;

public class CreatePojo {
	private static int i=0;
	public static Map<String, Object> create(){
		Pojo p=new Pojo();
		i++;
		p.setMap(new LinkedHashMap<String, Object>());
		Map<String, Object> map = p.getMap();
		map.put("name", "尚力力".getBytes());
		map.put("age", 26+i);
		map.put("height", new Short((short)100));
		map.put("weight", 70.25+i);
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		map.put("time", timestamp);
		map.put("new_time", new Time(System.currentTimeMillis()));
		map.put("bigDecimal", new BigDecimal("3423432423423"));
		return map;
	}
	
	public static ArrayList<String> getType(){
		ArrayList<String> list=new ArrayList<>();
		Map<String, Object> map = create();
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for(Entry<String, Object> entry:entrySet) {
			Object value = entry.getValue();
			System.out.println(value);
			if("[B".equalsIgnoreCase(value.getClass().getName().toString())) {
				list.add("binary");
			}
			if(value instanceof Short) {
				list.add("smallint");
			}
			if(value instanceof Integer) {
				list.add("int");
			}
			if(value instanceof Double) {
				list.add("double");
			}
			if(value instanceof Timestamp) {
				list.add("timestamp");
			}
			if(value instanceof Time) {
				list.add("binary");
			}
			if(value instanceof BigDecimal) {
				list.add("bigint");
			}
		}
		return list;
	}
}
