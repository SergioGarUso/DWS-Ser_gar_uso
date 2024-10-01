package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class youtuberService {
    private final List<youtuber> youtubers;

    // Creamos un formato que es el que tendra nuestra fecha en el csv
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor que carga los datos desde el CSV
    public youtuberService() throws IOException {
        youtubers = new ArrayList<>();
        Path filePath = Path.of("/home/sergaruso/Baixades/DES/DWS-Ser_gar_uso/Tema2_P1/src/main/resources/youtubers.csv");

        // Leemos el fichero y lo guardamos en una lista de youtubers
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                try {
                    Date fechaInicio = dateFormat.parse(data[1]); // Convertir fecha de String a Date

                    youtuber youtuber = new youtuber(
                            data[0],                      // Nombre
                            fechaInicio,                 // Fecha de inicio como Date
                            Integer.parseInt(data[2]),    // Número de videos
                            Integer.parseInt(data[3])     // Número de seguidores
                    );
                    youtubers.add(youtuber);
                } catch (ParseException e) {
                    System.err.println("Error al parsear la fecha para: " + data[0]);
                    e.printStackTrace();
                }
            }
        }
    }


    public Optional<youtuber> youtuberConMasSeguidores() {
        return youtubers.stream()
                .max(Comparator.comparingInt(youtuber::numeroSeguidores));
    }


    public double mediaDeVideos() {
        return youtubers.stream()
                .mapToInt(youtuber::numeroVideos)
                .average()
                .orElse(0.0);
    }


    public List<youtuber> youtubersQueEmpezaronEn2013() {
        return youtubers.stream()
                .filter(y -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(y.fechaInicio());
                    return cal.get(Calendar.YEAR) == 2013;
                })
                .collect(Collectors.toList());
    }


    public List<youtuber> tresYoutubersConMasIngresosEstimados() {
        return youtubers.stream()
                .sorted(Comparator.comparingDouble(youtuber::estimatedIncome).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }


    public Map<Integer, List<youtuber>> youtubersAgrupadosPorAno() {
        return youtubers.stream()
                .collect(Collectors.groupingBy(y -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(y.fechaInicio());
                    return cal.get(Calendar.YEAR);
                }));
    }

}

