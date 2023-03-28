package ficheros;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/*
 * Proyeto navidad Gestión stock
 * Grupo M11
 * Ignacio De Llano Varela
 * PD: El programa se inicia con un stock vacío, yo le he ido introduciendo productos hasta tener unos cuantos productos con los que trabajar. No se si cuando recivas este trabajo tendrás los productos guardados. De no ser tendrías que introducir unos ejemplos.
 */

public class ProyectoNavidad_IgnacioDellanoVarela {


	public static int mostrar_opciones_escoger_operacion () { //Creamos la función del menu que se repetira en bucle hasta que se solicite salir
		int operacion = 0;
		Scanner teclado = new Scanner(System.in);

		System.out.print(
				"       \n\n\n   Tienda De Reptiles Ignacio SL         " + "\n\n" +
						" Operaciones disponibles : " + "\n\n"+
						"  -0 - Exit "+ "\n" +
						"  -1 - Introducir nuevo producto" + "\n" +
						"  2 - Buscar producto" + "\n" +
						"  3 - Guardar matriz stock" + "\n" +
						"  4 - Cargar matriz stock" + "\n" +	
						"  -5 - Mostrar tienda completa " +
						"\n\n¿Que operación desea realizar?:" //Solicitamos opcion para ejecutarla después
				);

		if (teclado.hasNextInt()) {
			operacion = teclado.nextInt();
		}
		else {
			teclado.next(); //quitamos la basura
		}

		return operacion;
	}

	
	//Esta función registrará los nuevos productos y los añadirá a la matriz stock
	public static String[][] añadir_nuevo_producto(String matrizbase[][]){ 
		Scanner teclado = new Scanner(System.in);

		//Creamos una nueva matriz la cual tendrá el mismo numero de columnas que la stock anterior pero una fila mas para el nuevo producto

		String[][] actualizado = new String[matrizbase.length+1][5]; 
		for (int i=0; i<matrizbase.length; i++) {
			actualizado[i] = matrizbase[i]; 
			// Copiamos toda la informacion a la nueva mariz
		}
		//Asignamos la información que se desee en la posición y ordenes correspondientes [0]Id [1]nombre etc
		System.out.println("Introduzca Id nuevo producto  :");
		if (teclado.hasNextLine()) {
			actualizado[actualizado.length-1][0] =  teclado.nextLine();
		}
		else {
			teclado.next();
		}

		System.out.println("Introduzca nombre nuevo producto  :");
		if (teclado.hasNextLine()) {
			actualizado[actualizado.length-1][1] =  ("\t" + teclado.nextLine());//Añadimos line para poder introducir varias palabras 
		}
		else {
			teclado.next();
		}

		System.out.println("Introduzca cantidad nuevo producto  :");
		if (teclado.hasNextLine()) {
			actualizado[actualizado.length-1][2] =   ("\t" + teclado.nextLine());
		}
		else {
			teclado.next();
		}
		System.out.println("Introduzca precio nuevo producto  :");
		if (teclado.hasNextLine()) {
			actualizado[actualizado.length-1][3] =  ("\t" + teclado.nextLine());
		}
		else {
			teclado.next();
		}
		System.out.println("Introduzca caracteristicas nuevo producto  :");
		if (teclado.hasNextLine()) {
			actualizado[actualizado.length-1][4] =   ("\t" + teclado.nextLine());
		}
		else {
			teclado.next();
		}




		return actualizado;//Nos devuelve la nueva matriz ya con el nuevo producto integrado
	}

//Con esta función guardaremos nuestro stock en un fichero en formato csv
	private static void guardar_stock(String[][] matriz, String ruta) {
		FileWriter fichero;
		try {
			fichero = new FileWriter(ruta); 
			
			for(int i = 0; i< matriz.length; i++) {
				for (int j=0; j< matriz[i].length; j++) {
					fichero.write(matriz[i][j]);
					if (j != matriz[i].length-1) { 
						fichero.write(";");
					} 
				}
				fichero.write("\n"); 
			}
			fichero.close();
		} catch (IOException e) {
			System.out.println("ERROR en el guardado de la matriz Stock.");
		}
	}
	public static int contar_filas(String ruta) {//contamos filas del fichero
		int lineas = 0;
		File fichero = new File(ruta);
		try {
			Scanner sc = new Scanner(fichero);
			while(sc.hasNextLine()) {
				sc.nextLine();
				lineas++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error, no se puede abrir el fichero.");
		}
		return lineas;
	}
	private static String[][] leer_matriz_fichero(String ruta) {//leemos los datos del fichero y los volcamos en una matriz para poder trabajar con ella
		String[][] matriz = {};
		File fichero = new File(ruta);
		
		try {
			Scanner sc = new Scanner(fichero);
			matriz = new String[contar_filas(ruta)][5];
			
			int i = 0;
			String fila;
			String[] array_fila;
			
			while(sc.hasNextLine()) {
				fila = sc.nextLine(); 
				array_fila = fila.split(";");//extraemos los datos string del fichero y los introducimos en un array
				
				
				for (int j=0; j<array_fila.length; j++) {
					matriz[i][j] = (array_fila[j]); //Creamos una matriz cuyas filas serán los array creados anteriormente por los datos del fichero.
				}
				i++;
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error, no se puede abrir el fichero.");
		}
		
		
		return matriz;
	}
	


	//Esta función simplemente es para dejar el main mas limpio

	public static String[][] introducir_Matriz_base() {
		String matriz[][] = {{"Id", "\tNombre","\tPrecio","\tCantidad","Características"}};

		return matriz;
	}






	public static void main(String[] args) {

		//String[][]stock = {{"Id", "\tNombre","\tPrecio","\tCantidad","Características"}};

		//escribir_fichero_de_producto(nuevo_producto(stock), "Stock");


		
				
        int j=0;
		int i=0;
		int operacion = -1;
		String[][]stock = introducir_Matriz_base();


		while (operacion != 0) {
			operacion =  mostrar_opciones_escoger_operacion();

			switch (operacion) {

			case 1: //Registrar nuevo producto
				 stock = añadir_nuevo_producto(stock);

				break;

			case 2: //Buscar Id
				Scanner teclado = new Scanner(System.in);
				String busqueda = "";
				System.out.print("Introduzca Id :");
				
				if (teclado.hasNext()) {
				 busqueda = teclado.next();
				}
				else {
					teclado.next();
				}
				
				boolean encontrado = false;
				int w=0;
				
			while (!encontrado & w<stock.length) {
				if(busqueda.equals(stock[i][0])) {
					encontrado = true;
				}
				else{
					i++;
				}
			}
				
			if(encontrado) {
				
						System.out.print( stock[i][0] +" ");

						System.out.print( stock[i][1]+" ");

						System.out.print( stock[i][2]+" ");

						System.out.print( stock[i][3]+" ");

						System.out.print( stock[i][4]+" ");
						
					
				
			}
				
				

				break;

			case 3: //Guardar stock en un fichero

				 guardar_stock(stock,"Tienda.csv");
				
				break;

			case 4://Leer stock del fichero para trabajar con el
				 stock = leer_matriz_fichero("Tienda.csv");
				break;

			case 5:
				 for( i=0; i<stock.length; i++) {
						for( j=0; j< stock[i].length; j++) {
							System.out.print("\t" + stock[i][j]);
			   }
			System.out.println();
		}
				break;

			case 0:
				System.out.println("Ha solicitado salir, hasta pronto");
				break;
			default:
				System.out.println("Operación incorrecta!");
			}




		}

	}
}	





