package edu.escuelaing.arsw.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThreadClient implements Runnable{
    public void run() {
        String url = "https://gentle-tor-62831.herokuapp.com/File.js";
        String respuesta = "";
        try {
            respuesta = peticionHttpGet(url);
            System.out.println("La respuesta es:\n" + respuesta);
        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }

    public static String peticionHttpGet(String urlParaVisitar) throws Exception {
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        URL url = new URL(urlParaVisitar);

        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        // Búferes para leer
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        // Cerrar el BufferedReader
        rd.close();
        // Regresar resultado, pero como cadena, no como StringBuilder
        return resultado.toString();
    }
}
