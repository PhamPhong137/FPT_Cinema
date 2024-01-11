/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Timestamp;

/**
 *
 * @author PC-Phong
 */
public class Ticket {

    private int id;
    private int account_id;
    private int seat_id;
    private int ticketfilm_id;
    private Timestamp bookingTime;
    private int status;

    public Ticket() {
    }

    public Ticket(int id, int account_id, int seat_id, int ticketfilm_id, Timestamp bookingTime, int status) {
        this.id = id;
        this.account_id = account_id;
        this.seat_id = seat_id;
        this.ticketfilm_id = ticketfilm_id;
        this.bookingTime = bookingTime;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getTicketfilm_id() {
        return ticketfilm_id;
    }

    public void setTicketfilm_id(int ticketfilm_id) {
        this.ticketfilm_id = ticketfilm_id;
    }

}
