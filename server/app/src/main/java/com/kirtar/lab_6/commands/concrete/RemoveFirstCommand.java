package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;

/**
 * remove the first element from the collection
 */

public class RemoveFirstCommand implements Command{

    private Receiver receiver;

    public RemoveFirstCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public ServerResponse execute()
    {
        return receiver.removeFirst();
    }
    public void setArg(Object element){}
}
