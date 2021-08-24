package org.bolivianjug;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;

@Path("/monitoring")
public class MonitoringResource {

    private final MeterRegistry registry;

    private final LinkedList<Long> myCache = new LinkedList<>();

    MonitoringResource(MeterRegistry registry) {
        this.registry = registry;
        this.registry.gaugeCollectionSize("myapp_gauge", Tags.empty(), myCache);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("counter/{name}")
    public String counter(@PathParam("name") String name) {
        registry.counter("myapp_counter", Tags.of("name", name)).increment();
        return "Received " + name;
    }

    @PUT
    @Path("gauge")
    public int addElement(){
        myCache.add(System.currentTimeMillis());
        return myCache.size();
    }

    @DELETE
    @Path("gauge")
    public int removeElement(){
        if(myCache.size() > 0){
            myCache.removeFirst();
        }
        return myCache.size();
    }
}
