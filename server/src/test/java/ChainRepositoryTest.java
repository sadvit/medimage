import com.sadvit.Application;
import com.sadvit.enums.OperationType;
import com.sadvit.models.Chain;
import com.sadvit.models.ChainElement;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import com.sadvit.repositories.ChainRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ChainRepositoryTest {

    public static final String USERNAME = "sadvit";

    @Autowired
    private ChainRepository chainRepository;

    @Test
    public void testAddChain() {
        List<Chain> chains = createChains();
        chains.forEach(chain -> chainRepository.addChain(USERNAME, chain));
        List<Chain> _chains = chainRepository.getChains(USERNAME);
        List<Chain> byOne = new ArrayList<>();
        _chains.forEach(chain -> {
            byOne.add(chainRepository.getChain(chain.getId()));
        });
        Assert.assertNotNull(byOne);
        Assert.assertNotNull(chains);
        Assert.assertNotNull(_chains);
        Assert.assertTrue(chains.size() == _chains.size());
        Assert.assertTrue(chains.size() == byOne.size());
    }

    public List<Chain> createChains() {
        List<Chain> chains = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Chain chain = new Chain();
            chain.setName("Chain " + i);
            List<ChainElement> elements = new ArrayList<>();
            double max = Math.random() * 25.0;
            for (int j = 0; j < max; j++) {
                ChainElement element = new ChainElement();
                BinaryParams binaryParams = new BinaryParams();
                binaryParams.setType(BinaryType.MEAN);
                element.setBinaryParams(binaryParams);
                element.setOperationType(getRandomBinary());
                elements.add(element);
            }
            chain.setChainElements(elements);
            chains.add(chain);
        }
        return chains;
    }

    private OperationType getRandomBinary() {
        double v = Math.random() * 100.0;
        if (v < 33) {
            return OperationType.BINARY;
        } else if (v < 66) {
            return OperationType.BLUR;
        } else if (v < 99) {
            return OperationType.CROP;
        } else {
            return OperationType.RESIZE;
        }
    }

}
