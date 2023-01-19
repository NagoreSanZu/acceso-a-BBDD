package Ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestorArboles {
	private static final String HOST = "localHost";
	private static final String BBDD ="eh_garden";
	private static final String USERNAME = "root";
	private static final String PASSWROD = "";
	
	public void run() throws ClassNotFoundException, SQLException {
	
		Connection conexion;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion= DriverManager.getConnection("jdbc:mysql://"+HOST+"/"+BBDD, USERNAME, PASSWROD);
		Statement st = conexion.createStatement();
		
		Arbol arbol = new Arbol();
		final int OPCION_UNO = 1;
		final int OPCION_DOS = 2;
		final int OPCION_TRES = 3;
		final int OPCION_CUATRO = 4;
		final int SALIR = 0;

		Scanner scan = new Scanner(System.in);
		int opcion_menu;

		do {
			System.out.println("------MENU-------");
			System.out.println(OPCION_UNO + ". Insertar arbol");
			System.out.println(OPCION_DOS + ". Eliminar arbol");
			System.out.println(OPCION_TRES + ". modifiar informacion del arbol");
			System.out.println(OPCION_CUATRO + ". visualizar arboles");
			System.out.println(SALIR + ". Salir");
			System.out.println("Elije una de las opciones");
			opcion_menu = Integer.parseInt(scan.nextLine());

			switch (opcion_menu) {
			case OPCION_UNO:
				System.out.println("Introduce el nombreComun del arbol");
				String nombre = scan.nextLine();
				System.out.println("Introduce el nombre cientifico");
				String cientifico = scan.nextLine();
				System.out.println("Introduce su habitat");
				String haabitat= scan.nextLine();
				System.out.println("Introduce la altura");
				int altArbol = Integer.parseInt(scan.nextLine());
				System.out.println("Introduce su origen");
				String origenArbol=scan.nextLine();
			
				arbol.setNombreComun(nombre);
				arbol.setNombreCientifico(cientifico);
				arbol.setHabitat(haabitat);
				arbol.setAltura(altArbol);
				arbol.setOrigen(origenArbol);
				
				st.execute("INSERT INTO eh_garden (nombre_comun, nombre_cientifico, habitat, altura, origen ) VALUES"+ "('"+nombre+"', '"+cientifico+"', '"+haabitat+"', "+altArbol+" , '"+origenArbol+"' )");
			


				break;
				
				
			case OPCION_DOS:
				System.out.println("Indica el nombre comun del arbol que quieras eliminar:");
				String nombreArbolDelete = scan.nextLine();
				String sentenciaDelete= "DELETE FROM eh_garden WHERE nombre_comun ='"+nombreArbolDelete+"'" ;
				st.execute(sentenciaDelete);
				
				break;
			case OPCION_TRES:
				System.out.println("Indica el id del arbol que quieras editar:");
				int idArbolEdit = Integer.parseInt(scan.nextLine());
				
				
				String sentenciaUpdate= "UPDATE animales SET nombre='aaa' WHERE id 12";	
				st.executeUpdate(sentenciaUpdate);

				
				break;
			case OPCION_CUATRO:
				System.out.println("tercera opcion seleccionada\n");
				break;
			case SALIR:
				System.out.println("ADIOS");
				break;
			default:
				System.out.println("Opcion incorrecta!");
			}

		} while (opcion_menu != SALIR);
		scan.close();
	}
}
