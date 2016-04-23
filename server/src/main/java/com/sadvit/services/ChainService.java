package com.sadvit.services;

import com.sadvit.models.CacheObject;
import com.sadvit.models.Chain;
import com.sadvit.models.ChainElement;
import com.sadvit.models.User;
import com.sadvit.repositories.ChainRepository;
import com.sadvit.repositories.UserRepository;
import com.sadvit.to.ChainRequestTO;
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

    public List<CacheObject> process(ChainRequestTO request) {
        ChainTO chainTO = request.getChainTO();
        List<String> images = request.getImages();
        Chain chain = conversionService.convert(chainTO, Chain.class);
        if (isExistingChain(request)) {
            Long chainId = chainTO.getId();
            chain = chainRepository.findOne(chainId);
        }
        return process(images, chain);
    }

    public List<CacheObject> process(List<String> images, Chain chain) {
        return images.stream()
                .map(image -> processChain(image, chain))
                .collect(Collectors.toList());
    }

    public boolean isExistingChain(ChainRequestTO request) {
        return request.getChainTO().getId() != null;
    }

    public CacheObject processChain(String id, Chain chain) {
        List<ChainElement> elements = chain.getChainElements();
        BufferedImage currentImage = imageService.getBufferedImage(id);
        for (ChainElement chainElement : elements) {
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

    public List<ChainTO> getChains(Long userId) {
        Set<Chain> chains = chainRepository.findByUserId(userId);
        return chains.stream()
                .map(chain -> conversionService.convert(chain, ChainTO.class))
                .collect(Collectors.toList());
    }

    public ChainTO saveChain(Long userId, ChainTO chainTO) {
        Chain chain = conversionService.convert(chainTO, Chain.class);
        User user = userRepository.findOne(userId);
        chain.setUser(user);
        chain = chainRepository.save(chain);
        return conversionService.convert(chain, ChainTO.class);
    }

}
