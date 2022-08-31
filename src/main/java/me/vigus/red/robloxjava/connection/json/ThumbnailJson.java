package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Arrays.asList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.connection.structs.ThumbnailRequest;

public class ThumbnailJson {    
    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("errorCode")
    private Integer errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("targetId")
    private Long targetId;

    @JsonProperty("state")
    private String state;

    @JsonProperty("imageUrl")
    private String imageURL;

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonIgnore 
    public static CompletableFuture<ThumbnailJson> request(ThumbnailRequest request) throws InterruptedException{
        return request(asList(request)).thenApply(response -> response.get(0));
    }  

    @JsonIgnore
    public static CompletableFuture<List<ThumbnailJson>> request(List<ThumbnailRequest> requests) throws InterruptedException{
        StringBuilder x = new StringBuilder("[");
        for (int i=0; i < requests.size(); i++){
            ThumbnailRequest req = requests.get(i);
            ObjectNode json = CustomObjectMapper.getMapper().createObjectNode();
            json.put("format", req.getFormat().toString());
            json.put("size", req.getSize().getValue());
            json.put("targetId", req.getTargetId());
            json.put("type", req.getType().toString());
            x.append(json.toString());
            if (i < requests.size()-1){
                x.append(",");
            }
        }
        x.append("]");
        return HTTPConnection.getInstance().postRequest("https://thumbnails.roblox.com/v1/batch", x.toString())
            .thenApply(response -> {
                ArrayList<ThumbnailJson> it = new ArrayList<>();
                try {
                    JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response.body());
                    Iterator<JsonNode> f = jsonNode.get("data").elements();
                    while (f.hasNext()) {
                        ThumbnailJson o = CustomObjectMapper.getMapper().treeToValue(f.next(), ThumbnailJson.class);
                        if (o.getErrorMessage() != ""){
                            throw new CompletionException(o.getErrorMessage(), null);
                        }
                        it.add(o);
                    }
                    return it;
                } catch (JsonProcessingException e) {
                    throw new CompletionException(e);
                }
            });


    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getTargetId() {
        return this.targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<ErrorJson> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorJson> errors) {
        this.errors = errors;
    }
    public void setAdditionalProperties(Map<String,Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
    
}
