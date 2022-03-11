package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.BillboardData;

public class Main {

	private static BillboardData data;
	private static Scanner in;
	
	public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		data = new BillboardData();
		
		System.out.println("----------Bienvenido----------\n");
		menu();
	}
	
	public static void menu() throws IOException {
		System.out.println("\n----- Menú -----");
		
		System.out.println("\n1) Importar datos."
				+ "\n2) Agregar valla publicitaria."
				+ "\n3) Mostrar vallas publicitarias."
				+ "\n4) Exportar reporte de peligrosidad."
				+ "\n0) Salir.");
		
		int selection = Integer.parseInt(in.nextLine());
		
		switch (selection) {
		case 1:
			importData();
			break;
			
		case 2:
			addBillboard();
			break;
			
		case 3:
			seeBillboards();
			break;
			
		case 4:
			exportDangerReport();
			break;
			
		case 0:
			exit();
			break;

		default:
			System.out.println("\nLa opción que has digitado no existe. Intenta nuevamente");
			menu();
			break;
		}
	}
	
	public static void importData() throws IOException {
		System.out.println("\n----- Importar datos -----");
		
		try {
			System.out.println("\nIngresa el path absoluto del archivo que quieres importar:");
			String path = in.nextLine();
			
			data.loadDatos(path);
			
			System.out.println("\nDatos importados correctamente.");
			menu();
		} catch (FileNotFoundException e) {
			System.out.println("\nNo se ha encontrado el archivo. Intenta nuevamente.");
			menu();
		}
	}
	
	public static void addBillboard() throws IOException {
		try {
			System.out.println("\n----- Agregar valla publicitaria -----"
					+ "\nDigite la información de la valla publicitaria:");
			
			String billboardInfo = in.nextLine();
			
			data.addBillboard(billboardInfo);
			
			System.out.println("\nValla publicitaria creada exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error creando la valla publicitaria. Intenta nuevamente");
		}
		
		menu();
	}
	
	public static void seeBillboards() throws IOException {
		System.out.println("\n----- Ver vallas publicitarias -----"
				+ "\n\n" + data.billboardsToString());
		
		menu();
	}
	
	public static void exportDangerReport() throws IOException {
		System.out.println("\n----- Reporte de peligrosidad -----");
		
		data.exportDangerousReport();
		
		System.out.println(data.dangerousBillboardsToString());
		
		menu();
	}

	public static void exit() {
		System.out.println("\n¡Adiós!");
		System.exit(0);
	}
	
}
