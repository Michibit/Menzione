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
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class DeleteGUI extends JFrame {

	private JPanel contentPane;
	private JButton Update;
	private JLabel valori;
	private JLabel Inserito;
	private JLabel valori1;
	private JLabel trovato;
	private JTextField txtTable;
	private JButton btnNewButton_2;
	private JTextField txtColonne;
	private JButton btnNewButton_3;
	private JTextField txtValoreOld;
	private JButton btnNewButton_4;
	private JLabel nuovoValore;
	private JLabel nuovoValoreMod;
	private JLabel ELIMINARE;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public DeleteGUI(SQLDatabaseConnection d) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{141, 98, 2, 130, 64, 33, 0};
		gbl_contentPane.rowHeights = new int[]{16, 29, 16, 16, 29, 16, 30, 16, 16, 30, 29, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Quale tabella vuoi eliminare il valore?");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		txtTable = new JTextField();
		GridBagConstraints gbc_txtTable = new GridBagConstraints();
		gbc_txtTable.fill = GridBagConstraints.BOTH;
		gbc_txtTable.insets = new Insets(0, 0, 5, 5);
		gbc_txtTable.gridx = 0;
		gbc_txtTable.gridy = 1;
		contentPane.add(txtTable, gbc_txtTable);
		txtTable.setColumns(10);

		btnNewButton_4 = new JButton("Verifica");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				try {
					rs = d.getStatment().executeQuery("Select * FROM "+ txtTable.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String TypeT = "";
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
				int n = 0;
				String[] Name = NameT.split(",");
				for(int i = 0 ; i< Name.length;i++) {
					if(Name[i].equalsIgnoreCase(txtColonne.getText()))
						n = i;
				}


				String[] Type = TypeT.split(",");


				try {
					while(rs.next()) {
						if(Type[n].equalsIgnoreCase("CHAR") || Type[n].equalsIgnoreCase("DATE") || Type[n].equalsIgnoreCase("DATETIME")) {
							if(rs.getString(Name[n]).equalsIgnoreCase(txtValoreOld.getText())) {
								String va = "";
								for(int i = 0 ; i< Name.length;i++) {
									if(Type[i].equalsIgnoreCase("CHAR") || Type[i].equalsIgnoreCase("DATE") || Type[i].equalsIgnoreCase("DATETIME"))
										va += rs.getString(Name[i]);
									else if(Type[i].equalsIgnoreCase("INT"))
										va += rs.getInt(Name[i]);
									else if(Type[i].equalsIgnoreCase("DECIMAL"))
										va += rs.getFloat(Name[i]);
									if(i< Name.length - 1)
										va += ",";
								}

								nuovoValoreMod.setText("Vuoi eliminare " +txtValoreOld.getText().toUpperCase()+" in:");
								ELIMINARE.setText("(" + va + ")");
								Update.setEnabled(true);
								return;
							}
						}
						else if(Type[n].equalsIgnoreCase("INT")) {
							if(rs.getInt(Name[n]) == Integer.parseInt(txtValoreOld.getText())) {

								String va = "";
								for(int i = 0 ; i< Name.length;i++) {
									if(Type[i].equalsIgnoreCase("CHAR") || Type[i].equalsIgnoreCase("DATE") || Type[i].equalsIgnoreCase("DATETIME"))
										va += rs.getString(Name[i]);
									else if(Type[i].equalsIgnoreCase("INT"))
										va += rs.getInt(Name[i]);
									else if(Type[i].equalsIgnoreCase("DECIMAL"))
										va += rs.getFloat(Name[i]);
									if(i< Name.length - 1)
										va += ",";
								}

								nuovoValoreMod.setText("Vuoi eliminare " +txtValoreOld.getText().toUpperCase()+" in:");
								ELIMINARE.setText("(" + va + ")");
								Update.setEnabled(true);
								return;
							}

						}
						else if(Type[n].equalsIgnoreCase("DECIMAL")) {
							if(rs.getFloat(Name[n]) == Float.parseFloat(txtValoreOld.getText())) {
								String va = "";
								for(int i = 0 ; i< Name.length;i++) {
									if(Type[i].equalsIgnoreCase("CHAR") || Type[i].equalsIgnoreCase("DATE") || Type[i].equalsIgnoreCase("DATETIME"))
										va += rs.getString(Name[i]);
									else if(Type[i].equalsIgnoreCase("INT"))
										va += rs.getInt(Name[i]);
									else if(Type[i].equalsIgnoreCase("DECIMAL"))
										va += rs.getFloat(Name[i]);
									if(i< Name.length - 1)
										va += ",";
								}

								nuovoValoreMod.setText("Vuoi eliminare " +txtValoreOld.getText().toUpperCase()+" in:");
								ELIMINARE.setText("(" + va + ")");
								Update.setEnabled(true);
								return;
							}
						}
					}
					nuovoValoreMod.setForeground(Color.RED);
					nuovoValoreMod.setText(txtValoreOld.getText() + " Non trovato!");
					Update.setEnabled(false);
					return;
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		JButton btnNewButton = new JButton("Verifica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = "";
				try {
					s = Query.getNameColonne(d.getStatment().executeQuery("Select * FROM "+ txtTable.getText())).toString();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(s.isEmpty()) {
					trovato.setForeground(Color.RED);
					trovato.setText("Tabella non Trovata! Riprova");
					txtColonne.setEnabled(false);
					txtColonne.setEditable(false);
					Update.setEnabled(false);
					valori.setText("");
					valori1.setText("");
					txtColonne.setText("");
					Inserito.setText("");
					Update.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				else {
					trovato.setForeground(Color.GREEN);
					btnNewButton_3.setEnabled(true);
					trovato.setText("Tabella Trovata!");
					valori.setText("Quali valori Vuoi Eliminare");
					valori1.setForeground(getColor());
					valori1.setText("(" + s + ")");
					txtColonne.setEnabled(true);
					txtColonne.setEditable(true);
					Table t = new Table(d,"Select * FROM " +txtTable.getText());
					t.setVisible(true);

				}

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		trovato = new JLabel("");
		GridBagConstraints gbc_trovato = new GridBagConstraints();
		gbc_trovato.fill = GridBagConstraints.BOTH;
		gbc_trovato.insets = new Insets(0, 0, 5, 5);
		gbc_trovato.gridwidth = 3;
		gbc_trovato.gridx = 2;
		gbc_trovato.gridy = 1;
		contentPane.add(trovato, gbc_trovato);

		valori = new JLabel("");
		GridBagConstraints gbc_valori = new GridBagConstraints();
		gbc_valori.fill = GridBagConstraints.BOTH;
		gbc_valori.insets = new Insets(0, 0, 5, 5);
		gbc_valori.gridwidth = 4;
		gbc_valori.gridx = 0;
		gbc_valori.gridy = 2;
		contentPane.add(valori, gbc_valori);

		valori1 = new JLabel("");
		GridBagConstraints gbc_valori1 = new GridBagConstraints();
		gbc_valori1.fill = GridBagConstraints.BOTH;
		gbc_valori1.insets = new Insets(0, 0, 5, 5);
		gbc_valori1.gridwidth = 4;
		gbc_valori1.gridx = 0;
		gbc_valori1.gridy = 3;
		contentPane.add(valori1, gbc_valori1);

		JLabel lbOK = new JLabel("");
		GridBagConstraints gbc_lbOK = new GridBagConstraints();
		gbc_lbOK.fill = GridBagConstraints.BOTH;
		gbc_lbOK.insets = new Insets(0, 0, 5, 0);
		gbc_lbOK.gridx = 5;
		gbc_lbOK.gridy = 3;
		contentPane.add(lbOK, gbc_lbOK);

		txtColonne = new JTextField();
		txtColonne.setEnabled(false);
		GridBagConstraints gbc_txtColonne = new GridBagConstraints();
		gbc_txtColonne.fill = GridBagConstraints.BOTH;
		gbc_txtColonne.insets = new Insets(0, 0, 5, 5);
		gbc_txtColonne.gridx = 0;
		gbc_txtColonne.gridy = 4;
		contentPane.add(txtColonne, gbc_txtColonne);
		txtColonne.setColumns(10);

		btnNewButton_3 = new JButton("Verifica");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = txtColonne.getText();
				String [] colonne = null;
				try {
					colonne = Query.getNameColonne(d.getStatment().executeQuery("Select * FROM "+ txtTable.getText())).split(",");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (String p : colonne) {
					if (p.equalsIgnoreCase(s)) {
						btnNewButton_4.setEnabled(true);
						txtValoreOld.setEnabled(true);
						//nuovoValore.setForeground(Color.GREEN);
						nuovoValore.setText("Inserisci il "+ s.toUpperCase() + " da ELIMINARE");
						return;

					}
				}
				btnNewButton_4.setEnabled(false);
				txtValoreOld.setText("");

				txtValoreOld.setEnabled(false);

				Update.setEnabled(false);
				nuovoValore.setForeground(Color.RED);
				nuovoValore.setText("Valore non trovato!");
				return;



			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 4;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);

		nuovoValore = new JLabel("");
		GridBagConstraints gbc_nuovoValore = new GridBagConstraints();
		gbc_nuovoValore.fill = GridBagConstraints.BOTH;
		gbc_nuovoValore.insets = new Insets(0, 0, 5, 5);
		gbc_nuovoValore.gridwidth = 3;
		gbc_nuovoValore.gridx = 0;
		gbc_nuovoValore.gridy = 5;
		contentPane.add(nuovoValore, gbc_nuovoValore);

		txtValoreOld = new JTextField();
		txtValoreOld.setEnabled(false);
		GridBagConstraints gbc_txtValoreOld = new GridBagConstraints();
		gbc_txtValoreOld.fill = GridBagConstraints.BOTH;
		gbc_txtValoreOld.insets = new Insets(0, 0, 5, 5);
		gbc_txtValoreOld.gridx = 0;
		gbc_txtValoreOld.gridy = 6;
		contentPane.add(txtValoreOld, gbc_txtValoreOld);
		txtValoreOld.setColumns(10);



		btnNewButton_4.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 6;
		contentPane.add(btnNewButton_4, gbc_btnNewButton_4);

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

		nuovoValoreMod = new JLabel("");
		GridBagConstraints gbc_nuovoValoreMod = new GridBagConstraints();
		gbc_nuovoValoreMod.fill = GridBagConstraints.BOTH;
		gbc_nuovoValoreMod.insets = new Insets(0, 0, 5, 5);
		gbc_nuovoValoreMod.gridwidth = 2;
		gbc_nuovoValoreMod.gridx = 0;
		gbc_nuovoValoreMod.gridy = 7;
		contentPane.add(nuovoValoreMod, gbc_nuovoValoreMod);

		ELIMINARE = new JLabel("");
		ELIMINARE.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		GridBagConstraints gbc_ELIMINARE = new GridBagConstraints();
		gbc_ELIMINARE.fill = GridBagConstraints.BOTH;
		gbc_ELIMINARE.insets = new Insets(0, 0, 5, 0);
		gbc_ELIMINARE.gridwidth = 6;
		gbc_ELIMINARE.gridx = 0;
		gbc_ELIMINARE.gridy = 8;
		contentPane.add(ELIMINARE, gbc_ELIMINARE);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 10;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

		Inserito = new JLabel("");
		GridBagConstraints gbc_Inserito = new GridBagConstraints();
		gbc_Inserito.fill = GridBagConstraints.BOTH;
		gbc_Inserito.insets = new Insets(0, 0, 0, 5);
		gbc_Inserito.gridx = 3;
		gbc_Inserito.gridy = 10;
		contentPane.add(Inserito, gbc_Inserito);

		Update = new JButton("Elimina");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				try {
					rs = d.getStatment().executeQuery("Select * FROM "+ txtTable.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String TypeT = "";
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
				int n = 0;
				String[] Name = NameT.split(",");
				for(int i = 0 ; i< Name.length;i++) {
					if(Name[i].equalsIgnoreCase(txtColonne.getText()))
						n = i;
				}


				String[] Type = TypeT.split(",");
				String m ="";


				try {
					while(rs.next()) {
						if(Type[n].equalsIgnoreCase("CHAR") || Type[n].equalsIgnoreCase("DATE")|| Type[n].equalsIgnoreCase("DATETIME")) {
							if(rs.getString(Name[n]).equalsIgnoreCase(txtValoreOld.getText())) {
								m += "'" + txtValoreOld.getText() + "'";



							}
						}
						else if(Type[n].equalsIgnoreCase("INT")) {
							if(rs.getInt(Name[n]) == Integer.parseInt(txtValoreOld.getText())) {

								m += txtValoreOld.getText();

							}

						}
						else if(Type[n].equalsIgnoreCase("DECIMAL")) {
							if(rs.getFloat(Name[n]) == Float.parseFloat(txtValoreOld.getText())) {
								m += txtValoreOld.getText();

							}
						}
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String st = "DELETE FROM " + txtTable.getText() +" WHERE "
						+txtColonne.getText()+"=" + m;
				try {
					if(d.getStatment().executeUpdate(st) == 1) {
						Inserito.setForeground(Color.GREEN);
						Inserito.setText("Valore Eliminato!");
					}




				} catch (SQLException e1) {
					Inserito.setForeground(Color.RED);
					Inserito.setText("Valore Non Eliminato!");
					e1.printStackTrace();
				}

			}
		});
		Update.setEnabled(false);
		GridBagConstraints gbc_Update = new GridBagConstraints();
		gbc_Update.fill = GridBagConstraints.BOTH;
		gbc_Update.gridwidth = 2;
		gbc_Update.gridx = 4;
		gbc_Update.gridy = 10;
		contentPane.add(Update, gbc_Update);
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
