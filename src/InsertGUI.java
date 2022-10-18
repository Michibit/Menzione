import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InsertGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea_1;
	private JButton btnNewButton_1;
	private JLabel valori;
	private JLabel Inserito;
	private JLabel valori1;
	private JLabel trovato;
	private JTextField textField;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public InsertGUI(SQLDatabaseConnection d) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{141, 82, 149, 64, 33, 0};
		gbl_contentPane.rowHeights = new int[]{16, 29, 16, 16, 132, 36, 29, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Quale tabella vuoi inserire un valore?");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTHEAST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Verifica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = "";
				try {
					s = Query.getNameColonne(d.getStatment().executeQuery("Select * FROM "+ textField.getText())).toString();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(s.isEmpty()) {
					trovato.setForeground(Color.RED);
					trovato.setText("Tabella non Trovata! Riprova");
					textArea_1.setEnabled(false);
					textArea_1.setEditable(false);
					btnNewButton_1.setEnabled(false);
					valori.setText("");
					valori1.setText("");
					textArea_1.setText("");
					Inserito.setText("");
				}
				else {
					trovato.setForeground(Color.GREEN);
					trovato.setText("Tabella Trovata!");
					valori.setText("Inserisci i valori nel seguente formato:");
					valori1.setForeground(getColor());
					valori1.setText("(" + s + ")");
					textArea_1.setEnabled(true);
					textArea_1.setEditable(true);
					btnNewButton_1.setEnabled(true);

				}

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		trovato = new JLabel("");
		GridBagConstraints gbc_trovato = new GridBagConstraints();
		gbc_trovato.fill = GridBagConstraints.BOTH;
		gbc_trovato.insets = new Insets(0, 0, 5, 5);
		gbc_trovato.gridwidth = 2;
		gbc_trovato.gridx = 2;
		gbc_trovato.gridy = 1;
		contentPane.add(trovato, gbc_trovato);

		valori = new JLabel("");
		GridBagConstraints gbc_valori = new GridBagConstraints();
		gbc_valori.fill = GridBagConstraints.BOTH;
		gbc_valori.insets = new Insets(0, 0, 5, 5);
		gbc_valori.gridwidth = 3;
		gbc_valori.gridx = 0;
		gbc_valori.gridy = 2;
		contentPane.add(valori, gbc_valori);

		valori1 = new JLabel("");
		GridBagConstraints gbc_valori1 = new GridBagConstraints();
		gbc_valori1.fill = GridBagConstraints.BOTH;
		gbc_valori1.insets = new Insets(0, 0, 5, 0);
		gbc_valori1.gridwidth = 5;
		gbc_valori1.gridx = 0;
		gbc_valori1.gridy = 3;
		contentPane.add(valori1, gbc_valori1);

		JLabel lbOK = new JLabel("");
		GridBagConstraints gbc_lbOK = new GridBagConstraints();
		gbc_lbOK.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbOK.insets = new Insets(0, 0, 5, 0);
		gbc_lbOK.gridx = 4;
		gbc_lbOK.gridy = 3;
		contentPane.add(lbOK, gbc_lbOK);

		textArea_1 = new JTextArea();
		textArea_1.setEnabled(false);
		textArea_1.setEditable(false);
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_1.gridwidth = 3;
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 4;
		contentPane.add(textArea_1, gbc_textArea_1);

		btnNewButton_1 = new JButton("Inserisci");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				try {
					rs = d.getStatment().executeQuery("Select * FROM "+ textField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String valori = "";
				String TypeT = "";
				String Value[] = textArea_1.getText().split(",");
				String NameT = "";
				java.sql.ResultSetMetaData resultSetMetaData = null;
				try {
					resultSetMetaData = rs.getMetaData();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++ ) {            
						NameT+=resultSetMetaData.getColumnName(i);   // Nome colonne
						TypeT+= resultSetMetaData.getColumnTypeName(i);  // Tipi colonne 
						if(i <= (resultSetMetaData.getColumnCount() - 1)) {
							NameT+= ",";
							TypeT += ",";
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String[] Type = TypeT.split(",");


				for (int i = 0 ; i<Value.length   ; i++ ) {
					if(Type[i].equalsIgnoreCase("CHAR") || Type[i].equalsIgnoreCase("DATE") || Type[i].equalsIgnoreCase("DATETIME"))
						valori += "'" + Value[i] + "'";
					else if(Type[i].equalsIgnoreCase("INT"))
						valori += Integer.parseInt(Value[i]);
					else if(Type[i].equalsIgnoreCase("DECIMAL"))
						valori += Float.parseFloat(Value[i]);
					if(i < (Value.length - 1))
						valori += ",";


				}

				String st = "INSERT INTO " + textField.getText() +"("+NameT+")" + " VALUES (" + valori + ")";
				try {
					if(d.getStatment().executeUpdate(st) == 1) {
						Inserito.setForeground(Color.GREEN);
						Inserito.setText("Valore inserto!");
					}




				} catch (SQLException e1) {
					Inserito.setForeground(Color.RED);
					Inserito.setText("Valore Non inserto!");
					e1.printStackTrace();
				}

			}
		});

		btnNewButton_2 = new JButton("<̶-̶ Indietro");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gui frame = null;
				try {
					frame = new Gui();
					setVisible(false);
					frame.setVisible(true);
				} catch (Exception s) {
					s.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 6;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

		Inserito = new JLabel("");
		GridBagConstraints gbc_Inserito = new GridBagConstraints();
		gbc_Inserito.fill = GridBagConstraints.BOTH;
		gbc_Inserito.insets = new Insets(0, 0, 0, 5);
		gbc_Inserito.gridx = 2;
		gbc_Inserito.gridy = 6;
		contentPane.add(Inserito, gbc_Inserito);
		btnNewButton_1.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 6;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
	}
	public Color getColor() {
		Random r = new Random();
		ArrayList<Color> c = new ArrayList<>();
		c.add(new Color(215, 38, 49)); // Dark State Blue
		c.add(new Color(162, 24, 84)); //Khaki web
		c.add(new Color(187, 95, 54)); //  coffe
		c.add(new Color(92, 60, 146)); // Persian Green
		c.add(new Color(204, 193, 174)); // Oxford Blue
		return c.get(r.nextInt(c.size()));

	}
}
