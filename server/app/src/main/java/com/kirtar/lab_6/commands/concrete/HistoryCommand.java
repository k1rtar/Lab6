package com.kirtar.lab_6.commands.concrete;

import com.kirtar.lab_6.commands.Command;
import com.kirtar.lab_6.commands.Receiver;
import com.kirtar.lab_6.messages.ServerResponse;

import java.util.LinkedList;

/**
 * print the last 11 commands (without their arguments)
 */

public class HistoryCommand implements Command{

    private Receiver receiver;

    private LinkedList<String> lastCommands;

    public HistoryCommand(Receiver receiver,LinkedList<String> lastCommands)
    {
        this.receiver = receiver;
        this.lastCommands = lastCommands;
    }

    @Override
    public ServerResponse execute()
    {
        return receiver.history(lastCommands);
    }
    public void setArg(Object element){}
}
