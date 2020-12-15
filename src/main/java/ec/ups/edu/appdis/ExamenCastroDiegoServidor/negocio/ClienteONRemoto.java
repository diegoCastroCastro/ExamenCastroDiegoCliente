package ec.ups.edu.appdis.ExamenCastroDiegoServidor.negocio;

import java.util.List;

import javax.ejb.Remote;

import ec.ups.edu.appdis.ExamenCastroDiegoServidor.modelo.Cliente;

@Remote
public interface ClienteONRemoto {
	
	public boolean registrarCliente (Cliente cliente) throws Exception;
	//public List<Cliente> getClientesTipo1();


}
