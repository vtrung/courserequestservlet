
package userInterface;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class OpenPopup  extends JDialog{


	    private guiFrame parent;
//
//	    private String btnString1 = "Enter";
//	    private String btnString2 = "Cancel";
	   

	    /** Creates the reusable dialog. */
	    public OpenPopup(Frame parent) {
	        super(parent, true);
	        this.parent = (guiFrame) parent;

	        setTitle("Open/Import");

	      //Custom button text
	      		Object[] options = {"Cancel",
	      		                    "Import from DB",
	      		                    "Open CSV"};
	      		int n = JOptionPane.showOptionDialog(parent,
	      		    "What would you like to do?",
	      		    "Import/Open",
	      		    JOptionPane.YES_NO_CANCEL_OPTION,
	      		    JOptionPane.QUESTION_MESSAGE,
	      		    null,
	      		    options,
	      		    options[2]);
	      		
	      	System.out.println("result =" + n);
	      	 if(n == 0)
	      		  setVisible(false);//canceled
	      	 else if(n == 1){
	      		((guiFrame) parent).iniDbData();
	      	 } else if(n == 2){
	      		 ((guiFrame) parent).loadCSV();
	      	 }
	      		 
	    }


}
