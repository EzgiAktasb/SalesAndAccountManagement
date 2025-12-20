package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	AccountManagementFrame amf = new AccountManagementFrame();
	TransactionFrame tf = new TransactionFrame();
	ProductManagementFrame pmf = new ProductManagementFrame();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel WelcomeLabel = new JLabel("Welcome To Sales Management System!");
		WelcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		WelcomeLabel.setBounds(43, 11, 368, 30);
		contentPane.add(WelcomeLabel);
		
		JLabel lblNewLabel = new JLabel("1) To manage products and access the related operations, press \"Manage Products\"\r\n\r\n");
		lblNewLabel.setBounds(22, 52, 497, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2) To manage accounts and access the related operations, press \"Manage Accounts\" \r\n");
		lblNewLabel_1.setBounds(22, 79, 424, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("3) To view the transactions, press \"View Transactions\" ");
		lblNewLabel_2.setBounds(22, 104, 414, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Manage Products");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pmf.setVisible(true);
			}
		});
		btnNewButton.setBounds(22, 168, 137, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Manage Accounts");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amf.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(191, 170, 145, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Transactions");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(363, 170, 145, 34);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("EXIT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(218, 256, 88, 22);
		contentPane.add(btnNewButton_3);

	}
}