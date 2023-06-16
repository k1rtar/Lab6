package com.kirtar.lab_7.iomanagers;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.kirtar.lab_7.messages.*;
import com.kirtar.lab_7.models.*;
import com.kirtar.lab_7.network.ServerConnection;
import com.kirtar.lab_7.parsers.*;

/**
 * Parsing of all commands. Call them for execution.
 * Work, both in interactive mode and when reading a file
 */
public class InputConsoleManager
{

        public CommandParser commandParser;
        ExecuteScriptManager executeManager;
        public LinkedList<String> executedFiles = new LinkedList<String>();
        public ServerConnection connection;
        public Map<String,ClientRequest<String>> requests = new HashMap<String,ClientRequest<String>>();
        public Set<String> requestsWithFlatArg = new HashSet<String>();
        public Set<String> requestsWithFlatLong = new HashSet<String>();
        public InputConsoleManager(CommandParser commandParser,ServerConnection connection,ExecuteScriptManager executeManager)
        {
            this.commandParser = commandParser;
            this.connection = connection;
            this.executeManager = executeManager;
            registerRequest(new ClientRequest<String>("help",""));
            registerRequest(new ClientRequest<String>("info",""));
            registerRequest(new ClientRequest<String>("clear",""));
            registerRequest(new ClientRequest<String>("show",""));
            registerRequest(new ClientRequest<String>("remove_first",""));
            registerRequest(new ClientRequest<String>("group_counting_by_area",""));
            registerRequest(new ClientRequest<String>("print_unique_view",""));
            registerRequest(new ClientRequest<String>("history",""));
            requestsWithFlatArg.add("add");
            requestsWithFlatArg.add("remove_lower");

        }
        public void run(Scanner scanner)
        {
        
        String input;
        ClientRequest<?> request = new ClientRequest<String>("help","");
        ServerResponse response = new ServerResponse(ResultStatus.OK,"Events did not happen");
        do{
            if(!scanner.hasNextLine()){
                return;
            }
            input = scanner.nextLine();

            boolean commandEnteredCorrectly = false;
            boolean requestCreated = false;

            String[] inputArr = input.split(" ");
            ClientRequest<String> commandWithoutFlat = requests.get(inputArr[0]);
    

            if (commandWithoutFlat!=null) {
                requestCreated = true;
                getServerResponse(connection, commandWithoutFlat, commandEnteredCorrectly);
                continue;}

            if (null!=inputArr[0] && requestsWithFlatArg.contains(inputArr[0]))
            {
                Flat flat = commandParser.ParseFlatObject(scanner);
                if (flat!=null)
                {
                    request = new ClientRequest<Flat>(inputArr[0], flat);
                    requestCreated = true;
                    getServerResponse(connection, request, commandEnteredCorrectly);
                    continue;
                }
            }


            if (input.length()>=6 && "update".equals(input.substring(0,6))){
                String[] inputArray = input.split(" ");
                Long id = 0L; boolean flag = true;
                try{id = Long.parseLong(inputArray[1]);}
                catch (Exception e) {System.out.println("Error! The entered id is invalid");flag=false;}
                if (flag){
                    Flat flatup = commandParser.ParseFlatObject(scanner);
                    if (flatup!=null){
                        flatup.setId(id);
                        request = new ClientRequest<Flat>("update", flatup);
                        requestCreated = true;}
                    }
                }
            if (input.length()>14 && "execute_script".equals(input.substring(0,14))){
                    String[] inputArray = input.split(" ");
                    if (executedFiles.contains(inputArray[1])){
                        System.out.println("Recursion detected! The recursion is exiting!"+inputArray[1]);
                        scanner.close();
                        return;
                    }
                    executedFiles.add(inputArray[1]);
                    ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(executeManager, inputArray[1]); 
                    executeScriptCommand.execute();
                    executedFiles.removeLast();
                    commandEnteredCorrectly = true;
                }
                if (input.length()>=12 && "remove_by_id".equals(input.substring(0,12))){
                    String[] inputArray = input.split(" ");
                    Long id = 0L;
                    boolean flag = true;
                    try{id = Long.parseLong(inputArray[1]);}
                    catch (Exception e) {System.out.println("Error! The entered id is invalid");flag=false;}
                    commandEnteredCorrectly = true;
                    if (flag){
                        request = new ClientRequest<Long>("remove_by_id",id);
                        requestCreated = true; }  
                }

                try{
                if (input.length()>24 && "filter_starts_with_name".equals(input.substring(0,23))){
                    String[] inputArray = input.split(" ");
                    request = new ClientRequest<String>("filter_starts_with_name", inputArray[1]);
                    requestCreated = true;}}
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("The name cannot contain empty characters");
                }

            if (requestCreated){
                try{response = connection.processTheRequest(request);} 
                catch (Exception e){System.out.println("Request not created");}
                if (response.getResultStatus() == ResultStatus.OK){System.out.println(response.getMessage());commandEnteredCorrectly = true;}
                else{System.out.println("An error has occurred: "+ response.getMessage());}
            }
            
            if ("exit".equals(input)){commandEnteredCorrectly = true;}  

            if (!commandEnteredCorrectly)
            {
                System.out.println("Command entered incorrectly. Follow the command pattern specified in the available command help.");
                System.out.println("For help on available commands, type ''help'' without quotes");
            }

        } while ( !"exit".equals(input) && scanner.hasNextLine());
        scanner.close();
        return;
        }

        public void registerRequest(ClientRequest<String> newRequest)
        {
            if(requests.containsKey(newRequest.getCommandType())){
                throw new IllegalArgumentException("A request with the same name already exists!");
            }
    
            requests.put(newRequest.getCommandType(), newRequest);
        }
        
        public void getServerResponse(ServerConnection connection, ClientRequest<?> request,boolean commandEnteredCorrectly)
        {
            ServerResponse response = null;
            try{response = connection.processTheRequest(request);} 
            catch (Exception e){System.out.println("Request not created");}
            if (response.getResultStatus() == ResultStatus.OK){System.out.println(response.getMessage());commandEnteredCorrectly = true;}
            else{System.out.println("An error has occurred: "+ response.getMessage());}

        }
}