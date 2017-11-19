package aleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import com.csvreader.*;

public class datos {

	public static final String datosH = "./data/hombres.csv";
	public static final String datosM = "./data/mujeres.csv";
	public static final String datosA = "./data/apellidos.csv";
	public static void main(String[] args) throws IOException {

		datos dd= new datos();
		Long x=System.currentTimeMillis();

		ArrayList<VOUsuario> datos = new ArrayList<VOUsuario>();
		ArrayList<String> nombresH = new ArrayList<String>();
		nombresH = dd.cargarNombresH(datosH);
		ArrayList<String> nombresM = new ArrayList<String>();
		nombresM = dd.cargarNombresM(datosM);
		ArrayList<String> apellidos = new ArrayList<String>();
		apellidos = dd.cargarApellido(datosH);

		String[] nombres = new String[2];

		for(int i = 0; i<=1000000; i++) {
			if(dd.eleccion()==0) {
				nombres = dd.nombreEmailHombre(nombresH, apellidos);
			}
			else {
				nombres = dd.nombreEmailMujer(nombresM, apellidos);
			}
			VOUsuario agregar = new VOUsuario();
			agregar.setNombre(nombres[0]);
			agregar.setEmail(nombres[1]);
			agregar.setPass(dd.pass());
			agregar.setRol(dd.rol());
			System.out.println("Nombre: "+agregar.getNombre());
			System.out.println("Email: "+agregar.getEmail());
			System.out.println("Pass: "+agregar.getPass());
			System.out.println("Rol: "+agregar.getRol());
			System.out.println("Van: "+i+" hijueputas.");
			datos.add(agregar);
		}

		Long resta = System.currentTimeMillis()-x;
		dd.agregarCSV(datos);
		System.out.println("Tiempo: "+resta); 
	}

	public ArrayList<String> cargarNombresH(String file) throws IOException{
		CsvReader nombresH = new CsvReader(file);
		nombresH.readHeaders();
		ArrayList<String> retorno = new ArrayList<String>();
		while(nombresH.readRecord()) {
			String nombre = nombresH.get("nombre");
			retorno.add(nombre);
			System.out.println("Hombre: "+nombre);
		}
		return retorno;
	}

	public String pass() {
		double tamanio = Math.random()*10;
		int otro = (int) (tamanio+5);
		int menor = 97;
		int mayor = 122;
		int perro = 0;
		char[] palabra = new char[otro]; 
		String nombre = "";
		for(int i = 0; i<tamanio+4; i++) {
			Random numero = new Random();
			perro = numero.nextInt(mayor-menor)+menor;
			palabra[i] = (char) perro;
		}
		for(int j = 0; j<palabra.length; j++) {
			nombre += palabra[j];
		}
		return nombre;
	}

	public ArrayList<String> cargarNombresM(String file) throws IOException{
		CsvReader nombresM = new CsvReader(file);
		nombresM.readHeaders();
		ArrayList<String> retorno = new ArrayList<String>();
		while(nombresM.readRecord()) {
			String nombre = nombresM.get("nombre");
			retorno.add(nombre);
			System.out.println("Mujer: "+nombre);
		}
		return retorno;
	}

	public ArrayList<String> cargarApellido(String file) throws IOException{
		CsvReader apellidos = new CsvReader(file);
		apellidos.readHeaders();
		ArrayList<String> retorno = new ArrayList<String>();
		while(apellidos.readRecord()) {
			String nombre = apellidos.get("apellido");
			retorno.add(nombre);
			System.out.println("Apellido: "+nombre);
		}
		return retorno;
	}

	public int eleccion() {
		double decidir = Math.random()*2;
		int decid = (int) decidir;
		return decid;
	}

	public String[] nombreEmailHombre(ArrayList<String> nombreH, ArrayList<String> apellido) {


		int registros = nombreH.size();
		int registrosApellidos = apellido.size();
		double randomNombres = Math.random()*registros;
		int randomFinalNombres = (int) randomNombres;
		double randomApellidos = Math.random()*registrosApellidos;
		int randomFinalApellidos = (int) randomApellidos;

		String[] retorno = new String[2];

		String nombre = nombreH.get(randomFinalNombres);
		nombre.trim();
		String nombreF = nombre.replace(' ', '_');

		String apellidoN = apellido.get(randomFinalApellidos);
		apellidoN.trim();
		String apellidoF = apellidoN.replace(' ', '_');

		String nombreApellido = nombre+apellidoN;

		retorno[0] = nombreApellido;

		String nombreEmail = nombreF+apellidoF;
		String email = "";
		email = nombreEmail+email();

		retorno[1] = email;

		return retorno;
	}

	public String[] nombreEmailMujer(ArrayList<String> nombreM, ArrayList<String> apellido) {
		int registros = nombreM.size();
		int registrosApellidos = apellido.size();
		double randomNombres = Math.random()*registros;
		int randomFinalNombres = (int) randomNombres;
		double randomApellidos = Math.random()*registrosApellidos;
		int randomFinalApellidos = (int) randomApellidos;

		String[] retorno = new String[2];

		String nombre = nombreM.get(randomFinalNombres);
		nombre.trim();
		String nombreF = nombre.replace(' ', '_');
		String apellidoN = apellido.get(randomFinalApellidos);
		apellidoN.trim();
		String apellidoF = apellidoN.replace(' ', '_');

		String nombreApellido = nombre+apellidoN;

		retorno[0] = nombreApellido;

		String nombreEmail = nombreF+apellidoF;
		String email = "";
		email = nombreEmail+email();

		retorno[1] = email;

		return retorno;
	}

	public void agregarCSV(ArrayList<VOUsuario> datos) {
		String outputFile = "C:\\Users\\ka.babativa\\Documents\\usuarios.csv";
		boolean alreadyExists = new File(outputFile).exists();

		if(alreadyExists){
			File Archivo = new File(outputFile);
			Archivo.delete();
		}        

		try {

			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

			csvOutput.write("ID");
			csvOutput.write("NOMBRE");
			csvOutput.write("EMAIL");
			csvOutput.write("ROL");
			csvOutput.write("PASSWORD");
			csvOutput.endRecord();

			for(int i = 0; i<datos.size(); i++){
				VOUsuario user = datos.get(i);
				csvOutput.write(""+i);
				csvOutput.write(user.getNombre());
				csvOutput.write(user.getEmail());
				csvOutput.write(user.getRol());
				csvOutput.write(user.getPass());
				csvOutput.endRecord();                   
			}

			csvOutput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public String email() {
		String email = "";
		double tipoCorreo = (Math.random()*3)+1;
		double num = (Math.random()*100)-1;
		int num2 = (int) num;
		String numF = ""+num2;

		if((int) tipoCorreo == 1) {
			email+="@gmail.com";
		}
		else if((int) tipoCorreo == 2) {
			email+="@hotmail.com";
		}
		else if((int) tipoCorreo == 3) {
			email+="@uniandes.edu.co";
		}
		else {
			email+="@yahoo.com";
		}
		return num2+email;
	}

	public String rol() {
		double numero = Math.random()*3 ;
		System.out.println((int)numero);
		if((int)numero==0) {
			return "Administrador";
		}
		else if((int)numero==1) {
			return "UsuarioRegistrado";
		}
		else if((int)numero==2) {
			return "RESTAURANTE";
		}
		else
			return "Administrador";
	}

}
