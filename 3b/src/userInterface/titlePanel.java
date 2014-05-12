package userInterface;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;


public class titlePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public titlePanel() {
		setBackground( new Color(193, 4, 53) );
		setLayout(new BorderLayout(0, 0));
		
		JLabel pccLogo = new JLabel("");
		pccLogo.setHorizontalAlignment(SwingConstants.CENTER);
		pccLogo.setIcon(new ImageIcon(titlePanel.class.getResource("/resources/logo-header-white.png")));
		add(pccLogo);
		
		JPanel topBlackBorder = new JPanel();
		FlowLayout fl_topBlackBorder = (FlowLayout) topBlackBorder.getLayout();
		fl_topBlackBorder.setVgap(20);
		add(topBlackBorder, BorderLayout.NORTH);
		topBlackBorder.setBackground( new Color(33, 33, 33) );
		
		JPanel botBlackBorder = new JPanel();
		FlowLayout fl_botBlackBorder = (FlowLayout) botBlackBorder.getLayout();
		fl_botBlackBorder.setVgap(20);
		add(botBlackBorder, BorderLayout.SOUTH);
		botBlackBorder.setBackground( new Color(33, 33, 33) );
		
	}

}
