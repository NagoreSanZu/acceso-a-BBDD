package Ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				
				//st.execute("INSERT INTO eh_garden (nombre_comun, nombre_cientifico, habitat, altura, origen ) VALUES"+ "('"+nombre+"', '"+cientifico+"', '"+haabitat+"', "+altArbol+" , '"+origenArbol+"' )");
				
				PreparedStatement pstInsert=conexion.prepareStatement("INSERT INTO eh_garden (nombre_comun, nombre_cientifico, habitat, altura, origen ) VALUES(?,?,?,?,?)");
				pstInsert.setString(1,nombre );
				pstInsert.setString(2, cientifico);
				pstInsert.setString(3,haabitat );
				pstInsert.setInt(4, altArbol);
				pstInsert.setString(5,origenArbol);
				pstInsert.execute();
				break;
				
				
			case OPCION_DOS:
				System.out.println("Indica el ID del arbol que quieras eliminar:");
				int idArbolDelete = Integer.parseInt(scan.nextLine());
				String sentenciaDelete= "DELETE FROM eh_garden WHERE nombre_comun ='"+idArbolDelete+"'" ;
				st.execute(sentenciaDelete);
				
				break;
			case OPCION_TRES:
				System.out.println("EDITANDO LOS DATOS DEL ARBOL:");
				System.out.println("Indica el id del arbol que quieras editar:");
				int idArbolEdit = Integer.parseInt(scan.nextLine());
				
				System.out.println("Introduce el nombreComun del arbol");
				String nombreEdit = scan.nextLine();
				System.out.println("Introduce el nombre cientifico");
				String cientificoEdit = scan.nextLine();
				System.out.println("Introduce su habitat");
				String haabitatEdit= scan.nextLine();
				System.out.println("Introduce la altura");
				int altArbolEdit = Integer.parseInt(scan.nextLine());
				System.out.println("Introduce su origen");
				String origenArbolEdit=scan.nextLine();
				
				String sentenciaUpdate= "UPDATE eh_garden SET nombre_comun='"+nombreEdit+"', nombre_cientifico='" + cientificoEdit + "', habitat='" + haabitatEdit +"', altura='" + altArbolEdit + "', origen= '" + origenArbolEdit + "' WHERE id = " + idArbolEdit;	
				st.executeUpdate(sentenciaUpdate);
				System.out.println("Arbol modificado, muchas gracias por tu espera y paciencia");
				
				break;
			case OPCION_CUATRO:
				String senteciaSelect = "SELECT * FROM eh_garden";
				ResultSet resultado =st.executeQuery(senteciaSelect);

				while(resultado.next()) {
					System.out.println(resultado.getInt(1) + " - " + resultado.getString(2) + " - " + resultado.getString(3) + " - " + resultado.getString(4) + " - " + resultado.getString(5) + " - " + resultado.getString(6));
				}
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
