package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	// static - para que não seja criado um novo SimpleDateFormat p/ cada objeto Reservation
	// que a minha aplicação tiver. Preciso apenas UM.
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) { // Se a data de checkOut NÃO FOR posterior a data de checkIn, não posso aceitar.
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	// Cálculando a duração em dias com base nas duas datas.
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // converte milisseg para dias.
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		Date now = new Date(); // Cria uma data com o horário de agora
		if (checkIn.before(now) || checkOut.before(now)) { // before = antes
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if (!checkOut.after(checkIn)) { // Se a data de checkOut NÃO FOR posterior a data de checkIn, não posso aceitar.
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkIn = checkIn; // checkIn do objeto recebe checkInt do método
		this.checkOut = checkOut; // checkOut do objeto recebe checkOut do método
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
	
}
