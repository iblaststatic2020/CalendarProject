import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarProgram {
	 static JLabel lblMonth, lblYear, lblHour;
	 static JButton btnPrev, btnNext, btnSave;
	 static JTable tblCalendar, tblTime;
	 static JComboBox cmbYear;
	 static JFrame frmMain, frmDate;
	 static Container pane;
	 static DefaultTableModel mtblCalendar, mtblTime;
	 static JScrollPane stblCalendar, stblTime;
	 static JPanel pnlCalendar, pnlDay;
	 static int realDay, realMonth, realYear, currentMonth, currentYear;
	 
	 public static void main(String[] args){
		 //Set theme?!?!
		 setTheme();
		 createCalanderView();
		 calandarData();
		 buttonFunctions();
		 mouseControls();
		
		 refreshCalendar(realMonth, realYear);
		 
	 }
	 private static void createDayView() {
		// TODO Auto-generated method stub
		 
		
		 
		 
		 frmDate = new JFrame("Calander Application");
		 lblHour = new JLabel();
		 btnSave = new JButton("Save");
		 pnlDay = new JPanel(null);
		 tblTime = new JTable(mtblCalendar);
		 mtblTime = new DefaultTableModel();
		 stblTime = new JScrollPane(tblTime);
		 
		 frmDate.setSize(330, 375);
		 pane = frmDate.getContentPane();
		 pane.setLayout(null);
		 frmDate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 pane.add(pnlDay);
		 pnlDay.add(lblHour);
		 pnlDay.add(btnSave);
		 pnlDay.add(stblTime);
		 //pnlDay.setBorder(BorderFactory.createTitledBorder("Calendar"));
		 
		 //set bounds
		 pnlDay.setBounds(0, 0, 335, 335);
		 stblTime.setBounds(10, 50, 300, 250);
		 btnSave.setBounds(230, 305, 80, 20);
		 
		 
		tblTime.getParent().setBackground(tblTime.getBackground());
		 //no resize/reorder
		 tblTime.getTableHeader().setResizingAllowed(false);
		 tblTime.getTableHeader().setReorderingAllowed(false);
		 tblTime.setRowHeight(38);
		 mtblTime.setColumnCount(1);
		 mtblTime.setRowCount(48);
		 
		 frmMain.setVisible(false);
		 stblCalendar.getViewport().remove(tblCalendar);
		 stblCalendar.remove(pnlCalendar);
		 stblCalendar.remove(pnlDay);
		 tblCalendar.remove(pnlCalendar);
		 pnlDay.repaint();
		 frmDate.setVisible(true);
		
	}
	private static void mouseControls() {
		// TODO Auto-generated method stub
		 //Make cells clickable
		 tblCalendar.addMouseListener(new MouseAdapter(){
			 @Override
			 public void mouseClicked(MouseEvent e){
				 int row = tblCalendar.rowAtPoint(e.getPoint());
				 int col = tblCalendar.columnAtPoint(e.getPoint());
				 if (row >=0 && col >= 0){
					createDayView();
					
				 }
			 }
		 });
	}
	private static void buttonFunctions() {
		// TODO Auto-generated method stub
		 //methods to set up buttons to do something
		 btnPrev.addActionListener(new btnPrev_Action());
		 btnNext.addActionListener(new btnNext_Action());
		 cmbYear.addActionListener(new cmbYear_Action());
	}
	/*
	  * 
	  */
	 private static void calandarData() {
		// TODO Auto-generated method stub
		//get real month and year
		 GregorianCalendar cal = new GregorianCalendar(); //create calendar
		 realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
		 realMonth = cal.get(GregorianCalendar.MONTH);
		 realYear = cal.get(GregorianCalendar.YEAR);
		 currentMonth = realMonth;//match month and year
		 currentYear = realYear;
		 
		//populate combo box
		 for(int i = realYear - 10; i<=realYear+100; i++){
			 cmbYear.addItem(String.valueOf(i));
		 }
		 //add headers
		 String[] headers = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};// all headers
		 for(int i = 0; i<7; i++){
			 mtblCalendar.addColumn(headers[i]);
		 }
		 tblCalendar.getParent().setBackground(tblCalendar.getBackground());
		 //no resize/reorder
		 tblCalendar.getTableHeader().setResizingAllowed(false);
		 tblCalendar.getTableHeader().setReorderingAllowed(false);
		 
		 //set cell selection
		 tblCalendar.setColumnSelectionAllowed(true);
		 tblCalendar.setRowSelectionAllowed(true);
		 tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 //set row/column count
		 tblCalendar.setRowHeight(38);
		 mtblCalendar.setColumnCount(7);
		 mtblCalendar.setRowCount(6);
		 
		
	}
	/*
	  * set up calendar month gui view
	  */
	 private static void createCalanderView() {
		// TODO Auto-generated method stub
		 frmMain = new JFrame("Calander Application");
		 lblMonth = new JLabel("January");
		 lblYear = new JLabel("Change Year:");
		 cmbYear = new JComboBox();
		 btnPrev = new JButton("<<");
		 btnNext = new JButton(">>");
		 mtblCalendar = new DefaultTableModel();
		 tblCalendar = new JTable(mtblCalendar);// table using the above model
		 stblCalendar = new JScrollPane(tblCalendar);//the scrollpane of the above table
		 pnlCalendar = new JPanel(null);//create the panel to place components
		 
		 //Prepare frame
		 frmMain.setSize(330, 375);
		 pane = frmMain.getContentPane();
		 pane.setLayout(null);
		 frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		//Set border
		 pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
		 
		 //add gui components
		 pane.add(pnlCalendar);
		 pnlCalendar.add(lblMonth);
		 pnlCalendar.add(lblYear);
		 pnlCalendar.add(cmbYear);
		 pnlCalendar.add(btnPrev);
		 pnlCalendar.add(btnNext);
		 pnlCalendar.add(stblCalendar);
		 
		 //set bounds
		 pnlCalendar.setBounds(0, 0, 335, 335);
		 lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
		 lblYear.setBounds(10, 305, 85, 20);
		 cmbYear.setBounds(230, 305, 80, 20);
		 btnPrev.setBounds(10, 25, 70, 25);
		 btnNext.setBounds(260, 25, 70, 25);
		 stblCalendar.setBounds(10, 50, 300, 250);
		 
		 frmMain.setResizable(false);
		 frmMain.setVisible(true);
	}
	/*
	  * This method will set the ui to match the theme on the users computer
	  */
	 private static void setTheme() {
		// TODO Auto-generated method stub
		 try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		 catch (ClassNotFoundException e) {}
		 catch (InstantiationException e) {}
		 catch (IllegalAccessException e) {}
		 catch (UnsupportedLookAndFeelException e) {}
	}

	public static void refreshCalendar(int month, int year){
		 
		 String[] months = {"january", "February", "March", "April", "May", "June", "July",
			 "August", "September", "October", "November", "December"};
			 int nod, som; //number of day, start of month
			 btnPrev.setEnabled(true);
			 btnNext.setEnabled(true);
			 if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);}//Too early
			 if (month == 11 && year >= realYear + 100 ){
				 btnNext.setEnabled(false);
		 }
			 lblMonth.setText(months[month]);
			 lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25);//realign label with calendar
			 cmbYear.setSelectedItem(String.valueOf(year)); //select the correct year in the combo
			 
			 //get first day of month and number of days
			 GregorianCalendar cal = new GregorianCalendar(year, month, 1);
			 nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			 som = cal.get(GregorianCalendar.DAY_OF_WEEK);
			 
			 //clear table
			 for(int i = 0; i < 6; i++){
				 for(int j = 0; j< 7; j++){
					 mtblCalendar.setValueAt(null, i, j);
				 }
			 }
			 //draw calendar
			 for(int i = 1; i<=nod; i++){
				 int row = new Integer((i + som-2)/7);
				 int column = (i + som - 2) % 7 ;
				 mtblCalendar.setValueAt(i, row, column);
			 }
			 
	 }
	 
	 
	 static class btnPrev_Action implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 if (currentMonth == 0){
				 currentMonth = 11;
				 currentYear -= 1;
			 }
			 else{
				 currentMonth -= 1;
			 }
			 refreshCalendar(currentMonth, currentYear);
		 }
	 }
	 static class btnNext_Action implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 if (currentMonth == 11){
				 currentMonth = 0;
				 currentYear += 1;
			 }
			 else{
				 currentMonth += 1;
			 }
			 refreshCalendar(currentMonth, currentYear);
		 }
	 }
	 static class cmbYear_Action implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 if (cmbYear.getSelectedItem() != null){
				 String b = cmbYear.getSelectedItem().toString();
				 currentYear = Integer.parseInt(b);
				 refreshCalendar(currentMonth, currentYear);
			 }
		 }
	 }
}

