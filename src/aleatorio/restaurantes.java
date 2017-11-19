package aleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.csvreader.*;

public class restaurantes {

	public static final String restaurantes = "./data/restaurantes.csv";
	public static final String datosH = "./data/hombres.csv";

	public static void main(String[] args) throws IOException {

		restaurantes rr = new restaurantes();

		ArrayList<VORestaurante> datos = new ArrayList();

		Long x=System.currentTimeMillis();
		
		ArrayList<String> nombres = new ArrayList<String>();

		nombres = rr.cargarNombres(restaurantes);

		ArrayList<String> repres = new ArrayList<String>();
		repres = rr.cargarRepres(datosH);

		for(int i = 0; i<=1000000; i++) {
			VORestaurante agregar = new VORestaurante();
			String nombreF = nombres.get(i);
			agregar.setNombre(nombreF);
			agregar.setRepresentante(rr.nombreRepre(repres));
			agregar.setPagina(rr.web(nombreF));
			agregar.setTipo(rr.tipoComida());
			agregar.setZona(rr.zona());
			System.out.println("Nombre: "+agregar.getNombre());
			System.out.println("Representante: "+agregar.getRepresentante());
			System.out.println("Web: "+agregar.getPagina());
			System.out.println("Tipo: "+agregar.getTipo());
			System.out.println("Zona: "+agregar.getZona());
			System.out.println("Van: "+i+" hijueputas.");
			datos.add(agregar);
		}
		Long resta = System.currentTimeMillis()-x;
		rr.agregarCSV(datos);
		System.out.println("Tiempo: "+resta); 
	}
	
	public void agregarCSV(ArrayList<VORestaurante> datos) {
		String outputFile = "C:\\Users\\ka.babativa\\Documents\\restaurantes2.csv";
		boolean alreadyExists = new File(outputFile).exists();

		if(alreadyExists){
			File Archivo = new File(outputFile);
			Archivo.delete();
		}        

		try {

			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			csvOutput.write("NOMBRE");
			csvOutput.write("REPRESENTANTE");
			csvOutput.write("PAGINA_WEB");
			csvOutput.write("TIPO_COMIDA_RESTAURANTE");
			csvOutput.write("ZONA_RESTAURANTE");
			csvOutput.endRecord();

			for(int i = 0; i<datos.size(); i++){
				VORestaurante user = datos.get(i);
				csvOutput.write(user.getNombre());
				csvOutput.write(user.getRepresentante());
				csvOutput.write(user.getPagina());
				csvOutput.write(user.getTipo());
				csvOutput.write(user.getZona());
				csvOutput.endRecord();                   
			}

			csvOutput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public ArrayList<String> cargarNombres(String file) throws IOException {
		CsvReader nombre = new CsvReader(file);
		nombre.readHeaders();
		ArrayList<String> retorno = new ArrayList<String>();
		for( int i=0; i<2000001; i+=2) {
			nombre.readRecord();
			String nombres = nombre.get("Company Name");
			retorno.add(nombres);
			System.out.println("Van: "+i+" pirobos");
			System.out.println(nombres);
			nombre.readRecord();
		}
		return retorno;
	}

	public ArrayList<String> cargarRepres(String file) throws IOException {
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

	public String nombreRepre(ArrayList<String> repres) {
		double numero = Math.abs((Math.random()*repres.size())-2);
		int numeroF = (int) numero;

		String retorno = repres.get(numeroF);

		return retorno;
	}

	public String web(String nombre) {
		nombre = nombre.replace(' ', '-');
		nombre = nombre.replace(',', '-');
		nombre = nombre.replace('.', '-');
		nombre = nombre.replace('?', '-');
		nombre = nombre.replace('&', '-');
		nombre = nombre.replace('(', '-');
		nombre = nombre.replace(')', '-');
		nombre = nombre.replace('\'', '-');
		nombre = nombre.toLowerCase();
		double numero = Math.random() * 11;
		int numeroF = (int) numero;
		String dominio = "";
		if(numeroF==0) {
			dominio=".com";
		}
		else if(numeroF==1) {
			dominio=".net";
		}
		else if(numeroF==2) {
			dominio=".edu";
		}
		else if(numeroF==3) {
			dominio=".io";
		}
		else if(numeroF==4) {
			dominio=".co";
		}
		else if(numeroF==5) {
			dominio=".org";
		}
		else if(numeroF==6) {
			dominio=".ch";
		}
		else if(numeroF==7) {
			dominio=".mx";
		}
		else if(numeroF==8) {
			dominio=".uk";
		}
		else if(numeroF==9) {
			dominio=".us";
		}
		else {
			dominio=".uniandes.edu.co";
		}
		String retorno = "https://"+nombre+dominio;
		return retorno;
	}

	public String tipoComida() {
		double numero = Math.random()*16;
		int numeroF = (int) numero;
		
		String comida = "";
		
		if(numeroF==0) {
			comida="RAPIDA";
		}
		else if(numeroF==1) {
			comida="ITALIANA";
		}
		else if(numeroF==2) {
			comida="DE MAR";
		}
		else if(numeroF==3) {
			comida="HAWAINA";
		}
		else if(numeroF==4) {
			comida="MEXICANA";
		}
		else if(numeroF==5) {
			comida="LIBRE EN GRASA";
		}
		else if(numeroF==6) {
			comida="COLOMBIANA";
		}
		else if(numeroF==7) {
			comida="CHINA";
		}
		else if(numeroF==8) {
			comida="PERUANA";
		}
		else if(numeroF==9) {
			comida="SANDUCHES";
		}
		else if(numeroF==10) {
			comida="SOPAS";
		}
		else if(numeroF==11) {
			comida="POSTRES";
		}
		else if(numeroF==12) {
			comida="CASERITO DE LA MAMÁ DE KEVIN UNO";
		}
		else {
			comida="POSTRES DE LA HERMANA DE KOBS";
		}
		return comida;
	}
	
	public String zona() {
		double numero = Math.random()*12;
		int numeroF = (int) numero;
		
		String zona = "";
		
		if(numeroF==0) {
			zona="ROCKSTAR";
		}
		else if(numeroF==1) {
			zona="FAIRYLAND";
		}
		else if(numeroF==2) {
			zona="PAISAS";
		}
		else if(numeroF==3) {
			zona="PLAZOLETA";
		}
		else if(numeroF==4) {
			zona="CENTRAL";
		}
		else if(numeroF==5) {
			zona="SUR";
		}
		else if(numeroF==6) {
			zona="NORTE";
		}
		else if(numeroF==7) {
			zona="ESTE";
		}
		else if(numeroF==8) {
			zona="OESTE";
		}
		else if(numeroF==9) {
			zona="T";
		}
		else if(numeroF==10) {
			zona="ROSA";
		}
		else {
			zona="SUR-ORIENTE";
		}
		return zona;
	}
	
	


}
