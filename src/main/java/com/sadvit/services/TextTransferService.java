package com.sadvit.services;

import com.sadvit.ws.transfer.ImageTransfer;
import com.sadvit.ws.transfer.TextTransfer;
import com.sadvit.ws.transfer.Transfer;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by sadvit on 26.11.15.
 */
@Service
public class TextTransferService {

    @Autowired
    private ImageService imageService;

    public Transfer process(TextTransfer transfer) {
        Map<String, Object> map = (Map<String, Object>) transfer.getMessage();
        String id = (String) map.get("id");
        String name = transfer.getUser();
        byte[] array = imageService.getImageAsByteArray(id, name);
        String image = Base64.encodeBase64URLSafeString(array);

        ImageTransfer imageTransfer = new ImageTransfer();
        imageTransfer.setId(transfer.getId());
        imageTransfer.setImage(image);

        return imageTransfer;
    }

}
