package com.hadoop.orcWriter.CreatePojo;

import java.util.ArrayList;
import java.util.Map;

import org.apache.orc.TypeDescription;

public class SchemaType {
	public static TypeDescription schema() {
		Map<String, Object> map = CreatePojo.create();
		ArrayList<String> list = CreatePojo.getType();
		StringBuffer sb=new StringBuffer();
		sb.append("struct<");
		int i=0;
		for(String key:map.keySet()) {
			String type = list.get(i);
			i++;
			sb.append(key).append(":").append(type).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(">");
		System.out.println(sb.toString());
		TypeDescription schema = TypeDescription.fromString(sb.toString());
		return schema;
	}
	
}
