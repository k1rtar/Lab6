package com.kirtar.lab_6.commands.concrete;


import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.models.Flat;

import java.util.Queue;

/**
 * save collection to file
 */

public class SaveCommand 
{
    private Queue<Flat> collection;
    private Receiver receiver;
    private String path;
    public SaveCommand(Receiver receiver, Queue<Flat> collection,String path)
    {
        this.receiver = receiver;
        this.collection = collection;
        this.path = path;
    }
    
    public void execute()
    {
        receiver.save(collection,path);
    }
}
