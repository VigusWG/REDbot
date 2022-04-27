package me.vigus.red.robloxjava.connection.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.util.concurrent.CompletableFuture;

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
}
