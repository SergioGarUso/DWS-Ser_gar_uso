package org.example;

import java.util.Date;

public record youtuber(String nombre, Date fechaInicio, int numeroVideos, int numeroSeguidores) {

    public double estimatedIncome() {
        return (numeroVideos * numeroSeguidores) / 2.0 * 0.002;
    }

}