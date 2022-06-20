package edu.escuelaing.arsw.Client;
import edu.escuelaing.arsw.app.Server.httpServer;
import edu.escuelaing.arsw.app.TheadRequest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {

        ExecutorService poolHilos = Executors.newFixedThreadPool(100);

        for(int i =0; i<100; i++){

            ThreadClient Hilo = new ThreadClient();
            poolHilos.execute(Hilo);
        }



    }
}
