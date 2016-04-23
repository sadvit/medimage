package com.sadvit.services;

import com.sadvit.models.CacheObject;
import com.sadvit.models.Chain;
import com.sadvit.models.ChainElement;
import com.sadvit.models.User;
import com.sadvit.repositories.ChainRepository;
import com.sadvit.repositories.UserRepository;
import com.sadvit.to.ChainTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by sadvit on 21.12.15.
 */
@Service
public class ChainService {

    @Autowired
    private ImageCache imageCache;

    @Autowired
    private ImageService imageService;

    @Autowired
    private BinaryService binaryService;

    @Autowired
    private BlurService blurService;

    @Autowired
    private ChainRepository chainRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    public CacheObject processChain(String id, List<ChainElement> chain) {
        BufferedImage currentImage = imageService.getBufferedImage(id);
        for (ChainElement chainElement : chain) {
            switch (chainElement.getOperationType()) {
                case BINARY:
                    if (chainElement.getBinaryParams() != null)
                        currentImage = binaryService.processAsImage(currentImage, chainElement.getBinaryParams());
                    break;
                case BLUR:
                    if (chainElement.getBlurParams() != null)
                        currentImage = blurService.processAsImage(currentImage, chainElement.getBlurParams());
                    break;
            }
        }
        byte[] result = toByteArray(currentImage);
        return imageCache.addToCache(result);
    }

    public CacheObject processChain(String id, Long chainId) {
        Chain chain = getChain(chainId);
        return processChain(id, chain.getChainElements());
    }

    public List<String> processChains(List<String> images, Long chainId) {
        Chain chain = chainRepository.findOne(chainId);
        List<String> result = new ArrayList<>();
        images.forEach(imageId -> {
            result.add(processChain(imageId, chain.getChainElements()).getId());
        });
        return result;
    }

    public List<ChainTO> getChains(Long userId) {
        Set<Chain> chains = chainRepository.findByUserId(userId);
        return chains.stream()
                .map(chain -> conversionService.convert(chain, ChainTO.class))
                .collect(Collectors.toList());
    }

    public Chain getChain(Long id) {
        return chainRepository.findOne(id);
    }

    public void saveChain(Long userId, ChainTO chainTO) {
        Chain chain = conversionService.convert(chainTO, Chain.class);
        User user = userRepository.findOne(userId);
        chain.setUser(user);
        chainRepository.save(chain);
    }

}
