package farmacia.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import farmacia.modelo.dao.MedicamentoDao;
import farmacia.modelo.dto.Medicamento;
import farmacia.modelo.imp.MedicamentoImp;

@SuppressWarnings("serial")
public class frmCajaBusqueda extends JFrame implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnCerrar;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private String seleccionador;
	private MedicamentoImp medicamentoImp = new MedicamentoImp();
	private DefaultTableModel model;
	private JTextField txtBusqueda;
	private JComboBox<Object> cbBusqueda;
	private JButton btnFiltrar;
	private JButton btnInfo;

	public frmCajaBusqueda() {
		setResizable(false);
		String[] sr = { "Clave", "Nombre", "Precio" };
		setTitle("Consulta de medicamentos");
		setBounds(100, 100, 801, 632);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		model = new DefaultTableModel(null, new String[] { "Clave", "Nombre",
				"Precio", "Marca", "Pasillo", "Estante", "Repisa", "Baja" });

		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;// the 4th column is not editable
				}
				return true;
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				case 7:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		table.setFont(new Font("Albertus MT", Font.PLAIN, 11));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(21, 91, 750, 455);
		contentPanel.add(scroll);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(new ImageIcon("icons/cancelar.png"));
		btnCerrar.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		btnCerrar.setBounds(605, 557, 149, 36);
		btnCerrar.addActionListener(this);
		contentPanel.add(btnCerrar);

		JPanel panelInfo = new JPanel();
		panelInfo.setOpaque(false);
		panelInfo.setBorder(new TitledBorder(null, "Operaciones",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfo.setBounds(21, 11, 292, 60);
		contentPanel.add(panelInfo);
		panelInfo.setLayout(null);

		JLabel lblMostrarPor = new JLabel("INDEXAR");
		lblMostrarPor.setBounds(10, 27, 213, 14);
		panelInfo.add(lblMostrarPor);
		lblMostrarPor.setFont(new Font("Calisto MT", Font.PLAIN, 18));

		comboBox = new JComboBox<Object>(sr);
		comboBox.setBounds(144, 20, 138, 28);
		panelInfo.add(comboBox);
		comboBox.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		comboBox.addActionListener(this);

		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setOpaque(false);
		panelBusqueda.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Busqueda de producto",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBusqueda.setBounds(323, 11, 448, 60);
		contentPanel.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(141, 21, 149, 25);
		panelBusqueda.add(txtBusqueda);
		txtBusqueda.setColumns(10);

		btnInfo = new JButton("Info");
		btnInfo.setIcon(new ImageIcon("icons/info.png"));
		btnInfo.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		btnInfo.setBounds(41, 557, 125, 36);
		btnInfo.addActionListener(this);
		contentPanel.add(btnInfo);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setIcon(new ImageIcon("icons/Action-find-icon.png"));
		btnFiltrar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnFiltrar.setBounds(300, 14, 127, 38);
		btnFiltrar.addActionListener(this);
		panelBusqueda.add(btnFiltrar);

		cbBusqueda = new JComboBox<Object>(new String[] { "Nombre", "Clave",
				"Precio", "Marca", "Farmaco", "Pasillo", "Estante", "Repisa" });
		cbBusqueda.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		cbBusqueda.setBounds(10, 18, 121, 28);
		panelBusqueda.add(cbBusqueda);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("k.jpg"));
		fondo.setBounds(0, 0, 795, 604);
		contentPanel.add(fondo);

		setVisible(true);
		crearTabla("Clave");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnCerrar) {
			this.dispose();
		}

		if (e.getSource() == comboBox) {
			seleccionador = (String) comboBox.getSelectedItem();
			if (seleccionador.equals("Nombre")) {
				crearTabla("Nombre");
			} else if (seleccionador.equals("Clave")) {
				crearTabla("Clave");
			} else if (seleccionador.equals("Precio")) {
				crearTabla("Precio");
			}
		}

		if (e.getSource() == btnFiltrar) {
			setFiltro((String) cbBusqueda.getSelectedItem());
		}

		if (e.getSource() == btnInfo) {
			getInfo();
		}

	}

	private void crearTabla(String tabla) {
		ArrayList<Medicamento> meds = new ArrayList<Medicamento>();
		try {
			meds = medicamentoImp.getListadoMedicamentos(tabla);
			model = new DefaultTableModel(null, new String[] { "Clave",
					"Nombre", "Precio", "Marca", "Pasillo", "Estante",
					"Repisa", "Seleccion" });
			for (Medicamento m : meds) {
				Object[] arreglo = new Object[7];
				arreglo[0] = m.getClave();
				arreglo[1] = m.getNombre();
				arreglo[2] = m.getPrecio();
				arreglo[3] = m.getMarca().getMarca();
				arreglo[4] = m.getUbicacion().getPasillo();
				arreglo[5] = m.getUbicacion().getEstante();
				arreglo[6] = m.getUbicacion().getRepisa();
				model.addRow(arreglo);
			}
			table.setModel(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void setFiltro(String filtro) {
		ArrayList<Medicamento> meds = new ArrayList<Medicamento>();
		try {
			meds = medicamentoImp.filtrarTabla(filtro.toLowerCase(),
					txtBusqueda.getText());
			model = new DefaultTableModel(null, new String[] { "Clave",
					"Nombre", "Precio", "Marca", "Pasillo", "Estante",
					"Repisa", "Seleccion" });
			for (Medicamento m : meds) {
				Object[] arreglo = new Object[7];
				arreglo[0] = m.getClave();
				arreglo[1] = m.getNombre();
				arreglo[2] = m.getPrecio();
				arreglo[3] = m.getMarca().getMarca();
				arreglo[4] = m.getUbicacion().getPasillo();
				arreglo[5] = m.getUbicacion().getEstante();
				arreglo[6] = m.getUbicacion().getRepisa();
				model.addRow(arreglo);
			}
			table.setModel(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getInfo() {
		ArrayList<Medicamento> listadosMedicamentos = new ArrayList<Medicamento>();
		for (int i = 0; i < table.getRowCount(); i++) {
			boolean isChecked = false;
			System.out.println(table.getValueAt(i, 0));
			try {
				isChecked = (Boolean) table.getValueAt(i, 7);
			} catch (Exception ex) {
			}
			if (isChecked) {
				String clave = (String) table.getValueAt(i, 0);
				System.out.println(clave);
				MedicamentoDao lmed = new MedicamentoImp();
				try {
					listadosMedicamentos.add(lmed.getMedicamentoById(clave));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (listadosMedicamentos != null)
			new frmConsultaInfo(listadosMedicamentos);
	}

}
