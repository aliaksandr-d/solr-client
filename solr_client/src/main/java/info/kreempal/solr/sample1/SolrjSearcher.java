package info.kreempal.solr.sample1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SolrjSearcher implements SolrConstants {
    public static void main(String[] args) throws IOException, SolrServerException {
    	SolrClient client = new HttpSolrClient.Builder("http://"+HOST+":8983/solr/"+COLLECTION).build();

        SolrQuery query = new SolrQuery();
        query.setQuery("The Black Company");
        query.addFilterQuery("cat:book","genre_s:fantasy");
        query.setFields("id","genre_s","name","cat","author");
        query.setStart(0);
       	query.set("inStock", false);

        QueryResponse response = client.query(query);
        SolrDocumentList results = response.getResults();
        for (int i = 0; i < results.size(); ++i) {
            System.out.println(results.get(i));
        }
    }
}
