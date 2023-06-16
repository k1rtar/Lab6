package com.kirtar.lab_7.commands.concrete;

import com.kirtar.lab_7.commands.Command;
import com.kirtar.lab_7.commands.Receiver;
import com.kirtar.lab_7.messages.ServerResponse;

/**
 * clear the collection
 */

public class ClearCommand implements Command{

    private Receiver receiver;

    public ClearCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public ServerResponse execute()
    {
       return  receiver.clear();
    }
    public void setArg(Object element){}
}
