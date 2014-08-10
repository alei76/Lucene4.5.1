package com.undergrowth.lucene.index.method;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.IndexWriter;
import com.undergrowth.lucene.index.utils.IndexUtils;

public class IndexDelete {

	private static Logger logger=LogManager.getLogger();
	
	//删除索引
	public static void deleteIndexAll() {
		// TODO Auto-generated method stub
		IndexWriter writer=IndexUtils.getIndexWrite();
		try {
			//删除所有索引项
			writer.deleteAll();
			writer.close();
			logger.info("成功删除索引文件");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
