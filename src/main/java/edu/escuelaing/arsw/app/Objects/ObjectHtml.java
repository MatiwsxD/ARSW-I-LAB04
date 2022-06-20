package edu.escuelaing.arsw.app.Objects;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectHtml extends genObject {

    protected String http;
    protected String body;

    public ObjectHtml(Socket clientSocket){
        super(clientSocket);
    }


    @Override
    public void createHttp(String url) {
        body = reader.readText(url);
        if(body.length()==0){
            statusLine="HTTP/1.1 404 Not Found";
            http=statusLine;
        }
        else{
            statusLine="HTTP/1.1 200 OK\r\n" ;
            header="Content-Type: text/html\r\n";
            http = statusLine + header + "\r\n" + body;


        }

    }

    @Override
    public void sendResult() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(http);
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObjectHtml.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
