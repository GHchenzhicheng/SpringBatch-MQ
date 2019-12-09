package com.hadoop.orcWriter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hadoop.orcWriter.CreatePojo.ReadOrc;
import com.hadoop.orcWriter.CreatePojo.WriterOrc;



import java.util.List;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcInputFormat;
import org.apache.hadoop.hive.ql.io.orc.OrcSerde;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

@SpringBootApplication
public class WriterStarter {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WriterStarter.class, args);
		Configuration conf=new Configuration();
//		conf.set("fs.defaultFS", "hdfs://192.168.100.84:9000");
//		Path path1 = new Path("/orc/tmp.orc");
//		FileSystem.get(conf);
//		WriterOrc.writer(conf,path1);
		ReadOrc.readOrc(conf);
	}
	
	
	
	/*
	 * public static void orcFileRead() throws Exception { String
	 * path="/orc/tmp.orc"; JobConf conf=new JobConf();
	 * conf.set("fs.default.name","hdfs://192.168.100.84:9000"); OrcSerde serde=new
	 * OrcSerde(); Properties p=new Properties(); p.setProperty("columns",
	 * "name,age,height,weight,time,new_time,bigDecimal");
	 * p.setProperty("columns.types",
	 * "binary,int,smallint,double,timestamp,binary,bigint"); serde.initialize(conf,
	 * p); StructObjectInspector inspector = (StructObjectInspector)
	 * serde.getObjectInspector(); InputFormat in = new OrcInputFormat();
	 * FileInputFormat.setInputPaths(conf, new Path(path)); InputSplit[] splits =
	 * in.getSplits(conf, 1); conf.set("hive.io.file.readcolumn.ids",
	 * "1");//hive.io.file.readcolumn.ids的默认值是空，如果没有字段名
	 * ，就会产生空值，在Integer.parseInt(element)就会报错。 RecordReader reader =
	 * in.getRecordReader(splits[0], conf, Reporter.NULL); Object key =
	 * reader.createKey(); Object value = reader.createValue(); List<? extends
	 * StructField> fields = inspector.getAllStructFieldRefs(); long offset =
	 * reader.getPos(); while(reader.next(key, value)) { Object date_id =
	 * inspector.getStructFieldData(value, fields.get(0)); Object referrer_type =
	 * inspector.getStructFieldData(value, fields.get(1)); Object referrer_shopid =
	 * inspector.getStructFieldData(value, fields.get(2)); Object is_test =
	 * inspector.getStructFieldData(value, fields.get(3)); Object referrer_id =
	 * inspector.getStructFieldData(value, fields.get(4)); Object user_unique =
	 * inspector.getStructFieldData(value, fields.get(5)); Object apply_mobile =
	 * inspector.getStructFieldData(value, fields.get(6)); offset = reader.getPos();
	 * System.out.println(date_id + "|" + referrer_type + "|" + referrer_shopid +
	 * "|" + is_test+ "|" + referrer_id+ "|" + user_unique+ "|" + apply_mobile); } }
	 */
}
