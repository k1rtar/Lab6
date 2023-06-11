package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;

/**
 * display elements whose name field value starts with the given substring
 */

public class FilterNameCommand implements Command{

    private Receiver receiver;
    private String name;

    public FilterNameCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }
    public FilterNameCommand(Receiver receiver,String name)
    {
        this.receiver = receiver;
        this.name = name;
    }

    @Override
    public ServerResponse execute()
    {
        return receiver.filterStartsWithName(name);
    }


    public void setArg(Object name){
        this.name = (String) name;
    }
}
