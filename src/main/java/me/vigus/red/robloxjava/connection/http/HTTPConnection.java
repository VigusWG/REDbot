package me.vigus.red.robloxjava.connection.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.fasterxml.jackson.core.JsonProcessingException;


public final class HTTPConnection {

    private static HTTPConnection instance;
    private static HttpClient client;
    private static Semaphore semaphore;

    public static HTTPConnection getInstance() {
        if(instance == null) {
            instance = new HTTPConnection();
            client = HttpClient.newBuilder()
                //.executor(Executors.newFixedThreadPool(200))
                .version(Version.HTTP_2)
                .followRedirects(Redirect.NEVER)
                .connectTimeout(Duration.ofSeconds(2))
                .build();
            semaphore = new Semaphore(40);
        }
        return instance;
    }

    public CompletableFuture<HttpResponse<String>> makeRequest(String url) throws InterruptedException{
        semaphore.acquire();
        CompletableFuture<HttpResponse<String>> a = client.sendAsync(toHttpRequest(url), HttpResponse.BodyHandlers.ofString());
        a.thenRun(() -> semaphore.release());
        return a;
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

    public CompletableFuture<HttpResponse<String>> postRequest(String url, String data) throws JsonProcessingException, InterruptedException {
        semaphore.acquire();
        CompletableFuture<HttpResponse<String>> a = client.sendAsync(toHttpPostRequest(url, data), HttpResponse.BodyHandlers.ofString());
        a.thenRun(() -> semaphore.release());
        return a;
    }
}
