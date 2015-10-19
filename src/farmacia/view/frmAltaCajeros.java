package farmacia.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import farmacia.modelo.dao.CajeroDao;
import farmacia.modelo.dto.Cajero;
import farmacia.modelo.imp.CajeroImp;
import farmacia.utils.WallpaperBorder;

@SuppressWarnings("serial")
public class frmAltaCajeros extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JTextField txtEdad;
	private JTextField txtApellidos;
	private JTextField txtNombre;
	private JTextField txtID;
	private JButton btnCancelar;
	private JButton btnAlta;
	private JButton btnComprobar;

	public frmAltaCajeros() {
		setTitle("Nuevo cajero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Favor de rellenar el siguiente formulario");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Calisto MT", Font.PLAIN, 22));
		label.setBounds(20, 22, 404, 27);
		contentPane.add(label);

		JLabel label_1 = new JLabel("ID");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label_1.setBounds(52, 74, 105, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Nombre");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label_2.setBounds(52, 99, 105, 14);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Apellidos");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label_3.setBounds(52, 124, 105, 14);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("Edad");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label_4.setBounds(52, 150, 105, 14);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("Contrase\u00F1a");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label_5.setBounds(52, 176, 105, 14);
		contentPane.add(label_5);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtPassword.setEchoChar('*');
		txtPassword.setColumns(10);
		txtPassword.setBounds(167, 174, 139, 20);
		contentPane.add(txtPassword);

		txtEdad = new JTextField();
		txtEdad.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtEdad.setColumns(10);
		txtEdad.setBounds(167, 148, 38, 20);
		contentPane.add(txtEdad);

		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(167, 124, 139, 20);
		contentPane.add(txtApellidos);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(167, 99, 99, 20);
		contentPane.add(txtNombre);

		txtID = new JTextField();
		txtID.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtID.setColumns(10);
		txtID.setBounds(167, 74, 99, 20);
		contentPane.add(txtID);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.setIcon(new ImageIcon("icons/Select.png"));
		btnComprobar.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		btnComprobar.setBounds(276, 65, 142, 36);
		btnComprobar.addActionListener(this);
		contentPane.add(btnComprobar);

		btnAlta = new JButton("Alta");
		btnAlta.setIcon(new ImageIcon("icons/Save.png"));
		btnAlta.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnAlta.setBounds(53, 221, 139, 39);
		btnAlta.addActionListener(this);
		contentPane.add(btnAlta);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("icons/Delete.png"));
		btnCancelar.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnCancelar.setBounds(249, 221, 132, 39);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
		setVisible(true);
		setFondo("amd.jpg");
	}

	private void setFondo(String rutaImagen) {
		try {
			File file = new File(rutaImagen);
			BufferedImage image = ImageIO.read(file);
			WallpaperBorder wall = new WallpaperBorder(image);
			contentPane.setBorder(wall);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAlta) {
			try {
				alta();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == btnCancelar) {
			this.dispose();
		}

		if (e.getSource() == btnComprobar) {
			try {
				if (CajeroImp.isCajeroExists(Integer.parseInt(txtID.getText()))) {
					JOptionPane.showOptionDialog(rootPane,
							"ID no disponible, elejir otro", "Error",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				} else {
					JOptionPane.showOptionDialog(rootPane, "ID disponible",
							"Altas", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void alta() throws SQLException {
		Cajero cajero = new Cajero();
		cajero.setId(Integer.parseInt(txtID.getText()));
		cajero.setNombre(txtNombre.getText());
		cajero.setApellido(txtApellidos.getText());
		cajero.setEdad(Integer.parseInt(txtEdad.getText()));
		cajero.setPassword(txtPassword.getText());
		CajeroDao caj = new CajeroImp();
		if (caj.insertCajero(cajero) == 1) {
			JOptionPane.showMessageDialog(rootPane,
					"Cajero dado de alta satisfactoriamente");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(rootPane, "Ha ocurrido algun error");
		}
	}
}
