package daw.ed.m001;

import static java.util.Arrays.asList;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import org.bson.Document;

public class FindTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("findTest");

        collection.drop();

        //insert 10 documents
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document("x", i));
        }

        System.out.println("Find one:");
        Document first = collection.find().first();
        //System.out.println(first.toJson());
        printJson(first);

        System.out.println("Find all with into: ");
        ArrayList<Document> all = collection.find().into(
                new ArrayList<Document>()
        );
        
        for (Document d: all) {
            printJson(d);
        }

        System.out.println("find all with iteration: ");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document cur = cursor.next();
                printJson(cur);
            }
        } finally {
            cursor.close();
        }

        System.out.println("Count:");
        long count = collection.count();
        System.out.println(count);

        client.close();

        /* Responde
         ¿Qué tipo de datos devuelve el método find().first()?
         ¿Cuándo es conveniente utilizar el método  find().into()?
         ¿Qué es un cursor (google: mongodb cursor)?
         ¿Cuándo es conveniente obtener un iterador?
         ¿Por qué se utiliza la instrucción cursor.close() en el bloque finally?
         ¿Cuánto tiempo permanece el cursor abierto, consumiendo recursos si
         este no se cierra explícitamente?                
         */
    }
}
