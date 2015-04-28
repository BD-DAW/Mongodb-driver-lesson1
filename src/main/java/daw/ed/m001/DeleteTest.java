/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.ed.m001;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author carlos
 */
public class DeleteTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection
                = database.getCollection("DeleteTest");

        collection.drop();

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document()
                    .append("_id", i));
        }
        
        //¿Qué hara la siguiente sentencia?, ¿Cuántos documentos borrará?
        //reescribe la sentencia en mongo shell
        //collection.deleteOne(gt("_id",5));
        
        //reescribe la sentencia en mongo shell - ver documentación
        //collection.deleteMany(gt("_id",5));

        for (Document cur : collection.find().into(new ArrayList<Document>())) {
            printJson(cur);
        }

        client.close();
    }

}
