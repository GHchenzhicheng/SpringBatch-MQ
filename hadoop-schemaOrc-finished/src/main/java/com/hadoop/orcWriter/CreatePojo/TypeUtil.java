package com.hadoop.orcWriter.CreatePojo;

import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;

public class TypeUtil {
	public static ColumnVector TypeMapping(String javaType) {
		if("bigint".equalsIgnoreCase(javaType) || "date".equalsIgnoreCase(javaType) 
				|| "int".equalsIgnoreCase(javaType) || "smallint".equalsIgnoreCase(javaType)
				|| "tinyint".equalsIgnoreCase(javaType)
				||"short".equalsIgnoreCase(javaType)) {
			return  new LongColumnVector();
		}
		if("binary".equalsIgnoreCase(javaType) || "char".equalsIgnoreCase(javaType)
				|| "string".equalsIgnoreCase(javaType) || "varchar".equalsIgnoreCase(javaType)||
				"byte".equalsIgnoreCase(javaType)) {
			return new BytesColumnVector();
			
		}
		if("double".equalsIgnoreCase(javaType) || "float".equalsIgnoreCase(javaType)) {
			return new DoubleColumnVector();
		}
		if("timestamp".equalsIgnoreCase(javaType)) {
			return new TimestampColumnVector();
		}
		if("decimal".equalsIgnoreCase(javaType)) {
			return new DecimalColumnVector(10, 0);
		}
		throw new RuntimeException("无法识别数据类型" + javaType);
	}
}
