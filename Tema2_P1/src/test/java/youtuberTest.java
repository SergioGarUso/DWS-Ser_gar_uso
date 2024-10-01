import org.example.youtuberService;
import org.example.youtuber;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class youtuberTest {

    private List<youtuber> youtubers;
    private youtuberService youtuberService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    youtuberService service;

    {
        try {
            service = new youtuberService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testYoutuberConMasSeguidores() {
        Optional<youtuber> youtuberConMasSeguidores = service.youtuberConMasSeguidores();
        assertEquals("MrBeast", youtuberConMasSeguidores.get().nombre());
    }

    @Test
    public void testMediaDeVideos() {
        double mediaDeVideos = service.mediaDeVideos();
        assertEquals(2405.714285714286, mediaDeVideos, 0.01); // Media esperada calculada manualmente
    }

    @Test
    public void testYoutubersQueEmpezaronEn2013() {
        List<youtuber> youtubers2013 = service.youtubersQueEmpezaronEn2013();
        assertEquals(3, youtubers2013.size());
        List<String> nombresEsperados = Arrays.asList("Whindersson Nunes", "Luzu", "JuegaGerman");
        List<String> nombresObtenidos = youtubers2013.stream().map(youtuber::nombre).toList();
        assertTrue(nombresObtenidos.contains(nombresEsperados.get(0)) && nombresObtenidos.contains(nombresEsperados.get(1)) && nombresObtenidos.contains(nombresEsperados.get(2)));
    }


}
