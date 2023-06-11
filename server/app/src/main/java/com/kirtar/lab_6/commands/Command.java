package com.kirtar.lab_6.commands;
import com.kirtar.lab_6.messages.*;
/**
 * An abstract command with a single method
 */
public interface Command {
     ServerResponse execute();

    void setArg(Object commandArgument);
}
