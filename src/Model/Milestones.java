package Model;

public class Milestones {
    private long id;
    private String milestones;
    private int userID;
    private int courseworkID;
    private int isCompleted;

    protected Milestones() {

    }
    public Milestones(long id, String milestones, int userID, int courseworkID, int isCompleted){
        this.id = id;
        this.milestones = milestones;
        this.userID = userID;
        this.courseworkID = courseworkID;
        this.isCompleted = isCompleted;

    }
    public Milestones(String milestones, int userID, int courseworkID, int isCompleted){
        this.milestones = milestones;
        this.userID = userID;
        this.courseworkID = courseworkID;
        this.isCompleted = isCompleted;

    }
    public Milestones(long id, int isCompleted, int userID, int courseworkID) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.userID = userID;
        this.courseworkID = courseworkID;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMilestone() {
        return milestones;
    }

    public void setMilestone(String milestone) {
        this.milestones = milestone;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCourseworkID() {
        return courseworkID;
    }

    public void setCourseworkID(int courseworkID) {
        this.courseworkID = courseworkID;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
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
        Milestones other = (Milestones) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "Milestone" + this.milestones;
    }


}
