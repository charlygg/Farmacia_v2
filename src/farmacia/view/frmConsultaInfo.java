package farmacia.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import farmacia.modelo.dto.Medicamento;
import java.awt.Color;

@SuppressWarnings("serial")
public class frmConsultaInfo extends JFrame {

	private JPanel contentPane;
	private ArrayList<Medicamento> listadoMedicamentos;
	private JTextArea taInfo;

	public frmConsultaInfo(ArrayList<Medicamento> listadoMedicamentos) {
		this.listadoMedicamentos = listadoMedicamentos;
		setTitle("Informacion detallada");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 400);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		taInfo = new JTextArea();
		taInfo.setEditable(false);
		taInfo.setLineWrap(true);
		taInfo.setWrapStyleWord(true);
		taInfo.setFont(new Font("Monospaced", Font.PLAIN, 18));
		JScrollPane scroll = new JScrollPane(taInfo);
		contentPane.add(scroll, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblInformacionDeLos = new JLabel("INFORMACION DE LOS MEDICAMENTOS");
		lblInformacionDeLos.setForeground(Color.WHITE);
		lblInformacionDeLos.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		panel.add(lblInformacionDeLos);
		
		JButton btnCerrar = new JButton("CERRAR");
		btnCerrar.setIcon(new ImageIcon("icons/cancelar.png"));
		btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnCerrar.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		panel.add(btnCerrar);
		setVisible(true);
		rellenarTodo();
	}

	private void rellenarTodo() {
		for(Medicamento meds : listadoMedicamentos){
			taInfo.append("-------------------------------------");
			taInfo.append("\nClave: " + meds.getClave());
			taInfo.append("\nNombre: " + meds.getNombre());
			taInfo.append("\nMarca: "+ meds.getMarca().getMarca());
			taInfo.append("\nPresentacion: " + meds.getPresentacion());
			taInfo.append("\nConcentracion: " + meds.getConcentracion());
			taInfo.append("\nPrecio: " + meds.getPrecio());
			taInfo.append("\nFarmaco: " + meds.getFarmaco());
			taInfo.append("\nExistencias: " + meds.getExistencia());
			taInfo.append("\n");
		}
		
	}

}
