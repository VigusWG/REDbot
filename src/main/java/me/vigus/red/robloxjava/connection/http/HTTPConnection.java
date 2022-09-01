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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.fasterxml.jackson.core.JsonProcessingException;


public final class HTTPConnection {

    private static HTTPConnection instance;
    private static HttpClient client;
    private static Semaphore semaphore;
    private static String XCSRF = "";

    public static HTTPConnection getInstance() {
        if(instance == null) {
            instance = new HTTPConnection();
            client = HttpClient.newBuilder()
                .executor(Executors.newFixedThreadPool(20))
                .version(Version.HTTP_2)
                .followRedirects(Redirect.NEVER)
                .connectTimeout(Duration.ofSeconds(2))
                .build();
            semaphore = new Semaphore(50);
        }
        return instance;
    }

    public static HttpClient getClient(){
        if (client == null){
            getInstance();
        }
        return client;
    }

    public CompletableFuture<HttpResponse<String>> makeRequest(String url) throws InterruptedException{
        semaphore.acquire();
        CompletableFuture<HttpResponse<String>> a = client.sendAsync(toHttpRequest(url), HttpResponse.BodyHandlers.ofString())
            .thenComposeAsync(resp -> {
                Optional<String> xc = resp.headers().firstValue("x-csrf-token");
                if (xc.isPresent()){
                    setcsrf(resp.headers().firstValue("x-csrf-token").get());
                }
                if (resp.statusCode() == 403){
                    try {
                        return makeRequest(url);
                    } catch (InterruptedException e) {
                        throw new CompletionException(e);
                    }
                }
                return CompletableFuture.supplyAsync(() -> resp);
            });
        a.thenRun(() -> semaphore.release());
        return a;
    }

    private HttpRequest toHttpRequest(String url){
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("x-csrf-token", XCSRF)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                .build();
    }

    private HttpRequest toHttpPostRequest(String url, String data){
        return HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(BodyPublishers.ofString(data))
            .header("x-csrf-token", XCSRF)
            .header("Content-Type", "application/json")
            .build();
    }

    public CompletableFuture<HttpResponse<String>> postRequest(String url, String data) throws InterruptedException {
        semaphore.acquire();
        CompletableFuture<HttpResponse<String>> a = client.sendAsync(toHttpPostRequest(url, data), HttpResponse.BodyHandlers.ofString())    
            .thenComposeAsync(resp -> {
                Optional<String> xc = resp.headers().firstValue("x-csrf-token");
                if (xc.isPresent()){
                    setcsrf(resp.headers().firstValue("x-csrf-token").get());
                }
                if (resp.statusCode() == 403){
                    try {
                        System.out.println("new xcsfr token");
                        return postRequest(url, data);
                    } catch (InterruptedException e) {
                        throw new CompletionException(e);
                    }
                }
                return CompletableFuture.supplyAsync(() -> resp);
            });
        a.thenRun(() -> semaphore.release());
        return a;
    }

    public void setcsrf(String csrf){
        XCSRF = csrf;
    }

    public String getcsrf() {
        return XCSRF;
    }
}
