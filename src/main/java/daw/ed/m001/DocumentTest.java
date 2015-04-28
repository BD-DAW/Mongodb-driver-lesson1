package daw.ed.m001;

import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.Arrays;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author carlos
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document();
        document.append("str", "MongoDB, Hello")
                .append("int",41)
                .append("b",false)
                .append("date",new Date())
                .append("objectId",new ObjectId())
                .append("null",null)
                .append("embeddedDoc",new Document("x",0))
                .append("list", Arrays.asList(1,2,3));
        
        String str = document.getString("str");
        int i = document.getInteger("int");
        
        printJson(document);
        
        /* Crea los siguientes documentos Json */
        /* ejemplo
        {
            "name" : "MongoDB",
            "type" : "database",
            "count" : 1,
            "info" : {
                x : 203,
                y : 102
            }
        }
        */
        
        /* usuarios
        {
            "nombre" : "usuario",
            "contraseña" : "mypassword",
            "grupo: daw",
            "fecha_nac" : 10/10/1990, <- como fecha!!!
            "telefonos" : {
                "fijo": 1111111,
                "móvil": 22222222
            },
            "emails": [un@email.com, dos@email.com],
            "cursos": [bd,ed]
        }
        */
        
        
    }
    
}
