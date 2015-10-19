package farmacia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import farmacia.modelo.dao.AdministradorDao;
import farmacia.modelo.dto.Administrador;
import farmacia.modelo.imp.AdministradorImp;
import farmacia.utils.WallpaperBorder;

@SuppressWarnings("serial")
public class frmAltaAdministrador extends JFrame implements ActionListener {

	private final JPanel Panel = new JPanel();
	private JTextField txt_Nombre, txt_Apellidos, txt_Correo, txt_Edad, txt_ID;
	private JPasswordField txt_Contraseña;
	private JButton btnOk, btnCancelar, btnComprobar;
	private Administrador admin = new Administrador();


	public frmAltaAdministrador() {
		this.setResizable(false);
		setTitle("Nuevo Administrador");
		setBounds(100, 100, 465, 335);
		getContentPane().setLayout(new BorderLayout());
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(Panel, BorderLayout.CENTER);
		Panel.setLayout(null);

		JLabel lblIngresarElNombre = new JLabel("Nombre");
		lblIngresarElNombre.setForeground(Color.WHITE);
		lblIngresarElNombre.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblIngresarElNombre.setBounds(87, 101, 105, 14);
		Panel.add(lblIngresarElNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblApellidos.setBounds(87, 126, 105, 14);
		Panel.add(lblApellidos);

		JLabel lblFavorDeRellenar = new JLabel(
				"Favor de rellenar el siguiente formulario");
		lblFavorDeRellenar.setForeground(Color.WHITE);
		lblFavorDeRellenar.setFont(new Font("Calisto MT", Font.PLAIN, 22));
		lblFavorDeRellenar.setBounds(40, 22, 409, 27);
		Panel.add(lblFavorDeRellenar);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblCorreo.setBounds(87, 151, 105, 14);
		Panel.add(lblCorreo);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setForeground(Color.WHITE);
		lblEdad.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblEdad.setBounds(87, 176, 105, 14);
		Panel.add(lblEdad);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblContrasea.setBounds(87, 201, 105, 14);
		Panel.add(lblContrasea);

		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblId.setBounds(87, 76, 105, 14);
		Panel.add(lblId);

		txt_ID = new JTextField();
		txt_ID.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txt_ID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < '0' || c > '9') {
					e.consume();
				}
			}
		});
		txt_ID.setBounds(202, 76, 86, 20);
		Panel.add(txt_ID);
		txt_ID.setColumns(10);

		txt_Nombre = new JTextField();
		txt_Nombre.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txt_Nombre.setBounds(202, 101, 99, 20);
		Panel.add(txt_Nombre);
		txt_Nombre.setColumns(10);

		txt_Apellidos = new JTextField();
		txt_Apellidos.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txt_Apellidos.setBounds(202, 126, 139, 20);
		Panel.add(txt_Apellidos);
		txt_Apellidos.setColumns(10);

		txt_Correo = new JTextField();
		txt_Correo.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txt_Correo.setBounds(202, 151, 139, 20);
		Panel.add(txt_Correo);
		txt_Correo.setColumns(10);

		txt_Edad = new JTextField();
		txt_Edad.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txt_Edad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (txt_Edad.getText().length() == 3) {
					e.consume();
				}
				if (c < '0' || c > '9') {
					e.consume();
				}
			}
		});
		txt_Edad.setBounds(202, 176, 38, 20);
		Panel.add(txt_Edad);
		txt_Edad.setColumns(10);

		txt_Contraseña = new JPasswordField();
		txt_Contraseña.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txt_Contraseña.setEchoChar('*');
		txt_Contraseña.setBounds(202, 201, 139, 20);
		Panel.add(txt_Contraseña);
		txt_Contraseña.setColumns(10);

		btnOk = new JButton("Alta");
		btnOk.setIcon(new ImageIcon("icons/Save.png"));
		btnOk.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		btnOk.setBounds(82, 242, 139, 39);
		Panel.add(btnOk);
		btnOk.addActionListener(this);
		getRootPane().setDefaultButton(btnOk);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new  ImageIcon("icons/Delete.png"));
		btnCancelar.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		btnCancelar.setBounds(270, 242, 139, 39);
		Panel.add(btnCancelar);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.setIcon(new ImageIcon("icons/Select.png"));
		btnComprobar.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		btnComprobar.setBounds(310, 66, 139, 36);
		btnComprobar.addActionListener(this);
		Panel.add(btnComprobar);
		btnCancelar.addActionListener(this);
		setFondo("usuario.jpg");
		
		this.setVisible(true);
	}

	private void setFondo(String rutaImagen) {
		try {
			File file = new File(rutaImagen);
			BufferedImage image = ImageIO.read(file);
			WallpaperBorder wall = new WallpaperBorder(image);
			Panel.setBorder(wall);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnComprobar) {
			boolean existe;
			try {
				existe = AdministradorImp.isClaveExists(Integer.parseInt(txt_ID
						.getText()));
				if (existe) {
					JOptionPane.showOptionDialog(rootPane,
							"ID no disponible, elejir otro", "Error",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				} else {
					JOptionPane.showOptionDialog(rootPane, "ID disponible",
							"Altas", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (e.getSource() == btnOk) {
			alta();
		}
		
		if(e.getSource() == btnCancelar){
			this.dispose();
		}

	}

	@SuppressWarnings("deprecation")
	private void alta() {
		admin.setNombre(txt_Nombre.getText());
		admin.setID(Integer.parseInt(txt_ID.getText()));
		admin.setCorreo(txt_Correo.getText());
		admin.setApellido(txt_Apellidos.getText());
		admin.setPassword(txt_Contraseña.getText());
		admin.setEdad(Integer.parseInt(txt_Edad.getText()));

		try {
			AdministradorDao ad = new AdministradorImp();
			int res = ad.insertAdministrador(admin);
			if(res==1){
				JOptionPane.showMessageDialog(rootPane,
						"Admin. guardado correctamente");
				this.dispose();
			} else {
				JOptionPane.showOptionDialog(rootPane,
						"Ha ocurrido un error", "Error",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE, null, null, null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
