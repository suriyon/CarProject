package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CarDAO;
import model.Car;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TableJFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JPanel panel;
	private JTextField txtBrand;
	private JTextField txtModel;
	private JTextField txtPrice;
	private JButton btnInsert;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JComboBox cmbColor;

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
					TableJFrame frame = new TableJFrame();
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
	public TableJFrame() {
		setTitle("Test JTable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		model = new DefaultTableModel(null, new String[] {"รหัส","ยี่ห้อรถยนต์","รุ่นรถยนต์","สีรถยนต์","ราคารถยนต์"});		
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setResizable(false);		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("brand");
		panel.add(lblNewLabel);
		
		txtBrand = new JTextField();
		panel.add(txtBrand);
		txtBrand.setColumns(10);
		
		lblNewLabel_1 = new JLabel("model");
		panel.add(lblNewLabel_1);
		
		txtModel = new JTextField();
		panel.add(txtModel);
		txtModel.setColumns(10);
		
		lblNewLabel_2 = new JLabel("color");
		panel.add(lblNewLabel_2);
		
		cmbColor = new JComboBox();
		cmbColor.setModel(new DefaultComboBoxModel(new String[] {"WHITE", "BLACK", "RED", "YELLOW", "GRAY", "BLUE"}));
		panel.add(cmbColor);
		
		lblNewLabel_3 = new JLabel("price");
		panel.add(lblNewLabel_3);
		
		txtPrice = new JTextField();
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brand = txtBrand.getText();
				String model = txtModel.getText();
				String color = cmbColor.getSelectedItem().toString();
				int price = Integer.parseInt(txtPrice.getText());
				
				Car car = new Car(brand, model, color, price);
				CarDAO dao = new CarDAO();
				boolean result = dao.insert(car);
				
				if(result){
					JOptionPane.showMessageDialog(null, "Insert Success");		
					addDataToTable();
				}else{
					JOptionPane.showMessageDialog(null, "Insert Fail");
				}
				clearText();
			}
		});
		panel.add(btnInsert);
		
		addDataToTable();
	}

	protected void clearText() {
		txtBrand.setText("");
		txtModel.setText("");
		txtPrice.setText("");
		cmbColor.setSelectedIndex(0);
	}

	private void addDataToTable() {
		if(table.getRowCount() > 0){
			removeTable();
		}
		
		CarDAO dao = new CarDAO();
		Vector cars = dao.selectAll();
		int size = cars.size();
		for(int i=0; i<size; i++){
			model.addRow((Vector) cars.get(i));
		}
	}

	private void removeTable() {
		if(table.getRowCount() > 0){
			int row = table.getRowCount();
			for(int i=0; i<row; i++){
				model.removeRow(0);
			}
		}
	}

}
