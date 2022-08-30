package me.vigus.red.robloxjava.connection.structs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

//right off stack overflow babyyyyy
public class DateDeserializer extends StdDeserializer<Date> {
    

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat withMillis = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        SimpleDateFormat withoutMillis = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"); //not static cause so it's thread safe

        String dateString = p.getText();
        if (dateString.isEmpty()) {
            //handle empty strings however you want,
            //but I am setting the Date objects null
            return null;
        }

        try {
            return withMillis.parse(dateString);
        } catch (ParseException e) {
            try {
                return withoutMillis.parse(dateString);
            } catch (ParseException e1) {
                throw new RuntimeException("Unable to parse date", e1);
            }
        }
    }
}
