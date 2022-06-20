package edu.escuelaing.arsw.app.Objects;
import edu.escuelaing.arsw.app.Objects.ObjectHtml;
import edu.escuelaing.arsw.app.Objects.ObjectImg;
import edu.escuelaing.arsw.app.Objects.genObject;
import edu.escuelaing.arsw.app.Type.ContentType;

import java.net.Socket;

public class typeObject {
    public static genObject build(ContentType type, Socket clientSocket){
        switch(type){
            case html:
                return new ObjectHtml(clientSocket);
            case jpg :
                return new ObjectImg(clientSocket,type);

            case js:
                return new ObjectJS(clientSocket);
        }

        return null;
    }
}
