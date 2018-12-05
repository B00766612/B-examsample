package com.gillian;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.*;  //add imports//add imports
import java.sql.Connection;
import java.sql.DriverManager;

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
        layout.setSpacing(true);
        final HorizontalLayout horizontalLayout = new HorizontalLayout();

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
        //setContent(layout);
    
//********************End Database***********************************

//********************Start Components******************** */
    
        Label heading = new Label();
        heading.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);
        heading.setValue("<H1>Marty Party Planners</H1>" +"<p/>"+
        "<h3>Please enter the details below and click Book</h3>");
        
        Label result= new Label();
        result.setValue("Your party is not booked yet");

        Label studentNo = new Label("B00766612");

        final TextField partyName= new TextField("Name of Party");
        partyName.setMaxLength(25);
        partyName.setPlaceholder("");

        Slider people = new Slider("How many people are coming to this party", 0, 500);
        people.setValue(0.0);
        people.setWidth(people.getMax()+"px");
    
        people.addValueChangeListener(e ->{
                double noPeople = people.getValue();
        });
        
        ComboBox <String> children = new ComboBox<String>("Children Attending?");
        children.setItems("Yes", "No");

        Button button = new Button("BOOK");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + partyName.getValue() 
                    + ", it works!"));
        });
        //************ End of Components */
        //****** Add Components to Layout */
        horizontalLayout.addComponents(partyName, people, children);
        layout.addComponents(heading, horizontalLayout, button, result, studentNo);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
