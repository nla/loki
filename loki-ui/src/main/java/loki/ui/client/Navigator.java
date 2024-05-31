package loki.ui.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

import loki.ErrorHandler;
import loki.StringPair;
import loki.ui.Element;
import loki.ui.client.screen.Screen;

/**
 * A class that clients can use to automatically handle inter-screen navigation based on components in the page's URL.
 * This class supports the single page paradigm, allowing different {@link loki.ui.client.screen.Screen} objects 
 * and states to be referenced by different URL components.
 * 
 * For example, a user could navigate to /#screen=Monitor/host=host.gov.au/resource=cpu/ which would use the Screen
 * associated with the id 'Monitor' and pass in the host and resource arguments for the screen to render with the appropriate state.
 * 
 * This class also allows applications to programmatically navigate to other screens.
 */

public abstract class Navigator<T extends ClientContext>
{
    private static final String TOKEN_SEPARATOR = "/";
    private static final String VALUE_SEPARATOR = "=";
    private static final String SCREEN_ARG = "screen";
    
    private T context;
    private Element screenContainer;
    private Screen<T> defaultScreen;
    private Screen<T> currentScreen;
    private Screen<T>.ScreenState currentScreenState;
    private ErrorHandler errorHandler;
    
    /**
     * Creates a new instance with the given container in which screens will render their content.
     * 
     * @param screenContainer       The container in which screens will render their content.
     * @param defaultScreen         The default screen to render if no screen ID is specified in the URL.
     * @param errorHandler          The class that will handle errors arising form this navigator.
     */
    
    public Navigator(Element screenContainer, Screen<T> defaultScreen, ErrorHandler errorHandler)
    {
        this.screenContainer = screenContainer;
        this.defaultScreen = defaultScreen;
        this.errorHandler = errorHandler;
    }
    
    /**
     * Initialises this instance, and if specified, immediately navigates based on the current URL.
     * Once this is called, any manual change to the URL by a user (including using 'Back' and 'Forward' in their browser), 
     * will trigger the navigator to handle the new URL and navigate accordingly.
     * This should only be called once.
     * 
     * @param screenContainer       The container in which screens will render their content.
     * @param defaultScreen         The default screen to render if no screen ID is specified in the URL.
     * @param errorHandler          The class that will handle errors arising form this navigator.
     */
    
    public void initialise(T context, boolean navigate)
    {
        this.context = context;
        
        History.addValueChangeHandler(new ValueChangeHandler<String>(){
            public void onValueChange(ValueChangeEvent<String> event)
            {
                handleToken(History.getToken());
            }
        });
        
        if(navigate)
        {
            handleToken(History.getToken());
        }
    }
    
    private void handleToken(String token)
    {
        String[] split = token.split(TOKEN_SEPARATOR);
        List<StringPair> argList = new ArrayList<StringPair>();
        String screenId = null;
        
        for(String entity: split)
        {
            if(!entity.contains(VALUE_SEPARATOR))
            {
                continue;
            }
            
            String[] entitySplit = entity.split(VALUE_SEPARATOR);
            String arg = entitySplit[0];
            String value = entitySplit[1];
            
            if(arg.equals(SCREEN_ARG))
            {
                screenId = value;
            }
            else
            {
                argList.add(new StringPair(arg, value));
            }
        }
        
        StringPair[] args = argList.toArray(new StringPair[argList.size()]);
        
        if(screenId==null)
        {
            navigate(defaultScreen, args);
        }
        else
        {
            renderScreen(screenId, args);
        }
    }
    
    /**
     * Render the screen specified, with the specified args.
     * Updates the URL of the browser.
     * 
     * @param screen                The screen to render.
     * @param args                  The args to supply to the screen state.
     */
    
    public void navigate(Screen<T> screen, StringPair... args)
    {
        navigate(screen.getId(), args);
    }
    
    /**
     * Render the screen specified, with the specified args.
     * Updates the URL of the browser.
     * 
     * @param screen                The ID of the screen to render.
     * @param args                  The args to supply to the screen state.
     */
    
    public void navigate(String screenId, StringPair... args)
    {
        History.newItem(buildURL(screenId, args), true);
    }
    
    /**
     * Updates the URL in the browser to reflect the current state. Does not trigger a navigation.
     * 
     * @param screen                The screen to render.
     * @param args                  The args to supply to the screen state.
     */
    
    public void updateURL(String screenId, StringPair... args)
    {
        History.newItem(buildURL(screenId, args), false);
    }
    
    private String buildURL(String screenId, StringPair... args)
    {
        StringBuilder b = new StringBuilder();
        
        for(StringPair arg: args)
        {
            b.append("/").append(arg.getKey()).append(VALUE_SEPARATOR).append(arg.getValue());
        }
        
        return SCREEN_ARG+"="+screenId+b.toString();
    }
    
    private void renderScreen(String screenId, StringPair... args)
    {
        renderScreen(getScreen(screenId), args);
    }
    
    private void renderScreen(Screen<T> screen, StringPair... args)
    {
        if(screen==null)
        {
            errorHandler.handleError(new Exception("Screen does not exist."));
            return;
        }
//        if(currentScreen!=null && screen.equals(currentScreen))
//        {
//            currentScreenState.onArgsChanged(args);
//            return;
//        }
        if(currentScreenState!=null)
        {
            currentScreenState.disposeScreen();
        }
        
        screenContainer.clear();
        currentScreen = screen;
        currentScreenState = screen.createState();
        
        try
        {
            currentScreenState.renderScreen(context, screenContainer, args);
        }
        catch(Exception e)
        {
            errorHandler.handleError(e);
        }
    }
    
    /**
     * Implementations must override this method to supply the navigator with the Screen relating to the given ID.
     * 
     * @param screen                The ID of the requested Screen.
     * @return                      The screen referenced by the specified ID.
     */
    
    protected abstract Screen<T> getScreen(String id);
}