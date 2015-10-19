package farmacia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import farmacia.modelo.dao.AdministradorDao;
import farmacia.modelo.dto.Administrador;
import farmacia.modelo.imp.AdministradorImp;

@SuppressWarnings("serial")
public class frmModificarMyInfo extends JFrame implements ActionListener {

	private final JPanel Panel = new JPanel();
	private JButton btnGuardar, btnSalir;
	private JTextField txt_nombre, txt_edad, txt_apellidos, txt_correo;
	private Administrador administrador;
	private JTextArea txAdminInfo;

	public frmModificarMyInfo(Administrador administrador, JTextArea txAdminInfo) {
		this.administrador = administrador;
		this.txAdminInfo = txAdminInfo;
		this.setResizable(false);
		setTitle("Informacion personal del administrador");
		setBounds(100, 100, 450, 279);
		getContentPane().setLayout(new BorderLayout());
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(Panel, BorderLayout.CENTER);
		Panel.setLayout(null);

		JLabel etn = new JLabel("Nombre");
		etn.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		etn.setBounds(99, 70, 66, 14);
		Panel.add(etn);

		txt_nombre = new JTextField();
		txt_nombre.setBounds(175, 70, 106, 20);
		Panel.add(txt_nombre);
		txt_nombre.setColumns(10);

		JLabel eta = new JLabel("Apellidos");
		eta.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		eta.setBackground(Color.WHITE);
		eta.setForeground(Color.BLACK);
		eta.setBounds(99, 101, 66, 14);
		Panel.add(eta);

		txt_apellidos = new JTextField();
		txt_apellidos.setBounds(175, 98, 158, 20);
		Panel.add(txt_apellidos);
		txt_apellidos.setColumns(10);

		JLabel etc = new JLabel("Correo");
		etc.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		etc.setBounds(99, 133, 66, 14);
		Panel.add(etc);

		txt_correo = new JTextField();
		txt_correo.setBounds(175, 130, 158, 20);
		Panel.add(txt_correo);
		txt_correo.setColumns(10);

		JLabel ete = new JLabel("Edad");
		ete.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		ete.setBounds(99, 165, 66, 14);
		Panel.add(ete);

		txt_edad = new JTextField();
		txt_edad.setBounds(175, 161, 46, 20);
		Panel.add(txt_edad);
		txt_edad.setColumns(10);

		JLabel a = new JLabel("A continuacion puede editar la informacion");
		a.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		a.setBounds(99, 31, 272, 14);
		Panel.add(a);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		btnGuardar.setIcon(new ImageIcon("icons/Save.png"));
		btnGuardar.setBounds(78, 197, 130, 33);
		btnGuardar.addActionListener(this);
		Panel.add(btnGuardar);

		btnSalir = new JButton("Cancelar");
		btnSalir.setIcon(new ImageIcon("icons/Delete.png"));
		btnSalir.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		btnSalir.setBounds(239, 197, 132, 33);
		btnSalir.addActionListener(this);
		Panel.add(btnSalir);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("amd.JPG"));
		label.setBounds(-74, -220, 518, 471);
		Panel.add(label);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.rellenarCampos();
	}

	private void rellenarCampos() {
		txt_nombre.setText(administrador.getNombre());
		txt_edad.setText("" + administrador.getEdad());
		txt_apellidos.setText(administrador.getApellido());
		txt_correo.setText(administrador.getCorreo());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			modificar();
		}

		if (e.getSource() == btnSalir) {
			this.dispose();
		}

	}
	
	private void setAdminInfo() {
		txAdminInfo.append("ID:" + administrador.getID());
		txAdminInfo.append("\nNombre: " + administrador.getNombre());
		txAdminInfo.append("\nApellidos: " + administrador.getApellido());
		txAdminInfo.append("\nEdad: " + administrador.getEdad());
		txAdminInfo.append("\nCorreo: " + administrador.getCorreo());
	}

	private void modificar() {
		administrador.setNombre(txt_nombre.getText());
		administrador.setEdad(Integer.parseInt(txt_edad.getText()));
		administrador.setApellido(txt_apellidos.getText());
		administrador.setCorreo(txt_correo.getText());

		try {
			AdministradorDao ad = new AdministradorImp();
			int res = ad.updateAdministrador(administrador.getID(), administrador);
			if(res == 1){
				txAdminInfo.setText("");
				setAdminInfo();
				JOptionPane.showMessageDialog(rootPane, "Se han modificado los datos");
				this.dispose();
			} else {
				JOptionPane.showOptionDialog(rootPane,
						"Ha ocurrido un error", "Error",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, null, null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
