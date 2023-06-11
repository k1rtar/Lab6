package com.kirtar.lab_6.messages;

import java.io.Serializable;

/**
 * A class that makes a request to the server to execute a command with the specified arguments
 */
public class ClientRequest<T> implements Serializable{
     private String commandType;
     private T commandArgument;
     public ClientRequest(String commandType, T commandArgument)
     {
          this.commandType = commandType;
          this.commandArgument = commandArgument;
     }
     public String getCommandType()
     {
          return commandType;
     }
     public T getCommandArgument()
     {
          return commandArgument;
     }

     public void setCommandType(String commandType)
     {
          this.commandType = commandType;
     }

     public void setCommandArgument(T commandArgument)
     {
          this.commandArgument = commandArgument;
     }

}
