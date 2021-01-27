package services;

import util.Line;
import util.Point;

import java.util.List;

public interface CsvReadingService {
    public List<Point> readPoints();
    public List<Line> readLines();
}
