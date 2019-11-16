package Model;

import java.time.LocalDate;

public class CourseworkProject {
    private Long id;
    private String courseworkTitle;
    private String moduleTitle;
    private LocalDate intendedDueDate;
    private LocalDate actualCompletionDate;
    private int userID;
    private boolean status;

    protected CourseworkProject() {

    }

    public CourseworkProject(long id, String courseworkTitle, String moduleTitle, LocalDate intendedDueDate, LocalDate actualCompletionDate, int userID, boolean isDone) {
        super();
        this.id = id;
        this.courseworkTitle = courseworkTitle;
        this.moduleTitle = moduleTitle;
        this.intendedDueDate = intendedDueDate;
        this.actualCompletionDate = actualCompletionDate;
        this.userID = userID;
        this.status = isDone;
    }

    public CourseworkProject(String courseworkTitle, String moduleTitle, LocalDate intendedDueDate, LocalDate actualCompletionDate, int userID, boolean isDone) {
        super();
        this.courseworkTitle = courseworkTitle;
        this.moduleTitle = moduleTitle;
        this.intendedDueDate = intendedDueDate;
        this.actualCompletionDate = actualCompletionDate;
        this.userID = userID;
        this.status = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseworkTitle() {
        return courseworkTitle;
    }

    public void setCourseworkTitle(String courseworkTitle) {
        this.courseworkTitle = courseworkTitle;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public LocalDate getIntendedDueDate() {
        return intendedDueDate;
    }

    public void setIntendedDueDate(LocalDate intendedDueDate) {
        this.intendedDueDate = intendedDueDate;
    }

    public LocalDate getActualCompletionDate() {
        return actualCompletionDate;
    }

    public void setActualCompletionDate(LocalDate actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CourseworkProject other = (CourseworkProject) obj;
        if (id != other.id)
            return false;
        return true;
    }


}
