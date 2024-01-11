/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;
import java.sql.Timestamp;

public class TicketHistory {

    private int ticketFilmId;
    private String seat_number;
    private int price;
    private String title;
    private String name;
    private String startHour;
    private Date date;
    private Timestamp bookingTime;

    public TicketHistory() {
    }

    public TicketHistory(int ticketFilmId, String seat_number, int price, String title, String name, String startHour, Date date, Timestamp bookingTime) {
        this.ticketFilmId = ticketFilmId;
        this.seat_number = seat_number;
        this.price = price;
        this.title = title;
        this.name = name;
        this.startHour = startHour;
        this.date = date;
        this.bookingTime = bookingTime;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }

    

    

    public int getTicketFilmId() {
        return ticketFilmId;
    }

    public void setTicketFilmId(int ticketFilmId) {
        this.ticketFilmId = ticketFilmId;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
