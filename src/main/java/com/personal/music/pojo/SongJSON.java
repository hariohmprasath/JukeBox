package com.personal.music.pojo;

import org.apache.solr.client.solrj.beans.Field;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hrajagopal on 8/19/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SongJSON {
    @Field
    @XmlElement
    private String songName;

    @Field
    @XmlElement
    private String songUrl;

    @Field
    @XmlElement
    private String musicDirector;

    @Field
    @XmlElement
    private String releaseDate;

    @Field
    @XmlElement
    private String albumArt;

    @Field
    @XmlElement
    private String albumName;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getMusicDirector() {
        return musicDirector;
    }

    public void setMusicDirector(String musicDirector) {
        this.musicDirector = musicDirector;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }
}
