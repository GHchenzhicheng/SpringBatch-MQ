package com.parquet.hadoop_schemaparquet.conf;

public class UtilType {
	public static String getFieldType(String type) {
		String typeName=null;
		String lowerType = type.toLowerCase();
		switch (lowerType) {
		case "int":
		case "short":
			typeName="int32"; 
			break;
		case "string":
		case "timestamp":
		case "date":
		case "[b":
			typeName="binary";
			break;
		case "long":
		case "bigdecimal":
			typeName="int64";
			break;
		case "double":
			typeName="double";
			break;
		case "float":
			typeName="float";
			break;
		case "boolean":
			typeName="boolean";
			break;
		/*
		 * case "bigdecimal": typeName="int96"; break;
		 */
		default:
			System.out.println("~~~~~~该格式未知~~~~~~");
			break;
		}
		return typeName;
	}

}
