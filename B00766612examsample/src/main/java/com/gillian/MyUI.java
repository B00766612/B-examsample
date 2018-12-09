package com.gillian;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;  //add imports//add imports
import java.sql.*;
import java.util.*;
import com.vaadin.ui.Grid.SelectionMode;

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

//********************Start loading Database and convert to Grid************************************* */

        //add connection String edit relevant
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

        //start try
        try 
        {
            // Connect with JDBC driver to a database
            connection = DriverManager.getConnection(connectionString);
            // Add a label to the web app with the message and name of the database we connected to 
            layout.addComponent(new Label("Connected to database: " + connection.getCatalog()));

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM PartyRooms;");
            // Convert the resultset that comes back into a List -
            // we need a Java class to represent the data (Party.java in this case)
            List<Party> partys = new ArrayList<Party>();
    
            // While there are more records in the resultset
            while(rs.next()){   
            // Add a new Customer instantiated with the fields from the record 
            //(that we want, we might not want all the fields
	        partys.add(new Party(rs.getString("roomName"), 
				rs.getInt("maxPeople"), 
				rs.getString("alcohol"),
                rs.getString("Feature")));

            } //end while

        // Add my component, grid is templated
        Grid<Party> partyGrid = new Grid<>();

        // Set the items (List)
        partyGrid.setItems(partys);
        // Configure the order and the caption of the grid
        partyGrid.addColumn(Party::getPartyName).setCaption("Room");
        partyGrid.addColumn(Party::getPeople).setCaption("Capacity");
        partyGrid.addColumn(Party::getFeature).setCaption("Feature");
        partyGrid.addColumn(Party::getAlcohol).setCaption("Alcohol Allowed");

        partyGrid.setSizeFull(); // This makes the grid the width of the page
        // This makes the grid 'multi-select', adds the checkboxes for selecting to the side
        partyGrid.setSelectionMode(SelectionMode.MULTI);
        //Set <Party> selected= partyGrid.getSelectedItems();
        partyGrid.addSelectionListener( e ->{
        Set <Party> selected= e.getAllSelectedItems();});
        
    
//********************End Grid/ Database***********************************

//********************Start other Components******************** */
    
        Label heading = new Label();
        heading.setContentMode(com.vaadin.shared.ui.ContentMode.HTML); //HTML
        heading.setValue("<H1>Marty Party Planners</H1>" +"<p/>"+
        "<h3>Please enter the details below and click Book</h3>");
        
        Label result= new Label("Your party is not booked yet", ContentMode.HTML);

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
            Boolean bookable= true;  //is it bookable?
            //check grid selection
            if(partyGrid.getSelectedItems().size() == 0)

                {
                    bookable=false; //not bookable
                    result.setValue("<strong>Please select at least one room!</strong>");
                    return;
                }
            //check party name entried
                if(partyName.getValue().length() == 0)
                {
                    bookable=false;
                    result.setValue("<strong>Please enter party name.</strong>");
                    return;
                }
            //check children selection
            if(!children.getSelectedItem().isPresent())
                {
                    bookable=false;
                    result.setValue("<strong>Please confirm if children attending your party</strong>");
                    return;
                }
    
            //check children/alcohol
            for(Party o : selected){
                if ((o.getAlcohol() == "Yes")&&(children.getValue()=="Yes"))
                { 
                    bookable=false;
                   result.setValue("<strong> You cannot select any rooms serving alcohol if children are attending.</strong>");
                    return;
                }
            } //for
            
                
                //if(children.getValue().equals("yes")&& Party.getAlcohol().equals("yes"))
                
            //check capacity of room
            for(Party o : selected){
                if ((o.getPeople())>people.getValue())
                { 
                        bookable=false;
                        result.setValue("<strong>You have selected rooms with a max capacity of 100 which is not enough to hold 300</strong>");
                        return;
                 }
                }//for

                //all ok
            if (bookable==true)
            {
                result.setValue("<h3>Success! The party is booked now</h3>");
                return;
            }


            layout.addComponent(new Label("Thanks " + partyName.getValue() 
                    + ", it works!"));
        });
        //************ End of other Components and calculations******* */

        //------------ Add Components to Layout ------------------------/
        horizontalLayout.addComponents(partyName, people, children);
        layout.addComponents(heading, horizontalLayout, button, result, partyGrid, studentNo);
        setContent(layout);
        //----------------------------------------------------------------
    
} //end try
catch (Exception e) 
{
    // This will show an error message if something went wrong
    layout.addComponent(new Label(e.getMessage()));
    setContent(layout);
} //end catch

}//class

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
