import com.sadvit.Application;
import com.sadvit.analysis.recognizer.statistic.StatisticalRecognizer;
import com.sadvit.analysis.recognizer.statistic.distribution.HistogramDistribution;
import com.sadvit.models.NetworkEntity;
import com.sadvit.repositories.NetworkRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by sadvit on 4/16/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class NetworkRepositoryTest {

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    public static final String USERNAME = "sadvit";
    public static final String MEMORY = "memory.txt";

    @Test
    public void createTestNetwork() {
        Map<String, double[][]> memory = null;
        try {
            File file = resourceLoader.getResource(MEMORY).getFile();
            StatisticalRecognizer recognizer = new StatisticalRecognizer(new HistogramDistribution(), file);
            memory = recognizer.getMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetworkEntity networkEntity = new NetworkEntity();
        networkEntity.setMemory(memory);
        networkEntity.setName("Default");
        networkRepository.addNetwork(USERNAME, networkEntity);
    }

    @Test
    public void test() {
        /*List<NetworkEntity> networkEntities = createNetworks();
        networkEntities.forEach(network -> networkRepository.addNetwork(USERNAME, network));
        List<NetworkEntity> _networkEntities = networkRepository.getNetworks(USERNAME);
        Assert.assertNotNull(_networkEntities);
        Assert.assertTrue(networkEntities.size() == _networkEntities.size());*/
    }

    public List<NetworkEntity> createNetworks() {
        List<NetworkEntity> networkEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NetworkEntity networkEntity = new NetworkEntity();
            networkEntity.setName("Network " + i);
            networkEntity.setAnswers(Arrays.asList("bovis", "alabamensis", "aubermensis"));
            networkEntity.setPerceptron(new MultiLayerPerceptron(3, 50, 3));
            networkEntities.add(networkEntity);
        }
        return networkEntities;
    }

}
