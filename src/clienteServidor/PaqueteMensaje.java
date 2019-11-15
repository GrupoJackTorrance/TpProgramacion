package clienteServidor;

public class PaqueteMensaje {
String accion;
Object obj;
String ubicacionDestino; // para indicar la ubicacion del cliente para que reciba el mensaje
public PaqueteMensaje(String accion, Object obj) {
	this.accion= accion;
	this.obj=obj;
}
public PaqueteMensaje(String accion, Object obj, String ubicacionDestino) {
	this.accion=accion;
	this.obj=obj;
	this.ubicacionDestino=ubicacionDestino;
}
public String getAccion() {
	return accion;
}
public void setAccion(String accion) {
	this.accion = accion;
}
public String getUbicacionDestino() {
	return ubicacionDestino;
}
public void setUbicacionDestino(String ubicacionDestino) {
	this.ubicacionDestino = ubicacionDestino;
}
public Object getObj() {
	return obj;
}
public void setObj(Object obj) {
	this.obj = obj;
}

}
