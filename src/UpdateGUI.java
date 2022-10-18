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

public class UpdateGUI extends JFrame {

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
	private JTextField txtValoreNew;
	private JButton btnNewButton_4;
	private JLabel nuovoValore;
	private JLabel nuovoValoreMod;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UpdateGUI(SQLDatabaseConnection d) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Quale tabella vuoi aggiornare un valore?");
		lblNewLabel.setBounds(11, 11, 270, 16);

		JButton btnNewButton = new JButton("Verifica");
		btnNewButton.setBounds(142, 33, 71, 29);
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
					txtValoreNew.setEnabled(false);
					Update.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				else {
					trovato.setForeground(Color.GREEN);
					btnNewButton_3.setEnabled(true);
					trovato.setText("Tabella Trovata!");
					valori.setText("Quali valori Vuoi Modificare");
					valori1.setForeground(getColor());
					valori1.setText("(" + s + ")");
					txtColonne.setEnabled(true);
					txtColonne.setEditable(true);
					Table t = new Table(d,"Select * FROM " +txtTable.getText());
					t.setVisible(true);

				}

			}
		});

		JLabel lbOK = new JLabel("");
		lbOK.setBounds(461, 90, 0, 0);

		Update = new JButton("Update");
		Update.setBounds(153, 230, 97, 29);
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
				String l = "";


				try {
					while(rs.next()) {
						if(Type[n].equalsIgnoreCase("CHAR") || Type[n].equalsIgnoreCase("DATE") || Type[n].equalsIgnoreCase("DATETIME")) {
							if(rs.getString(Name[n]).equalsIgnoreCase(txtValoreOld.getText())) {
								m += "'" + txtValoreOld.getText() + "'";
								l += "'" + txtValoreNew.getText() + "'";



							}
						}
						else if(Type[n].equalsIgnoreCase("INT")) {
							if(rs.getInt(Name[n]) == Integer.parseInt(txtValoreOld.getText())) {

								m += txtValoreOld.getText();
								l +=txtValoreNew.getText();
							}

						}
						else if(Type[n].equalsIgnoreCase("DECIMAL")) {
							if(rs.getFloat(Name[n]) == Float.parseFloat(txtValoreOld.getText())) {
								m += txtValoreOld.getText();
								l +=txtValoreNew.getText();
							}
						}
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String st = "UPDATE " + txtTable.getText() +" SET "+txtColonne.getText()+"=" +l+"WHERE "
						+txtColonne.getText()+"=" + m;
				try {
					if(d.getStatment().executeUpdate(st) == 1) {
						Inserito.setForeground(Color.GREEN);
						Inserito.setText("Valore Aggiornato!");
					}




				} catch (SQLException e1) {
					Inserito.setForeground(Color.RED);
					Inserito.setText(e1.toString());
					e1.printStackTrace();
				}

			}
		});
		Update.setEnabled(false);

		valori = new JLabel("");
		valori.setBounds(11, 68, 305, 16);
		contentPane.setLayout(null);
		contentPane.add(Update);
		contentPane.add(btnNewButton);
		contentPane.add(lbOK);
		contentPane.add(lblNewLabel);
		contentPane.add(valori);

		Inserito = new JLabel("");
		Inserito.setBounds(255, 286, 130, 16);
		contentPane.add(Inserito);

		valori1 = new JLabel("");
		valori1.setBounds(11, 90, 289, 16);
		contentPane.add(valori1);

		trovato = new JLabel("");
		trovato.setBounds(248, 33, 213, 18);
		contentPane.add(trovato);

		txtTable = new JTextField();
		txtTable.setBounds(11, 33, 130, 26);
		contentPane.add(txtTable);
		txtTable.setColumns(10);

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
		btnNewButton_2.setBounds(0, 281, 117, 29);
		contentPane.add(btnNewButton_2);

		txtColonne = new JTextField();
		txtColonne.setEnabled(false);
		txtColonne.setBounds(11, 106, 130, 26);
		contentPane.add(txtColonne);
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
						nuovoValore.setText("Inserisci il "+ s.toUpperCase() + " da modificare");
						return;

					}
				}
				btnNewButton_4.setEnabled(false);
				txtValoreOld.setText("");
				txtValoreNew.setText("");
				txtValoreOld.setEnabled(false);
				txtValoreNew.setEnabled(false);
				Update.setEnabled(false);
				nuovoValore.setForeground(Color.RED);
				nuovoValore.setText("Valore non trovato!");
				return;



			}
		});
		btnNewButton_3.setBounds(142, 106, 71, 29);
		contentPane.add(btnNewButton_3);

		txtValoreOld = new JTextField();
		txtValoreOld.setEnabled(false);
		txtValoreOld.setBounds(11, 171, 130, 26);
		contentPane.add(txtValoreOld);
		txtValoreOld.setColumns(10);

		txtValoreNew = new JTextField();
		txtValoreNew.setEnabled(false);
		txtValoreNew.setBounds(11, 230, 130, 26);
		contentPane.add(txtValoreNew);
		txtValoreNew.setColumns(10);

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
						if(Type[n].equalsIgnoreCase("CHAR") || Type[n].equalsIgnoreCase("DATE") ||
								Type[n].equalsIgnoreCase("DATETIME")) {
							if(rs.getString(Name[n]).equalsIgnoreCase(txtValoreOld.getText())) {

								nuovoValoreMod.setText("Inserisci il nuovo valore " +txtValoreOld.getText().toUpperCase());
								txtValoreNew.setEnabled(true);
								Update.setEnabled(true);
								return;
							}
						}
						else if(Type[n].equalsIgnoreCase("INT")) {
							if(rs.getInt(Name[n]) == Integer.parseInt(txtValoreOld.getText())) {

								nuovoValoreMod.setText("Inserisci il nuovo valore  " +txtValoreOld.getText().toUpperCase());
								txtValoreNew.setEnabled(true);
								Update.setEnabled(true);
								return;
							}

						}
						else if(Type[n].equalsIgnoreCase("DECIMAL")) {
							if(rs.getFloat(Name[n]) == Float.parseFloat(txtValoreOld.getText())) {
								nuovoValoreMod.setText("Inserisci il nuovo valore " +txtValoreOld.getText().toUpperCase());
								txtValoreNew.setEnabled(true);
								Update.setEnabled(true);
								return;
							}
						}
					}
					nuovoValoreMod.setForeground(Color.RED);
					nuovoValoreMod.setText(txtValoreOld.getText() + " Non trovato!");
					txtValoreNew.setEnabled(false);
					Update.setEnabled(false);
					return;
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}







			}

		});



		btnNewButton_4.setEnabled(false);
		btnNewButton_4.setBounds(142, 167, 71, 26);
		contentPane.add(btnNewButton_4);

		nuovoValore = new JLabel("");
		nuovoValore.setBounds(11, 144, 239, 16);
		contentPane.add(nuovoValore);

		nuovoValoreMod = new JLabel("");
		nuovoValoreMod.setBounds(11, 212, 229, 16);
		contentPane.add(nuovoValoreMod);
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
