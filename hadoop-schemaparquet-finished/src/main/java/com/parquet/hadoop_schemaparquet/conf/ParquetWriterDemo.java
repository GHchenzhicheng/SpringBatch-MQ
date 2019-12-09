package com.parquet.hadoop_schemaparquet.conf;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.ipc.RPC;
import org.apache.parquet.column.ParquetProperties.WriterVersion;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.Int96Value;
import org.apache.parquet.example.data.simple.NanoTime;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;


public class ParquetWriterDemo {
	
//	 static String ClusterName = "192.168.100.83:9000";
//		private static final String HADOOP_URL = "hdfs://"+ClusterName;
//		public static Configuration conf;
//
//	    static {
//	        conf = new Configuration();
//	        conf.set("fs.defaultFS", HADOOP_URL);
//	        conf.set("dfs.nameservices", ClusterName);
//	        conf.set("dfs.ha.namenodes."+ClusterName, "nn1,nn2");
//	        conf.set("dfs.namenode.rpc-address."+ClusterName+".nn1", "192.168.100.83:9000");
//	        conf.set("dfs.namenode.rpc-address."+ClusterName+".nn2", "192.168.100.84:9000");
//	        //conf.setBoolean(name, value);
//	        conf.set("dfs.client.failover.proxy.provider."+ClusterName, 
//	        		"org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
//	        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
//	    }
	
	public static void writer() throws IOException, ParseException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.83:9000");
		Path path = new Path("/parquet7/d.parquet");
		MessageType schema = MessageTypeParser.parseMessageType(SchemaType.getSchemaType());
		SimpleGroupFactory sgf=new SimpleGroupFactory(schema);
		GroupWriteSupport.setSchema(schema, conf);
		@SuppressWarnings("deprecation")
		ParquetWriter<Group> writer=new ParquetWriter<>(path,  new GroupWriteSupport(), CompressionCodecName.SNAPPY, 
				128*1024*1024, 
                5*1024*1024, 
                5*1024*1024, 
                ParquetWriter.DEFAULT_IS_DICTIONARY_ENABLED, 
                ParquetWriter.DEFAULT_IS_VALIDATING_ENABLED, 
                WriterVersion.PARQUET_1_0, 
                conf);
		for(int j=0;j<100000;j++) {
		Map<String, Object> create = CreateSchema.create();
		Set<Entry<String, Object>> entrySet = create.entrySet();
		ArrayList<String> list = CreateSchema.createList();
		int i=0;
		Group group=sgf.newGroup();
		for(Entry<String, Object> entry:entrySet) {
			String type = list.get(i++);
			String fieldType = UtilType.getFieldType(type);
			if("int32".equalsIgnoreCase(fieldType)) {
				group.append(entry.getKey(), Integer.valueOf(entry.getValue().toString()));
			}
			if("binary".equalsIgnoreCase(fieldType)) {
				group.append(entry.getKey(), String.valueOf(entry.getValue()));
			}
			if("int64".equalsIgnoreCase(fieldType)) {
				group.append(entry.getKey(), Long.valueOf(entry.getValue().toString()));
			}
			if("double".equalsIgnoreCase(fieldType)) {
				group.append(entry.getKey(), Double.valueOf(entry.getValue().toString()));
			}
			if("boolean".equalsIgnoreCase(fieldType)) {
				group.append(entry.getKey(), Boolean.valueOf(entry.getValue().toString()));
			}
			if("int96".equalsIgnoreCase(fieldType)) {
				group.append(entry.getKey(), Integer.valueOf(entry.getValue().toString()));
			}
		}
		writer.write(group);
		}
		writer.close();
	}
}
