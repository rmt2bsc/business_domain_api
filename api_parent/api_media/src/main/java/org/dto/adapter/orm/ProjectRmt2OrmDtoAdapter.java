package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.AvProject;
import org.dto.ProjectDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link ProjectDto} interface.
 * 
 * @author rterrell
 * 
 */
class ProjectRmt2OrmDtoAdapter extends TransactionDtoImpl implements ProjectDto {

    private AvProject p;

    /**
     * Creates a ProjectRmt2OrmDtoAdapter without initializing its adaptee
     */
    private ProjectRmt2OrmDtoAdapter() {
        super();
        this.p = null;
        return;
    }

    /**
     * Creates a ProjectRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>project</i>
     * 
     * @param project
     *            an instance of {@link AvProject}. Set to null for the purpose
     *            of instantiating a new adaptee.
     */
    protected ProjectRmt2OrmDtoAdapter(AvProject project) {
        this();
        if (project == null) {
            project = new AvProject();
        }
        this.p = project;
        this.init(project.getDateCreated(), project.getDateUpdated(),
                project.getUserId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getProjectId()
     */
    @Override
    public int getProjectId() {
        return this.p.getProjectId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setProjectId(int)
     */
    @Override
    public void setProjectId(int projId) {
        this.p.setProjectId(projId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getArtistId()
     */
    @Override
    public int getArtistId() {
        return this.p.getArtistId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setArtistId(int)
     */
    @Override
    public void setArtistId(int artistId) {
        this.p.setArtistId(artistId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getProjectTypeId()
     */
    @Override
    public int getProjectTypeId() {
        return this.p.getProjectTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setProjectTypeId(int)
     */
    @Override
    public void setProjectTypeId(int projTypeId) {
        this.p.setProjectTypeId(projTypeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getGenreId()
     */
    @Override
    public int getGenreId() {
        return this.p.getGenreId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setGenreId(int)
     */
    @Override
    public void setGenreId(int genreId) {
        this.p.setGenreId(genreId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getMediaTypeId()
     */
    @Override
    public int getMediaTypeId() {
        return this.p.getMediaTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setMediaTypeId(int)
     */
    @Override
    public void setMediaTypeId(int mediaTypeId) {
        this.p.setMediaTypeId(mediaTypeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getTitle()
     */
    @Override
    public String getTitle() {
        return this.p.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setTitle(java.lang.String)
     */
    @Override
    public void setTitle(String title) {
        this.p.setTitle(title);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getYear()
     */
    @Override
    public int getYear() {
        return this.p.getYear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setYear(int)
     */
    @Override
    public void setYear(int year) {
        this.p.setYear(year);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getMasterDupId()
     */
    @Override
    public int getMasterDupId() {
        return this.p.getMasterDupId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setMasterDupId(int)
     */
    @Override
    public void setMasterDupId(int value) {
        this.p.setMasterDupId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getRippedInd()
     */
    @Override
    public int getRippedInd() {
        return this.p.getRipped();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setRippedInd(int)
     */
    @Override
    public void setRippedInd(int flag) {
        this.p.setRipped(flag);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getCost()
     */
    @Override
    public double getCost() {
        return this.p.getCost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setCost(double)
     */
    @Override
    public void setCost(double cost) {
        this.p.setCost(cost);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getContentId()
     */
    @Override
    public int getContentId() {
        return this.p.getContentId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setContentId(int)
     */
    @Override
    public void setContentId(int contentId) {
        this.p.setContentId(contentId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getGenreName()
     */
    @Override
    public String getGenreName() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setGenreName(java.lang.String)
     */
    @Override
    public void setGenreName(String name) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getContentPath()
     */
    @Override
    public String getContentPath() {
        return this.p.getContentPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setContentPath(java.lang.String)
     */
    @Override
    public void setContentPath(String contentPath) {
        this.p.setContentPath(contentPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getArtWorkPath()
     */
    @Override
    public String getArtWorkPath() {
        return this.p.getArtWorkPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setArtWorkPath(java.lang.String)
     */
    @Override
    public void setArtWorkPath(String artWorkPath) {
        this.p.setArtWorkPath(artWorkPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getComments()
     */
    @Override
    public String getComments() {
        return this.p.getProjectComments();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setComments(java.lang.String)
     */
    @Override
    public void setComments(String comments) {
        this.p.setProjectComments(comments);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getArtWorkFilename()
     */
    @Override
    public String getArtWorkFilename() {
        return this.p.getArtWorkFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setArtWorkFilename(java.lang.String)
     */
    @Override
    public void setArtWorkFilename(String artWorkFilename) {
        this.p.setArtWorkFilename(artWorkFilename);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getContentFilename()
     */
    @Override
    public String getContentFilename() {
        return this.p.getContentFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setContentFilename(java.lang.String)
     */
    @Override
    public void setContentFilename(String contentFilename) {
        this.p.setContentFilename(contentFilename);

    }

}
