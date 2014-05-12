package userInterface;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import scheduler.XMLwriter;

public class ConnectionPopup  extends JDialog
								implements ActionListener, PropertyChangeListener {


	   private String typedText = null;
	    private JTextField dbLocation, dbUser;
	    private JPasswordField dbPass;
	    private JCheckBox save;
	    private guiFrame parent;
	    private XMLwriter db;

	    private JOptionPane optionPane;

	    private String btnString1 = "Enter";
	    private String btnString2 = "Cancel";
	   

	    /**
	     * Returns null if the typed string was invalid;
	     * otherwise, returns the string as the user entered it.
	     */
	    public String getValidatedText() {
	        return typedText;
	    }

	    /** Creates the reusable dialog. */
	    public ConnectionPopup(Frame aFrame, XMLwriter dbData) {
	        super(aFrame, true);
	        this.parent = (guiFrame) aFrame;

	        db = dbData;
	        setTitle("Db info");

	        dbLocation = new JTextField("jdbc:mysql://localhost:3306/schedule");
	        dbUser = new JTextField("root");
	        dbPass = new JPasswordField();
	        save = new JCheckBox();

	        //Create an array of the text and components to be displayed.
	        String msgString1 = "Enter the locaion and Port of your database: ";
	        String msgString11 = "eg jdbc:mysql://localhost:3306/schedule";
	        String msgString2 = "User Name: ";
	        String msgString3 = "Password: ";
	        String checkBoxString = "Save db connection info, warning Unencrypted.";
	        Object[] array = {msgString1,msgString11, dbLocation, msgString2, dbUser, msgString3, dbPass, checkBoxString, save};

	        //Create an array specifying the number of dialog buttons
	        //and their text.
	        Object[] options = {btnString1, btnString2};

	        //Create the JOptionPane.
	        optionPane = new JOptionPane(array,
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]);

	        //Make this dialog display it.
	        setContentPane(optionPane);

	        Dimension d = new Dimension(450,300);
	        setSize(d);
	        setPreferredSize(d);
	        setMaximumSize(d);
	        setMinimumSize(d);
	        //Handle window closing correctly.
	        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	        addWindowListener(new WindowAdapter() {
	                public void windowClosing(WindowEvent we) {
	                /*
	                 * Instead of directly closing the window,
	                 * we're going to change the JOptionPane's
	                 * value property.
	                 */
	                    optionPane.setValue(new Integer(
	                                        JOptionPane.CLOSED_OPTION));
	            }
	        });

	        //Ensure the text field always gets the first focus.
	        addComponentListener(new ComponentAdapter() {
	            public void componentShown(ComponentEvent ce) {
	                dbLocation.requestFocusInWindow();
	            }
	        });

	        //Register an event handler that puts the text into the option pane.
	        dbLocation.addActionListener(this);

	        //Register an event handler that reacts to option pane state changes.
	        optionPane.addPropertyChangeListener(this);
	    }

	    /** This method handles events for the text field. */
	    public void actionPerformed(ActionEvent e) {
	    	
	        optionPane.setValue(btnString1);
	    }

	    /** This method reacts to state changes in the option pane. */
	    public void propertyChange(PropertyChangeEvent e) {
	        String prop = e.getPropertyName();

	   
	        if (isVisible()  && (e.getSource() == optionPane)
	        			&& (JOptionPane.VALUE_PROPERTY.equals(prop) ||
	        					JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
	            System.out.println("Property change!");
	            Object value = optionPane.getValue();

	            System.out.println(value);
	            if (value == JOptionPane.UNINITIALIZED_VALUE) {
	                //ignore reset
	                System.out.println("Property change. Reset.");
	                return;
	            }
	            if(value.equals("Enter")){

	            	System.out.println("saving db info");
	            	db.setPort(dbLocation.getText());
	            	db.setUser(dbUser.getText());
	            	db.setPass(new String(dbPass.getPassword()));
	            	
		            //save db info if save is checked
	            	
		            if(save.isSelected()){
		            	try {
		            		System.out.println("printing db info to connect.xml");
							db.connectXML();
						} catch (ParserConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            	
		            }
		            clearAndHide();
		            	//parent.setDbData(db);
	            }
	            else if(value.equals("Cancel")){ //user closed dialog or clicked cancel	  
		              //  System.out.println("Property change. canceled");
		                clearAndHide();
		            }
	            //Reset the JOptionPane's value.
	            //If you don't do this, then if the user
	            //presses the same button next time, no
	            //property change event will be fired.
	            else{
	            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
	            clearAndHide();
	            }
	        }
	        
	    }

	    /** This method clears the dialog and hides it. */
	    public void clearAndHide() {
	    	dbLocation.setText(null);
	    	dbUser.setText(null);
	    	dbPass.setText(null);
	        setVisible(false);
	    }
}
