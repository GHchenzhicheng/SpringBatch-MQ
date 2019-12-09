package com.hadoop.hadoop_csv.conf;

import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.springframework.data.hadoop.store.codec.CodecInfo;
import org.springframework.data.hadoop.store.codec.Codecs;

public class MyCodecs implements CodecInfo{
	
	String codecName=null;
	  public MyCodecs(String codecName) {
	  this.codecName=codecName;
	  }
	  
	 
	@Override
	public boolean isSplittable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getCodecClass() {
		// TODO Auto-generated method stub
		return codecName;
	}

	@Override
	public String getDefaultSuffix() {
		// TODO Auto-generated method stub
		return null;
	}

}
