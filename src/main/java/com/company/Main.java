package com.company;

import WebUtilities.LoginReq;
import WebUtilities.LoginRes;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.net.*;

import static com.mongodb.client.model.Filters.eq;


public class Main {


    public static void main(String[] args) {

        ServerSocket providerSocket = null;
        Socket clientSocket;
        ObjectInputStream is;
        ObjectOutputStream os;
        String message;
        LoginRes response = new LoginRes();

        try {
            providerSocket = new ServerSocket(3000);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            while(true) new Main().takeNewConnection( providerSocket.accept());
//            is = new ObjectInputStream(clientSocket.getInputStream());
//            os = new ObjectOutputStream(clientSocket.getOutputStream());
//            while (true) {
//
//                message = is.readObject().toString();
//                System.out.println(message);
//                response.admin = true;
//                response.success = true;
//                os.writeObject(response);
//            }


        } catch (IOException e) {
            System.out.println(e);
        }


        // write your code here
    }

    public void takeNewConnection(Socket socket) {

         final Socket _socket=socket;



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectInputStream is = new ObjectInputStream(_socket.getInputStream());
                    ObjectOutputStream os = new ObjectOutputStream(_socket.getOutputStream());
                    MongoClient mongoClient = new MongoClient( "localhost" );
                    MongoDatabase database = mongoClient.getDatabase("CF");

                    Object o = is.readObject();

                    if(o instanceof LoginReq){
                        MongoCollection<Document> collection = database.getCollection("login");
                        System.out.println("Loggin IN!");
                        Document _doc = collection.find(eq("username",((LoginReq) o).username)).first();
                        LoginRes response = new LoginRes();

                        System.out.println("  "+_doc.toJson());
                        response.admin = true;
                        response.success = true;
                        os.writeObject(response);
                    }



                } catch (IOException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
