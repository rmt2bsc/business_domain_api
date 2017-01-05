package org.dao.audiovideo;

import java.io.File;
import java.util.List;

import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.ID3Tag;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.v1.ID3V1Tag;
import org.blinkenlights.jid3.v2.ID3V2Tag;

import com.RMT2Base;

/**
 * @author rterrell
 * 
 */
public class Id3Mp3WmvLibImpl extends RMT2Base implements MP3Reader {

    private MP3File mediaFile;

    private ID3V1Tag tag1;

    private ID3V2Tag tag2;

    /**
     * 
     */
    public Id3Mp3WmvLibImpl(File mp3Source) {
        this.mediaFile = new MP3File(mp3Source);
        try {
            this.tag1 = this.mediaFile.getID3V1Tag();
            this.tag2 = this.mediaFile.getID3V2Tag();
            ID3Tag tags[] = this.mediaFile.getTags();
            return;
        } catch (ID3Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getArtist()
     */
    @Override
    public String getArtist() {
        return this.tag1.getArtist();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getAlbum()
     */
    @Override
    public String getAlbum() {
        return this.tag1.getAlbum();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getTrackTitle()
     */
    @Override
    public String getTrackTitle() {
        return this.tag1.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getTrack()
     */
    @Override
    public int getTrack() {
        try {
            return this.tag2.getTrackNumber();
        } catch (ID3Exception e) {
            return 0;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getYear()
     */
    @Override
    public int getYear() {
        String val = this.tag1.getYear();
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getDuration()
     */
    @Override
    public List<Integer> getDuration() {

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getGenre()
     */
    @Override
    public String getGenre() {
        return this.tag1.getGenre().toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getComment()
     */
    @Override
    public String getComment() {
        return this.tag1.getComment();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getComposer()
     */
    @Override
    public String getComposer() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getLyricist()
     */
    @Override
    public String getLyricist() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getProducer()
     */
    @Override
    public String getProducer() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getDiscNumber()
     */
    @Override
    public int getDiscNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.audiovideo.MP3Reader#getTrackCount()
     */
    @Override
    public int getTrackCount() {
        try {
            return this.tag2.getTotalTracks();
        } catch (ID3Exception e) {
            return -1;
        }
    }

}
