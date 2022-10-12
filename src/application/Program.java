package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Room number: ");
		int number = sc.nextInt();
		System.out.print("Check-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next()); // entra no formato texto e o Simple.. e converte
		System.out.print("Check-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next()); // entra no formato texto e o Simple.. e converte

		// testa para ver se essa dataOut não é depois do checkIn
		if (!checkOut.after(checkIn)) { // testa se é uma data é depois da outra.
			System.out.print("Error in reservation: Check-out date must be after check-in date");
		} else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);

			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next()); // entra no formato texto e o Simple.. e converte
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next()); // entra no formato texto e o Simple.. e converte

			String error = reservation.updateDates(checkIn, checkOut); // atualiza minhas datas
			if (error != null) {
				System.out.println("Error in reservation:" + error);
			} 
			else {
				System.out.println("Reservation: " + reservation); // imprimi novamente a reserva

			}
		}

		sc.close();
	}

}
