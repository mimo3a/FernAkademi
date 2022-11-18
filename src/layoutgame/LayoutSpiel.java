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
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.TitledBorder;

public class LayoutSpiel extends JFrame {
	// die Komponenten
	// funf Schaltflachen für die Ausgabe
	JButton button1, button2, button3, button4, button5;
	//und eine für BorderLayout panel
	JButton goBorderLayout;

	// fünf Label fur Borderpanel
	JLabel blabel1, blabel2, blabel3, blabel4, blabel5;
	//zwei label fur gridPanel
	JLabel glabel1, glabel2;
	// drei Radiobuttons fur die Layoutauswahlpanel
	JRadioButton flow, border,grid; 
	// zwei Konbination feldern fur das GridPanel
	JComboBox reihe, spalte;
	// funf Kuntrolkastchen fur das BorderPanel
	JCheckBox north, south, west, east, center;

	// Alle Panele
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

		tempPanel.setLayout(new GridLayout(0, 1, 10, 10));
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

	// die Methode erzeugt das Panel für das Borderlayoutpanel
	public JPanel createborderpanel() {

		JPanel temPanel = new JPanel();
		temPanel.setLayout(new GridLayout(0, 2, 10, 0));

		blabel1 = new JLabel("button 1");
		blabel2 = new JLabel("button 2");
		blabel3 = new JLabel("button 3");
		blabel4 = new JLabel("button 4");
		blabel5 = new JLabel("button 5");

		north = new JCheckBox("NORTH");
		south = new JCheckBox("SOUTH");
		west = new JCheckBox("WEST");
		east = new JCheckBox("EAST");
		center = new JCheckBox("CENTER");
		
		goBorderLayout = new JButton("Go");

		temPanel.add(blabel1);
		temPanel.add(north);
		temPanel.add(blabel2);
		temPanel.add(south);
		temPanel.add(blabel3);
		temPanel.add(west);
		temPanel.add(blabel4);
		temPanel.add(east);
		temPanel.add(blabel5);
		temPanel.add(center);
		temPanel.add(goBorderLayout);

		MyListener listener = new MyListener();
		north.addActionListener(listener);
		south.addActionListener(listener);
		west.addActionListener(listener);
		east.addActionListener(listener);
		center.addActionListener(listener);
		goBorderLayout.addActionListener(listener);
		goBorderLayout.setActionCommand("go");
	
		temPanel.setBorder(new TitledBorder("Change BorderLayout"));
//		das Panel inaktiv machen
		temPanel.setEnabled(false);

		return temPanel;

	}

	// die Methode erzeugt das Panel zum Auswählen der Anzahl der Zeilen
	// und Spalten Gridpanels

	public JPanel creategridpanel() {
		JPanel temPanel = new JPanel();

		glabel1 = new JLabel("Reihe");
		glabel2 = new JLabel("Spalte");
		String [] tempArr = {"0","1","2","3"};
		reihe = new JComboBox<String>(tempArr);		
		spalte = new JComboBox<String>(tempArr);		

		temPanel.setLayout(new GridLayout(0, 2,0,10));
		temPanel.setBorder(new TitledBorder("Grid"));

		temPanel.add(glabel1);
		temPanel.add(reihe);
		temPanel.add(glabel2);
		temPanel.add(spalte);
		
		MyListener listener = new MyListener();
		reihe.addActionListener(listener);
		spalte.addActionListener(listener);		

		setEnableGrid(false);

		return temPanel;
	}

	// die Methode zum Erstellen eines Ausgabebereichs

	public JPanel createviewpanel() {

		JPanel temPanel = new JPanel();
		temPanel.setBorder(new TitledBorder("view panel"));
		temPanel.setPreferredSize(new Dimension(250, 150));

		return temPanel;
	}

	// die Methode macht das Borderlayoutspanel erreichbar oder nicht
	public void setEnableBorder(Boolean bool) {

		north.setEnabled(bool);
		south.setEnabled(bool);
		west.setEnabled(bool);
		east.setEnabled(bool);
		center.setEnabled(bool);
		goBorderLayout.setEnabled(bool);
		
	}

	// die Methode macht das Gridpanel erreichbar oder nicht
	public void setEnableGrid(Boolean bool) {
		reihe.setEnabled(bool);
		spalte.setEnabled(bool);
	}

