package com.parquet.hadoop_schemaparquet.conf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.example.GroupReadSupport;

public class ParquetReaderData {
	
	public static void parquetReader() throws IOException {
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.83:9000");
		Path path = new Path("/parquet7/d.parquet");
		String schemaType = SchemaType.getSchemaType();
		ParquetReader<Group> reader = ParquetReader.
                builder(new GroupReadSupport(), path)
                .withConf(conf)
                .build();
		Group group = null;
        int rowCount = 0;
        while ((group = reader.read()) != null) {
        	int id = group.getInteger("id", rowCount);
        	int short1 = group.getInteger("short", rowCount);
        	byte[] bytes = group.getBinary("name", rowCount).getBytes();
        	String name=new String(bytes,"UTF-8");
            long money = group.getLong("money", rowCount);
            boolean man = group.getBoolean("Man", rowCount);
            byte[] bytes1 = group.getBinary("new_time", rowCount).getBytes();
        	String new_time=new String(bytes1,"UTF-8");
        	long bigDecimal = group.getLong("bigDecimal", rowCount);
            double weight = group.getDouble("weight", rowCount);
            String time = new String(group.getBinary("time", rowCount).getBytes(), "UTF-8");
            String date=new String(group.getBinary("date", rowCount).getBytes(),"UTF-8");
            System.out.println("name : " + name + " id : " + id +"money : " + money + " Man : " + man +"weight :"+weight+"time :"+time+"date:"+date+"short:"+short1+"new_time:"+new_time+"bigDecimal:"+bigDecimal);
        }
        reader.close();
	}
}
