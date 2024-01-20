package org.example.back

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class SocketController {

    @MessageMapping("/send")
    fun webSocket(message: Message) =
         MessageResponse(message.value)

}