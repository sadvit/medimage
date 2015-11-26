package com.sadvit.ws;

import com.sadvit.services.ImageTransferService;
import com.sadvit.services.TextTransferService;
import com.sadvit.ws.transfer.ImageTransfer;
import com.sadvit.ws.transfer.TextTransfer;
import com.sadvit.ws.transfer.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * Created by sadvit on 26.11.15.
 */
public class GatewayHandler extends TransferWebSocketHandler {

    @Autowired
    private TextTransferService textTransferService;

    @Autowired
    private ImageTransferService imageTransferService;

    @Override
    protected Transfer handleTextTransfer(TextTransfer transfer) {
        return textTransferService.process(transfer);
    }

    @Override
    protected Transfer handleImageTransfer(ImageTransfer transfer) {
        return imageTransferService.process(transfer);
    }

}
