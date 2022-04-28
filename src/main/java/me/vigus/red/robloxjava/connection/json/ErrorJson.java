package me.vigus.red.robloxjava.connection.json;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"code",
"message",
"userFacingMessage"
})

public class ErrorJson {

@JsonProperty("code")
private Integer code;
@JsonProperty("message")
private String message;
@JsonProperty("userFacingMessage")
private String userFacingMessage;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("code")
public Integer getCode() {
    return code;
}

@JsonProperty("code")
public void setCode(Integer code) {
this.code = code;
}

@JsonProperty("message")
public String getMessage() {
    return message;
}

@JsonProperty("message")
public void setMessage(String message) {
    this.message = message;
}

@JsonProperty("userFacingMessage")
public void setUserFacingMessage(String userFacingMessage) {
    this.userFacingMessage = userFacingMessage;
}

@JsonProperty("userFacingMessage")
public String getUserFacingMessage() {
    return this.userFacingMessage;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
}

}