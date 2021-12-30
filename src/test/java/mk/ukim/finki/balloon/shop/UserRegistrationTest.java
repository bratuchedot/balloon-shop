package mk.ukim.finki.balloon.shop;

import mk.ukim.finki.balloon.shop.model.User;
import mk.ukim.finki.balloon.shop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.balloon.shop.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.balloon.shop.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.balloon.shop.repository.jpa.UserRepository;
import mk.ukim.finki.balloon.shop.service.impl.AuthServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    private AuthServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "name", "surname", "password", LocalDate.now());
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);

        service = Mockito.spy(new AuthServiceImpl(this.userRepository));
    }

    @Test
    public void testSuccessRegister() {
        User user = this.service.register("username", "password", "password", "name", "surname", LocalDate.now());

        Mockito.verify(this.service).register("username", "password", "password", "name", "surname", LocalDate.now());

        Assert.assertNotNull("User is null", user);

        Assert.assertEquals("username do not match", "username", user.getUsername());
        Assert.assertEquals("password do not match", "password", user.getPassword());
        Assert.assertEquals("name do not match", "name", user.getName());
        Assert.assertEquals("surname do not match", "surname", user.getSurname());
        Assert.assertEquals("date of birth do not match", LocalDate.now(), user.getDateOfBirth());
    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.register(null, "password", "password", "name", "surename", LocalDate.now()));
        Mockito.verify(this.service).register(null, "password", "password", "name", "surename", LocalDate.now());
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.register(username, "password", "password", "name", "surename", LocalDate.now()));
        Mockito.verify(this.service).register(username, "password", "password", "name", "surename", LocalDate.now());
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.register(username, password, "password", "name", "surename", LocalDate.now()));
        Mockito.verify(this.service).register(username, password, "password", "name", "surename", LocalDate.now());
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.register(username, password, "password", "name", "surename", LocalDate.now()));
        Mockito.verify(this.service).register(username, password, "password", "name", "surename", LocalDate.now());
    }


    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username, password, confirmPassword, "name", "surename", LocalDate.now()));
        Mockito.verify(this.service).register(username, password, confirmPassword, "name", "surename", LocalDate.now());
    }


    @Test
    public void testDuplicateUsername() {
        User user = new User("username", "password", "name", "surename", LocalDate.now());
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "password", "password", "name", "surename", LocalDate.now()));
        Mockito.verify(this.service).register(username, "password", "password", "name", "surename", LocalDate.now());
    }

}
