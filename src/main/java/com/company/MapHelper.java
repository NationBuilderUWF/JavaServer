package com.company;


import WebUtilities.GetMapRes;
import WebUtilities.SetMapReq;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.BSONObject;
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
        System.out.println(gameMap);
        GetMapRes object = new Gson().fromJson(gameMap.toJson(), GetMapRes.class);
        System.out.println(object);
        try {
            os.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMapReq(Socket socket, Object o,ObjectOutputStream os){
        MongoClient mongoClient = new MongoClient("localhost");
        MongoDatabase database = mongoClient.getDatabase("CF");
        MongoCollection<Document> collection = database.getCollection("map");
        System.out.println("Set Map Req");
        System.out.println(o);
        SetMapReq smr=(SetMapReq)o;
        System.out.println(smr.maps);
        String newMapJSON = new Gson().toJson(smr).toString();
        //Document doc = new Document("tiles",smr.maps).append("banks",smr.banks);
        collection.insertOne(new Document("Map?",newMapJSON));

        System.out.println("Hello:: "+new Gson().fromJson((String) collection.find().first().get("Map?"),SetMapReq.class).banks);

        //collection.updateOne(eq("_id",((SetMapReq) o).map._id),new Document("$set",new Document("tiles",newMap.tiles)));
    }
}
