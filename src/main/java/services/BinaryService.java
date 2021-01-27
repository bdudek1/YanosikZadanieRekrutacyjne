package services;

import util.Line;

import java.io.IOException;
import java.util.List;

public interface BinaryService {
    public boolean writeLinesToBinaryFile(String fileName) throws IOException;
    public List<Line> readLinesFromBinaryFile(String fileName) throws IOException, ClassNotFoundException;
    public void setCsvReadingService(CsvReadingService csvReadingService);
}
