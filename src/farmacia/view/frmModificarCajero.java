package farmacia.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import farmacia.modelo.dao.CajeroDao;
import farmacia.modelo.dto.Cajero;
import farmacia.modelo.imp.CajeroImp;
import farmacia.utils.WallpaperBorder;

@SuppressWarnings("serial")
public class frmModificarCajero extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtEdad;
	private JTextField txtApellidos;
	private JTextField txtNombre;
	private JTextField txtID;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private Cajero cajero;
	private int privilegio;
	private JTextArea txAdminInfo;
	
	public frmModificarCajero(Cajero cajero, int i, JTextArea txAdminInfo) {
		this.txAdminInfo = txAdminInfo;
		this.privilegio = i;
		this.cajero = cajero;
		setTitle("Modificar un cajero");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 395, 290);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label.setBounds(59, 79, 105, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label_1.setBounds(59, 104, 105, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Apellidos");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label_2.setBounds(59, 129, 105, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Edad");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		label_3.setBounds(59, 155, 105, 14);
		contentPane.add(label_3);
		
		txtEdad = new JTextField();
		txtEdad.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtEdad.setColumns(10);
		txtEdad.setBounds(174, 153, 38, 20);
		contentPane.add(txtEdad);
		
		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(174, 129, 139, 20);
		contentPane.add(txtApellidos);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(174, 104, 99, 20);
		contentPane.add(txtNombre);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		txtID.setColumns(10);
		txtID.setBounds(174, 79, 99, 20);
		contentPane.add(txtID);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon("icons/Save.png"));
		btnGuardar.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnGuardar.setBounds(31, 191, 139, 39);
		btnGuardar.addActionListener(this);
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("icons/Delete.png"));
		btnCancelar.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnCancelar.setBounds(221, 191, 132, 39);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
		
		JLabel lblModificacionesDeLa = new JLabel("Modificaciones del cajero");
		lblModificacionesDeLa.setForeground(Color.BLACK);
		lblModificacionesDeLa.setFont(new Font("Calisto MT", Font.PLAIN, 22));
		lblModificacionesDeLa.setBounds(77, 27, 286, 27);
		contentPane.add(lblModificacionesDeLa);
		setVisible(true);
		rellenarCampos();
		setFondo("gdgdgd.jpg");
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

	private void rellenarCampos() {
		txtID.setText(""+cajero.getId());
		txtNombre.setText(cajero.getNombre());
		txtApellidos.setText(cajero.getApellido());
		txtEdad.setText(""+cajero.getEdad());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnGuardar){
			try {
				modificar();
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == btnCancelar){
			this.dispose();
		}
		
	}

	private void modificar() throws HeadlessException, SQLException {
		cajero.setNombre(txtNombre.getText());
		cajero.setApellido(txtApellidos.getText());
		cajero.setEdad(Integer.parseInt(txtEdad.getText()));
		CajeroDao caj = new CajeroImp();
		if (caj.modificarCajero(cajero, Integer.parseInt(txtID.getText())) == 1) {
			if(privilegio == 1){
				JOptionPane.showMessageDialog(rootPane,
						"Cajero modificado correctamente");
			} else {
				txAdminInfo.setText("");
				setCajeroInfo();
				JOptionPane.showMessageDialog(rootPane,
						"Informacion actualizada");
			}

			this.dispose();
		} else {
			JOptionPane.showMessageDialog(rootPane, "Ha ocurrido algun error");
		}
		
	}
	
	private void setCajeroInfo() {
		txAdminInfo.append("ID:" + cajero.getId());
		txAdminInfo.append("\nNombre: " + cajero.getNombre());
		txAdminInfo.append("\nApellidos: " + cajero.getApellido());
		txAdminInfo.append("\nEdad: " + cajero.getEdad());
	}

}
