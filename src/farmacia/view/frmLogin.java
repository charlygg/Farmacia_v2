package farmacia.view;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import farmacia.modelo.dto.Administrador;
import farmacia.modelo.dto.Cajero;
import farmacia.modelo.imp.AdministradorImp;
import farmacia.modelo.imp.CajeroImp;
import farmacia.utils.WallpaperBorder;

@SuppressWarnings("serial")
public class frmLogin extends JFrame implements ActionListener {

	public JTextField txUser;
	public JPasswordField txPassword;
	public JButton btnIngresar, btnSalir;
	public JComboBox<String> cbTipo;
	private AdministradorImp administradorImp = new AdministradorImp();
	private CajeroImp cajeroImp = new CajeroImp();
	private Administrador administrador;
	private Cajero cajero;
	private final static int ADMINISTRADOR = 1;
	private final static int CAJERO = 2;

	public static void main(String[] args) {
		frmLogin dialog = new frmLogin();
		dialog.setDefaultCloseOperation(EXIT_ON_CLOSE);
		dialog.setVisible(true);
	}

	public frmLogin() {
		setResizable(false);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Ingresar al sistema");
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		getContentPane().setForeground(Color.green);

		JLabel u = new JLabel("USER");
		u.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		u.setBounds(68, 107, 62, 14);
		getContentPane().add(u);

		txUser = new JTextField(10);
		txUser.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		txUser.setBounds(191, 96, 141, 29);
		getContentPane().add(txUser);

		JLabel p = new JLabel("PASSWORD");
		p.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		p.setBounds(68, 155, 113, 14);
		getContentPane().add(p);

		txPassword = new JPasswordField(10);
		txPassword.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		txPassword.setEchoChar('*');
		txPassword.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {

				}
			}
		});
		txPassword.setBounds(191, 144, 141, 29);
		getContentPane().add(txPassword);

		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnIngresar.setBounds(28, 204, 168, 45);
		btnIngresar.addActionListener(this);
		btnIngresar.setIcon(new ImageIcon("icons/App-key-icon.png"));
		getContentPane().add(btnIngresar);

		btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnSalir.setBounds(216, 204, 148, 45);
		btnSalir.addActionListener(this);
		btnSalir.setIcon(new ImageIcon("icons/App-exit-icon.png"));
		getContentPane().add(btnSalir);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		lblTipo.setBounds(68, 48, 113, 14);
		getContentPane().add(lblTipo);

		cbTipo = new JComboBox<String>();
		cbTipo.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		cbTipo.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Seleccione uno--", "Administrador", "Cajero" }));
		cbTipo.setBounds(156, 43, 176, 29);
		getContentPane().add(cbTipo);
		this.setLocationRelativeTo(this);
		setFondo("pan.jpg");
	}
	
	private void setFondo(String rutaImagen) {
		try {
			String file = new File("").getAbsolutePath();
			JOptionPane.showConfirmDialog(null, file);
			File bFile = new File(file+"/"+rutaImagen);
			BufferedImage image = ImageIO.read(bFile);
			WallpaperBorder wall = new WallpaperBorder(image);
			((JComponent) getContentPane()).setBorder(wall);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnIngresar) {
			int index = cbTipo.getSelectedIndex();
			if (index == ADMINISTRADOR) {
				try {
					administrador = administradorImp.getLogueoAdministrador(txUser.getText(), txPassword.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(administrador!=null){
					JOptionPane.showMessageDialog(rootPane, "Bienvenido " + administrador.getNombre());
					new frmVentanaAdministrador(administrador);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(rootPane, "No existe el usuario y/o password erroneos");
				}
			} else if(index == CAJERO){
				try {
					cajero = cajeroImp.getLogueoCajero(Integer.parseInt(txUser.getText()), txPassword.getText());
					if(cajero != null){
						JOptionPane.showMessageDialog(rootPane, "Bienvenido " + cajero.getNombre());
						new frmVentanaCajero(cajero);
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(rootPane, "No existe el usuario y/o password erroneos");
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(evt.getSource() == btnSalir){
			System.exit(0);
		}

	}

}
