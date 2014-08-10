package com.undergrowth.lucene.index.utils;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.undergrowth.utils.EntityManagerHelper;

public class IndexUtils {
	
	public static Directory getLuceneIndexPath(){
		//��ȡ�������ļ�·��
		File path=new File(EntityManagerHelper.getProperty("luceneIndex"));
		//�ж��ļ��Ƿ���� ������ �򴴽�
		if(!path.exists()) path.mkdirs();
		Directory dictory=null;
		try {
			
			dictory = FSDirectory.open(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dictory;
	}

   //��ȡ����д����
	public static IndexWriter getIndexWrite() {
		IndexWriter writer=null;
		Directory directory=getLuceneIndexPath();
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer(Version.LUCENE_45);
		//Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_45);
		IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_45, analyzer);
		try {
			writer=new IndexWriter(directory, config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer;
	}
	
	
}
