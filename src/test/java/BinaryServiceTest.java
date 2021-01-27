import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.BinaryService;
import services.BinaryServiceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryServiceTest {
    static BinaryService binaryService;

    @BeforeAll
    public static void initBinaryService(){
        binaryService = new BinaryServiceImpl();
    }

    @Test
    public void binaryServiceShouldntBeNull(){
        assertNotNull(binaryService);
    }

    @Test
    public void binaryServiceShouldntThrowException(){
        assertDoesNotThrow(() -> binaryService.writeLinesToBinaryFile("binarylines"));
        assertDoesNotThrow(() -> binaryService.readLinesFromBinaryFile("binarylines"));
    }

    @Test
    public void binaryServiceShouldThrowException(){
        assertThrows(FileNotFoundException.class,
                () -> binaryService.readLinesFromBinaryFile("randomName"));
    }

    @Test
    public void shouldWriteLinesToBinary() throws IOException {
        assertTrue(binaryService.writeLinesToBinaryFile("binarylines"));
    }

    @Test
    public void shouldReadLinesFromBinary() throws IOException, ClassNotFoundException {
        assertTrue(binaryService.writeLinesToBinaryFile("binarylines"));
        assertEquals(4, binaryService.readLinesFromBinaryFile("binarylines").size());
        assertNotEquals(0, binaryService.readLinesFromBinaryFile("binarylines").size());
        assertNotEquals(10, binaryService.readLinesFromBinaryFile("binarylines").size());
    }
}
