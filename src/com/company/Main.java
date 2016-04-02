package com.company;
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

        try
        {
            providerSocket = new ServerSocket(3000);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        try
        {
            clientSocket = providerSocket.accept();
            is = new ObjectInputStream(clientSocket.getInputStream());
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            while(true)
            {

                message = is.readObject().toString();
                System.out.println(message);
                response.admin = true;
                response.success = true;
                os.writeObject(response);
            }


        }
        catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // write your code here
    }
}
