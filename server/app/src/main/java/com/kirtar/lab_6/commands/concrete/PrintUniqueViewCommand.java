package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;
/**
 * display unique values of the view field of all elements in the collection
 */

public class PrintUniqueViewCommand implements Command{

    private Receiver receiver;

    public PrintUniqueViewCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public ServerResponse execute()
    {
        return receiver.printUniqueView();
    }
    public void setArg(Object element){}
}
