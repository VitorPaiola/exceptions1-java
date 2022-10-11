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
		}
		else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next()); // entra no formato texto e o Simple.. e converte
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next()); // entra no formato texto e o Simple.. e converte
			
			Date now = new Date(); // Cria uma data com o horário de agora
			if (checkIn.before(now) || checkOut.before(now)) { // before = antes
				System.out.println("Error in reservation: Reservation dates for update must be future dates");
			}
			else if (!checkOut.after(checkIn)) { // Se a data de checkOut NÃO FOR posterior a data de checkIn, não posso aceitar.
				System.out.println("Error in reservation: Check-out date must be after check-in date");
			}
			else {
				reservation.updateDates(checkIn, checkOut); // atualiza minhas datas
				System.out.println("Reservation: " + reservation); // imprimi novamente a reserva
			}
			
		}
		
		sc.close();
	}

}



