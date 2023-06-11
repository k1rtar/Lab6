package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;
/**
 * print information about the collection to the standard output stream (type, initialization date, number of elements, etc.)
 */

public class InfoCommand implements Command
{
    private Receiver receiver;
    public InfoCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public ServerResponse execute()
    {
        return receiver.info();
    }
    public void setArg(Object element){}
}


