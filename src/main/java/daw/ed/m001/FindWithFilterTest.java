package daw.ed.m001;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author carlos
 */
public class FindWithFilterTest {
        public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = 
                database.getCollection("findWithFilterTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100)));
        }
        
        //Ejemplos de filtros de búsqueda
        //Escribe el equivalente en mongo shell
        Bson filter1 = new Document("x",0);
        Bson filter2 = new Document("x",0).append("y",0);
        Bson filter3 = new Document("x",0).append("y", new Document("$gt",10));
        //Escribe en java el siguiente filtro de mongo shell
        //db.findTest.find({x:0, y: { $gt: 10, $lt :90}})
        Bson filter4 = and(eq("x",0),gt("y",50));
        //reescribe los filtros anteriores utilizando el "query builder" del driver

        
        MongoCursor<Document> cursor = collection.find(filter4).iterator();
        try {
            while (cursor.hasNext()) {
                Document cur = cursor.next();
                printJson(cur);
            }
        } finally {
            cursor.close();
        }
        
        client.close();
    }

    
}
