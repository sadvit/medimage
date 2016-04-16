import com.sadvit.Application;
import com.sadvit.configs.PersistenceConfiguration;
import com.sadvit.enums.Role;
import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sadvit on 4/16/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        userRepository.addUser("sadvit", "sadvit", Role.USER);
        userRepository.addUser("mrsadvit", "sadvit", Role.USER);
    }

    @Test
    public void testGetUser() {
        User sadvit = userRepository.getUser("sadvit");
        User mrsadvit = userRepository.getUser("mrsadvit");
        Assert.assertNotNull(sadvit);
        Assert.assertNotNull(mrsadvit);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userRepository.getAllUsers();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() == 2);
    }

}
