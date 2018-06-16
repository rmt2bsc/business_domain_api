package org.modules.audiovideo;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.RMT2Base;
import com.RMT2Constants;
import com.util.RMT2Date;

import entagged.audioformats.AudioFile;
import entagged.audioformats.AudioFileIO;
import entagged.audioformats.generic.TagTextField;

/**
 * An EntaggedMp3 implementaion of {@link MP3Reader}
 * 
 * @author appdev
 * 
 */
class EntaggedMp3LibImpl extends RMT2Base implements MP3Reader {

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
        } catch (Throwable e) {
            throw new Mp3ReaderIdentityNotConfiguredException("Unable to create Entagged Mp3 Implementation", e);
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
