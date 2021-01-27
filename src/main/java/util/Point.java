package util;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public class Point implements Serializable {
    private final float x;
    private final float y;
    private final int lineId;
    private final ZonedDateTime createdAt;

    public Point(float x, float y, int lineId) {
        this.x = x;
        this.y = y;
        this.lineId = lineId;
        createdAt = ZonedDateTime.now();
        Logging.log(Level.INFO, "Created new point: " + this.toString());
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int getLineId(){
        return lineId;
    }

    public ZonedDateTime getCreationTime(){
        return createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point)){
            return false;
        }else{
            Point point = (Point) obj;
            if((point.getX() == this.getX()) && (point.getY() == this.getY()) &&
                point.getLineId() == this.getLineId()){
                return true;
            }
            return false;
        }
    }

    @Override
    public String toString() {
        return "Point [" + getX() + ", " + getY() +"], created at " +
                getCreationTime().format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
                + " which is part of line of id = " + getLineId() + "\n";
    }
}