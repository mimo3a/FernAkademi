package layoutgame;


import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.TitledBorder;

public class LayoutSpiel extends JFrame{
	// die Komponenten
	// funf Schaltflachen für die Ausgabe	
	JButton button1, button2, button3, button4, button5;
	
	// vier Label fur Gridauswahlpanel
	JLabel blabel1, blabel2, blabel3, blabel4;
	//drei Radiobuttons fur die Layoutauswahlpanel und GridAuswahlpanel
	JRadioButton flow, border, grid, gridNullEins, gridZweiZwei;
	// vier ComboBox fur das Gridauswahlpanel
	JComboBox<String> comboButton1, comboButton2, comboButton3, comboButton4;	
	//	Alle Panele
	JPanel generalpanel, changepanel, borderpanel, gridpanel, viewpanel;
	
	// die Methode erzeugt das Panel für die Layoutsarten
	
	public JPanel createChangePanel() {
		
		JPanel tempPanel = new JPanel();
		flow = new JRadioButton("Flow Layout");
		border = new JRadioButton("Border Layout");
		grid = new JRadioButton("Grid Layout");
		flow.setSelected(true);
		
		ButtonGroup gruppe = new ButtonGroup();
		gruppe.add(flow);
		gruppe.add(border);
		gruppe.add(grid);
		
		tempPanel.setLayout(new GridLayout(0,1,10,10));
		tempPanel.setBorder(new TitledBorder("Change the layout"));
		
		tempPanel.add(flow);
		tempPanel.add(border);
		tempPanel.add(grid);
		
		MyListener listener = new MyListener();
		flow.addActionListener(listener);
		border.addActionListener(listener);
		grid.addActionListener(listener);
		
		return tempPanel;
				
	}
	
	// die Methode erzeugt das Panel für den BorderLayout
	
	public JPanel createborderpanel() {
		
		JPanel temPanel = new JPanel();
		// 
		temPanel.setLayout(new GridLayout(4,2,10,10));
		
		blabel1 = new JLabel("button 1");
		blabel2 = new JLabel("button 2");
		blabel3 = new JLabel("button 3");
		blabel4 = new JLabel("button 4");
		
		String comboarray [] = {"NORTH", "SOUTH", "WEST", "EAST", "CENTER"};
		comboButton1 = new JComboBox<String>(comboarray);
		comboButton2 = new JComboBox<String>(comboarray);
		comboButton3 = new JComboBox<String>(comboarray);
		comboButton4 = new JComboBox<String>(comboarray);
		
		temPanel.add(blabel1);
		temPanel.add(comboButton1);
		temPanel.add(blabel2);
		temPanel.add(comboButton2);
		temPanel.add(blabel3);
		temPanel.add(comboButton3);
		temPanel.add(blabel4);
		temPanel.add(comboButton4);
		MyListener listener = new MyListener();
		comboButton1.setActionCommand("button1");
		comboButton1.addActionListener(listener);
		comboButton2.setActionCommand("button2");
		comboButton2.addActionListener(listener);
		comboButton3.setActionCommand("button3");
		comboButton3.addActionListener(listener);
		comboButton4.setActionCommand("button4");
		comboButton4.addActionListener(listener);
		
		
		setEnableBorder(false);
		
		temPanel.setBorder(new TitledBorder("Change BorderLayout"));
		temPanel.setEnabled(false);		
		
		return temPanel;
		
	}
	
	// die Methode erzeugt das Panel  zum Auswählen der Anzahl der Zeilen
	//	und Spalten Gridpanels
	
	public JPanel creategridpanel() {
		JPanel temPanel = new JPanel();
		
		gridNullEins = new JRadioButton("(0, 1)");
		gridZweiZwei = new JRadioButton("(2,2)");
		
		temPanel.setLayout(new GridLayout(0,1));
		temPanel.setBorder(new TitledBorder("Grid"));
		
		ButtonGroup gruppe = new ButtonGroup();
		gruppe.add(gridNullEins);
		gruppe.add(gridZweiZwei);
		
		temPanel.add(gridNullEins);
		temPanel.add(gridZweiZwei);
		
		setEnableGrid(false);
		
		return temPanel;
	}
	
	//die Methode zum Erstellen eines Ausgabebereichs	
	
	public JPanel createviewpanel() {
		
		JPanel temPanel = new JPanel();						
		temPanel.setBorder(new TitledBorder("view panel"));
		temPanel.setPreferredSize(new Dimension(250,150));
		
		return temPanel;
		}
	
