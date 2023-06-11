package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.models.Flat;
import com.kirtar.lab_6.messages.ServerResponse;
/**
 *  remove from the collection all elements smaller than the given one
 */

public class RemoveLowerCommand implements Command{

    private Receiver receiver;
    private Flat flat;
    public RemoveLowerCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }
    public RemoveLowerCommand(Receiver receiver,Flat flat)
    {
        this.receiver = receiver;
        this.flat = flat;
    }

    @Override
    public ServerResponse execute()
    {
        return receiver.removeLower(flat);
    }

    public void setArg(Object flat){
        this.flat = (Flat) flat;
    }
}
