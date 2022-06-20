package edu.escuelaing.arsw.app.Objects;
import edu.escuelaing.arsw.app.Server.Reader;

import java.net.Socket;

public abstract class genObject {
    protected String statusLine;
    protected String header;
    protected Reader reader;
    protected Socket clientSocket;
    public genObject(Socket clientSocket){
        this.clientSocket=clientSocket;
        reader = new Reader();
    }
    public abstract void createHttp(String url);
    public abstract void sendResult();

}
