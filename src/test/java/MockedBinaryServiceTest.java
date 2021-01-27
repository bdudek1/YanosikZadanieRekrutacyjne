import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.BinaryService;
import services.BinaryServiceImpl;
import services.CsvReadingService;
import util.Line;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockedBinaryServiceTest {
    static BinaryService binaryService;
    static CsvReadingService csvReadingService;
    static List<Line> lines;

    @BeforeAll
    static void init(){
        binaryService = new BinaryServiceImpl();
        csvReadingService = mock(CsvReadingService.class);
        binaryService.setCsvReadingService(csvReadingService);
        lines = new ArrayList<>();
    }

    @AfterEach
    public void clearList(){
        lines.clear();
    }

    //funkcja nie powinna nic zapisac do pliku binarnego, poniewaz wszystkie flagi sa ustawione na
    //false, wiec nie powinna tez nic pobrac z pliku binarnego
    @Test
    public void binaryServiceShouldReturnNothing() throws IOException, ClassNotFoundException {
        lines.add(new Line(null, false, 0));
        lines.add(new Line(null, false, 1));
        lines.add(new Line(null, false, 2));
        lines.add(new Line(null, false, 3));
        lines.add(new Line(null, false, 4));

        when(csvReadingService.readLines()).thenReturn(lines);

        assertTrue(binaryService.writeLinesToBinaryFile("binarylines"));
        assertEquals(0, binaryService.readLinesFromBinaryFile("binarylines").size());
    }

    //funkcja powinna zwrocic wszystkie linie dodane do listy, poniewaz kazda wartosc flagi jest
    //rowna true
    @Test
    public void binaryServiceShouldReturnWholeList() throws IOException, ClassNotFoundException {
        lines.add(new Line(null, true, 0));
        lines.add(new Line(null, true, 1));
        lines.add(new Line(null, true, 2));
        lines.add(new Line(null, true, 3));
        lines.add(new Line(null, true, 4));

        when(csvReadingService.readLines()).thenReturn(lines);

        assertTrue(binaryService.writeLinesToBinaryFile("binarylines"));
        assertEquals(lines.size(), binaryService.readLinesFromBinaryFile("binarylines").size());
    }

    @Test
    public void binaryServiceShouldReturnSomeLines() throws IOException, ClassNotFoundException {
        lines.add(new Line(null, true, 0));
        lines.add(new Line(null, false, 1));
        lines.add(new Line(null, true, 2));
        lines.add(new Line(null, false, 3));
        lines.add(new Line(null, true, 4));

        when(csvReadingService.readLines()).thenReturn(lines);

        assertTrue(binaryService.writeLinesToBinaryFile("binarylines"));
        assertEquals(3, binaryService.readLinesFromBinaryFile("binarylines").size());
    }

    //funkcja nie powinna nic zwrocic, poniewaz na wejscie wchodzi pusta lista
    @Test
    public void binaryServiceShouldReturnNoLines() throws IOException, ClassNotFoundException {
        when(csvReadingService.readLines()).thenReturn(lines);

        assertTrue(binaryService.writeLinesToBinaryFile("binarylines"));
        assertEquals(0, binaryService.readLinesFromBinaryFile("binarylines").size());
    }
}
