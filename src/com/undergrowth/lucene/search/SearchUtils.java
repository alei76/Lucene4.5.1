/**
 * 
 */
package com.undergrowth.lucene.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.undergrowth.lucene.index.method.IndexAdd;
import com.undergrowth.lucene.index.utils.IndexUtils;

/**
 * @author u1
 *
 */
public class SearchUtils {

	private static Logger logger=LogManager.getLogger(SearchUtils.class);
	
	//根据查询条件 返回结果集
	public static List<Document> search(TermQuery query,int n) {
		// TODO Auto-generated method stub
		
		List<Document> list=new ArrayList<Document>();
		Directory directory=IndexUtils.getLuceneIndexPath();
		try {
			IndexReader reader=DirectoryReader.open(directory);
			IndexSearcher searcher=new IndexSearcher(reader);
			TopDocs docs=searcher.search(query, n);
			logger.info("找到了"+docs.totalHits+"个元素");
			ScoreDoc[] scoreDocs=docs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document document=searcher.doc(scoreDoc.doc);
				list.add(document);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
}
