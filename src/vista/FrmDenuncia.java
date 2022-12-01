package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DenunciaDAO;
import dao.DepartamentoDAO;
import modelo.Denuncia;
import modelo.Departamento;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class FrmDenuncia extends JInternalFrame implements ActionListener{
	private JTextField txtNombre;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField txtTelefono , txtDireccion,txtCentroLaboral;
	private JTextArea txtDenuncia;
	private JTable tabla;
	private JComboBox cbDepartamento;
	private JButton btnRegistrar , btnEditar , btnLimpiar , btnEliminar;
	private DefaultTableModel modelo = new DefaultTableModel();
	private ArrayList<Departamento > listadoDepartamentos = new ArrayList<Departamento>();
	private ArrayList<Denuncia > listadoDenuncias = new ArrayList<Denuncia>();
	private DepartamentoDAO depDAO = new DepartamentoDAO();
	private DenunciaDAO denunciaDAO = new DenunciaDAO();
	private int id = 0;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDenuncia frame = new FrmDenuncia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrmDenuncia() {
		this.setBounds(100, 100, 686, 537);
		this.setClosable(true);
		this.setIconifiable(true);
		this.setTitle("Denuncias");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Denuncias");
		lblNewLabel.setBounds(10, 11, 201, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombres y apellidos:");
		lblNewLabel_1.setBounds(10, 57, 130, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(136, 54, 200, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Dirección:");
		lblNewLabel_10.setBounds(350, 57, 130, 14);
		getContentPane().add(lblNewLabel_10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(446, 54, 200, 20);
		getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("DNI:");
		lblNewLabel_1_1.setBounds(10, 85, 110, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(136, 82, 200, 20);
		getContentPane().add(txtDNI);
		
		JLabel lblNewLabel_1_10 = new JLabel("Denuncia:");
		lblNewLabel_1_10.setBounds(350, 85, 130, 14);
		getContentPane().add(lblNewLabel_1_10);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(446, 85, 200, 140);
		getContentPane().add(scrollPane2);
		
		txtDenuncia = new JTextArea();
		txtDenuncia.setBounds(446, 85, 200, 140);
		scrollPane2.setViewportView(txtDenuncia);
		
		
		JLabel lblNewLabel_1_2 = new JLabel("Correo");
		lblNewLabel_1_2.setBounds(10, 113, 110, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(136, 113, 200, 20);
		getContentPane().add(txtCorreo);
		
		JLabel lblNewLabel_1_3 = new JLabel("Telefono");
		lblNewLabel_1_3.setBounds(10, 144, 110, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(136, 141, 200, 20);
		getContentPane().add(txtTelefono);
		
		JLabel lblNewLabel_1_4 = new JLabel("Centro Laboral");
		lblNewLabel_1_4.setBounds(10, 175, 110, 14);
		getContentPane().add(lblNewLabel_1_4);
		
		txtCentroLaboral = new JTextField();
		txtCentroLaboral.setColumns(10);
		txtCentroLaboral.setBounds(136, 172, 200, 20);
		getContentPane().add(txtCentroLaboral);
		
		JLabel lblNewLabel_1_5 = new JLabel("Departamento");
		lblNewLabel_1_5.setBounds(10, 206, 110, 14);
		getContentPane().add(lblNewLabel_1_5);
		
		cbDepartamento = new JComboBox();
		cbDepartamento.setBounds(136, 203, 200, 20);
		getContentPane().add(cbDepartamento);
	
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setBounds(10, 239, 126, 30);
		btnRegistrar.addActionListener(this);
		getContentPane().add(btnRegistrar);
		
		btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(166, 239, 126, 30);
		btnEditar.addActionListener(this);
		getContentPane().add(btnEditar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(324, 239, 126, 30);
		btnEliminar.addActionListener(this);
		getContentPane().add(btnEliminar);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBounds(484, 239, 126, 30);
		btnLimpiar.addActionListener(this);
		getContentPane().add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 297, 650, 199);
		getContentPane().add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		tabla.setModel(modelo);
		
		
		modelo.addColumn("Id");
		modelo.addColumn("Nombres y Apellidos");
		modelo.addColumn("DNI");
		modelo.addColumn("Telefono");
		modelo.addColumn("Centro Laboral");
		modelo.addColumn("Departamento");
		
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila = tabla.getSelectedRow();
				
				if(fila >=0) {
					Denuncia obj = listadoDenuncias.get(fila);
					id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
					txtTelefono.setText(obj.getTelefono());
					txtCorreo.setText(obj.getCorreo());
					txtDenuncia.setText(obj.getDenuncia());
					txtDireccion.setText(obj.getDireccion());
					txtDNI.setText(obj.getDni());
					txtNombre.setText(obj.getNom_ape());
					txtCentroLaboral.setText(obj.getCentro_laboral());
					cbDepartamento.setSelectedItem(obj.getNom_dep());
				}
			}
		});
		
		CargarDepartamentos();
		listarDenuncias();
	}
	
	private void listarDenuncias() {
		modelo.setRowCount(0);
		listadoDenuncias = denunciaDAO.listarTodos();
		for(int i = 0; i<listadoDenuncias.size();i++) {
			Denuncia d = listadoDenuncias.get(i);
			Object datos[] = {d.getId_denuncia(), d.getNom_ape(), d.getDni(), d.getTelefono(), d.getCentro_laboral(), d.getNom_dep()};
			modelo.addRow(datos);	
		}
		
	}
	
	public void CargarDepartamentos() {
		cbDepartamento.removeAllItems();
		listadoDepartamentos = depDAO.listarTodos(); 
		for(int i = 0; i<listadoDepartamentos.size();i++) {
			Departamento d = listadoDepartamentos.get(i);
			cbDepartamento.addItem(d.getNom_dep());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRegistrar) {
			Registrar();
		}else if(e.getSource() == btnEditar) {
			Editar();
		}else if(e.getSource() == btnEliminar) {
			Eliminar();
		}else if(e.getSource() == btnLimpiar) {
			Limpiar();
		}
	}
	
	public void Registrar() {
		Denuncia obj = new Denuncia();
		obj.setCentro_laboral(txtCentroLaboral.getText().trim());
		obj.setDenuncia(txtDenuncia.getText().trim());
		obj.setDireccion(txtDireccion.getText().trim());
		obj.setDni(txtDNI.getText().trim());
		obj.setTelefono(txtTelefono.getText().trim());
		obj.setNom_ape(txtNombre.getText().trim());
		obj.setCorreo(txtCorreo.getText().trim());
		obj.setId_dep(listadoDepartamentos.get(cbDepartamento.getSelectedIndex()).getId_dep());
		
		if(denunciaDAO.Registrar(obj) > 0) {
			Limpiar();
			listarDenuncias();
			Mensaje("Registro correcto");
		}else {
			Mensaje("No se ha podido registrar");
		}
	}
	
	public void Editar() {
		if(id > 0) {
			Denuncia obj = new Denuncia();
			obj.setId_denuncia(id);
			obj.setCentro_laboral(txtCentroLaboral.getText().trim());
			obj.setDenuncia(txtDenuncia.getText().trim());
			obj.setDireccion(txtDireccion.getText().trim());
			obj.setDni(txtDNI.getText().trim());
			obj.setTelefono(txtTelefono.getText().trim());
			obj.setNom_ape(txtNombre.getText().trim());
			obj.setCorreo(txtCorreo.getText().trim());
			obj.setId_dep(listadoDepartamentos.get(cbDepartamento.getSelectedIndex()).getId_dep());
			
			if(denunciaDAO.Editar(obj) > 0) {
				Limpiar();
				listarDenuncias();
				Mensaje("Edicion correcto");
			}else {
				Mensaje("No se ha podido editar");
			}
		}else {
			Mensaje("Seleccione una fila a editar");
		}
	}
	
	public void Eliminar() {
		if(id > 0) {
			if(denunciaDAO.Eliminar(id) > 0) {
				Limpiar();
				listarDenuncias();
				Mensaje("Eliminacion correcto");
			}else {
				Mensaje("No se ha podido eliminar");
			}
		}else {
			Mensaje("Seleccione una fila a eliminar");
		}
	}
	
	
	public void Limpiar() {
		txtCorreo.setText("");
		txtDenuncia.setText("");
		txtDireccion.setText("");
		txtDNI.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		txtCentroLaboral.setText("");
		id = 0;
		tabla.setSelectionMode(0);
	}

	public void Mensaje(String men) {
		JOptionPane.showMessageDialog(null,men);
	}
}









