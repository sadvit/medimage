package com.sadvit.services;

import com.sadvit.models.CacheObject;
import com.sadvit.models.Chain;
import com.sadvit.models.ChainElement;
import com.sadvit.models.User;
import com.sadvit.repositories.ChainRepository;
import com.sadvit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private UserService userService;

    public CacheObject processChain(String id, ChainElement[] chain) {
        BufferedImage currentImage = imageService.getBufferedImage(id);
        for (ChainElement chainElement : chain) {
            switch (chainElement.getOperationType()) {
                case BINARY:
                    currentImage = binaryService.processAsImage(currentImage, chainElement.getBinaryParams());
                    break;
                case BLUR:
                    currentImage = blurService.processAsImage(currentImage, chainElement.getBlurParams());
                    break;
            }
        }
        byte[] result = toByteArray(currentImage);
        return imageCache.addToCache(result);
    }

    public List<Chain> getChains() {
        String username = userService.getCurrentUser();
        return chainRepository.getChains(username);
    }

}
