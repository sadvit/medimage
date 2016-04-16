package com.sadvit.controllers;

import com.sadvit.models.CacheObject;
import com.sadvit.models.Chain;
import com.sadvit.enums.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import com.sadvit.models.ChainElement;
import com.sadvit.services.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadvit on 21.12.15.
 */
@RestController
@RequestMapping("/chains")
public class ChainController {

    @Autowired
    private ChainService chainService;

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public CacheObject processChain(@PathVariable String id, @RequestBody ChainElement[] chain) {
        return chainService.processChain(id, chain);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Chain> getChains() {
        List<Chain> chains = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Chain chain = new Chain();
            chain.setId(i);
            chain.setName("chain" + i);
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
