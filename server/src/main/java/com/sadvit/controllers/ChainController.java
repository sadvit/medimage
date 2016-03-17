package com.sadvit.controllers;

import com.sadvit.models.CacheObject;
import com.sadvit.models.Chain;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import com.sadvit.operations.chains.ChainElement;
import com.sadvit.services.BinaryService;
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
            chain.setName("chain" + i);
            List<ChainElement> elements = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ChainElement element = new ChainElement();
                BinaryParams binaryParams = new BinaryParams();
                binaryParams.setType(BinaryType.MEAN);
                element.setBinaryParams(new BinaryParams());
                elements.add(element);
            }
            chain.setChainElements(elements);
            chains.add(chain);
        }
        return chains;
    }

}
