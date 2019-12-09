package com.hadoop.orcWriter.CreatePojo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;
import org.apache.orc.TypeDescription;

public class ReadOrc {
	public static void readOrc(	Configuration conf) throws IllegalArgumentException, IOException {
		conf.set("fs.defaultFS", "hdfs://192.168.100.84:9000");
		TypeDescription schema = TypeDescription.fromString("struct<name:binary,age:int,height:smallint,weight:double,time:timestamp,new_time:binary,bigDecimal:bigint>");
		Reader reader = OrcFile.createReader(new Path("/orc/tmp.orc"), OrcFile.readerOptions(conf));
		
		VectorizedRowBatch batch = schema.createRowBatch();
		RecordReader rowIterator = reader.rows(reader.options().schema(schema));
		
		BytesColumnVector name=(BytesColumnVector) batch.cols[0];
		LongColumnVector age=(LongColumnVector) batch.cols[1];
		LongColumnVector height=(LongColumnVector) batch.cols[2];
		DoubleColumnVector weight=(DoubleColumnVector) batch.cols[3];
		TimestampColumnVector time=(TimestampColumnVector) batch.cols[4];
		BytesColumnVector new_time=(BytesColumnVector) batch.cols[5];
		LongColumnVector bigDecimal=(LongColumnVector) batch.cols[6];

		
		while(rowIterator.nextBatch(batch)) {
			for(int row=0;row<batch.size;row++) {
				 int nameRow = name.isRepeating ? 0:row;
				 int ageRow = age.isRepeating ? 0:row;
				 int heightRow  = height.isRepeating ? 0:row;
				 int weightRow = weight.isRepeating ? 0:row;
				 int timeRow= time.isRepeating ? 0:row;
				 int new_timeRow = new_time.isRepeating ? 0:row;
				 int bigDecimalRow=bigDecimal.isRepeating ? 0:row;
				 String sname=new String(name.vector[nameRow]);
				 System.out.println("name:"+(name.noNulls || !name.isNull[nameRow] ? sname : null));
				 System.out.println("age:"+(age.noNulls || !age.isNull[ageRow] ? age.vector[ageRow] : null));
				 System.out.println("height:"+(height.noNulls || !height.isNull[ageRow] ? height.vector[heightRow] : null));
				 System.out.println("weight:"+(weight.noNulls || !weight.isNull[weightRow] ? weight.vector[weightRow] : null));
				 System.out.println("time:"+(time.noNulls || !time.isNull[timeRow] ? new SimpleDateFormat("yyyy-MM-dd").format(new Date(time.time[timeRow])): null));
				 System.out.println("new_time"+(new_time.noNulls || !new_time.isNull[nameRow] ? new String(new_time.vector[new_timeRow]) : null));
				 System.out.println("bigDecimal"+(bigDecimal.noNulls || !bigDecimal.isNull[bigDecimalRow] ? bigDecimal.vector[bigDecimalRow]: null));
			}
		}
		 rowIterator.close();
	}

}
