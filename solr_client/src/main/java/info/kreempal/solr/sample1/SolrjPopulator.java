package info.kreempal.solr.sample1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class SolrjPopulator implements SolrConstants {
	  public static void main(String[] args) throws IOException, SolrServerException {
	    SolrClient client = new HttpSolrClient.Builder("http://"+HOST+":8983/solr/"+COLLECTION).build();
	    for(int i=1;i<=1000;++i) {
	      SolrInputDocument doc = new SolrInputDocument();
	      doc.addField("cat", "book");
	      doc.addField("id", "book-" + i);
	      doc.addField("name", "The Legend of the Hobbit part " + i);
	      client.add(doc);
    	  System.out.print(".");
	      if(i%100==0) {
	    	  System.out.println(i);
	    	  client.commit();  // periodically flush
	      }
	    }
	    client.commit();
	  }
	}
