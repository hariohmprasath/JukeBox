package com.personal.music.test;

import com.personal.music.Crawler;
import com.personal.music.parser.AlbumParserConfiguration;
import com.personal.music.parser.AlbumParserUnit;
import com.personal.music.pojo.AlbumJSON;
import com.personal.music.pojo.AlbumReaderConfiguration;
import com.personal.music.pojo.Configuration;
import com.personal.music.util.JAXBUtils;
import com.personal.music.util.ParserConfigurationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hrajagopal on 5/18/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-appContext.xml"})
public class TestCrawler {

    @Autowired
    private Crawler crawler;

    @Autowired
    private JAXBUtils jaxbUtils;

    @Test
    public void testCrawler() {
        crawler.parseAndIndexData();
    }

    @Test
    public void testSongCrawler() {
        try {
            Configuration configuration = jaxbUtils.convertStringToObject(ParserConfigurationUtil.getParserConfigStream(), Configuration.class);

            List<String> albumIndexNameCollection = new LinkedList<>();
            List<AlbumJSON> albumJSONList = new ArrayList<>();
            AlbumReaderConfiguration albumReaderConfiguration = configuration.getPageConfiguration().get(0).getAlbumReaderConfigurations().get(0);
            AlbumParserConfiguration parserConfiguration = new AlbumParserConfiguration();
            parserConfiguration.setAlbumIndexNameCollection(new ArrayList<>(albumIndexNameCollection));
            parserConfiguration.setAlbumReaderConfiguration(albumReaderConfiguration);
            parserConfiguration.setSongReaderConfiguration(configuration.getPageConfiguration().get(0).getSongReaderConfiguration());

            AlbumJSON albumJSON = new AlbumJSON();
            albumJSON.setId("11");
            albumJSON.setAlbumName("Puli");
            albumJSON.setAlbumUrl("http://tamiltunes.me/aarambam-2013.html");

            AlbumParserUnit parserUnit = new AlbumParserUnit(parserConfiguration);
            parserUnit.getSongs(albumJSONList, albumJSON, "INDEX_NAME");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
