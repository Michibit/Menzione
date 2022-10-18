import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JLabel connesione;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSelect;
	private JButton btnQuery;
	private JButton btnQuery_2;
	private JButton btnNewButton;
	private JSplitPane splitPane;
	private JLabel utrnti;
	private JLabel scemo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Gui frame = new Gui();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		SQLDatabaseConnection d = new SQLDatabaseConnection();
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{118, 38, 73, 65, 134, 0};
		gbl_contentPane.rowHeights = new int[]{35, 16, 65, 29, 47, 27, 33, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Connessione al Server:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		connesione = new JLabel("");
		connesione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_connesione = new GridBagConstraints();
		gbc_connesione.anchor = GridBagConstraints.SOUTH;
		gbc_connesione.fill = GridBagConstraints.HORIZONTAL;
		gbc_connesione.insets = new Insets(0, 0, 5, 5);
		gbc_connesione.gridx = 2;
		gbc_connesione.gridy = 0;
		contentPane.add(connesione, gbc_connesione);



		btnNewButton = new JButton("Riprova");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(d.getConnection() == null) {
					setVisible(false);
					Gui s = new Gui();
					s.setVisible(true);
				}
				else {
					scemo.setText("Sei già connesso al server!");
					scemo.setForeground(Color.BLUE);
				}

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		scemo = new JLabel("  ");
		GridBagConstraints gbc_scemo = new GridBagConstraints();
		gbc_scemo.anchor = GridBagConstraints.NORTH;
		gbc_scemo.fill = GridBagConstraints.HORIZONTAL;
		gbc_scemo.insets = new Insets(0, 0, 5, 5);
		gbc_scemo.gridwidth = 3;
		gbc_scemo.gridx = 0;
		gbc_scemo.gridy = 1;
		contentPane.add(scemo, gbc_scemo);

		btnSelect = new JButton("Select ★");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectGUI s = new SelectGUI(d);
				s.setVisible(true);
				setVisible(false);
			}
		});

		btnDelete = new JButton("Delete ✘");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteGUI s = new DeleteGUI(d);
				s.setVisible(true);
				setVisible(false);
			}
		});

		btnInsert = new JButton("Insert ✎");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertGUI s = new InsertGUI(d);
				s.setVisible(true);
				setVisible(false);

			}
		});

		btnInsert.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.anchor = GridBagConstraints.NORTH;
		gbc_btnInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsert.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 3;
		contentPane.add(btnInsert, gbc_btnInsert);

		btnUpdate = new JButton("Update ✄");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateGUI s = new UpdateGUI(d);
				s.setVisible(true);
				setVisible(false);

			}
		});
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.anchor = GridBagConstraints.NORTH;
		gbc_btnUpdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridwidth = 2;
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 3;
		contentPane.add(btnUpdate, gbc_btnUpdate);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.anchor = GridBagConstraints.NORTH;
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 4;
		gbc_btnDelete.gridy = 3;
		contentPane.add(btnDelete, gbc_btnDelete);

		utrnti = new JLabel("");
		GridBagConstraints gbc_utrnti = new GridBagConstraints();
		gbc_utrnti.fill = GridBagConstraints.BOTH;
		gbc_utrnti.insets = new Insets(0, 0, 5, 5);
		gbc_utrnti.gridwidth = 3;
		gbc_utrnti.gridx = 0;
		gbc_utrnti.gridy = 5;
		contentPane.add(utrnti, gbc_utrnti);
		splitPane = new JSplitPane();

		btnQuery = new JButton("Query 1");
		splitPane.setLeftComponent(btnQuery);

		btnQuery_2 = new JButton("Query 2");
		splitPane.setRightComponent(btnQuery_2);
		btnQuery_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query1GUI s = new Query1GUI(d);
				s.setVisible(true);
				setVisible(false);

			}
		});
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryGUI s = new QueryGUI(d);
				s.setVisible(true);
				setVisible(false);

			}
		});
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_splitPane.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane.gridwidth = 3;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 6;
		contentPane.add(splitPane, gbc_splitPane);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.anchor = GridBagConstraints.NORTH;
		gbc_btnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSelect.gridx = 4;
		gbc_btnSelect.gridy = 6;
		contentPane.add(btnSelect, gbc_btnSelect);
		if(d.getConnection() != null) {
			connesione.setForeground(Color.GREEN);
			connesione.setText("✔");
			utrnti.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			utrnti.setText("Solo per utenti esperti:");
			utrnti.setForeground(Color.RED);

		}

		else {
			connesione.setForeground(Color.RED);
			connesione.setText("✘");
			btnQuery.setEnabled(false);
			btnQuery_2.setEnabled(false);

			btnInsert.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			btnSelect.setEnabled(false);

		}
	}
}
