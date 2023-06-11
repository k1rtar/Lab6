package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;

/**
 * print to standard output all elements of the collection in string representation
 */

public class ShowCommand implements Command
{
    private Receiver receiver;
    public ShowCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public ServerResponse execute()
    {
        return receiver.show();
    }
    public void setArg(Object element){}
}