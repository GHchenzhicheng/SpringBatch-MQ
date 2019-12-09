package com.rc.Hadoop_rcFile_finished;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hive.ql.io.RCFile;
import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.hive.serde2.columnar.BytesRefWritable;

public class RCfileWriter {
	private static String strings[] = { "1,true,123.123,2012-10-24 08:55:00",
			"2,false,1243.5,2012-10-25 13:40:00",
			"3,false,24453.325,2008-08-22 09:33:21.123",
			"4,false,243423.325,2007-05-12 22:32:21.33454",
			"5,true,243.325,1953-04-22 09:11:33 " };
	public static void createRcFile(Path src, Configuration conf)
			throws IOException {
		conf.setInt(RCFile.COLUMN_NUMBER_CONF_STR, 4);// 列数
		conf.setInt(RCFile.Writer.COLUMNS_BUFFER_SIZE_CONF_STR, 4 * 1024 * 1024);// 决定行数参数一
		conf.setInt(RCFile.RECORD_INTERVAL_CONF_STR, 3);// 决定行数参数二
		FileSystem fs = FileSystem.get(conf);
		RCFile.Writer writer = new RCFile.Writer(fs, conf, src);
		BytesRefArrayWritable cols = new BytesRefArrayWritable(4);// 列数，可以动态获取
		BytesRefWritable col = null;
		for (String s : strings) {
			String splits[] = s.split(",");
			int count = 0;
			for (String split : splits) {
				col = new BytesRefWritable(Bytes.toBytes(split), 0,
						Bytes.toBytes(split).length);
				cols.set(count, col);
				count++;
			}
			writer.append(cols);
		}
		writer.close();
		fs.close();
	}
}
