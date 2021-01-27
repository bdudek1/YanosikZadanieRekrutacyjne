import services.BinaryServiceImpl;
import util.Line;
import util.Logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) {
        List<Line> linesWithSomeFlagTrue = new ArrayList<>();
        BinaryServiceImpl binaryService = new BinaryServiceImpl();
        try{
            binaryService.writeLinesToBinaryFile("binarylines");
            linesWithSomeFlagTrue = binaryService.readLinesFromBinaryFile("binarylines");
        }catch(IOException | ClassNotFoundException e){
            Logging.log(Level.SEVERE, "A problem occured when writing or reading binary files");
            e.printStackTrace();
        }
        System.out.println(linesWithSomeFlagTrue);
    }

}
