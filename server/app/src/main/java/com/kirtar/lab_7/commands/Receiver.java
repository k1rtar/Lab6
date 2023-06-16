package com.kirtar.lab_7.commands;

import java.util.Queue;
import java.util.LinkedList;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import com.kirtar.lab_7.Main;
import com.kirtar.lab_7.messages.*;
import com.kirtar.lab_7.models.Flat;
import com.kirtar.lab_7.models.View;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;


import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet; 
import java.util.stream.Collectors;


/**
 * Implementation of all commands, executor class
 */
public class Receiver
{
    private Queue<Flat> collection;

    public Receiver(Queue<Flat> collection)
    {
        this.collection = collection;
    }

    public ServerResponse add(Flat element)
    {
        Long id = 0L;
        for (Flat el:collection)
        {
            if (el.getId()>id)
            {
                id = el.getId();
            }
        }
        element.setId(++id);
        collection.add(element);
        return new ServerResponse(ResultStatus.OK, "ADD");
    }

    public ServerResponse clear()
    {
        collection.clear();
        return new ServerResponse(ResultStatus.OK,"CLEAR");
    }

    public ServerResponse help()
    {
    
        String message = "help : Display help on available commands\ninfo : Display information about the collection (type, initialization date, number of elements, etc.) on standard output\nshow : Display all elements of the collection in string representation on standard output } : add a new element to the collection\nupdate id {element} : update the value of the collection element whose id is equal to the given\nremove_by_id id : remove the element from the collection by its id\nclear : clear the collection\nsave : save the collection to a file\nexecute_script file_name : read and execute the script from the specified file. The script contains commands in the same form as the user enters them in interactive mode.\nexit : terminate the program (without saving to a file)\nremove_first : remove the first element from the collection\nremove_lower {element} : remove from the collection all elements less than than the given one\nhistory : print the last 11 commands (without their arguments)\ngroup_counting_by_area : group the elements of the collection by the area field value, display the number of elements in each group\nfilter_starts_with_name name : display the elements whose name field value starts with the given substring\nprint_unique_view : display unique values of the view field of all elements in the collection";
        return new ServerResponse(ResultStatus.OK,message);
    }

    public ServerResponse show()
    {
        String message = "Collection elements (number of elements): " + collection.size() + ": \n"
        + collection.stream().map(e -> e + "\n").collect(Collectors.joining());
        return new ServerResponse(ResultStatus.OK, message);
    }

    public ServerResponse history(LinkedList<String> lastCommands)
    {
        String message = "";
        message+="Last 11 commands: ";
        for(String command: lastCommands)
        {
            message+="\n"+command;
        }
        return new ServerResponse(ResultStatus.OK,message);

    }

    public ServerResponse removeFirst()
    {
        collection.poll();
        return new ServerResponse(ResultStatus.OK,"REMOVE_FIRST");
    }

    public ServerResponse save(Queue<Flat> collection, String path)
    {
        String result = "";
        try{
        for (Flat el: collection)
        {
            
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String xmlString = xmlMapper.writeValueAsString(el);
            result+=xmlString;
        }

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
        bufferedOutputStream.write(result.getBytes());
        bufferedOutputStream.close();
        return new ServerResponse(ResultStatus.OK,result+'\n'+"SAVE");
    }
    catch (Exception e)
    {
        e.printStackTrace();
        return new ServerResponse(ResultStatus.OK,"Error writing collection to file");
        
    }


    }

    public ServerResponse update(Flat flat)
    {

        collection.stream()
        .filter(el -> el.getId().equals(flat.getId()))
        .forEach(el -> {
            el.setName(flat.getName());
            el.setCoordinates(flat.getCoordinates());
            el.setArea(flat.getArea());
            el.setNumberOfRooms(flat.getNumberOfRooms());
            el.setNumberOfBathrooms(flat.getNumberOfBathrooms());
            el.setView(flat.getView());
            el.setTransport(flat.getTransport());
            el.setHouse(flat.getHouse());     
        });
        return new ServerResponse(ResultStatus.OK,"UPDATE");
    }
    public ServerResponse removeById(Long id)
    {
        for (Flat el:collection)
        {
            if (el.getId().equals(id))
            {
                el.setId(-100000000L);
                collection.poll();
                break;
            }
        }
        return new ServerResponse(ResultStatus.OK,"REMOVE_BY_ID");
    }


    public ServerResponse info()
    {
        String message = "---Collection Information---\n"+"Collection type: "+collection.getClass().toString()+'\n'+"Initialization date: "+Main.date+"\nAmount of elements: "+collection.size();
        return new ServerResponse(ResultStatus.OK,message);
        
    }

    public ServerResponse removeLower(Flat flat)
    {
        Iterator<Flat> it = collection.iterator();
        while (it.hasNext())
        {
            Flat nextFlat = it.next();
            if (flat.getNumberOfRooms()>nextFlat.getNumberOfRooms())
            {
                it.remove();
            }
        }
        return new ServerResponse(ResultStatus.OK,"REMOVE_LOWER");
        
    }

    public ServerResponse groupCountingByArea()
    {

        Map<Double,Integer> dictionary = new HashMap<Double,Integer>();
        for(Flat el: collection)
        {
            dictionary.put(el.getArea(),0);
        }
        for(Flat el: collection)
        {
            dictionary.put(el.getArea(),dictionary.get(el.getArea())+1);
        }
        String message = "";
        for(Map.Entry<Double, Integer> item : dictionary.entrySet()){
         
            message+=String.format("Area: %f  Amount of elements: %d \n",item.getKey(),item.getValue());
        }
        return new ServerResponse(ResultStatus.OK,message);
    }

    public ServerResponse filterStartsWithName(String name)
    {
        System.out.println(name);
        String message = collection.stream()
        .filter(el -> name.length() <= el.getName().length())
        .filter(el -> el.getName().substring(0, name.length()).equals(name))
        .map(el -> el + "\n")
        .collect(Collectors.joining());
        return new ServerResponse(ResultStatus.OK,message+"FILTER_STARTS_WITH_NAME");
    }

    public ServerResponse printUniqueView()
    {
        String message="";
        HashSet<View> viewSet = new HashSet<View>();
        for (Flat el:collection)
        {
            viewSet.add(el.getView());
        }
        for (View el:viewSet)
        {
            message+=(el.toString()+"\n");
        }
        message+="PRINT_UNIQUE_VIEW";
        return new ServerResponse(ResultStatus.OK, message);
    }

}