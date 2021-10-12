package coursework2;

/**
 *
 * @author bh6779k
 */
public class Note {
    private int noteID = 0;
    private String course = "";
    private String dayte = "";
    private String note = "";
    private String orderedDate;
    
    public Note() {
        
    }
    
    public Note(int max, String crs, String nt) {
        setNoteID(max);
        setCourse(crs);
        setDayte();
        setNote(nt);
    }
    
    public Note(int nid, String crs, String dt, String nt) {
        setNoteID(nid);
        setCourse(crs);
        setDayte(dt);
        setNote(nt);
    }

    
    public int getNoteID() {
        // Any checking goes here.
        return noteID;
    }

    public final void setNoteID(int n) {
        int nid = n;
        noteID = nid;
    }

    public String getCourse() {
        return course;
    }

    public final void setCourse(String c) {
        String crse = c;
        // Any validation goes here.
        course = crse;
    }

    public String getDayte() {
        return dayte;
    }

    public final void setDayte() {
        dayte = orderedDate;
    }

    public final void setDayte(String dt) {
        dayte = dt;
    }

    public final void setNote(String n) {
        note = n;
    }

    public String getNote() {
        return note;
    }
    
}

    

