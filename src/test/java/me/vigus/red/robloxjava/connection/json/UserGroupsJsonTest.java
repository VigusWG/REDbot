package me.vigus.red.robloxjava.connection.json;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;

public class UserGroupsJsonTest {
    @Test
    public void testRequest() {
        try {
            ArrayList<UserGroupsJson> a = UserGroupsJson.request(72885863L).get();
            System.out.println("User is in "+ Integer.toString(a.size()) + " groups.");
            assertTrue(true);
        }catch (Exception e){
            assertFalse(e.getMessage(), false);
        }
    }

    @Test
    public void testMultiRequest() {
        try {
            ArrayList<CompletableFuture<ArrayList<UserGroupsJson>>> li = new ArrayList<>();
            Long[] a = {72885863L, 46579344L, 241416682L, 211661332L, 23609989L, 117142531L, 382373348L, 103520593L};
            for (Long i : a){
                li.add(UserGroupsJson.request(i).whenComplete((req, exc) -> System.out.println(i.toString() + ": " + Integer.toString(req.size()))));
            }
            CompletableFuture.allOf(li.toArray(new CompletableFuture[0])).join();
            assertTrue(true);
        } catch (Exception e) {
            assertFalse(e.getMessage(), false);
        }
    }

    
}
