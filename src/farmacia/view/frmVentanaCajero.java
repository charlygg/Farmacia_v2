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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import farmacia.modelo.dto.Cajero;
import farmacia.utils.WallpaperBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class frmVentanaCajero extends JFrame implements ActionListener, Runnable{

	private JScrollPane scrollPane;
	private JPanel Panel;
	private JButton btnInfo;
	private JTextArea txAdminInfo;
	private JButton btnSalir;
	private JLabel lblHoraCambiante;
	private Cajero cajero;
	private JMenuBar menuBar;
	private JMenuItem mntmVender;
	private JMenuItem mntmConsultar;
	private JMenuItem mntmEditarInfo;
	Calendar calendario;
	Thread h1;

	public frmVentanaCajero(Cajero cajero) {
		this.cajero = cajero;
		getContentPane().setLayout(null);
		setResizable(false);
		setTitle("Menu principal");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 380);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOperaciones = new JMenu("Operaciones");
		menuBar.add(mnOperaciones);
		
		mntmVender = new JMenuItem("Vender");
		mntmVender.addActionListener(this);
		mnOperaciones.add(mntmVender);
		
		mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(this);
		mnOperaciones.add(mntmConsultar);
		
		mntmEditarInfo = new JMenuItem("Editar info");
		mntmEditarInfo.addActionListener(this);
		mnOperaciones.add(mntmEditarInfo);

		Panel = new JPanel();
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		Panel.setLayout(null);
		Panel.setBackground(Color.CYAN);

		JLabel Et1 = new JLabel("MENU DEL CAJERO");
		Et1.setFont(new Font("Dialog", Font.BOLD, 18));
		Et1.setBounds(146, 27, 201, 23);
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
		txAdminInfo.setLineWrap(true);
		txAdminInfo.setWrapStyleWord(true);
		txAdminInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txAdminInfo.setEditable(false);
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
		setCajeroInfo();
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

	private void setCajeroInfo() {
		txAdminInfo.append("ID:" + cajero.getId());
		txAdminInfo.append("\nNombre: " + cajero.getNombre());
		txAdminInfo.append("\nApellidos: " + cajero.getApellido());
		txAdminInfo.append("\nEdad: " + cajero.getEdad());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mntmConsultar){
			new frmCajaBusqueda();
		}
		
		if(e.getSource() == btnSalir){
			System.exit(0);
		}
		
		if(e.getSource() == btnInfo || mntmEditarInfo == e.getSource()){
			new frmModificarCajero(cajero,0,txAdminInfo);
		}
		
		if(e.getSource() == mntmVender){
			new frmCaja(cajero);
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