	// die Methode macht die Panel erreichbar oder nicht
	public void setEnableBorder(Boolean bool) {
		
		comboButton1.setEnabled(bool);
		comboButton2.setEnabled(bool);
		comboButton3.setEnabled(bool);
		comboButton4.setEnabled(bool);
	}
	// die Methode macht die Panel erreichbar oder nicht
	public void setEnableGrid(Boolean bool) {
		gridNullEins.setEnabled(bool);
		gridZweiZwei.setEnabled(bool);
	}
	//Die Methode löscht zuerst das Panel, legt dann das BorderLayout 
	//dafür fest und fügt die Elemente hinzu aktualisiert dann die Layuts von general 
	//und view Panels
	public void setBorderLayout() {
		viewpanel.removeAll();
		viewpanel.repaint();
		viewpanel.setLayout(new BorderLayout());
		viewpanel.setPreferredSize(new Dimension(250,150));
								
		viewpanel.add(new JButton("button1"), BorderLayout.NORTH);
		viewpanel.add(new JButton("button2"), BorderLayout.SOUTH);
		viewpanel.add(new JButton("button3"), BorderLayout.NORTH);
//		viewpanel.add(new JButton("button4"), BorderLayout.EAST);
//		viewpanel.add(new JButton("button5"), BorderLayout.CENTER);					
				
		generalpanel.doLayout();
		viewpanel.doLayout();
//		viewpanel.removeAll();
	}
	//Die Methode löscht zuerst das Panel, legt dann das FlowLayout 
	//dafür fest und fügt die Elemente hinzu, aktualisiert dann die Layuts von general 
	//und view Panels
	
	public void setFlowLayout() {
		
		viewpanel.removeAll();
		viewpanel.repaint();
		viewpanel.setLayout(new FlowLayout());		
		viewpanel.setPreferredSize(new Dimension(250,150));
		
		for(int i = 1; i <= 5; i++ ) 
			viewpanel.add(new JButton("button " + i));
		
		generalpanel.doLayout();
		viewpanel.doLayout();
		
		
		
	}
	class MyListener implements ActionListener, ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			
				
			}
			
			
		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object ausloeser = e.getSource();
			if(ausloeser instanceof JRadioButton)
				if(flow.isSelected()) {
					setEnableBorder(false);
					setEnableGrid(false);
					setFlowLayout();
				}
				if(border.isSelected()) {
					setEnableBorder(true);
					setEnableGrid(false);
					setBorderLayout();
															
				}
				if(grid.isSelected()) {
					setEnableBorder(false);
					setEnableGrid(true);
				}
			if(ausloeser instanceof JComboBox) {
				if(e.getActionCommand().equals("button1")) {
					int selectedIndex = ((JComboBox<String>) ausloeser).getSelectedIndex();
					
					if (selectedIndex == 0)
						System.out.println("1 North");
					if(selectedIndex == 1)
						System.out.println("1 South");
					if(selectedIndex == 2)
						System.out.println("1 West");
					if(selectedIndex == 3)
						System.out.println("1 East");
					if(selectedIndex == 4)
						System.out.println("1 Center");
				}
				if(e.getActionCommand().equals("button2")) {
					int selectedIndex = ((JComboBox<String>) ausloeser).getSelectedIndex();
					
					if (selectedIndex == 0)
						System.out.println("2 North");
					if(selectedIndex == 1)
						System.out.println("2 South");
					if(selectedIndex == 2)
						System.out.println("2 West");
					if(selectedIndex == 3)
						System.out.println("2 East");
					if(selectedIndex == 4)
						System.out.println("2 Center");
				}
				if(e.getActionCommand().equals("button3")) {
					int selectedIndex = ((JComboBox<String>) ausloeser).getSelectedIndex();
					
					if (selectedIndex == 0)
						System.out.println("3 North");
					if(selectedIndex == 1)
						System.out.println("3 South");
					if(selectedIndex == 2)
						System.out.println("3 West");
					if(selectedIndex == 3)
						System.out.println("3 East");
					if(selectedIndex == 4)
						System.out.println("3 Center");
				}
				if(e.getActionCommand().equals("button4")) {
					int selectedIndex = ((JComboBox<String>) ausloeser).getSelectedIndex();
					
					if (selectedIndex == 0)
						System.out.println("4 North");
					if(selectedIndex == 1)
						System.out.println("4 South");
					if(selectedIndex == 2)
						System.out.println("4 West");
					if(selectedIndex == 3)
						System.out.println("4 East");
					if(selectedIndex == 4)
						System.out.println("4 Center");
				}
			}
				
		}
}
		
	
	

	public LayoutSpiel(String title) {
		
		super(title);
		
		generalpanel = new JPanel();
		changepanel = createChangePanel();
		borderpanel = createborderpanel();
		gridpanel = creategridpanel();
		viewpanel = createviewpanel();
		setFlowLayout();
		
		generalpanel.add(changepanel);
		generalpanel.add(borderpanel);
		generalpanel.add(gridpanel);
		generalpanel.add(viewpanel);
		
		
		
		
		
		setSize(950,200);
		
		add(generalpanel);

		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		new LayoutSpiel("Layout Spiel");
	}

}
