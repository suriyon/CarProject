package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import dao.CarDAO;
import model.Car;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

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
	private JButton btnEdit;
	private JButton btnDelete;

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

	/**
	 * Create the frame.
	 */
	public CarJFrame() {
		setTitle("Car JFrmae");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				}else{
					JOptionPane.showMessageDialog(null, "Insert Fail.");
				}
				
				clearInput();
			}
		});
		btnInsert.setBounds(10, 23, 61, 29);
		panel_1.add(btnInsert);
		
		btnUpdate = new JButton("Update");
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
				}else{
					JOptionPane.showMessageDialog(null, "Update Fail.");
				}
				
				clearInput();
				txtId.setEnabled(false);				
			}
		});
		btnUpdate.setBounds(155, 23, 67, 29);
		panel_1.add(btnUpdate);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setEnabled(true);
			}
		});
		btnEdit.setBounds(81, 23, 61, 29);
		panel_1.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				
				CarDAO dao = new CarDAO();
				boolean result = dao.delete(id);
				
				if(result){
					JOptionPane.showMessageDialog(null, "Delete Successfully.");
				}else{
					JOptionPane.showMessageDialog(null, "Delete Fail.");
				}
				
				clearInput();
				txtId.setEnabled(false);	
			}
		});
		btnDelete.setBounds(240, 26, 67, 29);
		panel_1.add(btnDelete);
	}

	protected void clearInput() {
		txtId.setText("");
		txtBrand.setText("");
		txtModel.setText("");
		txtPrice.setText("");
		cmbColor.setSelectedIndex(0);
		
	}
}
