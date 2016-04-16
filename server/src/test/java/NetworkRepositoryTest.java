import com.sadvit.Application;
import com.sadvit.models.Network;
import com.sadvit.repositories.NetworkRepository;
import com.sadvit.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class NetworkRepositoryTest {

    @Autowired
    private NetworkRepository networkRepository;

    public static final String USERNAME = "sadvit";

    @Test
    public void test() {
        Set<Network> networks = createNetworks();
        networks.forEach(network -> networkRepository.addNetwork(USERNAME, network));
        Set<Network> _networks = networkRepository.getNetworks(USERNAME);
        Assert.assertNotNull(_networks);
        Assert.assertTrue(networks.size() == _networks.size());
    }

    public Set<Network> createNetworks() {
        Set<Network> networks = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Network network = new Network();
            network.setName("Network " + i);
            network.setAnswers(Arrays.asList("bovis", "alabamensis", "aubermensis"));
            network.setPerceptron(new MultiLayerPerceptron(3, 50, 3));
            networks.add(network);
        }
        return networks;
    }

}
