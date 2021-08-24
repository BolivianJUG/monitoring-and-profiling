package org.bolivianjug;

import io.vertx.ext.web.impl.LRUCache;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/profiling")
public class ProfilingResource {

    private static final Map<Long, byte[]> cache = new HashMap<>();

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("memory")
    public String cacheThis() {
        long objectId = System.currentTimeMillis();
        cache.put(objectId, new byte[10485760]);
        return "Created object " + objectId;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("cpu")
    public String processThis() {
        method1();
        method2();
        method3();
        return "Processed at time "+System.currentTimeMillis();
    }

    public void method1(){
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 100) {}
    }

    public void method2(){
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 200) {}
    }

    public void method3(){
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 300) {}
    }

}
