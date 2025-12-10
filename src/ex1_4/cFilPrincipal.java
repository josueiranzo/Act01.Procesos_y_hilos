package ex1_4;

import java.util.Scanner;
import java.util.ArrayList;

public class cFilPrincipal {

	private static final int LIMIT_SUPERIOR = 100;
	private static final int TEMPORITZACIO_MINIMA = 300;
	private static final int INCREMENT_TEMPORITZACIO = 200;

	public static void main(String[] pArguments) {

		System.out.println("Fil principal iniciat.");
		System.out.println("Fil secundari iniciat.");

		int vNumProcesosFil = obtenirNumProcesosFil(pArguments);
		
		if (vNumProcesosFil <= 0) {
			System.out.println("Número de procesos inválido. Debe ser mayor que 0.");
			return;
		}
		
		ArrayList<Thread> vArrayFilos = new ArrayList<>();
		ArrayList<cFil> vArrayObjecteFil = new ArrayList<>();

		System.out.println("Iniciant execució procés principal");
		System.out.println("Creant " + vNumProcesosFil + " procesos hijo...");
		
		for (int vComptador = 1; vComptador <= vNumProcesosFil; vComptador++) {
			
			cFil vObjecteFil = new cFil("#" + vComptador);
			
			int vTemporitzacio = TEMPORITZACIO_MINIMA + (vComptador - 1) * INCREMENT_TEMPORITZACIO;
			vObjecteFil.sTemporitzacio(vTemporitzacio);
			
			Thread vFil = new Thread(vObjecteFil);

			vArrayFilos.add(vFil);
			vArrayObjecteFil.add(vObjecteFil);

			vFil.start();
			
			System.out.println("Procés hijo #" + vComptador + " iniciado (temporización: " + vTemporitzacio + " ms)");
		}

		try {
			System.out.println("Processo principal aguardando que terminen los procesos hijo...");
			for (Thread vFil : vArrayFilos) {
				vFil.join();
			}

		}

		catch (InterruptedException pExcepcio) {

			System.out.println("Interrompent execució procès principal");

		}

		System.out.println("Todos los procesos hijo han finalizado");
		System.out.println("Acabant execució procès principal");

	}
	
	private static int obtenirNumProcesosFil(String[] pArguments) {
		
		int vNumProcesos = -1;
		
		if (pArguments != null && pArguments.length > 0) {
			try {
				vNumProcesos = Integer.parseInt(pArguments[0]);
				System.out.println("Número de procesos leído desde parámetro: " + vNumProcesos);
			} catch (NumberFormatException e) {
				System.out.println("Parámetro inválido. Se solicitará por consola.");
			}
		}
		
		if (vNumProcesos <= 0) {
			try (Scanner vScanner = new Scanner(System.in)) {
				while (vNumProcesos <= 0) {
					System.out.print("Introduce el número de procesos hijo (1-" + LIMIT_SUPERIOR + "): ");
					try {
						vNumProcesos = vScanner.nextInt();
						if (vNumProcesos <= 0) {
							System.out.println("El número debe ser mayor que 0.");
						}
					} catch (Exception e) {
						System.out.println("Entrada inválida. Introduce un número entero.");
						vScanner.nextLine();
						vNumProcesos = -1;
					}
				}
			}
		}
		
		if (vNumProcesos > LIMIT_SUPERIOR) {
			System.out.println("Número de procesos excede el límite de " + LIMIT_SUPERIOR + ". Ajustando a " + LIMIT_SUPERIOR);
			vNumProcesos = LIMIT_SUPERIOR;
		}
		
		return vNumProcesos;
	}

}

