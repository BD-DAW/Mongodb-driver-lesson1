package daw.ed.m001;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author carlos
 */
public class UpdateTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection
                = database.getCollection("UpdateTest");

        collection.drop();

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i));
        }

        //escribe en mongoshell las siguentes sentencias
        collection.replaceOne(eq("x",4), new Document("_id",4).append("x",14)
                            .append("update",true));
        collection.updateOne(eq("x",5), new Document("$set", new Document("x",15)));
        
        //traduce a Java las siguientes sentencias en mongo shell
        //db.collection.update{ "_id" : 4, "x":14, "update" : true }
        
        //¿Que hace la siguiente sentencia?. Traducela a mongo shell
        collection.updateMany(gte("_id",6),new Document("$inc", new Document("x",15)));


        for (Document cur : collection.find().sort(new Document("_id",1))
                .into(new ArrayList<Document>())) {
            printJson(cur);
        }

        client.close();
    }

}
