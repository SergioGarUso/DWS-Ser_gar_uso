package org.example;
import org.example.youtuberService;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        youtuberService service = null;
        try {
            service = new youtuberService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Youtuber con mas seguidores: " + service.youtuberConMasSeguidores());
        System.out.println("Media de videos: " + service.mediaDeVideos());
        System.out.println("Tres youtubers con mas ingresos estimados: " +service.tresYoutubersConMasIngresosEstimados());
        System.out.println("Youtubers agrupados por año: "+service.youtubersAgrupadosPorAno());
        System.out.println("Youtubers que empezaron en 2013: "+service.youtubersQueEmpezaronEn2013());

    }

}