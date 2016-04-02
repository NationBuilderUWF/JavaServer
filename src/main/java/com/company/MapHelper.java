package com.company;


import Map.Map;
import WebUtilities.SetMapReq;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.mongodb.client.model.Filters.eq;

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

    public static void setMapReq(Socket socket, Object o,ObjectOutputStream os){
        MongoClient mongoClient = new MongoClient("localhost");
        MongoDatabase database = mongoClient.getDatabase("CF");
        MongoCollection<Document> collection = database.getCollection("map");
        System.out.println("Set Map Req");
        SetMapReq smr=(SetMapReq)o;
        Map newMap = (Map) smr.map;
//        String newMapJSON = new Gson().toJson(newMap).toString();
//        BSONObject dbObject = (BSONObject) JSON.parse(newMapJSON);
        collection.updateOne(eq("_id",((SetMapReq) o).map._id),new Document("$set",new Document("tiles",newMap.tiles)));

    }
}
