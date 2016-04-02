package com.company;

import Map.Map;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by bjc90_000 on 4/2/2016.
 */
public class MapHelper {
    public static void getMapReq(Socket socket, Object o, ObjectOutputStream os){
        MongoClient mongoClient = new MongoClient("localhost");
        MongoDatabase database = mongoClient.getDatabase("CF");
        MongoCollection<Document> collection = database.getCollection("map");
        System.out.println("Get Map Req!");
        Document gameMap = collection.find().first();
        try {
            os.writeObject(new Gson().fromJson(gameMap.toJson(), Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
