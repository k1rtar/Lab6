package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;

/**
 * display help on available commands
 */

public class HelpCommand implements Command
{
    private Receiver receiver;
    public HelpCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public ServerResponse execute()
    {
        return receiver.help();
    }
    public void setArg(Object element){}
}


