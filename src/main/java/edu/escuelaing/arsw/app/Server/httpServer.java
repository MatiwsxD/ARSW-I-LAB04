package edu.escuelaing.arsw.app.Server;


import edu.escuelaing.arsw.app.Objects.genObject;
import edu.escuelaing.arsw.app.Objects.typeObject;
import edu.escuelaing.arsw.app.TheadRequest;
import edu.escuelaing.arsw.app.Type.ContentType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class httpServer {
    public void start() throws IOException {

        ExecutorService poolHilos = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }
        boolean running = true;

        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            TheadRequest requestHilo = new TheadRequest(clientSocket);
            poolHilos.execute(requestHilo);


        }
    }

    private int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000; //returns default port if heroku-port isn't set(i.e. on localhost)
    }
}