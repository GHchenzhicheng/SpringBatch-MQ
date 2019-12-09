package com.parquet.hadoop_schemaparquet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parquet.hadoop_schemaparquet.conf.ParquetReaderData;
import com.parquet.hadoop_schemaparquet.conf.ParquetWriterDemo;

@SpringBootApplication
public class SchemaStarter {

		public static void main(String[] args) throws IOException, ParseException, InterruptedException, URISyntaxException {
			SpringApplication.run(SchemaStarter.class, args);
//			ParquetWriterDemo.writer();
			ParquetReaderData.parquetReader();
		}
}
