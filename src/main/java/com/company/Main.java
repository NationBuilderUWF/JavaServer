package com.company;

import WebUtilities.LoginReq;
import WebUtilities.LoginRes;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sun.rmi.runtime.Log;

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
            System.out.println("making server");
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

    public void takeNewConnection(final Socket socket) {

         final Socket _socket=socket;



        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("connection made, determining action");

                try {
                    ObjectInputStream is = new ObjectInputStream(_socket.getInputStream());
                    System.out.println("made input stream");

                   ObjectOutputStream os = new ObjectOutputStream(_socket.getOutputStream());

//                    MongoClient mongoClient = new MongoClient("localhost");
//                    MongoDatabase database = mongoClient.getDatabase("CF");

                    Object o = is.readObject();
                    System.out.println("made object");

                    if (o instanceof LoginReq) {
                        System.out.println("using helper");
                        LoginHelper.login(socket,o,os);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            }).start();

    }

}
