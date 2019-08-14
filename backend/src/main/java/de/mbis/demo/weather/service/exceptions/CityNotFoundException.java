package de.mbis.demo.weather.service.exceptions;

public class CityNotFoundException extends Exception {
    public CityNotFoundException(String message) { super(message); }
    public CityNotFoundException(String message, Throwable innerException) { super(message, innerException); }
}
