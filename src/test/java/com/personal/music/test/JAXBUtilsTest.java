package com.personal.music.test;

import com.personal.music.pojo.*;
import com.personal.music.util.JAXBUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hrajagopal on 5/15/15.
 */

public class JAXBUtilsTest {

    private JAXBUtils jaxbUtils = new JAXBUtils();

    @Test
    public void testJaxBMarshaller() {
        Configuration configuration = new Configuration();
        List<PageConfiguration> pageConfigurationList = new LinkedList<PageConfiguration>();

        PageConfiguration pageConfiguration = new PageConfiguration();
        pageConfiguration.setName("TamilTunes");
        pageConfiguration.setUrl("http://www.tamiltunes.com");
        pageConfiguration.setPageType(PageType.ALBUM);
        pageConfigurationList.add(pageConfiguration);
        pageConfigurationList.add(pageConfiguration);
        configuration.setPageConfiguration(pageConfigurationList);

        List<AlbumReaderConfiguration> albumReaderConfigurationList = new LinkedList<AlbumReaderConfiguration>();
        AlbumReaderConfiguration albumReaderConfiguration = new AlbumReaderConfiguration();
        albumReaderConfiguration.setUrl("a[href]");
        albumReaderConfiguration.setName("a > span");
        albumReaderConfiguration.setList(".azindex-1 > li");
        albumReaderConfiguration.setSongUrl("http://tamiltunes.com/a-e/");
        albumReaderConfiguration.setSplitterType(SplitterType.DELIMITER);
        albumReaderConfiguration.setSplitterValue("-");
        albumReaderConfigurationList.add(albumReaderConfiguration);

//        albumReaderConfiguration = new AlbumReaderConfiguration();
//        albumReaderConfiguration.setUrl("a > @href");
//        albumReaderConfiguration.setName("a > span > text()");
//        albumReaderConfiguration.setList("div[azindex-1] > li");
//        albumReaderConfiguration.setSongUrl("http://tamiltunes.com/f-j/");
//        albumReaderConfiguration.setSplitterType(SplitterType.DELIMITER);
//        albumReaderConfiguration.setSplitterValue("-");
//        albumReaderConfigurationList.add(albumReaderConfiguration);
//
//        albumReaderConfiguration = new AlbumReaderConfiguration();
//        albumReaderConfiguration.setUrl("a > @href");
//        albumReaderConfiguration.setName("a > span > text()");
//        albumReaderConfiguration.setList("div[azindex-1] > li");
//        albumReaderConfiguration.setSongUrl("http://tamiltunes.com/k-o/");
//        albumReaderConfiguration.setSplitterType(SplitterType.DELIMITER);
//        albumReaderConfiguration.setSplitterValue("-");
//        albumReaderConfigurationList.add(albumReaderConfiguration);
//
//        albumReaderConfiguration = new AlbumReaderConfiguration();
//        albumReaderConfiguration.setUrl("a > @href");
//        albumReaderConfiguration.setName("a > span > text()");
//        albumReaderConfiguration.setList("div[azindex-1] > li");
//        albumReaderConfiguration.setSongUrl("http://tamiltunes.com/p-t/");
//        albumReaderConfiguration.setSplitterType(SplitterType.DELIMITER);
//        albumReaderConfiguration.setSplitterValue("-");
//        albumReaderConfigurationList.add(albumReaderConfiguration);
//
//        albumReaderConfiguration = new AlbumReaderConfiguration();
//        albumReaderConfiguration.setUrl("a > @href");
//        albumReaderConfiguration.setName("a > span > text()");
//        albumReaderConfiguration.setList("div[azindex-1] > li");
//        albumReaderConfiguration.setSongUrl("http://tamiltunes.com/u-z/");
//        albumReaderConfiguration.setSplitterType(SplitterType.DELIMITER);
//        albumReaderConfiguration.setSplitterValue("-");
//        albumReaderConfigurationList.add(albumReaderConfiguration);

        pageConfiguration.setAlbumReaderConfigurations(albumReaderConfigurationList);

        List<SongReaderConfiguration> listOfSongReaderConfiguration = new ArrayList<>();
        SongReaderConfiguration songReaderConfiguration = new SongReaderConfiguration();
        songReaderConfiguration.setList(".content > ul > li");
        songReaderConfiguration.setName("a");
        songReaderConfiguration.setUrl("a[href]");
        songReaderConfiguration.setSplitterType(SplitterType.DELIMITER);
        songReaderConfiguration.setSplitterValue("-");
        listOfSongReaderConfiguration.add(songReaderConfiguration);

        songReaderConfiguration = new SongReaderConfiguration();
        songReaderConfiguration.setList(".content > p > em");
        songReaderConfiguration.setName("a");
        songReaderConfiguration.setUrl("a[href]");
        songReaderConfiguration.setSplitterType(SplitterType.DELIMITER);
        songReaderConfiguration.setSplitterValue("-");
        listOfSongReaderConfiguration.add(songReaderConfiguration);
        pageConfiguration.setSongReaderConfiguration(listOfSongReaderConfiguration);

        String pageConfigurationXml = jaxbUtils.convertObjectToString(configuration, Configuration.class);
        System.out.println(pageConfigurationXml);
        Assert.assertNotNull(pageConfigurationXml);
    }
}
