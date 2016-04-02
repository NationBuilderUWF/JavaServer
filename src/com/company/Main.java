package com.company;

import WebUtilities.LoginReq;
import WebUtilities.LoginRes;

import java.io.*;
import java.net.*;


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

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

                    Object o = is.readObject();

                    if(o instanceof LoginReq){
                        LoginRes response = new LoginRes();
                        System.out.println("  ");
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
