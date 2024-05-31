package loki.ui.client;

import java.util.HashMap;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * A container object that is passed into each {@link loki.ui.client.screen.Screen}.
 * Implementations can use the createServiceTarget method to create a RemoteService.
 * Custom headers can be specified to be added to the RemoteService calls if desired.
 */

public abstract class ClientContext
{
    private HashMap<String, String> headerMap;
    
    public ClientContext()
    {
        super();
        this.headerMap = new HashMap<String, String>();
    }
    
    /**
     * Sets a custom header to be used by all RemoteService objects created through this context.
     * 
     * @param header        The name of the custom header.
     * @param value         The value applied to the custom header.
     */
    
    public void setHeader(String header, String value)
    {
        headerMap.put(header, value);
    }
    
    /**
     * Removes a custom header.
     * 
     * @param header        The name of the custom header.
     */
    
    public void clearHeader(String header)
    {
        headerMap.remove(header);
    }
    
    /**
     * Removes all custom headers.
     */
    
    public void clearAllHeaders()
    {
        headerMap.clear();
    }
    
    /**
     * Called by implementations to instantiate a new RemoteService.
     */
    
    protected <T> T createServiceTarget(T service, String url)
    {
        ((ServiceDefTarget)service).setRpcRequestBuilder(new RpcRequestBuilder(){
            protected void doFinish(RequestBuilder b)
            {
                for(String header: headerMap.keySet())
                {
                    b.setHeader(header, headerMap.get(header));
                }
                
                super.doFinish(b);
            }
        });
        ((ServiceDefTarget)service).setServiceEntryPoint(url);
        
        return service;
    }
}