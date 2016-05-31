import com.sadvit.Application;
import com.sadvit.models.Network;
import com.sadvit.repositories.NetworkRepository;
import com.sadvit.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sadvit on 4/16/16.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)
public class NetworkRepositoryTest {

    /*@Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private UserRepository userRepository;

    public static final String USERNAME = "sadvit";
    public static final String MEMORY = "memory.txt";

    @Test
    public void createTestNetwork() {
        *//*User user = userRepository.getUser(USERNAME);
        int id = user.getId();

        Map<String, double[][]> memory = null;
        try {
            File file = resourceLoader.getResource(MEMORY).getFile();
            StatisticalRecognizer recognizer = new StatisticalRecognizer(new HistogramDistribution(), file);
            memory = recognizer.getMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Network networkEntity = new Network();
        networkEntity.setMemory(memory);
        networkEntity.setName("Default");
        networkRepository.addNetwork(id, networkEntity);*//*
    }

    @Test
    public void test() {
        *//*List<Network> networkEntities = createNetworks();
        networkEntities.forEach(network -> networkRepository.addNetwork(USERNAME, network));
        List<Network> _networkEntities = networkRepository.getNetworks(USERNAME);
        Assert.assertNotNull(_networkEntities);
        Assert.assertTrue(networkEntities.size() == _networkEntities.size());*//*
    }

    public List<Network> createNetworks() {
        List<Network> networkEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Network network = new Network();
            network.setName("Network " + i);
            network.setAnswers(Arrays.asList("bovis", "alabamensis", "aubermensis"));
            network.setPerceptron(new MultiLayerPerceptron(3, 50, 3));
            networkEntities.add(network);
        }
        return networkEntities;
    }*/

}
