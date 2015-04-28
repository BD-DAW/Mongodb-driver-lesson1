/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.ed.m001;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author carlos
 */
public class FindWithSortSkipLimitTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = 
                database.getCollection("findWithSortTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                collection.insertOne(new Document()
                        .append("i", i)
                        .append("j", j));
            }
        }

        //Deduce es siguiente código de mongo shell equivalente con el driver
        //db.findWithSortTest.find().sort({i:1,j:-1})
        //db.findWithSortTest.find().sort({i:1}).limit(2)
        //db.findWithSortTest.find({i: {$gt:5}}).sort({j:1}).limit(10).skip(3)



        //¿Qué hace la siguiente línea de código?    
        Bson projection = fields(include("i","j"),excludeId());
        
        MongoCursor<Document> cursor = collection.find()
                    .projection(projection)
                    .iterator();
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
