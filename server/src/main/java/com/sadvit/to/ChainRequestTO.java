package com.sadvit.to;

import java.util.List;

/**
 * Created by sadvit on 4/23/16.
 */
public class ChainRequestTO {

    private List<String> images;

    private ChainTO chainTO;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public ChainTO getChainTO() {
        return chainTO;
    }

    public void setChainTO(ChainTO chainTO) {
        this.chainTO = chainTO;
    }

}
