package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

import Classes.Course;
import Classes.Instructor;

public class InstructorTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3414229401892271792L;
	private JTable table;
	private InstructorTableModel model;
	
	public InstructorTable(InstructorTableModel m){
		model = m;
		
		setBackground( new Color(193, 4, 53) );
		this.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		table = new JTable();
		table.setRowHeight(18);
		table.setRowMargin(3);
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int width = ((int) tk.getScreenSize().getWidth());  
		int height = ((int) tk.getScreenSize().getHeight());  
		width = (int) (.25 * width) - 10;
		Dimension d = new Dimension(width, 180);
		table.setPreferredScrollableViewportSize(d);
		d = new Dimension(width, 200);
		scrollPane.setPreferredSize(d);
		if(width > 200)
		scrollPane.setMaximumSize(d);
		else scrollPane.setMaximumSize(new Dimension(200, 190));
		scrollPane.setMinimumSize(new Dimension(200, 190));
		
		setPreferredSize(d);
		if(width > 200)
		setMaximumSize(d);
		else setMaximumSize(new Dimension(200, 200));
		setMinimumSize(new Dimension(200, 200));
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setModel(model);

		scrollPane.setViewportView(table);
		add(scrollPane, BorderLayout.CENTER);
	
	}
	
	public void addItem(Instructor i){
		model.addItem(i);
	}
	public Instructor removeItem(int i){
		return model.removeItem(i);
	}
	
	public Instructor getItem(int i){
		return model.getItem(i);
	}
	
	
	public JTable getTable(){
		return table;
	}
	
}
