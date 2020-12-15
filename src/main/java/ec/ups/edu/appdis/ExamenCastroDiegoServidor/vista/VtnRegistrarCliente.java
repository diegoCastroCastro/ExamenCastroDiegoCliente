package ec.ups.edu.appdis.ExamenCastroDiegoServidor.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;

import ec.ups.edu.appdis.ExamenCastroDiegoServidor.modelo.Cliente;
import ec.ups.edu.appdis.ExamenCastroDiegoServidor.negocio.ClienteONRemoto;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Hashtable;

import javax.swing.JTextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VtnRegistrarCliente extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblFechaNacimiento;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFechaNacimiento;
	
	private ClienteONRemoto on;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VtnRegistrarCliente frame = new VtnRegistrarCliente();
					frame.referenciarONCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void referenciarONCliente() throws Exception {
		try {  
            final Hashtable<String, Comparable> jndiProperties =  
                    new Hashtable<String, Comparable>();  
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");  
            jndiProperties.put("jboss.naming.client.ejb.context", true);  
              
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "ejbremoto");  
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "ejb01");  
              
            final Context context = new InitialContext(jndiProperties);  
              
            final String lookupName = "ejb:/ExamenCastroDiegoServidor/ClienteON!ec.ups.edu.appdis.ExamenCastroDiegoServidor.negocio.ClienteONRemoto";  
              
            on = (ClienteONRemoto) context.lookup(lookupName);  
              
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        }
		
	}

	/**
	 * Create the frame.
	 */
	public VtnRegistrarCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Cedula");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(24, 28, 93, 26);
		contentPane.add(lblNewLabel);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNombre.setBounds(24, 79, 93, 26);
		contentPane.add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblApellido.setBounds(24, 126, 93, 26);
		contentPane.add(lblApellido);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFechaNacimiento.setBounds(10, 174, 134, 26);
		contentPane.add(lblFechaNacimiento);
		
		txtCedula = new JTextField();
		txtCedula.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCedula.setBounds(137, 28, 139, 26);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtNombre.setColumns(10);
		txtNombre.setBounds(137, 79, 139, 26);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtApellido.setColumns(10);
		txtApellido.setBounds(137, 126, 139, 26);
		contentPane.add(txtApellido);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(154, 174, 139, 26);
		contentPane.add(txtFechaNacimiento);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGuardar.setBounds(114, 259, 201, 43);
		contentPane.add(btnGuardar);
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		guardarCliente();
	}


	private void guardarCliente() {
		Cliente c = new Cliente();
		c.setCedula(txtCedula.getText());
		c.setNombre(txtNombre.getText());
		c.setApellido(txtApellido.getText());
		c.setFechaNacimiento(txtFechaNacimiento.getText());
		
		try {
			on.registrarCliente(c);
			System.out.println("Guardado Ok");
		} catch (Exception e) {
			System.out.println("Error al registrar");
		}
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
	}
	
}
