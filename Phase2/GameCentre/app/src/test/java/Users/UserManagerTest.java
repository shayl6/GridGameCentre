package Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagerTest {

    /**
     * A instance of UserManager for testing
     */
    private UserManager userManager;


    /**
     * Set up for testing
     */
    @Before
    public void setUp() {
        this.userManager = new UserManager();
        this.userManager.signUp("@test user 1", "111111");
        this.userManager.signUp("@test user 2", "222222");
    }

    /**
     * Clean up after testing
     */
    @After
    public void tearDown() {
        this.userManager = null;
    }

    /**
     * Test login functionality
     */
    @Test
    public void testLogin() {
        assertEquals("@test user 1", this.userManager.login("@test user 1", "111111"));
        assertEquals("", this.userManager.login("@test user 6", "666666"));
    }

    /**
     * test the exist method
     */
    @Test
    public void testExist() {
        assertTrue(this.userManager.exist("@test user 1"));
        assertTrue(!this.userManager.exist("@test user 6"));
    }

    /**
     * test the signUp functionality
     */
    @Test
    public void testSignUp() {
        this.userManager.signUp("@test user 2", "222222");
        assertTrue(this.userManager.exist("@test user 2"));
        assertEquals("@test user 2", this.userManager.login("@test user 2", "222222"));
    }

    /**
     * test the getUser method
     */
    @Test
    public void testGetUser() {
        assertEquals("@test user 1", this.userManager.getUser("@test user 1").getUserName());
        assertNull(this.userManager.getUser("@test user 3"));

    }

    /**
     * test the checkUserNameValidity method
     */
    @Test
    public void testCheckUserNameValidity() {
        assertEquals("OK!", this.userManager.checkUserNameValidity("@"));
        assertEquals("Please enter a valid e-mail", this.userManager.checkUserNameValidity(""));
        assertEquals("E-mail exist", this.userManager.checkUserNameValidity("@test user 1"));


    }

    /**
     * test isValidPassword method
     */
    @Test
    public void testIsValidPassword() {
        assertTrue(this.userManager.isValidPassword("111111"));
        assertTrue(!this.userManager.isValidPassword("1"));

    }
}