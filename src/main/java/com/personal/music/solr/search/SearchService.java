package com.personal.music.solr.search;

import com.personal.music.pojo.AlbumJSON;
import com.personal.music.solr.SolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by hrajagopal on 6/14/15.
 */
@Service
public class SearchService {

    public static final String ALBUM = "album";
    public static final String SONG = "song";

    public List<AlbumJSON> search(SearchObject search) {
        if (search != null && search.getSearchType() != null) {
            try {
                int start = (search.getStart() * search.getNumberOfRecords());
                int end = ((search.getStart() + 1) * search.getNumberOfRecords());

                StringBuilder queryBuilder = new StringBuilder();
                if (search.getSearchTerm() != null && search.getSearchTerm().trim().length() > 0) {

                    // Specify search type based on the page you are
                    switch (search.getSearchType()) {
                        case ALBUM:
                            queryBuilder.append("albumName");
                            break;
                        case SONG:
                            queryBuilder.append("songNameList");
                            break;
                    }

                    String searchQuery = search.getSearchTerm().trim();

                    if (searchQuery.trim().contains(" "))
                        searchQuery = searchQuery.replaceAll(Pattern.quote(" "), "+");

                    queryBuilder.append(":");
                    queryBuilder.append(searchQuery);
                    queryBuilder.append("*");
                    queryBuilder.append(" AND ");
                }

                queryBuilder.append("storageType");
                queryBuilder.append(":");
                queryBuilder.append(ALBUM);

                SolrQuery query = new SolrQuery();
                query.setQuery(queryBuilder.toString());
                query.setStart(start);
                query.setRows(end);

                QueryResponse queryResponse = SolrService.getSolrServer().query(query);
                return queryResponse.getBeans(AlbumJSON.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
