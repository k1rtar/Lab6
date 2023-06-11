package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;

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
