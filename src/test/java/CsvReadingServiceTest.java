import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.CsvReadingService;
import services.CsvReadingServiceImpl;
import util.Line;
import util.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class CsvReadingServiceTest {
    static CsvReadingService csvReadingService;

    @BeforeAll
    public static void initCsvService(){
        csvReadingService = new CsvReadingServiceImpl();
    }

    @Test
    public void csvServiceShouldntBeNull(){
        assertNotNull(csvReadingService);
    }

    @Test
    public void csvServiceDoesNotThrowExceptions(){
        assertDoesNotThrow(csvReadingService::readPoints);
        assertDoesNotThrow(csvReadingService::readLines);
    }

    @Test
    public void shouldReadCsvFilesCorrectly(){
        assertEquals(17, csvReadingService.readPoints().size());
        assertEquals(8, csvReadingService.readLines().size());
    }

    @Test
    public void shouldntReadCsvFilesIncorrectly(){
        assertNotEquals(53, csvReadingService.readPoints().size());
        assertNotEquals(1, csvReadingService.readLines().size());

        assertNotEquals(1, csvReadingService.readPoints().size());
        assertNotEquals(53, csvReadingService.readLines().size());

        assertNotEquals(0, csvReadingService.readPoints().size());
        assertNotEquals(0, csvReadingService.readLines().size());
    }

    //sprawdza, czy do linii zostaly przydzielone punkty, ktore mialy byc przydzielone wg. id
    @Test
    public void lineShouldContainItsPoints(){
        List<Point> points = csvReadingService.readPoints();
        List<Line> lines = csvReadingService.readLines();
        for(Line l:lines){
            int lineId = l.getId();
            List<Point> linesPoints = Arrays.asList(l.getPoints().clone());
            List<Point> pointsReadenFromCsv = points.stream().filter(p -> p.getLineId() == lineId)
                                                    .collect(Collectors.toList());
            assertEquals(pointsReadenFromCsv, linesPoints);
        }
    }
}
