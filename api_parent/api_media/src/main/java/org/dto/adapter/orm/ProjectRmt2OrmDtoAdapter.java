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

    private AvProject adaptee;

    /**
     * Creates a ProjectRmt2OrmDtoAdapter without initializing its adaptee
     */
    private ProjectRmt2OrmDtoAdapter() {
        super();
        this.adaptee = null;
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
        this.adaptee = project;
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
        return this.adaptee.getProjectId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setProjectId(int)
     */
    @Override
    public void setProjectId(int projId) {
        this.adaptee.setProjectId(projId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getArtistId()
     */
    @Override
    public int getArtistId() {
        return this.adaptee.getArtistId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setArtistId(int)
     */
    @Override
    public void setArtistId(int artistId) {
        this.adaptee.setArtistId(artistId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getProjectTypeId()
     */
    @Override
    public int getProjectTypeId() {
        return this.adaptee.getProjectTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setProjectTypeId(int)
     */
    @Override
    public void setProjectTypeId(int projTypeId) {
        this.adaptee.setProjectTypeId(projTypeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getGenreId()
     */
    @Override
    public int getGenreId() {
        return this.adaptee.getGenreId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setGenreId(int)
     */
    @Override
    public void setGenreId(int genreId) {
        this.adaptee.setGenreId(genreId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getMediaTypeId()
     */
    @Override
    public int getMediaTypeId() {
        return this.adaptee.getMediaTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setMediaTypeId(int)
     */
    @Override
    public void setMediaTypeId(int mediaTypeId) {
        this.adaptee.setMediaTypeId(mediaTypeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getTitle()
     */
    @Override
    public String getTitle() {
        return this.adaptee.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setTitle(java.lang.String)
     */
    @Override
    public void setTitle(String title) {
        this.adaptee.setTitle(title);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getYear()
     */
    @Override
    public int getYear() {
        return this.adaptee.getYear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setYear(int)
     */
    @Override
    public void setYear(int year) {
        this.adaptee.setYear(year);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getMasterDupId()
     */
    @Override
    public int getMasterDupId() {
        return this.adaptee.getMasterDupId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setMasterDupId(int)
     */
    @Override
    public void setMasterDupId(int value) {
        this.adaptee.setMasterDupId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getRippedInd()
     */
    @Override
    public int getRippedInd() {
        return this.adaptee.getRipped();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setRippedInd(int)
     */
    @Override
    public void setRippedInd(int flag) {
        this.adaptee.setRipped(flag);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getCost()
     */
    @Override
    public double getCost() {
        return this.adaptee.getCost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setCost(double)
     */
    @Override
    public void setCost(double cost) {
        this.adaptee.setCost(cost);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getContentId()
     */
    @Override
    public int getContentId() {
        return this.adaptee.getContentId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setContentId(int)
     */
    @Override
    public void setContentId(int contentId) {
        this.adaptee.setContentId(contentId);
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
        return this.adaptee.getContentPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setContentPath(java.lang.String)
     */
    @Override
    public void setContentPath(String contentPath) {
        this.adaptee.setContentPath(contentPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getArtWorkPath()
     */
    @Override
    public String getArtWorkPath() {
        return this.adaptee.getArtWorkPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setArtWorkPath(java.lang.String)
     */
    @Override
    public void setArtWorkPath(String artWorkPath) {
        this.adaptee.setArtWorkPath(artWorkPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getComments()
     */
    @Override
    public String getComments() {
        return this.adaptee.getProjectComments();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setComments(java.lang.String)
     */
    @Override
    public void setComments(String comments) {
        this.adaptee.setProjectComments(comments);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getArtWorkFilename()
     */
    @Override
    public String getArtWorkFilename() {
        return this.adaptee.getArtWorkFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setArtWorkFilename(java.lang.String)
     */
    @Override
    public void setArtWorkFilename(String artWorkFilename) {
        this.adaptee.setArtWorkFilename(artWorkFilename);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getContentFilename()
     */
    @Override
    public String getContentFilename() {
        return this.adaptee.getContentFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setContentFilename(java.lang.String)
     */
    @Override
    public void setContentFilename(String contentFilename) {
        this.adaptee.setContentFilename(contentFilename);

    }

    @Override
    public int getTotalTime() {
        return this.adaptee.getTotalTime();
    }

    @Override
    public void setTotalTime(int totalTime) {
        this.adaptee.setTotalTime(totalTime);
    }

    @Override
    public String getProducer() {
        return this.adaptee.getProducer();
    }

    @Override
    public void setProducer(String producer) {
        this.adaptee.setProducer(producer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return this.getProjectId() == ((ProjectDto) obj).getProjectId()
                && this.getTitle().equalsIgnoreCase(((ProjectDto) obj).getTitle());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.adaptee.hashCode();
    }
}
