package farmacia.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import farmacia.modelo.dto.Cajero;
import farmacia.modelo.dto.DetVenta;
import farmacia.modelo.dto.Medicamento;
import farmacia.modelo.dto.Venta;
import farmacia.modelo.imp.MedicamentoImp;
import farmacia.modelo.imp.VentaImp;
import farmacia.utils.WallpaperBorder;

@SuppressWarnings("serial")
public class frmCaja extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtClave;
	private JTextField txtTotal;
	private JTextField txtPago;
	private JTextField txtArticulos;
	private JLabel lblCajero;
	private JButton btnCobrar;
	private JButton btnBuscar;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Medicamento> listadoMedicamentos = new ArrayList<Medicamento>();
	private ArrayList<DetVenta> listaDetalle = new ArrayList<DetVenta>();
	static double total = 0;
	static int numMedicinas = 0;
	private Cajero cajero;

	public frmCaja(Cajero cajero) {
		this.cajero = cajero;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 724, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblClaveDeMedicamento = new JLabel("Clave de medicamento");
		lblClaveDeMedicamento.setFont(new Font("Arial", Font.PLAIN, 30));
		lblClaveDeMedicamento.setBounds(21, 11, 323, 38);
		contentPane.add(lblClaveDeMedicamento);

		txtClave = new JTextField();
		txtClave.setFont(new Font("Arial", Font.PLAIN, 20));
		txtClave.setColumns(10);
		txtClave.setBounds(349, 15, 159, 38);
		contentPane.add(txtClave);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBuscar.setBounds(537, 15, 159, 38);
		contentPane.add(btnBuscar);

		JLabel label_1 = new JLabel("Usuario");
		label_1.setFont(new Font("Arial", Font.PLAIN, 20));
		label_1.setBounds(10, 267, 88, 25);
		contentPane.add(label_1);

		lblCajero = new JLabel("lblUsuario");
		lblCajero.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCajero.setBounds(98, 267, 351, 25);
		lblCajero.setText(cajero.getNombre() + " " + cajero.getApellido());
		contentPane.add(lblCajero);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(537, 268, 159, 25);
		contentPane.add(txtTotal);

		txtPago = new JTextField();
		txtPago.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPago.setColumns(10);
		txtPago.setBounds(537, 305, 159, 25);
		contentPane.add(txtPago);

		JLabel label_3 = new JLabel("Total");
		label_3.setFont(new Font("Arial", Font.PLAIN, 18));
		label_3.setBounds(461, 268, 77, 25);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("Pago");
		label_4.setFont(new Font("Arial", Font.PLAIN, 18));
		label_4.setBounds(461, 305, 77, 25);
		contentPane.add(label_4);

		btnCobrar = new JButton("Cobrar");
		btnCobrar.addActionListener(this);
		btnCobrar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCobrar.setBounds(537, 353, 159, 38);
		contentPane.add(btnCobrar);

		model = new DefaultTableModel(null, new String[] { "Clave", "Nombre",
				"Precio" });
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int opcion = JOptionPane.showOptionDialog(rootPane,
						"Desea remover la fila seleccionada?", "Remover",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (opcion == JOptionPane.YES_OPTION) {
					int fila = table.getSelectedRow();
					listadoMedicamentos.remove(fila);
					total = (total - (Float) table.getValueAt(fila, 2));
					numMedicinas--;
					model.removeRow(fila);
					txtTotal.setText(""+total);
					txtArticulos.setText(""+numMedicinas);
				}
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 77, 686, 179);
		contentPane.add(scrollPane);

		JLabel lblArticulos = new JLabel("Articulos");
		lblArticulos.setFont(new Font("Arial", Font.PLAIN, 20));
		lblArticulos.setBounds(10, 296, 88, 25);
		contentPane.add(lblArticulos);

		txtArticulos = new JTextField();
		txtArticulos.setFont(new Font("Arial", Font.PLAIN, 15));
		txtArticulos.setEditable(false);
		txtArticulos.setColumns(10);
		txtArticulos.setBounds(98, 298, 126, 25);
		contentPane.add(txtArticulos);
		setVisible(true);
		setFondo("caja.jpg");
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

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			try {
				String clave = txtClave.getText();
				MedicamentoImp medImp = new MedicamentoImp();
				Medicamento medicamento = medImp.getMedicamentoById(clave);
				if (medicamento != null) {
					Object[] array = new Object[3];
					array[0] = medicamento.getClave();
					array[1] = medicamento.getNombre();
					array[2] = medicamento.getPrecio();
					model.addRow(array);
					listadoMedicamentos.add(medicamento);
					this.total = this.total + medicamento.getPrecio();
					this.txtTotal.setText("" + total);
					this.numMedicinas++;
					this.txtArticulos.setText("" + numMedicinas);
				} else {

				}
				this.txtClave.setText("");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == btnCobrar) {
			double pago = Double.parseDouble(this.txtPago.getText());
			double cambio = pago - total;
			if (cambio >= 0) {
				try {
					VentaImp ventaimp = new VentaImp();
					Venta venta = new Venta();
					venta.setTotal(total);
					venta.setIdCajero(cajero.getId());
					int idVenta = ventaimp.insertVenta(venta);

					for (Medicamento med : listadoMedicamentos) {
						DetVenta det = new DetVenta();
						det.setClave(med.getClave());
						det.setCantidad(1);
						det.setPrecio(med.getPrecio());
						det.setIdVenta(idVenta);
						listaDetalle.add(det);
					}
					ventaimp.insertDetVenta(listaDetalle);
					reiniciarCampos();
					JOptionPane.showOptionDialog(rootPane, "El cambio es de "
							+ cambio, "Venta", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showOptionDialog(rootPane,
						"El pago debe ser mayor que la venta", "Error",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
						null, null, null);
			}
		}
	}

	@SuppressWarnings("static-access")
	private void reiniciarCampos() {
		this.numMedicinas = 0;
		this.total = 0;
		this.txtArticulos.setText("" + numMedicinas);
		this.txtPago.setText("");
		this.txtTotal.setText("" + total);
		this.model.setRowCount(0);
		this.listaDetalle = new ArrayList<DetVenta>();
		this.listadoMedicamentos = new ArrayList<Medicamento>();
	}

}
