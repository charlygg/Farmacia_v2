package farmacia.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import farmacia.modelo.dto.Administrador;
import farmacia.modelo.dto.Cajero;
import farmacia.modelo.imp.CajeroImp;
import farmacia.utils.WallpaperBorder;

@SuppressWarnings("serial")
public class frmVentanaAdministrador extends JFrame implements ActionListener,
		Runnable {
	private JScrollPane scrollPane;
	private JPanel Panel;
	private JMenuBar menuBar;
	private JButton btnInfo;
	private JTextArea txAdminInfo;
	private JButton btnSalir;
	private JMenuItem submnuAltasDeMedicamento, submnuModificar,
			submnuNuevoAdministrador, submnuEditarMiInfo,
			mntmEliminarAdministrador, submnuBuscarMedicamento;
	private Administrador administrador;
	private JLabel lblHoraCambiante;
	Calendar calendario;
	Thread h1;
	private JMenuItem submnuNuevoCajero;
	private JMenuItem submnuCambiarDatos;
	private JMenu mnAcercaDe;
	private JMenuItem submnuAcerca;

	public frmVentanaAdministrador(Administrador administrador) {
		this.administrador = administrador;
		setResizable(false);
		setTitle("Menu principal");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 388);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnuArchivo = new JMenu("Medicinas");
		menuBar.add(mnuArchivo);

		submnuBuscarMedicamento = new JMenuItem("Consultar");
		submnuBuscarMedicamento.setIcon(new ImageIcon("Farmacia/iconos/1.png"));
		mnuArchivo.add(submnuBuscarMedicamento);
		submnuBuscarMedicamento.addActionListener(this);

		submnuAltasDeMedicamento = new JMenuItem("Altas");
		submnuAltasDeMedicamento.addActionListener(this);
		mnuArchivo.add(submnuAltasDeMedicamento);

		submnuModificar = new JMenuItem("Modificar");
		submnuModificar.addActionListener(this);
		mnuArchivo.add(submnuModificar);

		JMenu mnuAdministradores = new JMenu("Administradores");
		menuBar.add(mnuAdministradores);

		submnuNuevoAdministrador = new JMenuItem("Nuevo administrador");
		submnuNuevoAdministrador.addActionListener(this);
		mnuAdministradores.add(submnuNuevoAdministrador);

		submnuEditarMiInfo = new JMenuItem("Editar mi info");
		mnuAdministradores.add(submnuEditarMiInfo);
		submnuEditarMiInfo.addActionListener(this);

		mntmEliminarAdministrador = new JMenuItem("Eliminar administrador");
		mnuAdministradores.add(mntmEliminarAdministrador);

		JMenu mnuCajeros = new JMenu("Cajeros");
		menuBar.add(mnuCajeros);

		JMenuItem submnuVerTodos = new JMenuItem("Ver todos");
		mnuCajeros.add(submnuVerTodos);

		submnuNuevoCajero = new JMenuItem("Nuevo cajero");
		submnuNuevoCajero.addActionListener(this);
		mnuCajeros.add(submnuNuevoCajero);

		submnuCambiarDatos = new JMenuItem("Cambiar datos");
		submnuCambiarDatos.addActionListener(this);
		mnuCajeros.add(submnuCambiarDatos);
		mntmEliminarAdministrador.addActionListener(this);

		mnAcercaDe = new JMenu("Acerca de");
		menuBar.add(mnAcercaDe);

		submnuAcerca = new JMenuItem("Acerca");
		submnuAcerca.addActionListener(this);
		mnAcercaDe.add(submnuAcerca);

		Panel = new JPanel();
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		Panel.setLayout(null);
		Panel.setBackground(Color.CYAN);

		JLabel Et1 = new JLabel("MENU PRINCIPAL DE LA FARMACIA");
		Et1.setFont(new Font("Dialog", Font.BOLD, 18));
		Et1.setBounds(69, 28, 335, 23);
		Panel.add(Et1);

		JLabel Detalle = new JLabel("Informacion de la sesion");
		Detalle.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		Detalle.setForeground(Color.WHITE);
		Detalle.setBounds(26, 149, 201, 22);
		Panel.add(Detalle);

		btnInfo = new JButton("Editar Info");
		btnInfo.setIcon(new ImageIcon("icons/yo.png"));
		btnInfo.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		btnInfo.setBounds(279, 181, 180, 49);
		Panel.add(btnInfo);

		txAdminInfo = new JTextArea();
		txAdminInfo.setEditable(false);
		txAdminInfo.setLineWrap(false);
		txAdminInfo.setWrapStyleWord(false);
		scrollPane = new JScrollPane(txAdminInfo);
		scrollPane.setBounds(26, 181, 243, 99);
		Panel.add(scrollPane);

		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon("icons/App-exit-icon.png"));
		btnSalir.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		btnSalir.setBounds(279, 231, 180, 49);
		btnSalir.addActionListener(this);
		Panel.add(btnSalir);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 300, 469, 39);
		Panel.add(panel);
		panel.setLayout(null);

		JLabel lblHoraDelSistema = new JLabel("FECHA");
		lblHoraDelSistema.setForeground(Color.WHITE);
		lblHoraDelSistema.setFont(new Font("Calisto MT", Font.BOLD, 20));
		lblHoraDelSistema.setBounds(10, 0, 89, 39);
		panel.add(lblHoraDelSistema);

		lblHoraCambiante = new JLabel("HORA DEL SISTEMA");
		lblHoraCambiante.setForeground(Color.WHITE);
		lblHoraCambiante.setFont(new Font("Calisto MT", Font.BOLD, 20));
		lblHoraCambiante.setBounds(167, 0, 292, 39);
		panel.add(lblHoraCambiante);

		btnInfo.addActionListener(this);
		setAdminInfo();
		this.setLocationRelativeTo(null);
		setVisible(true);
		h1 = new Thread(this);
		h1.start();
		setFecha();
		setFondo("fondo.jpg");
	}

	private String setFecha() {
		Date hoy = new Date();
		DateFormat formato = DateFormat.getDateInstance();
		String dia = formato.format(hoy);

		String hora, minutos, segundos;
		Calendar calendario = new GregorianCalendar();
		Date fechaActual = new Date();

		calendario.setTime(fechaActual);
		String ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM"
				: "PM";

		if (ampm.endsWith("PM")) {
			int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
			hora = h > 9 ? "" + h : "0" + h;
		} else {
			hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? ""
					+ calendario.get(Calendar.HOUR_OF_DAY) : "0"
					+ calendario.get(Calendar.HOUR_OF_DAY);
		}

		minutos = calendario.get(Calendar.MINUTE) > 9 ? ""
				+ calendario.get(Calendar.MINUTE) : "0"
				+ calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND) > 9 ? ""
				+ calendario.get(Calendar.SECOND) : "0"
				+ calendario.get(Calendar.SECOND);

		return dia + " - " + hora + ":" + minutos + ":" + segundos + " " + ampm;

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

	private void setAdminInfo() {
		txAdminInfo.append("ID:" + administrador.getID());
		txAdminInfo.append("\nNombre: " + administrador.getNombre());
		txAdminInfo.append("\nApellidos: " + administrador.getApellido());
		txAdminInfo.append("\nEdad: " + administrador.getEdad());
		txAdminInfo.append("\nCorreo: " + administrador.getCorreo());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submnuAcerca){
			JOptionPane.showOptionDialog(rootPane,
					"Materia: Administracion de Sistemas de Informacion"
					+ "\nEste programa fue mejorado por: Equipo 4"
					+ "\nIntegrantes del equipo:"
					+ "\nJuan Trujillo  1495941"
					+ "\nIrma Andrea Salinas 1496953"
					+ "\nCynthia Jazmin Telles Garcia 1464182"
					+ "\nRaymundo Garcia Baca 1416243",
					"Acerca de", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}
		
		if (e.getSource() == btnInfo || e.getSource() == submnuEditarMiInfo) {
			new frmModificarMyInfo(administrador,txAdminInfo);
		}

		if (e.getSource() == submnuBuscarMedicamento) {
			new frmConsultaMedicamento();
		}

		if (e.getSource() == btnSalir) {
			System.exit(0);
		}

		if (e.getSource() == submnuNuevoAdministrador) {
			new frmAltaAdministrador();
		}

		if (e.getSource() == mntmEliminarAdministrador) {
			// new BajasAdmin(id);
		}

		if (e.getSource() == submnuAltasDeMedicamento) {
			new frmAltaMedicamento();
		}

		if (e.getSource() == submnuModificar) {
			String cve = JOptionPane
					.showInputDialog("Ingresa la clave del medicamento que se modificará");
			if (cve.equals("") || cve.equals(null)) {
				JOptionPane.showOptionDialog(rootPane, "No se ingreso nada",
						"Error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE, null, null, null);
			} else {
				new frmModificarMedicamento(cve);
			}
		}

		if (e.getSource() == submnuNuevoCajero) {
			new frmAltaCajeros();
		}

		if (e.getSource() == submnuCambiarDatos) {
			String id = JOptionPane
					.showInputDialog("Ingresa el ID del Cajero a modificar");
			Cajero cajero = CajeroImp.getCajero(Integer.parseInt(id));
			if (cajero == null) {
				JOptionPane.showOptionDialog(rootPane,
						"No se ha encontrado al cajero con el id " + id,
						"Error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE, null, null, null);
			} else {
				new frmModificarCajero(cajero,1,null);
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			lblHoraCambiante.setText(setFecha());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}