package org.example.back

import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SocketController {

    @MessageMapping("/hello") // config에서 prefix로 "pub" 를 설정했으니 /pub/hello로 보내야
    @SendTo("/topic/res") //처리를 마치면 해당 엔드포인트로 보냄. 즉, 해당 엔드포인트로 구독중인 애들한테 보냄
    fun webSocket(messageRequest: MessageRequest) =
        MessageResponse(messageRequest.value)

}