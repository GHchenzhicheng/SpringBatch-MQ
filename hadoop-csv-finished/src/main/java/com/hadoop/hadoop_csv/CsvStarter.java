package com.hadoop.hadoop_csv;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hadoop.hadoop_csv.conf.CSVReader;
import com.hadoop.hadoop_csv.conf.CSVconfig;


@SpringBootApplication
public class CsvStarter {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(CsvStarter.class, args);
		CSVconfig.csvWriter();
//		CSVReader.reader();
	}
}
