package services;

import util.Line;
import util.Logging;
import util.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CsvReadingServiceImpl implements CsvReadingService{

    @Override
    public List<Point> readPoints() {
        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("points.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                points.add(new Point(Float.parseFloat(values[0]),
                        Float.parseFloat(values[1]), Integer.parseInt(values[2])));
            }
        } catch (IOException e) {
            Logging.log(Level.SEVERE, "Could not load points!");
            e.printStackTrace();
        }
        Logging.log(Level.INFO, "Loaded points from csv file.");
        return points;
    }

    @Override
    public List<Line> readLines() {
        List<Point> points = readPoints();
        List<Line> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("lines.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                int lineId = Integer.parseInt(values[0]);
                Point[] linePoints = (points.stream()
                        .filter(p -> p.getLineId() == lineId)).toArray(Point[]::new);
                lines.add(new Line(linePoints, Boolean.parseBoolean(values[1]), lineId));
            }
        } catch (IOException e) {
            Logging.log(Level.SEVERE, "Could not load lines!");
            e.printStackTrace();
        }
        Logging.log(Level.INFO, "Loaded lines from csv file.");
        return lines;
    }
}
