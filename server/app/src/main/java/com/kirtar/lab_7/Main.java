package com.kirtar.lab_7;

import java.util.Queue;

import com.kirtar.lab_7.commands.*;
import com.kirtar.lab_7.commands.concrete.*;
import com.kirtar.lab_7.iomanagers.*;
import com.kirtar.lab_7.messages.*;
import com.kirtar.lab_7.models.*;

import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Date;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The main class through which the entire program is launched
 */
public class Main
{
    public static Date date = new Date();
    private static int port = 8888;
    private static String path = "D:/Lab5input/iksmle.xml";
    public static void main(String[] args)
    {      
        if (args.length>=1)
        {
            try{
                port = Integer.parseInt(args[0]);
            }
            catch (Exception e){
                System.out.println("Unable to parse port from command line arguments, standard port is used");
            }
            if (args.length==2){
                try{
                    path = args[1];
                }
                catch (Exception e)
                {
                    System.out.println("Unable to parse path from command line arguments, standard path is used");
                }
            }

        }
        Queue<Flat> collection = new PriorityQueue<Flat>();
        InputFileManager inputFileManager = new InputFileManager();
        try{collection = inputFileManager.XMLtoFlat(path);}
        catch (Exception e){System.out.println("File upload error! Check if the specified path to the file is correct (the path must be absolute)");}
        Receiver receiver = new Receiver(collection);
        RequestToCommand rqstToCmd = new RequestToCommand(receiver);
        ServerSocketChannel serverSocketChannel;

        Thread ServerInput = new ServerInput(receiver,collection);
        ServerInput.start();

        LinkedList<Command> commandList = new LinkedList<Command>();
        LinkedList<String> lastCommands = new LinkedList<String>();

        try{
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("Server started on port: "+port);
            while (true)
            {
                try(SocketChannel clientChannel = serverSocketChannel.accept()){
                    System.out.println("Received a new request from the machine " + clientChannel.getRemoteAddress());
    
                    ObjectInputStream ios = new ObjectInputStream(clientChannel.socket().getInputStream());
                    ClientRequest<?> request = (ClientRequest<?>) ios.readObject();
                    System.out.println("Request : " + request.getCommandType());

                    Command command = rqstToCmd.requestToCommand(request);
                    commandList.addLast(command);
                    
                    ServerResponse result = new ServerResponse(ResultStatus.ERROR, "Command missing, request failed");

                    if (request.getCommandType().equals("history")){command = new HistoryCommand(receiver,lastCommands);}
                    if (command!=null){result = command.execute();}
                    lastCommands.add(request.getCommandType());
    
                    if(result.getResultStatus() == ResultStatus.OK){
                        System.out.println("Execution result: successful");
                    }
                    else{
                        System.out.println("Execution result: failed");
                    }
                    System.out.println();
    
                    OutputStream os = clientChannel.socket().getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(result);
                }
                catch (IOException | ClassNotFoundException exc){
                    System.out.println(exc.getMessage());
                } 
            }
        }
        catch (IOException exc)
        {
            System.out.println("Server startup error! Most likely the port is busy!");
        } 
   

       

    }
}