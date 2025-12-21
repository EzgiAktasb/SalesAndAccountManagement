
package GUI;
import java.awt.EventQueue;

import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.Product;
import model.Transaction;
import SystemAndMain.SalesSystem;
import GUI.MainMenuFrame;
public class ProductManagementFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IDField;
	private JTextField NameField;
	private JTextField PriceField;
	private JTextField QuantityField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductManagementFrame frame = new ProductManagementFrame();
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
	public ProductManagementFrame() {
		setTitle("Product Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product ID:\r\n");
		lblNewLabel.setBounds(10, 36, 66, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(10, 61, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setBounds(10, 86, 48, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity:");
		lblNewLabel_3.setBounds(10, 111, 48, 14);
		contentPane.add(lblNewLabel_3);
		
		IDField = new JTextField();
		IDField.setBounds(72, 33, 96, 20);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		NameField = new JTextField();
		NameField.setBounds(72, 58, 96, 20);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		PriceField = new JTextField();
		PriceField.setBounds(72, 83, 96, 20);
		contentPane.add(PriceField);
		PriceField.setColumns(10);
		
		QuantityField = new JTextField();
		QuantityField.setBounds(72, 108, 96, 20);
		contentPane.add(QuantityField);
		QuantityField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 223, 503, 183);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Add Product");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (IDField.getText().isEmpty()	|| NameField.getText().isEmpty() || PriceField.getText().isEmpty()	|| QuantityField.getText().isEmpty()) {
					textArea.setText("Please fill all the information fields.");
				}
				
				else {
					Product obj = new Product(IDField.getText(), NameField.getText(), Double.parseDouble(PriceField.getText()), Integer.parseInt(QuantityField.getText()));
					SalesSystem.addProduct(obj);
					textArea.setText("Product added successfully.");
					textArea.setText(obj.displayProduct());
				}
				}
		});
		btnNewButton.setBounds(10, 190, 116, 22);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Product");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (IDField.getText().isEmpty()) {
					textArea.setText("Fill the ID field only to delete the product");
				}
				else {
					if(SalesSystem.deleteProduct(IDField.getText())) {
						textArea.setText("Product is deleted successfully");
					}
					else {
						textArea.setText("Product is already non-existent / not found");
					}
						
				}
			}
		});
		btnNewButton_1.setBounds(139, 190, 116, 22);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Search Product\r\n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (IDField.getText().isEmpty()) {
					textArea.setText("Fill the ID field to search the product");
				}
				else {
					if (SalesSystem.searchProductById(IDField.getText()) == null) {
						textArea.setText("The products is non-existent / not found");
					}
					else {
						textArea.setText(SalesSystem.searchProductById(IDField.getText()).displayProduct());
					}
				}
			}
		});
		btnNewButton_2.setBounds(265, 190, 113, 22);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Display All");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesSystem.displayAllProducts();
			}
		});
		btnNewButton_3.setBounds(389, 190, 113, 22);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("*To add a product, please fill in all the fields.");
		lblNewLabel_4.setBounds(215, 61, 222, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("**To delete/search a product, only enter the ID.");
		lblNewLabel_5.setBounds(215, 86, 263, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("***To exit this window, press EXIT.");
		lblNewLabel_6.setBounds(215, 111, 236, 14);
		contentPane.add(lblNewLabel_6);
		


	}
}
