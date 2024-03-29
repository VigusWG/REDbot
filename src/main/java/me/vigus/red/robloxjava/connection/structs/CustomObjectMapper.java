package me.vigus.red.robloxjava.connection.structs;

import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

public abstract class CustomObjectMapper {
    private static ObjectMapper mapper;
    
    public static ObjectMapper getMapper(){
        if (mapper == null){
            mapper = new ObjectMapper();
            SimpleModule dateModule = new SimpleModule();
            dateModule.addDeserializer(Date.class, new DateDeserializer());
            mapper.registerModule(dateModule);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return mapper;
    }
}

