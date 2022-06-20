package edu.escuelaing.arsw.app;

import edu.escuelaing.arsw.app.Objects.genObject;
import edu.escuelaing.arsw.app.Objects.typeObject;
import edu.escuelaing.arsw.app.Type.ContentType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TheadRequest implements Runnable{
    private final Socket clientSocket;

    public TheadRequest(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            request();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void request() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        String ruta= "/404.jpg";

        while ((inputLine = in.readLine()) != null) {
            //System.out.println("Received: " + inputLine);
            if (inputLine.contains("GET")) {
                ruta = inputLine.split(" ")[1];
                //System.out.println("Longitud ruta " + ruta);
                if (ruta.equals("")||ruta.equals("/")) {
                    ruta = "/404.jpg";
                }
                if ((!ruta.contains(".html")) && (!ruta.contains(".js")) && (!ruta.contains(".jpg"))) {
                    ruta = "/404.jpg";
                }
            }


            if (!in.ready()) {
                break;
            }
        }
        //System.out.println("Longitud ruta " + ruta);
        ruta = ruta.substring(1);

        try {
            genObject obj = typeObject.build(ContentType.valueOf(ruta.split("\\.")[ruta.split("\\.").length - 1]),clientSocket);
            obj.createHttp(ruta);
            obj.sendResult();
            String outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "<title>Title of the document</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>Mi propio mensaje</h1>\n"
                    + "</body>\n"
                    + "</html>\n" + inputLine;

        } catch (Exception ex) {
            String outputLine;
            outputLine = "HTTP/1.1 404 Not Found";
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            out.println(outputLine);
            out.close();
            clientSocket.close();
        }



    }
}
