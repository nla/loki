package loki.ui.client.screen;

import loki.StringPair;
import loki.ui.Element;
import loki.ui.client.ClientContext;

/**
 * Implementations logically separated rendering code that replace the content of the {@link loki.client.Navigator} screen container.
 * Each screen is treated like a page, even though technically the application is a single-page application, URL components can be used
 * to 'navigate' to different sections of the application, represented as screens.
 */

public abstract class Screen<T extends ClientContext>
{
    private String id;
    
    /**
     * Creates a new instance with the given ID. The ID is used in the URL to navigate to the screen.
     * 
     * @param id    The ID of the screen.
     */
    
    public Screen(String id)
    {
        this.id = id;
    }
    
    /**
     * Gets the ID of this screen.
     * 
     * @return    The ID of the screen.
     */
    
    public String getId()
    {
        return id;
    }
    
    /**
     * This is called by the application's {@link loki.client.Navigator}.
     * 
     * Implementations should override this method to create a unique instance of a ScreenState that renders the screen and
     * controls the UI elements for it. The state can open connections, keep data in memory, etc... and will be disposed when
     * the user navigates to a different screen.
     * 
     * @return    a unique ScreenState instance.
     */
    
    public abstract ScreenState createState();
    
    public abstract class ScreenState
    {
        /**
         * This is called by the application's {@link loki.client.Navigator} to render the screen.
         * 
         * Implementations should override this method to render the UI for this screen and add it to the container.
         * The given args can be used to perform arg-specific actions.
         * 
         * The implementation can throw an Exception if something goes wrong.
         * 
         * @param context       The web-app's context. This could contain useful methods or objects to be used by the screen state.
         * @param container     The container to which the UI should be rendered.
         * @param args          The args (if any) that were specified when navigating to this screen.
         */
        
        public abstract void renderScreen(T context, Element container, StringPair... args) throws Exception;
        
        /**
         * This is called by the application's {@link loki.client.Navigator} when a user navigates away from this screen.
         * 
         * Implementations should override this method to dispose of any resources or connections that might be in use.
         */
        
        public abstract void disposeScreen();
    }
}