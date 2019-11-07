package clienteServidor;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;

import logica.Sala;

public class VentanaLobby  extends JFrame{
	private static Socket socketCliente;	
	
	public static Socket getSocketCliente() {
		return socketCliente;
	}

	public static void setSocketCliente(Socket socketCliente) {
		VentanaLobby.socketCliente = socketCliente;
	}

	PanelLobby panel= new PanelLobby();
	public static void main(String[] args) {
		//VentanaLobby ventana= new VentanaLobby(socketCliente);
	}

	public VentanaLobby(Socket socketCliente) {
		this.socketCliente= socketCliente;
		add(panel);
		setSize(500,500);
		setVisible(true);
	}
}
class PanelLobby extends JPanel {
	JButton unirseSala,crearSala,salir,aceptarSala;
	JComboBox<String> opcionesSalas= new JComboBox<String>();
	JTextField etiquetaSalaEspera= new JTextField("Se creo la sala");
	HashMap <String,Sala> salasDisponibles= new HashMap<String,Sala>();
	public PanelLobby() {
		unirseSala=new JButton("Unirse a una sala");
		crearSala=new JButton("Crear sala");
		salir=new JButton("Salir");
		Botones botones = new Botones();
		etiquetaSalaEspera.setVisible(false);
		add(etiquetaSalaEspera);
		unirseSala.addActionListener(botones);
		crearSala.addActionListener(botones);
		salir.addActionListener(botones);
		opcionesSalas.setVisible(false);
		aceptarSala.setVisible(false);
		add(opcionesSalas);
		add(aceptarSala);
		add(unirseSala);
		add(crearSala);
		add(salir);
		
	}
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	
	
	
}
public void mostrarSalasDisponibles() {
	for (Map.Entry<String,Sala> sala : salasDisponibles.entrySet()) {
		opcionesSalas.addItem(sala.getValue().getNombreSala());
	}
	opcionesSalas.setLocation(200,200);
	opcionesSalas.setVisible(true);
	aceptarSala.setVisible(true);
	repaint();
}
class Botones implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== unirseSala) {
			try {
				//comunicarse con el servidor para pedirle la lista de Salas
				DataOutputStream flujoSalida= new DataOutputStream(VentanaLobby.getSocketCliente().getOutputStream());
				PaqueteMensaje mensaje= new PaqueteMensaje("MostrarSala",null);
				Gson gson = new Gson();				
				flujoSalida.writeUTF(gson.toJson(mensaje));				
				// recibir Salas
				DataInputStream flujoEntrada= new DataInputStream(VentanaLobby.getSocketCliente().getInputStream());
				String entrada=flujoEntrada.readUTF();
				
				salasDisponibles=gson.fromJson(entrada,HashMap.class);				
				mostrarSalasDisponibles();
				flujoSalida.close();
				flujoEntrada.close();
				 
				// cuando se apreta el boton " aceptar" se envia la opcione elegida		
			} catch (IOException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
		}
		else if (e.getSource()== crearSala) {
			try {
				DataOutputStream flujoSalida= new DataOutputStream(VentanaLobby.getSocketCliente().getOutputStream());
				PaqueteMensaje mensaje= new PaqueteMensaje("crearSala", "nombreSala");
				Gson gson = new Gson();
				flujoSalida.writeUTF(gson.toJson(mensaje));
				DataInputStream flujoEntrada= new DataInputStream(VentanaLobby.getSocketCliente().getInputStream());
				String respuesta=flujoEntrada.readUTF();
				visibilizarSalaEspera(respuesta);
			} catch (IOException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
			
		}
       else if (e.getSource()== salir) {
			System.out.println("salir");
		}
       else if ( e.getSource() == aceptarSala) {
    	   opcionesSalas.setVisible(false);
    	   aceptarSala.setVisible(false);
    	   String nombreSala= (String) opcionesSalas.getSelectedItem(); // es la opcion seleccionada en el momento que se apreto "aceptar"
    	   DataOutputStream flujoSalida;
		try {
			flujoSalida = new DataOutputStream(VentanaLobby.getSocketCliente().getOutputStream());
			// envio la sala seleccionada al servidor
			Gson gson = new Gson();			
			String mensaje= gson.toJson(salasDisponibles.get(nombreSala));
			flujoSalida.writeUTF(mensaje);
			
		} catch (IOException e2) {
			// TODO Bloque catch generado automáticamente
			e2.printStackTrace();
		}
    	   
       }
		
	}
	
}
public void visibilizarSalaEspera(String valoresActuales) {
	Gson gson = new Gson();
	etiquetaSalaEspera.setVisible(true);
	Sala sala= gson.fromJson(valoresActuales, Sala.class);
	
	
}
	
}