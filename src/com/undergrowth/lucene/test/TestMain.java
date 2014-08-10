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

	/**该测试代码主要做这么几件事
	 * 使用jpa2.0作为规范,eclipselink2.4作为实现 ,myeclipse2013作为工具映射oracle11g的表为实体(使用jpa反向工程自动生成相应实体的dao与interface,还有EntityManager的工具类)
	 * 使用poi读取word中的内容存放到lucene的索引库中
	 * 最后
	 * 1.添加索引
	 * 2.更新索引----lucene的更新实际上 是先将索引删掉 然后重新添加索引 所以这里要注意一点 如果你的索引项不需要修改的话 不要将不需要修改的索引项传递到修改方法里面 不然会造成未修改的索引项有多个
	 * 3.删除索引
	 * 4.根据条件,查找记录
	 * @param args
	 */
	public static Logger logger=LogManager.getLogger(TestMain.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuInfDAO entityDao=new StuInfDAO();
		List<StuInf> entityList=entityDao.findAll();
		disRecord(entityList);
		//删除掉已存在的所有索引
		IndexDelete.deleteIndexAll();
		//讲查询出来的记录添加到索引中
		IndexAdd.addIndex(entityList);
		//查询记录
		TermQuery query=new TermQuery(new Term("content", "搜索引擎"));
		//TermQuery query=new TermQuery(new Term("path", "lucene"));
		List<Document> resultList=SearchUtils.search(query,10);
		disResultRecord(resultList);
		
		//更新数据源
	    EntityManagerHelper.beginTransaction();
	    for (StuInf entity : entityList) {
	    	entity.setBirthday(new Timestamp(new Date().getTime()));
			entityDao.update(entity);
		}
	    EntityManagerHelper.commit();
	    //清除结果集
	    resultList.clear();
	    //重新装载结果集
	    for (StuInf stuInf : entityList) {
			Document document=new Document();
			document.add(new TextField("id", stuInf.getId(),Field.Store.YES));
			document.add(new TextField("birthday", stuInf.getBirthday().toString(),Field.Store.YES));
			document.add(new TextField("path",stuInf.getDescriptPath(),Field.Store.YES));
			document.add(new TextField("content",EntityManagerHelper.getDescriptContent(stuInf.getDescriptPath()),Field.Store.YES));
			resultList.add(document);
		}
	  //更新索引
		IndexUpdate.updateIndex(new Term("content", "搜索引擎"),resultList);
	  //再次搜索
		TermQuery queryUpdate=new TermQuery(new Term("content", "搜索引擎"));
		resultList=SearchUtils.search(queryUpdate,10);
		disResultRecord(resultList);
		//显示未更新的索引的记录
		queryUpdate=new TermQuery(new Term("content", "甲骨文"));
		resultList=SearchUtils.search(queryUpdate,10);
		disResultRecord(resultList);
	}

	//显示结果集
	private static void disResultRecord(List<Document> resultList) {
		// TODO Auto-generated method stub
		logger.error("开始显示结果!!");
		for (Document doc : resultList) {
			/*System.out.println(stuInf);*/
			logger.error("\n日期:"+doc.get("birthday")+"\n描述信息为:"+doc.get("content"));
		}
	}

	//显示查询的记录
	private static void disRecord(List<StuInf> entityList) {
		for (StuInf stuInf : entityList) {
			/*System.out.println(stuInf);*/
			logger.debug(stuInf);
		}
	}

}
