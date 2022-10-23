package me.vigus.red.robloxjava.connection.http;

import static org.junit.Assert.assertEquals;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class HttpConnectionTest {
    @Test(timeout = 3000)
    public void getRequestWorks() throws InterruptedException, ExecutionException {
        HTTPConnection.getInstance();

        long startTime = System.nanoTime();
        CompletableFuture<HttpResponse<String>> x = HTTPConnection.getInstance().makeRequest("https://httpbin.org/get");
        long firstTime = System.nanoTime();
        HttpResponse<String> a = x.get();
        long secondTime = System.nanoTime();
        System.out.println(String.format("Time to generate client and request: %s\nTime to get request: %s\n", (firstTime-startTime)/1000000, (secondTime-startTime)/1000000));
        assertEquals(a.statusCode(), 200);
    }

    @Test(timeout = 3000)
    public void multiRequestsWorks() throws InterruptedException, ExecutionException {
        ArrayList<CompletableFuture<Integer>> completables = new ArrayList<>();
        HTTPConnection instance = HTTPConnection.getInstance();

        long startTime = System.nanoTime();

        for (int i = 0; i < 15; i++){
            completables.add(HTTPConnection.getInstance().makeRequest("https://httpbin.org/get")
                .thenApply(response -> {
                    long now = System.nanoTime();
                    System.out.println(String.format("Request done in: %s", (now - startTime) / 1000000));
                    return response.statusCode();
                }));
        }
        
        CompletableFuture.allOf(completables.toArray(new CompletableFuture[0])).get();
        System.out.println(String.format("Fully completed in: %s", (System.nanoTime() - startTime) / 1000000));

        boolean g = completables.stream()
            .mapToInt(o -> {
                try {
                    return o.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return 0;
                }
            })
            .allMatch(n -> n == 200);

        assertEquals(g, true);  
    }
}
