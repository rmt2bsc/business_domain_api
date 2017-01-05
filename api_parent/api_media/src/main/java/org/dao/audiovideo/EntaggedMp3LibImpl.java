package org.dao.audiovideo;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import java.io.File;
import java.util.List;

import com.RMT2Base;
import com.RMT2Constants;
import com.SystemException;
import com.util.RMT2Date;

import entagged.audioformats.AudioFile;
import entagged.audioformats.AudioFileIO;
import entagged.audioformats.Tag;
import entagged.audioformats.generic.GenericTag;
import entagged.audioformats.generic.TagField;
import entagged.audioformats.generic.TagTextField;
import entagged.audioformats.mp3.util.id3frames.CommId3Frame;
import entagged.audioformats.mp3.util.id3frames.TextId3Frame;

/**
 * @author appdev
 * 
 */
public class EntaggedMp3LibImpl extends RMT2Base implements MP3Reader {

    private static Logger logger = Logger.getLogger(EntaggedMp3LibImpl.class);

    private AudioFile mp3;

    /**
     * 
     */
    EntaggedMp3LibImpl() {
        return;
    }

    EntaggedMp3LibImpl(File source) {
        try {
            this.mp3 = AudioFileIO.read(source);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new SystemException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getAlbum()
     */
    public String getAlbum() {
        return this.mp3.getTag().getFirstAlbum();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getArtist()
     */
    public String getArtist() {
        return this.mp3.getTag().getFirstArtist();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getComment()
     */
    public String getComment() {
        List<TagTextField> list = this.mp3.getTag().getComment();
        if (list != null && list.size() > 0) {
            String content = list.get(0).getContent();
            return content;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getComposer()
     */
    public String getComposer() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getDiscNumber()
     */
    public int getDiscNumber() {
        return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getDurationSeconds()
     */
    public List<Integer> getDuration() {
        int seconds = this.mp3.getLength();
        List<Integer> list = RMT2Date.convertSecondsToList(seconds);
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getGenre()
     */
    public String getGenre() {
        List<TagTextField> list = this.mp3.getTag().getGenre();
        if (list != null && list.size() > 0) {
            for (TagTextField item : list) {
                String val = item.getContent();
                return val;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getLyricist()
     */
    public String getLyricist() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getProducer()
     */
    public String getProducer() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getTrack()
     */
    public int getTrack() {
        String strTrack = this.mp3.getTag().getFirstTrack();
        int track;
        try {
            track = Integer.parseInt(strTrack);
        } catch (NumberFormatException e) {
            track = 0;
        }
        return track;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getTrackCount()
     */
    public int getTrackCount() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getTrackTitle()
     */
    public String getTrackTitle() {
        return this.mp3.getTag().getFirstTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getYear()
     */
    public int getYear() {
        String strYear = this.mp3.getTag().getFirstYear();
        int year;
        try {
            year = Integer.parseInt(strYear);
        } catch (NumberFormatException e) {
            year = 0;
        }
        return year;
    }

}
