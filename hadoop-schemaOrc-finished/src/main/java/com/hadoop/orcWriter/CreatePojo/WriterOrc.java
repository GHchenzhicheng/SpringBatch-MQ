package com.hadoop.orcWriter.CreatePojo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.metastore.api.Decimal;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;

public class WriterOrc {
	public static void writer(Configuration conf,Path path) throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException {
		TypeDescription schema = SchemaType.schema();
		Writer writerOrc = OrcFile.createWriter(path, OrcFile.writerOptions(conf).setSchema(schema).
				stripeSize(67108864)
				.bufferSize(131072)
				.blockSize(134217728)
				.compress(CompressionKind.ZLIB)
				.version(OrcFile.Version.V_0_12));
		VectorizedRowBatch batch = schema.createRowBatch();
//		封装field类型
		ArrayList<String> type = CreatePojo.getType();
//		封装field的对应的向量
		ArrayList<ColumnVector> vectorList=new ArrayList<>();
		
		for(int i=0;i<type.size();i++) {
			ColumnVector vector = TypeUtil.TypeMapping(type.get(i));
			if(vector instanceof LongColumnVector) {
				LongColumnVector longVector=(LongColumnVector)batch.cols[i];
				vectorList.add(longVector);
			}
			if(vector instanceof DoubleColumnVector) {
				DoubleColumnVector doubleVector=(DoubleColumnVector)batch.cols[i];
				vectorList.add(doubleVector);
			}
			if(vector instanceof BytesColumnVector) {
				BytesColumnVector bytesVector=(BytesColumnVector)batch.cols[i];
				vectorList.add(bytesVector);
			}
			if(vector instanceof DecimalColumnVector) {
				DecimalColumnVector decimalVector=(DecimalColumnVector)batch.cols[i];
				vectorList.add(decimalVector);
			}
			if(vector instanceof TimestampColumnVector) {
				TimestampColumnVector timestampVector=(TimestampColumnVector)batch.cols[i];
				vectorList.add(timestampVector);
			}
		}
		
//		写map数据
		Map<String, Object> map = CreatePojo.create();
		Set<Entry<String, Object>> entrySet = map.entrySet();
			int row = batch.size++;
			int i=0;
		for(Entry<String, Object> entry:entrySet) {
			ColumnVector v = vectorList.get(i++);
			if(v instanceof LongColumnVector) {
				((LongColumnVector) v).vector[row]=Long.parseLong(entry.getValue().toString());
			}
			if(v instanceof DoubleColumnVector) {
				((DoubleColumnVector) v).vector[row]=Double.parseDouble(entry.getValue().toString());
			}
			if(v instanceof BytesColumnVector) {
//				((BytesColumnVector) v).vector[row]= entry.getValue().toString().getBytes();
				byte[] buffer=(entry.getValue().toString().getBytes(StandardCharsets.UTF_8));
				((BytesColumnVector) v).setRef(row, buffer, 0, buffer.length);
			}
			if(v instanceof DecimalColumnVector) {
				((DecimalColumnVector) v).vector[row]=new HiveDecimalWritable(entry.getValue().toString().getBytes(),100);
			}
			if(v instanceof TimestampColumnVector) {
				((TimestampColumnVector) v).time[row]=((Timestamp)entry.getValue()).getTime();
			}
		}
		if (batch.size == batch.getMaxSize()) {
			writerOrc.addRowBatch(batch);
	        batch.reset();
	      }
		
		if (batch.size != 0) {
			writerOrc.addRowBatch(batch);
		    }
		writerOrc.close();
		
	}
}
