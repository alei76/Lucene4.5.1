/**
 * 
 */
package com.undergrowth.lucene.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;


import com.undergrowth.bean.StuInf;
import com.undergrowth.bean.StuInfDAO;
import com.undergrowth.lucene.index.method.IndexAdd;
import com.undergrowth.lucene.index.method.IndexDelete;
import com.undergrowth.lucene.index.method.IndexUpdate;
import com.undergrowth.lucene.search.SearchUtils;
import com.undergrowth.utils.EntityManagerHelper;

/**
 * @author u1
 *
 */
public class TestMain {

	/**�ò��Դ�����Ҫ����ô������
	 * ʹ��jpa2.0��Ϊ�淶,eclipselink2.4��Ϊʵ�� ,myeclipse2013��Ϊ����ӳ��oracle11g�ı�Ϊʵ��(ʹ��jpa���򹤳��Զ�������Ӧʵ���dao��interface,����EntityManager�Ĺ�����)
	 * ʹ��poi��ȡword�е����ݴ�ŵ�lucene����������
	 * ���
	 * 1.�������
	 * 2.��������----lucene�ĸ���ʵ���� ���Ƚ�����ɾ�� Ȼ������������� ��������Ҫע��һ�� �������������Ҫ�޸ĵĻ� ��Ҫ������Ҫ�޸ĵ�������ݵ��޸ķ������� ��Ȼ�����δ�޸ĵ��������ж��
	 * 3.ɾ������
	 * 4.��������,���Ҽ�¼
	 * @param args
	 */
	public static Logger logger=LogManager.getLogger(TestMain.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuInfDAO entityDao=new StuInfDAO();
		List<StuInf> entityList=entityDao.findAll();
		disRecord(entityList);
		//ɾ�����Ѵ��ڵ���������
		IndexDelete.deleteIndexAll();
		//����ѯ�����ļ�¼��ӵ�������
		IndexAdd.addIndex(entityList);
		//��ѯ��¼
		TermQuery query=new TermQuery(new Term("content", "��������"));
		//TermQuery query=new TermQuery(new Term("path", "lucene"));
		List<Document> resultList=SearchUtils.search(query,10);
		disResultRecord(resultList);
		
		//��������Դ
	    EntityManagerHelper.beginTransaction();
	    for (StuInf entity : entityList) {
	    	entity.setBirthday(new Timestamp(new Date().getTime()));
			entityDao.update(entity);
		}
	    EntityManagerHelper.commit();
	    //��������
	    resultList.clear();
	    //����װ�ؽ����
	    for (StuInf stuInf : entityList) {
			Document document=new Document();
			document.add(new TextField("id", stuInf.getId(),Field.Store.YES));
			document.add(new TextField("birthday", stuInf.getBirthday().toString(),Field.Store.YES));
			document.add(new TextField("path",stuInf.getDescriptPath(),Field.Store.YES));
			document.add(new TextField("content",EntityManagerHelper.getDescriptContent(stuInf.getDescriptPath()),Field.Store.YES));
			resultList.add(document);
		}
	  //��������
		IndexUpdate.updateIndex(new Term("content", "��������"),resultList);
	  //�ٴ�����
		TermQuery queryUpdate=new TermQuery(new Term("content", "��������"));
		resultList=SearchUtils.search(queryUpdate,10);
		disResultRecord(resultList);
		//��ʾδ���µ������ļ�¼
		queryUpdate=new TermQuery(new Term("content", "�׹���"));
		resultList=SearchUtils.search(queryUpdate,10);
		disResultRecord(resultList);
	}

	//��ʾ�����
	private static void disResultRecord(List<Document> resultList) {
		// TODO Auto-generated method stub
		logger.error("��ʼ��ʾ���!!");
		for (Document doc : resultList) {
			/*System.out.println(stuInf);*/
			logger.error("\n����:"+doc.get("birthday")+"\n������ϢΪ:"+doc.get("content"));
		}
	}

	//��ʾ��ѯ�ļ�¼
	private static void disRecord(List<StuInf> entityList) {
		for (StuInf stuInf : entityList) {
			/*System.out.println(stuInf);*/
			logger.debug(stuInf);
		}
	}

}
