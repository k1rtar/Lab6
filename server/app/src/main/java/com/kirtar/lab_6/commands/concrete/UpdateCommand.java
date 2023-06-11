package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;
import com.kirtar.lab_6.models.Flat;

/**
 * update the value of the collection element whose id is equal to the given one
 */

public class UpdateCommand implements Command
{
    private Receiver receiver;
    private Flat flat;
    public UpdateCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }
    public UpdateCommand(Receiver receiver, Flat flat)
    {
        this.receiver = receiver;
        this.flat = flat;
    }
    @Override
    public ServerResponse execute()
    {
        return receiver.update(flat);
    }

    public void setArg(Object flat){
        this.flat = (Flat) flat;
    }

}
