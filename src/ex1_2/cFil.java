package ex1_2;

//alternativa: extends Thread
public class cFil implements Runnable {

	String aNomFil;

	int aTemporitzacio;

	public cFil(String pNomFil) {

		aTemporitzacio = 500;

		aNomFil = pNomFil;

	}

	public String gNomFil() {

		return aNomFil;

	}

	public void sTemporitzacio(int pTemporitzacio) {

		aTemporitzacio = pTemporitzacio;

	}

	public void run() {

		System.out.println("Iniciant execució procés " + aNomFil);

		try {

			for (int vComptador = 0; vComptador < 10; vComptador++) {

				Thread.sleep(aTemporitzacio);

				System.out.println("Despertant aturada " + vComptador + " procès " + aNomFil);

			}

		}

		catch (InterruptedException pExcepcio) {

			System.out.println("Interrompent execució procès " + aNomFil);

		}

		System.out.println("Acabant execució procès " + aNomFil);

	}

}
