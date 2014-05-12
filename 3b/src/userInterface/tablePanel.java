package userInterface;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import Classes.Course;

interface TableListener {
	public Course addToTable();
	public void remove(Course c);
}
public class tablePanel extends JPanel implements ActionListener {
	private JTable table;
	private TableModel mModel;
	private TableRowSorter sorter;
	private guiFrame mListener;
	private JButton add, remove, swap;
	private JLabel status;
	/**
	 * Create the panel.
	 */

	public JTable getTable(){
		return table;
	}
	public void sort(String[] s){
		System.out.println("flitering for : " + s);
		 List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(s.length);
		   for(int i=0; i<s.length; i++){
			   System.out.println(i + " : " + s[i]);
			   filters.add(RowFilter.regexFilter(s[i]));
		   }
		   RowFilter<Object,Object> tableFilters = RowFilter.andFilter(filters);
		   sorter.setRowFilter(tableFilters);
		//sorter.setRowFilter(RowFilter.regexFilter(s));
	}
	public tablePanel(guiFrame Listener, TableModel model) {
		mListener = Listener;
		mModel = model;
		setBackground( new Color(193, 4, 53) );
		this.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		table = new JTable();
		table.setRowHeight(18);
		table.setRowMargin(3);
		
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int width = ((int) tk.getScreenSize().getWidth());  
		int height = ((int) tk.getScreenSize().getHeight());  
		width = (int) (.8 * width);
		Dimension d = new Dimension(width, height- 50);
		table.setPreferredScrollableViewportSize(d);
		d = new Dimension(width, height);
		scrollPane.setPreferredSize(d);
		scrollPane.setMaximumSize(d);
		scrollPane.setMinimumSize(d);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setRowHeight(24);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setModel(model);
		sorter = new TableRowSorter(model);
		table.setRowSorter(sorter);

		scrollPane.setViewportView(table);
		add(scrollPane, BorderLayout.CENTER);
	
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground( new Color(193, 4, 53) );
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.PAGE_AXIS));
		 d = new Dimension(width, 80);
		 bottomPanel.setSize(d);
		 bottomPanel.setPreferredSize(d);
		 bottomPanel.setMaximumSize(d);
		 bottomPanel.setMinimumSize(d);
		 JPanel buttonPanel = new JPanel();
		 d = new Dimension(width, 50);
		 buttonPanel.setSize(d);
		 buttonPanel.setPreferredSize(d);
		 buttonPanel.setMaximumSize(d);
		 buttonPanel.setMinimumSize(d);
		buttonPanel.setBackground( new Color(193, 4, 53) );
		 ImageIcon addIcon = new ImageIcon(tablePanel.class.getResource("/resources/3b_01.png"));
		 ImageIcon removeIcon = new ImageIcon(tablePanel.class.getResource("/resources/3b_02.png"));
		 ImageIcon swapIcon = new ImageIcon(tablePanel.class.getResource("/resources/3b_03.png"));
		 
		add = new JButton("Add", addIcon);
		remove = new JButton("Remove", removeIcon);
		swap = new JButton("Swap Courses", swapIcon);
		status = new JLabel("ready...");
		status.setFont(new Font("Serif", Font.BOLD, 16));
		status.setForeground(Color.WHITE);
		add.addActionListener(this);
		remove.addActionListener(this);
		swap.addActionListener(this);
		buttonPanel.add(add);
		buttonPanel.add(remove);
		buttonPanel.add(swap);
		
		
		bottomPanel.add(buttonPanel);
		bottomPanel.add(status);
		
		
		add(bottomPanel, BorderLayout.SOUTH);
		

	}
	public void setStatus(String s){
		status.setText(s);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(add)){
			Course c = mListener.addToTable();
			if(c != null)
				mModel.addItem(c);
		} else if(source.equals(remove)){
			if(table.getSelectedRowCount() == 1){
			mListener.remove(mModel.removeItem(table.getSelectedRow()));
			
			}
			else if(table.getSelectedRowCount() < 1)
				status.setText("No Items Selected");
			else if(table.getSelectedRowCount() > 1)
				status.setText("Too Many Items Selected");
			
		} else if(source.equals(swap)){
			if(table.getSelectedRowCount() == 2){
				int[] temp = table.getSelectedRows();
				//mModel.swapInstructors(temp[0],temp[1]);
				mModel.swapCourses(temp[0],temp[1]);
			}
				else if(table.getSelectedRowCount() < 2)
					status.setText("Not Enought Items Selected");
				else if(table.getSelectedRowCount() > 2)
					status.setText("Too Many Items Selected");
		}
		
	}


}
