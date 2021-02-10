package org.modules.audiovideo;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;

import com.RMT2Base;
import com.RMT2Constants;
import com.api.util.RMT2Date;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;

/**
 * An Mp3agic implementaion of {@link MP3Reader}
 * 
 * @author appdev
 * 
 */
class Mp3agicMp3LibImpl extends RMT2Base implements MP3Reader {

    private static Logger logger = Logger.getLogger(Mp3agicMp3LibImpl.class);
    private Mp3File mp3;
    private ID3v1 id3v1Tag;
    private ID3v2 id3v2Tag;

    /**
     * 
     */
    Mp3agicMp3LibImpl() {
        return;
    }

    Mp3agicMp3LibImpl(File source) {
        try {
            this.mp3 = new Mp3File(source);

            if (this.mp3.hasId3v1Tag()) {
                this.id3v1Tag = this.mp3.getId3v1Tag();
            }
            else {
                // mp3 does not have an ID3v1 tag, let's create one...
                this.id3v1Tag = new ID3v1Tag();
                this.mp3.setId3v1Tag(id3v1Tag);
            }
            // Use ID3v2 tagging
            if (this.mp3.hasId3v2Tag()) {
                this.id3v2Tag = this.mp3.getId3v2Tag();
            }
            else {
                // mp3 does not have an ID3v2 tag, let's create one...
                this.id3v2Tag = new ID3v24Tag();
                this.mp3.setId3v2Tag(id3v2Tag);
            }
        } catch (Throwable e) {
            throw new Mp3ReaderIdentityNotConfiguredException("Unable to create Mp3agic Mp3 Implementation", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getAlbum()
     */
    public String getAlbum() {
        return this.id3v2Tag.getAlbum();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getArtist()
     */
    public String getArtist() {
        return this.id3v2Tag.getArtist();
    }

    @Override
    public String getAlbumArtist() {
        return this.id3v2Tag.getAlbumArtist();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getComment()
     */
    public String getComment() {
        return id3v2Tag.getComment();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getComposer()
     */
    public String getComposer() {
        return id3v2Tag.getComposer();
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
        long secs = this.mp3.getLengthInSeconds();
        List<Integer> list = RMT2Date.convertSecondsToList(BigInteger.valueOf(secs).intValue());
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getGenre()
     */
    public String getGenre() {
        return this.id3v2Tag.getGenreDescription();
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
        String strTrack = this.id3v2Tag.getTrack();
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
        return this.id3v2Tag.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.audiovideo.MP3Reader#getYear()
     */
    public int getYear() {
        String strYear = this.id3v2Tag.getYear();
        int year;
        try {
            year = Integer.parseInt(strYear);
        } catch (NumberFormatException e) {
            year = 0;
        }
        return year;
    }


}
