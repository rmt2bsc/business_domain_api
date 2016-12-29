package org.dao.audiovideo;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvTracks;

public class AvCombinedProjectBean {

    private static final long serialVersionUID = -5685002700685343457L;

    private AvArtist ava;

    private AvProject av;

    private AvTracks avt;

    private String genre;

    public AvCombinedProjectBean() {
        this.av = new AvProject();
        this.ava = new AvArtist();
        this.avt = new AvTracks();
    }

    public AvArtist getAva() {
        return this.ava;
    }

    public void setAva(AvArtist value) {
        this.ava = value;
    }

    public AvProject getAv() {
        return this.av;
    }

    public void setAv(AvProject value) {
        this.av = value;
    }

    public AvTracks getAvt() {
        return this.avt;
    }

    public void setAvt(AvTracks value) {
        this.avt = value;
    }

    public void setGenre(String value) {
        this.genre = value;
    }

    public String getGenre() {
        return this.genre;
    }
}