package org.automation.com.ex_22092024.CRUD_E2E;

public class BookingResponse {
    private Integer bookingid;
    private Booking booking;

    public Booking getBooking()
    {
        return booking;
    }

    public void setBooking(Booking booking)
    {
        this.booking = booking;
    }

    public Integer getBookingid()
    {
        return bookingid;
    }

    public void setBookingid(Integer bookingid)
    {
        this.bookingid = bookingid;
    }


}
