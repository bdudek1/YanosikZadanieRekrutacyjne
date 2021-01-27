package util;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public class Line implements Serializable {
    private final int id;
    private final Point[] points;
    private final boolean someFlag;
    private final ZonedDateTime createdAt;

    public Line(Point[] points, boolean someFlag, int id) {
        this.points = points;
        this.someFlag = someFlag;
        this.id = id;
        createdAt = ZonedDateTime.now();
        Logging.log(Level.INFO, "Created new line: " + this.toString());
    }

    public int getId(){
        return id;
    }

    public Point[] getPoints(){
        return points;
    }

    public boolean isSomeFlag(){
        return someFlag;
    }

    public ZonedDateTime getCreationTime(){
        return createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Line)){
            return false;
        }else{
            Line line = (Line) obj;
            if((line.isSomeFlag() == this.isSomeFlag()) && (line.getId() == this.getId()) &&
                    line.getCreationTime().equals(this.getCreationTime())){
                return true;
            }
            return false;
        }
    }

    @Override
    public String toString() {
        return "Line of id " + getId() + ", created at " +
                getCreationTime().format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
                + ", someFlag = " + isSomeFlag() + "\n";
    }
}
