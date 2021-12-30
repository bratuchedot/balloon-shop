package mk.ukim.finki.balloon.shop.model.exceptions;

public class BalloonNotFoundException extends RuntimeException {
    public BalloonNotFoundException(Long id) {
        super(String.format("Balloon with id: %d was not found", id));
    }
}
