package me.vigus.red.robloxjava.connection.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import me.vigus.red.robloxjava.connection.json.FollowingCount;

public final class HTTPConnection {

    private static HTTPConnection instance;
    private static HttpClient client;


    public static HTTPConnection getInstance() {
        if(instance == null) {
            instance = new HTTPConnection();
            client = HttpClient.newBuilder()
                //.executor(Executors.newFixedThreadPool(5))
                .version(Version.HTTP_2)
                .build();
        }
        
        return instance;
    }


    public CompletableFuture<HttpResponse<String>> makeRequest(String url){
        return client.sendAsync(toHttpRequest(url), HttpResponse.BodyHandlers.ofString());
    }

    private HttpRequest toHttpRequest(String url){
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
    }

    private HttpRequest toHttpPostRequest(String url, String data){
        return HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(BodyPublishers.ofString(data))
            .header("Content-Type", "application/json")
            .build();
    }


    public CompletableFuture<HttpResponse<String>> postRequest(String url, String data) throws JsonProcessingException {
        return client.sendAsync(toHttpPostRequest(url, data), HttpResponse.BodyHandlers.ofString());
    }
}
