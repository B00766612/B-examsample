package com.gillian;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;  //add imports
import java.util.*; //add imports
import java.sql.*;//add imports


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
//********************Database************************************* */
        //add connection String edit relevant
        

        //String connectionString = "jdbc:sqlserver://firstdatagill.database.windows.net:1433;" + 
        //"database=firstdatabasegill;" + 
        //"user=gillian@firstdatagill;" + 
        //"password=Clouddev123;" + 
        //"encrypt=true;" + 
        //"trustServerCertificate=false;" + 
        //"hostNameInCertificate=*.database.windows.net;" +
        //"loginTimeout=30;";

        String connectionString = "jdbc:sqlserver://gillian2.database.windows.net:1433;" + 
        "database=secondtestgill;" + 
        "user=gillian@gillian2;" + 
        "password=Clouddev1;" + 
        "encrypt=true;" + 
        "trustServerCertificate=false;" + 
        "hostNameInCertificate=*.database.windows.net;" +
        "loginTimeout=30;";


         // Create the connection object
        Connection connection = null;  

        try 
        {
            // Connect with JDBC driver to a database
            connection = DriverManager.getConnection(connectionString);
            // Add a label to the web app with the message and name of the database we connected to 
            layout.addComponent(new Label("Connected to database: " + connection.getCatalog()));
        } 
        catch (Exception e) 
        {
            // This will show an error message if something went wrong
            layout.addComponent(new Label(e.getMessage()));
        }
        setContent(layout);
    
//********************End Database***********************************


        
        final TextField name = new TextField();
        name.setCaption("Testing deploy:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
