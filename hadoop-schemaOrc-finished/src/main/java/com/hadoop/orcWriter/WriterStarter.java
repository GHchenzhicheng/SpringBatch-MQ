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

@SpringBootApplication
public class WriterStarter {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException {
		SpringApplication.run(WriterStarter.class, args);
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.83:9000");
		Path path1 = new Path("/orc333/tmp8.orc");
//		FileSystem.get(conf);
		WriterOrc.writer(conf,path1);
//		ReadOrc.readOrc(conf);
	}
}