	// Die Methode löscht zuerst das Panel, legt dann das BorderLayout
	// dafür fest und fügt die Elemente hinzu aktualisiert dann die Layuts von
	// general
	// und view Panels
	public void setBorderLayout(int [] arr) {
		viewpanel.removeAll();
		viewpanel.repaint();
		viewpanel.setLayout(new BorderLayout());
		viewpanel.setPreferredSize(new Dimension(250, 150));
		
		//überprüfen jeden element von Array und wenn er eins ist 
		// setzen die schaltenflache an die entsprechende Position 
		
		if(arr[0] == 1)
			viewpanel.add(button1, BorderLayout.NORTH);
		if(arr[1] == 1)
			viewpanel.add(button2, BorderLayout.SOUTH);
		if(arr[2] == 1)
			viewpanel.add(button3, BorderLayout.WEST);
		if(arr[3] == 1)
			viewpanel.add(button4, BorderLayout.EAST);
		if(arr[4] == 1)
			viewpanel.add(button5, BorderLayout.CENTER);		

		
		viewpanel.doLayout();

	}
	// Die Methode löscht zuerst das Panel, legt dann das FlowLayout
	// dafür fest und fügt die Elemente hinzu, aktualisiert dann die Layuts von
	// view Panels

	public void setFlowLayout() {

		viewpanel.removeAll();
		viewpanel.repaint();
		viewpanel.setLayout(new FlowLayout());
		viewpanel.setPreferredSize(new Dimension(250, 150));

		viewpanel.add(button1);
		viewpanel.add(button2);
		viewpanel.add(button3);
		viewpanel.add(button4);
		viewpanel.add(button5);
		
		viewpanel.doLayout();

	}
	
	// Die Methode löscht zuerst das Panel, legt dann das GridLayout ensprechend
		//	der Anzahl der übergebenen Zeilen und Spalten
		// dafür fest und fügt die Elemente hinzu, aktualisiert dann die Layuts von
		// view Panels
	public void setGridLayout(int reihe, int spalte) {
		viewpanel.removeAll();
		viewpanel.repaint();
		viewpanel.setLayout(new GridLayout(reihe, spalte));
		viewpanel.setPreferredSize(new Dimension(250,150));		
		 			
			viewpanel.add(button1);
			viewpanel.add(button2);
			viewpanel.add(button3);
			viewpanel.add(button4);
			viewpanel.add(button5);			
			
		viewpanel.doLayout();
			
		
	}

	class MyListener implements ActionListener, ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//wählen das Layout
			Object ausloeser = e.getSource();
			if (ausloeser instanceof JRadioButton) {
				if (flow.isSelected()) {
					setEnableBorder(false);
					setEnableGrid(false);
					setFlowLayout();
				}
				if (border.isSelected()) {
					setEnableBorder(true);
					setEnableGrid(false);
					int [] arr = {0,0,0,0,0};
					setBorderLayout( arr);

				}
				if (grid.isSelected()) {
					setEnableBorder(false);
					setEnableGrid(true);
					//löschen das viewpanel 
					viewpanel.removeAll();
					viewpanel.repaint();
					
					
					reihe.setSelectedIndex(0);
					spalte.setSelectedIndex(0);
					
				}
			}
			if(ausloeser instanceof JComboBox) {
				//nehmen die Anzahl der Zeilen und Spalten und übergeben sie an
				//die Methode setGridLayout
				int reihenMenge = reihe.getSelectedIndex();
				int spalteMenge = spalte.getSelectedIndex();
				
				if (!(reihenMenge == 0 && spalteMenge == 0)) 					
				setGridLayout(reihenMenge, spalteMenge);				
				
			}
			// Wählen Sie aus, welche Elemente ausgewählt sind, und weisen Sie dem entsprechenden
			//Element des Arrays den Wert 1 zu		
			if (e.getActionCommand().equals("go")) {
				int tempArray [] = {0,0,0,0,0};
				if(north.isSelected())
					tempArray[0] = 1;
				if(south.isSelected())
					tempArray[1] = 1;
				if(west.isSelected())
					tempArray[2] = 1;
				if(east.isSelected())
					tempArray[3] = 1;
				if(center.isSelected())
					tempArray[4] = 1;
				
					
				setBorderLayout(tempArray);

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
		
		button1 = new JButton("button 1");
		button2 = new JButton("button 2");
		button3 = new JButton("button 3");
		button4 = new JButton("button 4");
		button5 = new JButton("button 5");
		
		setFlowLayout();

		generalpanel.add(changepanel);
		generalpanel.add(borderpanel);
		generalpanel.add(gridpanel);
		generalpanel.add(viewpanel);
			
		setSize(950, 250);
		add(generalpanel);
		setVisible(true);

	}

	public static void main(String[] args) {

		new LayoutSpiel("Layout Spiel");
	}

}
