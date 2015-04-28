package daw.ed.m001;

import static java.util.Arrays.asList;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.m101j.util.Helpers.printJson;
import org.bson.Document;

/**
 *
 * @author carlos
 */
public class insertTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("insertTest");

        collection.drop();

        Document smith = new Document()
                .append("nombre", "Smith")
                .append("edad", 30)
                .append("profesión", "programador");

        Document juan = new Document()
                .append("nombre", "Juan")
                .append("edad", 21)
                .append("profesión", "cocinero");

        printJson(smith);

        collection.insertOne(smith);

        printJson(smith);

        //Comprueba que efectivamente se ha insertado utilizando el cliente mongo
        //¿Qué diferencias hay entre el prirmer printJson y el segundo? explica tu respuesta
        //Utiliza el método insertMany para insertar varios documentos a la vez
        client.close();
    }
}
