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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import farmacia.modelo.dao.MedicamentoDao;
import farmacia.modelo.dao.UbicacionDao;
import farmacia.modelo.dto.Marca;
import farmacia.modelo.dto.Medicamento;
import farmacia.modelo.dto.Oferta;
import farmacia.modelo.dto.Tipo;
import farmacia.modelo.dto.Ubicacion;
import farmacia.modelo.imp.MarcaImp;
import farmacia.modelo.imp.MedicamentoImp;
import farmacia.modelo.imp.TipoImp;
import farmacia.modelo.imp.UbicacionImp;
import farmacia.utils.WallpaperBorder;

public class frmModificarMedicamento extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JComboBox<Object> cb_tipo, cb_pasillo, cb_estante, cb_repisa;
	private JButton btnAceptar, btnCancelar;
	private JTextField txt_nombre, txt_codigo, txt_contenido, txt_presentacion,
			txt_precio, txt_existencia;
	String tipo[] = { "---------------", "Ingerible", "Inyectable", "Soluble",
			"Untable" };
	String genero[] = { "---------------", "Analgésico", "Anestésico",
			"Antihistamínico", "Antibiótico", "Anticéptico", "Antiácido",
			"Antiflamatorio", "Antihipertensivo", "Anticonceptivo",
			"Vitamínico" };
	String pasillo[] = { "----", "1", "2", "3" };
	String estante[] = { "----", "1", "2", "3", "4" };
	String repisa[] = { "----", "BAJO", "MEDIO", "ALTO" };
	String type, eltipo, gen, ubi;
	private JComboBox<Object> cb_marca;
	private JTextField txt_farmaco;
	private Medicamento medicamento;

	private UbicacionDao ubicacion = new UbicacionImp();

	public frmModificarMedicamento(String clave) {
		this.setResizable(false);
		setFont(new Font("Albertus MT", Font.PLAIN, 14));
		setTitle("Modificar la informacion del medicamento");
		setLocationRelativeTo(this);
		setBounds(100, 100, 425, 555);
		getContentPane().setLayout(null);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon("icons/Save.png"));
		btnAceptar.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		btnAceptar.setBounds(54, 473, 130, 32);
		btnAceptar.addActionListener(this);
		getContentPane().add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("icons/Delete.png"));
		btnCancelar.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		btnCancelar.setBounds(248, 473, 130, 32);
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		JPanel pnlInfo = new JPanel();
		pnlInfo.setOpaque(false);
		pnlInfo.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Informacion basica del medicamento", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.WHITE));
		pnlInfo.setBounds(10, 14, 400, 230);
		getContentPane().add(pnlInfo);
		pnlInfo.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(26, 57, 63, 14);
		pnlInfo.add(lblNombre);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Calisto MT", Font.PLAIN, 14));

		txt_nombre = new JTextField();
		txt_nombre.setBounds(139, 54, 141, 20);
		pnlInfo.add(txt_nombre);
		txt_nombre.setFont(new Font("Albertus MT", Font.PLAIN, 12));
		txt_nombre.setColumns(10);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(26, 26, 101, 14);
		pnlInfo.add(lblCdigo);
		lblCdigo.setForeground(Color.WHITE);
		lblCdigo.setFont(new Font("Calisto MT", Font.PLAIN, 14));

		txt_codigo = new JTextField();
		txt_codigo.setBounds(139, 23, 86, 20);
		pnlInfo.add(txt_codigo);
		txt_codigo.setFont(new Font("Albertus MT", Font.PLAIN, 12));
		txt_codigo.setEditable(false);
		txt_codigo.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(26, 128, 46, 14);
		pnlInfo.add(lblTipo);
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Calisto MT", Font.PLAIN, 14));

		cb_tipo = new JComboBox<Object>(tipo);
		cb_tipo.setBounds(139, 125, 141, 20);
		pnlInfo.add(cb_tipo);
		cb_tipo.setFont(new Font("Albertus MT", Font.PLAIN, 12));

		txt_presentacion = new JTextField();
		txt_presentacion.setBounds(139, 160, 86, 20);
		pnlInfo.add(txt_presentacion);
		txt_presentacion.setFont(new Font("Albertus MT", Font.PLAIN, 12));

		JLabel lblContenidomg = new JLabel("Contenido (mg)");
		lblContenidomg.setBounds(26, 194, 114, 14);
		pnlInfo.add(lblContenidomg);
		lblContenidomg.setForeground(Color.WHITE);
		lblContenidomg.setFont(new Font("Calisto MT", Font.PLAIN, 14));

		txt_contenido = new JTextField();
		txt_contenido.setBounds(139, 191, 141, 20);
		pnlInfo.add(txt_contenido);
		txt_contenido.setFont(new Font("Albertus MT", Font.PLAIN, 12));
		txt_contenido.setColumns(10);

		JLabel lblcont = new JLabel("Cantidad");
		lblcont.setBounds(27, 164, 100, 14);
		pnlInfo.add(lblcont);
		lblcont.setForeground(Color.WHITE);
		lblcont.setFont(new Font("Calisto MT", Font.PLAIN, 14));

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(26, 90, 46, 14);
		pnlInfo.add(lblPrecio);
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setFont(new Font("Calisto MT", Font.PLAIN, 14));

		txt_precio = new JTextField();
		txt_precio.setBounds(139, 85, 141, 20);
		pnlInfo.add(txt_precio);
		txt_precio.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if (txt_precio.getText().length() == 5) {
					e.consume();
				}
				if (car < '0' || car > '9') {
					e.consume();
				}
			}
		});
		txt_precio.setFont(new Font("Albertus MT", Font.PLAIN, 12));
		txt_precio.setColumns(10);

		JLabel lblEjemTabletas = new JLabel("ejem: 14 tabletas, 250 ml...");
		lblEjemTabletas.setBounds(235, 163, 165, 14);
		pnlInfo.add(lblEjemTabletas);
		lblEjemTabletas.setFont(new Font("Calisto MT", Font.PLAIN, 13));
		lblEjemTabletas.setForeground(Color.LIGHT_GRAY);

		JPanel pnlUbicacion = new JPanel();
		pnlUbicacion.setOpaque(false);
		pnlUbicacion.setBorder(new TitledBorder(null,
				"Ubicacion del medicamento", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.WHITE));
		pnlUbicacion.setBounds(10, 256, 398, 81);
		getContentPane().add(pnlUbicacion);
		pnlUbicacion.setLayout(null);

		JLabel lblPasillo = new JLabel("Pasillo");
		lblPasillo.setBounds(32, 23, 46, 14);
		pnlUbicacion.add(lblPasillo);
		lblPasillo.setForeground(Color.WHITE);
		lblPasillo.setFont(new Font("Calisto MT", Font.PLAIN, 12));

		JLabel lblEstante = new JLabel("Estante");
		lblEstante.setBounds(159, 22, 46, 14);
		pnlUbicacion.add(lblEstante);
		lblEstante.setForeground(Color.WHITE);
		lblEstante.setFont(new Font("Calisto MT", Font.PLAIN, 12));

		JLabel lblRepisa = new JLabel("Repisa");
		lblRepisa.setBounds(292, 23, 46, 14);
		pnlUbicacion.add(lblRepisa);
		lblRepisa.setForeground(Color.WHITE);
		lblRepisa.setFont(new Font("Calisto MT", Font.PLAIN, 12));

		cb_pasillo = new JComboBox<Object>(pasillo);
		cb_pasillo.setBounds(32, 37, 46, 20);
		pnlUbicacion.add(cb_pasillo);
		cb_pasillo.setFont(new Font("Albertus MT", Font.PLAIN, 12));

		cb_estante = new JComboBox<Object>(estante);
		cb_estante.setBounds(159, 37, 46, 20);
		pnlUbicacion.add(cb_estante);
		cb_estante.setFont(new Font("Albertus MT", Font.PLAIN, 12));

		cb_repisa = new JComboBox<Object>(repisa);
		cb_repisa.setBounds(281, 37, 70, 20);
		pnlUbicacion.add(cb_repisa);
		cb_repisa.setFont(new Font("Albertus MT", Font.PLAIN, 12));

		JPanel pnlGenero = new JPanel();
		pnlGenero.setOpaque(false);
		pnlGenero.setBorder(new TitledBorder(null, "Genero y farmaco",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlGenero.setBounds(10, 349, 398, 112);
		getContentPane().add(pnlGenero);
		pnlGenero.setLayout(null);

		JLabel lblMarcaDeMedicamento = new JLabel("G\u00E9nero de medicamento");
		lblMarcaDeMedicamento.setBounds(23, 24, 160, 14);
		pnlGenero.add(lblMarcaDeMedicamento);
		lblMarcaDeMedicamento.setForeground(Color.WHITE);
		lblMarcaDeMedicamento.setFont(new Font("Calisto MT", Font.PLAIN, 12));

		cb_marca = new JComboBox<Object>(genero);
		cb_marca.setBounds(177, 21, 124, 20);
		pnlGenero.add(cb_marca);
		cb_marca.setFont(new Font("Albertus MT", Font.PLAIN, 12));

		txt_farmaco = new JTextField();
		txt_farmaco.setBounds(177, 49, 126, 20);
		pnlGenero.add(txt_farmaco);
		txt_farmaco.setFont(new Font("Albertus MT", Font.PLAIN, 12));
		txt_farmaco.setColumns(10);

		JLabel lblExistencias = new JLabel("Existencias disponibles");
		lblExistencias.setBounds(32, 83, 129, 14);
		pnlGenero.add(lblExistencias);
		lblExistencias.setForeground(Color.WHITE);
		lblExistencias.setFont(new Font("Calisto MT", Font.PLAIN, 12));

		JLabel lblPrincipioActivo = new JLabel("Principio activo");
		lblPrincipioActivo.setBounds(68, 55, 103, 14);
		pnlGenero.add(lblPrincipioActivo);
		lblPrincipioActivo.setForeground(Color.WHITE);
		lblPrincipioActivo.setFont(new Font("Calisto MT", Font.PLAIN, 12));

		txt_existencia = new JTextField();
		txt_existencia.setBounds(177, 80, 38, 20);
		pnlGenero.add(txt_existencia);
		txt_existencia.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (txt_existencia.getText().length() == 3) {
					e.consume();
				}
				if (c < '0' || c > '9') {
					e.consume();
				}
			}
		});
		txt_existencia.setFont(new Font("Albertus MT", Font.PLAIN, 12));
		txt_existencia.setColumns(10);
		cb_marca.addActionListener(this);
		cb_tipo.addActionListener(this);

		setFondo("med11.jpg");
		llenarCampos(clave);
	}

	private void llenarCampos(String cve) {
		try {
			MedicamentoDao med = new MedicamentoImp();
			this.medicamento = med.getMedicamentoById(cve);
			/* Rellenando los campos */
			if (this.medicamento != null) {
				cb_tipo.setSelectedItem(medicamento.getTipo().getTipo());
				cb_pasillo.setSelectedItem(""
						+ medicamento.getUbicacion().getPasillo());
				cb_estante.setSelectedItem(""
						+ medicamento.getUbicacion().getEstante());
				cb_repisa.setSelectedItem(medicamento.getUbicacion()
						.getRepisa());
				txt_nombre.setText(medicamento.getNombre());
				txt_codigo.setText(medicamento.getClave());
				txt_contenido.setText(medicamento.getConcentracion());
				txt_presentacion.setText(medicamento.getPresentacion());
				float precio = medicamento.getPrecio();
				int preciod = (int) precio;
				txt_precio.setText("" + preciod);
				txt_existencia.setText("" + medicamento.getExistencia());
				cb_marca.setSelectedItem(medicamento.getMarca().getMarca());
				txt_farmaco.setText(medicamento.getFarmaco());
				setVisible(true);
			} else {
				JOptionPane.showOptionDialog(rootPane,
						"No existe medicamento con esa clave", "Error",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, null, null);
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setFondo(String rutaImagen) {
		try {
			File file = new File(rutaImagen);
			BufferedImage image = ImageIO.read(file);
			WallpaperBorder wall = new WallpaperBorder(image);
			((JComponent) getContentPane()).setBorder(wall);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			this.dispose();
		}

		if (e.getSource() == btnAceptar) {
			try {
				modificar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void modificar() throws SQLException {
		if (txt_nombre.getText().equals("") || txt_codigo.getText().equals("")
				|| txt_contenido.getText().equals("")
				|| txt_contenido.getText().equals("")
				|| txt_farmaco.getText().equals("")
				|| txt_presentacion.getText().equals("")
				|| txt_precio.getText().equals("")
				|| txt_existencia.getText().equals("")
				|| cb_tipo.getSelectedItem().equals("---------------")
				|| cb_pasillo.getSelectedItem().equals("----")
				|| cb_estante.getSelectedItem().equals("----")
				|| cb_repisa.getSelectedItem().equals("----")
				|| cb_marca.getSelectedItem().equals("---------------")) {

			JOptionPane.showOptionDialog(rootPane,
					"Falta rellenar uno o mas campos", "Error",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, null, null);
		} else {

			String pasillo = (String) cb_pasillo.getSelectedItem();
			String estante = (String) cb_estante.getSelectedItem();
			String repisa = (String) cb_repisa.getSelectedItem();
			boolean respuesta = ubicacion.isUbicacionCorrect(pasillo, estante,
					repisa);
			if (respuesta) {
				int idUbicacion = ubicacion.getIdUbicacion(pasillo, estante,
						repisa);
				/* Obtencion de datos */
				String strTipo = (String) cb_tipo.getSelectedItem();
				int idTipo = TipoImp.getIdTipoBD(strTipo);

				String strMarca = (String) cb_marca.getSelectedItem();
				int idMarca = MarcaImp.getIdMarca(strMarca);

				String codigow = txt_codigo.getText();
				String nombrew = txt_nombre.getText();
				String concentracionw = txt_contenido.getText();
				String farmacow = txt_farmaco.getText();
				String preciow = txt_precio.getText();
				String presentacionw = txt_presentacion.getText();
				String existenciaw = txt_existencia.getText();

				try {
					/* Asingacion de datos y actualizacion */
					medicamento.setClave(codigow);
					medicamento.setNombre(nombrew);
					medicamento.setPrecio(Float.parseFloat(preciow));
					Ubicacion ub = new Ubicacion(idUbicacion,
							Integer.parseInt(pasillo),
							Integer.parseInt(estante), repisa);
					medicamento.setUbicacion(ub);
					Tipo tipo = new Tipo(idTipo, strTipo);
					medicamento.setTipo(tipo);
					Marca marca = new Marca(idMarca, strMarca);
					Oferta oferta = new Oferta(4, "some");
					medicamento.setMarca(marca);
					medicamento.setOferta(oferta);
					medicamento.setFarmaco(farmacow);
					medicamento.setExistencia(existenciaw);
					medicamento.setConcentracion(concentracionw);
					medicamento.setPresentacion(presentacionw);

					MedicamentoDao med = new MedicamentoImp();
					if (med.updateMedicamento(txt_codigo.getText(), medicamento) == 1) {
						JOptionPane.showMessageDialog(rootPane,
								"Guardado con éxito");
						this.dispose();
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			} else
				JOptionPane.showOptionDialog(rootPane,
						"Ubicacion incongruente", "Error",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
						null, null, null);

		}

	}
}
