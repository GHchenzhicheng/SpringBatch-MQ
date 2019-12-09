package com.hadoop.hadoop_csv.conf;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.springframework.data.hadoop.store.codec.Codecs;
import org.springframework.data.hadoop.store.input.DelimitedTextFileReader;

public class CSVReader {
	public static void reader() throws IOException {
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.83:9000");
		Path path=new Path("/writer5.csv");
		DelimitedTextFileReader reader=new DelimitedTextFileReader(conf, path,Codecs.BZIP2.getCodecInfo(), ",".getBytes());
		List<String> list=null;
		while((list=reader.read())!=null) {
			System.out.println(list.toString());
		}
		reader.close();
	}
}
