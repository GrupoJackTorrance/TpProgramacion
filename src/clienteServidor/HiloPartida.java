package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;

import logica.Jugador;
import logica.Sala;

public class HiloPartida extends Thread {
	private DataInputStream entrada;
	private DataOutputStream salida;
	boolean corriendo = true;
	private Sala sala;
	HashMap<Jugador,Socket> clienteServidor;
	HashMap<Jugador,Socket> servidorCliente;
	int contadorTurno;
	Jugador turno;

	public HiloPartida (Sala sala, HashMap clienteServidor, HashMap servidorCliente) throws IOException {
		this.sala = sala;
		this.clienteServidor=clienteServidor;
		this.servidorCliente=servidorCliente;
	}

	
	@Override
	public void run() {
		/*PROBABLE*/
		 try {
			int i=0;
			contadorTurno=1;
			Gson gson= new Gson();
			/*while (corriendo && i <sala.getPartida().getRondaMax()) {*/
				int index=contadorTurno-1;
				turno=sala.getJugadores2().get(index);
				String datosTurno=turno.getNombre();
				PaqueteMensaje mensaje = new PaqueteMensaje("EmpiezaTurno",datosTurno,"EnSala"+sala.getNombreSala());
				ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);
				entrada = new DataInputStream(clienteServidor.get(turno).getInputStream());
				salida = new DataOutputStream(servidorCliente.get(turno).getOutputStream());
				mensaje.setAccion("muestraTiraDado");
				String mens=gson.toJson(mensaje);
				salida.writeUTF(mens);
				System.out.println("aca");
				String msj= gson.fromJson(entrada.readUTF(),String.class);
				String mensajeCliente ="4;"+Integer.toString(index);//espera recibir numero del dado tirado y enviamos el turno del jugador
				mensaje.setAccion("muestraDado");
				mensaje.setObj(mensajeCliente);
				System.out.println("aca");
				ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);//replicar para simular tirada de dado
				msj= gson.fromJson(entrada.readUTF(),String.class);
				mensaje.setAccion("muestraVentanaAtaca");
				mensaje.setObj(Integer.toString(index));
				mens=gson.toJson(mensaje);
				salida.writeUTF(mens);
				msj= gson.fromJson(entrada.readUTF(),String.class);
				if(Integer.parseInt(msj)!=0) {	
					mensaje.setAccion("idObj");
					int jugadorAtacado= contadorTurno % sala.getPartida().getJugadores().size();
					mensaje.setObj(msj+";"+jugadorAtacado+";"+index);
					ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);
					Thread.sleep(2000);
				}
				contadorTurno++;
				if(contadorTurno>sala.getPartida().getTurnos())
					contadorTurno=1;
			/*}	
		/*} catch (IOException e){
			e.printStackTrace();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
