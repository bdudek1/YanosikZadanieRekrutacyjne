package services;

import util.Line;
import util.Logging;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class BinaryServiceImpl implements BinaryService {
    CsvReadingService csvReadingService = new CsvReadingServiceImpl();

    @Override
    public boolean writeLinesToBinaryFile(String fileName) throws IOException{
        fileName = fileName + ".bin";
        //pobiera linie, ktorych someFlag jest rowne true
        List<Line> linesToWrite = csvReadingService.readLines().stream()
                .filter(Line::isSomeFlag).collect(Collectors.toList());

        try (FileOutputStream fos = new FileOutputStream(fileName);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                linesToWrite.forEach(l -> {
                    try {
                        oos.writeObject(l);
                    } catch (IOException e) {
                        Logging.log(Level.SEVERE, "Could not save line" + l + "to binary file");
                        e.printStackTrace();
                    }
                });
            Logging.log(Level.INFO, "Lines successfully saved.");
            return true;
        }
    }

    @Override
    public List<Line> readLinesFromBinaryFile(String fileName) throws IOException, ClassNotFoundException {
        fileName = fileName + ".bin";
        List<Line> lines = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis)){
            Object line = null;
            while (true) {
                try{
                    line = ois.readObject();
                    if (line instanceof Line) {
                        lines.add((Line)line);
                    }
                }catch(EOFException e){
                    Logging.log(Level.INFO, "Lines successfully readen.");
                    return lines;
                }
            }
        }
    }

    public void setCsvReadingService(CsvReadingService csvReadingService){
        this.csvReadingService = csvReadingService;
    }
}
