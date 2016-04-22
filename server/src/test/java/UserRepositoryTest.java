import com.sadvit.Application;
import com.sadvit.models.Authority;
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

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void testAddUser() {
        /*userRepository.addUser("sadvit", "sadvit", Authority.USER);
        userRepository.addUser("mrsadvit", "sadvit", Authority.USER);

        User sadvit = userRepository.getUser("sadvit");
        User mrsadvit = userRepository.getUser("mrsadvit");
        Assert.assertNotNull(sadvit);
        Assert.assertNotNull(mrsadvit);

        List<User> users = userRepository.getAllUsers();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() == 2);*/
    }

    public User createUser(String username) {
        return null;
        /*User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(username));
        return user;*/
    }

}
