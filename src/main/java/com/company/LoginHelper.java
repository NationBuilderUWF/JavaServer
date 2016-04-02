package com.company;

import WebUtilities.LoginReq;
import WebUtilities.LoginRes;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by bjc90_000 on 4/2/2016.
 */
public class LoginHelper {


    public static void login(Socket socket, Object o, ObjectOutputStream os) {
        final Socket _socket = socket;

        try {
           // ObjectInputStream is = new ObjectInputStream(_socket.getInputStream());
          //  ObjectOutputStream os = new ObjectOutputStream(_socket.getOutputStream());

            MongoClient mongoClient = new MongoClient("localhost");
            MongoDatabase database = mongoClient.getDatabase("CF");



            if (o instanceof LoginReq) {
                MongoCollection<Document> collection = database.getCollection("login");
                System.out.println("Loggin IN!");
                Document _doc = collection.find(eq("username", ((LoginReq) o).username)).first();

                Login login = new Gson().fromJson(_doc.toJson(), Login.class);

                System.out.println(login.password + ":   ==??  :" + ((LoginReq) o).password);

                if (login.password.equals(((LoginReq) o).password)) {
                    System.out.println("entered the if !!");
                    LoginRes response = new LoginRes();
                    //System.out.println(new Gson().fromJson(_doc.toJson(),Login.class));
                    response.admin = login.admin;
                    response.success = true;
                    System.out.println("Sending Login Success");
                    os.writeObject(response);
                }


            }


        } catch (IOException e) {
            e.printStackTrace();

        } catch (NullPointerException e) {
            System.out.println("BAD LOGIN");
            LoginRes response = new LoginRes();
            response.admin = false;
            response.success = false;
            try {
                new ObjectOutputStream(_socket.getOutputStream()).writeObject(response);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
