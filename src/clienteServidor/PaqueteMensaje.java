package clienteServidor;

public class PaqueteMensaje {
String accion;
Object obj;
public PaqueteMensaje(String accion, Object obj) {
	this.accion= accion;
	this.obj=obj;
}
public String getAccion() {
	return accion;
}
public void setAccion(String accion) {
	this.accion = accion;
}
public Object getObj() {
	return obj;
}
public void setObj(Object obj) {
	this.obj = obj;
}

}
