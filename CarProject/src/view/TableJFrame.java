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

public class TableJFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

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
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		model = new DefaultTableModel(null, new String[] {"รหัส","ยี่ห้อรถยนต์","รุ่นรถยนต์","สีรถยนต์","ราคารถยนต์"});
//		model = new DefaultTableModel(
//				new Object[][] {
//				},
//				new String[] {
//					"\u0E23\u0E2B\u0E31\u0E2A\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C", "\u0E22\u0E35\u0E48\u0E2B\u0E49\u0E2D\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C", "\u0E23\u0E38\u0E48\u0E19\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C", "\u0E2A\u0E35\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C", "\u0E23\u0E32\u0E04\u0E32\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C"
//				}
//			);
		table.setModel(model);
		
//		model = new DefaultTableModel();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"\u0E23\u0E2B\u0E31\u0E2A\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C", "\u0E22\u0E35\u0E48\u0E2B\u0E49\u0E2D\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C", "\u0E23\u0E38\u0E48\u0E19\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C", "\u0E2A\u0E35\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C", "\u0E23\u0E32\u0E04\u0E32\u0E23\u0E16\u0E22\u0E19\u0E15\u0E4C"
//			}
//		));
		
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
		
		addDataToTable();
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
