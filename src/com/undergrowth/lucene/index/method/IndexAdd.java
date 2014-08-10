/**
 * 
 */
package com.undergrowth.lucene.index.method;


import java.io.File;
import java.io.IOException;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.undergrowth.bean.StuInf;
import com.undergrowth.lucene.index.utils.IndexUtils;
import com.undergrowth.utils.EntityManagerHelper;

/**
 * @author u1
 *
 */
public class IndexAdd {

	private static Logger logger=LogManager.getLogger(IndexAdd.class);
	
	//�������
	public static void addIndex(List<StuInf> entityList) {
		// TODO Auto-generated method stub
		IndexWriter writer=null;
		try {
			writer=IndexUtils.getIndexWrite();
			for (StuInf stuInf : entityList) {
				Document document=new Document();
				document.add(new TextField("id", stuInf.getId(),Field.Store.YES));
				document.add(new TextField("birthday", stuInf.getBirthday().toString(),Field.Store.YES));
				document.add(new TextField("path",stuInf.getDescriptPath(),Field.Store.YES));
				document.add(new TextField("content",EntityManagerHelper.getDescriptContent(stuInf.getDescriptPath()),Field.Store.YES));
				//���������
				writer.addDocument(document);
			}
			//�ύ���� �ر����й����ļ�
			writer.close();
			logger.info("�����"+entityList.size()+"������");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
}
