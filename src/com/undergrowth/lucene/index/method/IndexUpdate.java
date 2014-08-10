package com.undergrowth.lucene.index.method;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;

import com.undergrowth.lucene.index.utils.IndexUtils;

public class IndexUpdate {

	//¸üÐÂË÷Òý
	public static void updateIndex(Term term,List<Document> resultList) {
		// TODO Auto-generated method stub
		IndexWriter writer=IndexUtils.getIndexWrite();
		
		try {
			writer.updateDocuments(term, resultList);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

}
