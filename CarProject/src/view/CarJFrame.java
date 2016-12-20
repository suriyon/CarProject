package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CarDAO;
import model.Car;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CarJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtBrand;
	private JTextField txtModel;
	private JTextField txtPrice;
	private JButton btnInsert;
	private JComboBox cmbColor;
	private JButton btnUpdate;
	private JButton btnSearch;
	private JButton btnClose;
	private JButton btnDelete;
	private JPanel panel_3;
	private JTextField txtSearch;
	private JButton btnSearchBrand;
	private JScrollPane scrollPane;
	
	private JTable table;
	private DefaultTableModel dtm;
	private int rowSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarJFrame frame = new CarJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CarJFrame() {		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CarJFrame.class.getResource("/image32/car.png")));
		setTitle("Car JFrmae");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Car Detail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 149);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(22, 30, 26, 14);
		panel.add(lblId);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(58, 27, 35, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblBrand = new JLabel("brand");
		lblBrand.setBounds(162, 30, 46, 14);
		panel.add(lblBrand);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(207, 27, 179, 20);
		panel.add(txtBrand);
		txtBrand.setColumns(10);
		
		JLabel lblModel = new JLabel("model");
		lblModel.setBounds(22, 69, 46, 14);
		panel.add(lblModel);
		
		txtModel = new JTextField();
		txtModel.setBounds(58, 66, 131, 20);
		panel.add(txtModel);
		txtModel.setColumns(10);
		
		JLabel lblColor = new JLabel("color");
		lblColor.setBounds(207, 69, 46, 14);
		panel.add(lblColor);
		
		JLabel lblPrice = new JLabel("price");
		lblPrice.setBounds(22, 113, 46, 14);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(58, 110, 131, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		cmbColor = new JComboBox();
		cmbColor.setModel(new DefaultComboBoxModel(new String[] {"WHITE", "BLACK", "RED", "YELLOW", "GRAY", "BLUE"}));
		cmbColor.setBounds(246, 66, 140, 20);
		panel.add(cmbColor);
		
		btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				CarDAO dao = new CarDAO();
				
				Car car = dao.selectById(id);
				
				txtBrand.setText(car.getBrand());
				txtModel.setText(car.getModel());
				txtPrice.setText(car.getPrice() + "");
				
				switch(car.getColor()){
				case "WHITE" : cmbColor.setSelectedIndex(0);break;
				case "BLACK" : cmbColor.setSelectedIndex(1);break;
				case "RED" : cmbColor.setSelectedIndex(2);break;
				case "YELLOW" : cmbColor.setSelectedIndex(3);break;
				case "GRAY" : cmbColor.setSelectedIndex(4);break;
				case "BLUE" : cmbColor.setSelectedIndex(5);break;
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(CarJFrame.class.getResource("/image16/magnifier.png")));
		btnSearch.setBounds(103, 21, 35, 26);
		panel.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 171, 414, 69);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnInsert = new JButton("Insert");
		btnInsert.setIcon(new ImageIcon(CarJFrame.class.getResource("/image16/add.png")));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String brand = txtBrand.getText();
				String model = txtModel.getText();
				String color = cmbColor.getSelectedItem().toString();
				int price = Integer.parseInt(txtPrice.getText());
				
				Car car = new Car(brand, model, color, price);
				
				CarDAO dao = new CarDAO();
				boolean result = dao.insert(car);
				
				if(result){
					JOptionPane.showMessageDialog(null, "Insert Successfully.");
					addDataToTable();
				}else{
					JOptionPane.showMessageDialog(null, "Insert Fail.");
				}
				
				clearInput();
				disableButton();
			}
		});
		btnInsert.setBounds(15, 23, 82, 29);
		panel_1.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.setIcon(new ImageIcon(CarJFrame.class.getResource("/image16/update.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brand = txtBrand.getText();
				String model = txtModel.getText();
				String color = cmbColor.getSelectedItem().toString();
				int price = Integer.parseInt(txtPrice.getText());
				int id = Integer.parseInt(txtId.getText());
				
				Car car = new Car(id, brand, model, color, price);
				
				CarDAO dao = new CarDAO();
				boolean result = dao.update(car);
				
				if(result){
					JOptionPane.showMessageDialog(null, "Update Successfully.");
					addDataToTable();
				}else{
					JOptionPane.showMessageDialog(null, "Update Fail.");
				}
				
				clearInput();
				txtId.setEnabled(false);	
				disableButton();
			}
		});
		btnUpdate.setBounds(112, 23, 92, 29);
		panel_1.add(btnUpdate);
		
		btnClose = new JButton("Close");
		btnClose.setIcon(new ImageIcon(CarJFrame.class.getResource("/image16/cancel.png")));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setBounds(317, 23, 80, 29);
		panel_1.add(btnClose);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon(CarJFrame.class.getResource("/image16/delete.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				
				CarDAO dao = new CarDAO();
				boolean result = dao.delete(id);
				
				if(result){
					JOptionPane.showMessageDialog(null, "Delete Successfully.");
					addDataToTable();
				}else{
					JOptionPane.showMessageDialog(null, "Delete Fail.");
				}
				
				clearInput();
				txtId.setEnabled(false);
				disableButton();
			}
		});
		btnDelete.setBounds(219, 23, 83, 29);
		panel_1.add(btnDelete);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 311, 414, 128);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 394, 93);
		panel_2.add(scrollPane);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 246, 414, 62);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		btnSearchBrand = new JButton("Search");
		btnSearchBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText();
				addDataToTable(search);
			}
		});
		btnSearchBrand.setIcon(new ImageIcon(CarJFrame.class.getResource("/image16/magnifier.png")));
		btnSearchBrand.setBounds(304, 16, 89, 29);
		panel_3.add(btnSearchBrand);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(100, 20, 193, 20);
		panel_3.add(txtSearch);
		
		JLabel lblEnterBrand = new JLabel("enter brand");
		lblEnterBrand.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterBrand.setBounds(10, 26, 94, 14);
		panel_3.add(lblEnterBrand);
		
		prepareTable();
		addDataToTable();
	}

	protected void addDataToTable(String search) {
		if(dtm.getRowCount() > 0){
			clearDataTable();
		}
		CarDAO dao = new CarDAO();
		Vector cars = dao.selectCarByBrand(search);
		for(int i=0; i<cars.size(); i++){
			dtm.addRow((Vector) cars.get(i));
		}
	}

	protected void clearInput() {
		txtId.setText("");
		txtBrand.setText("");
		txtModel.setText("");
		txtPrice.setText("");
		cmbColor.setSelectedIndex(0);
		txtSearch.setText("");
	}
	
	public void prepareTable(){
		dtm = new DefaultTableModel();
		dtm.addColumn("id");
		dtm.addColumn("brand");
		dtm.addColumn("model");
		dtm.addColumn("color");
		dtm.addColumn("price");
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				rowSelected = table.getSelectedRow();
				txtId.setText(table.getValueAt(rowSelected, 0).toString());
				txtBrand.setText(table.getValueAt(rowSelected, 1).toString());
				txtModel.setText(table.getValueAt(rowSelected, 2).toString());
				txtPrice.setText(table.getValueAt(rowSelected, 4).toString());
				String color = table.getValueAt(rowSelected, 3).toString();
				
				enableButton();
				
				int size = cmbColor.getModel().getSize();
//				JOptionPane.showMessageDialog(null, "" + size);
				for(int i=0; i<size; i++){
					if(color.equals(cmbColor.getModel().getElementAt(i))){
						cmbColor.setSelectedIndex(i);
						return;
					}
				}	
								
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.setFillsViewportHeight(true);

		scrollPane.add(table);
		scrollPane.setViewportView(table);
	}
	
	protected void enableButton() {
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
		
		btnInsert.setEnabled(false);		
	}

	protected void disableButton() {
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		
		btnInsert.setEnabled(true);		
	}
	
	public void addDataToTable(){
		if(dtm.getRowCount() > 0){
			clearDataTable();
		}
		CarDAO dao = new CarDAO();
		Vector cars = dao.selectAll();
		for(int i=0; i<cars.size(); i++){
			dtm.addRow((Vector) cars.get(i));
		}
	}
	
	public void clearDataTable(){
		if(dtm.getRowCount() > 0){
			int row = dtm.getRowCount();
			for(int i=0;i<row;i++){
				dtm.removeRow(0);
			}
		}
	}
}
