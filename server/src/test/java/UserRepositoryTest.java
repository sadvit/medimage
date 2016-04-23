import com.sadvit.Application;
import com.sadvit.models.Authority;
import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import com.sadvit.services.UserService;
import com.sadvit.to.UserTO;
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
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        UserTO userTO = new UserTO();
        userTO.setUsername("sadvit");
        userTO.setNewPassword("123456");
        userService.register(userTO);
        userTO.setUsername("mrsadvit");
        userService.register(userTO);

        User sadvit = userRepository.findByUsername("sadvit");
        User mrsadvit = userRepository.findByUsername("mrsadvit");
        Assert.assertNotNull(sadvit);
        Assert.assertNotNull(mrsadvit);

        List<User> users = userRepository.findAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() == 2);
    }

}
