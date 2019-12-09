package com.rc.Hadoop_rcFile_finished;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RCstarter {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(RCstarter.class, args);
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.84:9000");
		Path path=new Path("/rc/a.rc");
		RCfileWriter.createRcFile(path, conf);
	}
}
