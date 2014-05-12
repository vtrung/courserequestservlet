package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import Classes.Course;
import Classes.Instructor;
import Classes.TimeBlock;
import scheduler.CSVTools;
import scheduler.DbConnection;
import scheduler.ImportParser;
import scheduler.XMLwriter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class guiFrame extends JFrame implements SortPanelListener, TableListener, ActionListener, TableModelListener {

	private JPanel contentPane;
	private tablePanel table;
	private XMLwriter dbData;
	private JMenuItem mntmSettings, mntmImport, mntmExport, mntmExit, mntmSave;
	private InstructorTable iTable;
	private CourseTable cTable;
	private TableModel model;
	private InstructorTableModel iModel;
	private CourseTableModel cModel;
	private InfoPanel info;
	private DbConnection db;
	private SortPanel sort;

	public void setDbData(XMLwriter dat){
		dbData = dat;
	}

	/**
	 * Create the frame.
	 */
	public guiFrame() {
		setTitle("ACME Scheduler vBETA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(guiFrame.class.getResource("/resources/pcc.png")));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setMacFriendly();  	// setup ui for for mac friendly. does nothing on windows
		//set frame size to fill screen
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		setSize(xSize,ySize);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(193, 4, 53));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
		
	
	
		
		//iniDbData();
		// db = new DbConnection(dbData);	//connect to database and retrieve request

		model = new TableModel(new ArrayList<Course>());
		//model = new TableModel(getTestData());	//for testing purpose
		//load models and tables
		//model = new TableModel(db.getCourseData());	
		model.addTableModelListener(this);
		table = new tablePanel(this, model);
		iModel = new InstructorTableModel();
		iModel.addTableModelListener(this);
		iTable = new InstructorTable(iModel);
		cModel = new CourseTableModel();
		cModel.addTableModelListener(this);
		cTable = new CourseTable(cModel);
		
		//create and add sections to GUI
		addTableAndTitle();
		createSidePanel(xSize,ySize);
		model.fireTableDataChanged();	//update info panel
		createBottomPanel(xSize);
		createMenuBar();
		
		OpenPopup open = new OpenPopup(this);
		//open.setVisible(true);
		
	}
	
	private void setMacFriendly(){
		// setup ui for for mac friendly. does nothing on windows
		// take the menu bar off the jframe
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		// set the name of the application menu item
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "ACME Scheduler");

		// set the look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public void iniDbData(){
		
		dbData = new XMLwriter();
		try {
			if(dbData.connectParse())
			System.out.println("dbdata found! location: " + dbData.getPort() + " user: " + dbData.getUser() + " pass: " + dbData.getPass());
			else {
				System.out.println("connection.xml file not found. Opening Conection Dialog");
				ConnectionPopup popup = new ConnectionPopup(this, dbData);
				popup.setVisible(true);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = new DbConnection(dbData);
		model.setData(db.getCourseData());
		model.fireTableDataChanged();
		sort.iniPanel(this, db);
		
	}

	private void addTableAndTitle(){
		//add table and title
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridwidth = 5;
				c.gridy = 0;
				c.weightx = .5;
				titlePanel title = new titlePanel();
				contentPane.add(title,c);
				 c = new GridBagConstraints();
				 c.gridx = 0;
				 c.gridy = 1;
				 c.weightx = 1;
				 c.weighty = .8;
				 c.gridwidth = 3;
				 c.insets = new Insets(10, 0, 0, 0);
				contentPane.add(table, c);
	}
	private void createSidePanel(int xSize, int ySize){
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(Color.black);
		Dimension d = new Dimension((int) (xSize*.15), (int) (ySize*.6));
		sidePanel.setSize(d);
		sidePanel.setPreferredSize(d);
		sidePanel.setMinimumSize(d);
		info = new InfoPanel();
		sidePanel.add(info);
		sort = new SortPanel(this, db);	
		sidePanel.add(sort);
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 1;
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 10);
		contentPane.add(sidePanel, c);
	}
	private void createMenuBar(){

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmImport = new JMenuItem("Open/Import");
		mntmImport.addActionListener(this);
		mnFile.add(mntmImport);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(this);
		mnFile.add(mntmSave);
		
		mntmExport = new JMenuItem("Export Completed Courses");
		mntmExport.addActionListener(this);
		mnFile.add(mntmExport);
		
		mntmSettings = new JMenuItem("Db Settings");
		mntmSettings.addActionListener(this);
		mnFile.add(mntmSettings);
		
		//will exit the program
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);
	}
	
	private void createBottomPanel(int xSize) {
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridBagLayout());
		Dimension d = new Dimension((int) (xSize ), 210);
		bottom.setSize(d);
		bottom.setPreferredSize(d);
		bottom.setMinimumSize(d);
		bottom.setBackground(Color.black);
		GridBagConstraints c = new GridBagConstraints();
		 c.gridy = 0;
		 c.gridx = 0;
		 c.gridwidth = 1;
		 c.insets = new Insets(5, 5, 5, 5);
		bottom.add(iTable);
		 c = new GridBagConstraints();
		 c.gridy = 0;
		 c.gridx = 2;
		 c.gridwidth = 3;
		 c.weightx = 1;
		 c.insets = new Insets(5, 0, 5, 5);
		bottom.add(cTable);
		 c = new GridBagConstraints();
		 c.gridy = 2;
		 c.gridx = 0;
		 c.gridwidth = 5;
		 c.insets = new Insets(10, 10, 10, 10);
		contentPane.add(bottom, c);
	}

	@Override
	public void sort(String[] s) {
		table.sort(s);
		
	}

	public void loadCSV(){
		CSVTools.importCSV(model, cModel, iModel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == mntmSettings){
		ConnectionPopup popup = new ConnectionPopup(this, dbData);
		popup.setVisible(true);
		}
		else if(source == mntmImport){
			new OpenPopup(this);
			//CSVTools.importCSV(model, cModel, iModel);
		}else if(source == mntmSave){
			System.out.println("saving tables");
			CSVTools.saveCSV(table.getTable(), cTable.getTable(), iTable.getTable());
		}
		else if(source == mntmExport){
			System.out.println("Exporting tables");
			CSVTools.exportCSV(table.getTable());
		}
		else if(source == mntmExit)
		System.exit(0);	
		
	}

	//called to add a course from subtables to main table
	@Override
	public Course addToTable() {
		if(iTable.getTable().getSelectedRowCount() == 1 && cTable.getTable().getSelectedRowCount() == 1){
			Course temp = cTable.removeItem(cTable.getTable().getSelectedRow());
			temp.setInstructor(iTable.removeItem(iTable.getTable().getSelectedRow()));
			return temp;
		}
		table.setStatus("Select 1 Instructor and 1 Course Below to Add.");
			
		return null;
	}

	//called to remove a course from maintable into subtables
	@Override
	public void remove(Course c) {
		iTable.addItem(c.removeInstructor());
		cTable.addItem(c);
	}
	
	
	public  ArrayList<Course> getTestData(){
		 ArrayList<Course> temp = new ArrayList<Course>();
		 temp.add(new Course("234", "blah",new TimeBlock("sfdk", "fjskfk", "dsfjksd"), new Instructor("Someone", "34343", true), 1, "notes"));
		 temp.add(new Course("23sdfd4", "ttttblah",new TimeBlock("232", "343", "666666"), new Instructor("Someone", "34343", true), 2, "ican't read"));
		 temp.add(new Course("aaa", "333",new TimeBlock("444", "55", "7"), new Instructor("Me", "111", false), 1, "i hate math"));
		
		 return temp;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		String t = "", c = "", i= "";
		
			t += "Completed Courses: " + model.getRowCount();
		
			c += "Unassigned Courses: " + cModel.getRowCount();
		
			i += "Unassigned instructors: " + iModel.getRowCount();
		
		info.setData(t,c,i);
	}

}


