package com.hadoop.hadoop_csv.conf;

import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.springframework.data.hadoop.store.codec.Codecs;
import org.springframework.data.hadoop.store.output.DelimitedTextFileWriter;

public class CSVconfig {
	
	public static void csvWriter() throws IOException {
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.83:9000");
		conf.setBoolean("dfs.support.append", true);
		Path path=new Path("/writer9.csv");
		DelimitedTextFileWriter writer=new DelimitedTextFileWriter(conf, path,null/*Codecs.BZIP2.getCodecInfo()*/);
		Map<String, Object> map=new LinkedHashMap<String, Object>();
		map.put("id", 1);
		map.put("name", "尚力力");
		map.put("weight", 70.5);
		map.put("time", new Timestamp(System.currentTimeMillis()));
		map.put("Man", true);
		for(int i=0;i<100;i++) {
		List<String> list=new ArrayList<>();
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for(Entry<String, Object> entry:entrySet) {
			String value = entry.getValue().toString();
			list.add(value);
		}
		writer.write(list);
		
		}
		writer.flush();
		writer.close();
	}
}
