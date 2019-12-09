package com.parquet.hadoop_schemaparquet.conf;

import java.util.ArrayList;
import java.util.Map;

public class SchemaType {
	
	public static String getSchemaType() {
		Map<String, Object> map = CreateSchema.create();
		ArrayList<String> list = CreateSchema.createList();
		
		StringBuffer sb=new StringBuffer();
		sb.append("message test{");
		int i=0;
		for(String key:map.keySet()) {
			String fieldType = UtilType.getFieldType(list.get(i++));
			sb.append("repeated").append(" ").append(fieldType).append(" ").append(key).append(";");
		}
		sb.append("}");
		return sb.toString();
		
	}

}
