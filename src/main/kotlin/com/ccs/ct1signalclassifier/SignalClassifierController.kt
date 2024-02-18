package com.ccs.ct1signalclassifier

import com.ccs.ct1signalclassifier.dto.ProcessedSignalMessage
import com.ccs.ct1signalclassifier.dto.Signal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignalClassifierController(private val signalClassifierService: SignalClassifierService) {

    @PostMapping("/signal")
    fun process(@RequestBody signal: Signal): ProcessedSignalMessage {
        return signalClassifierService.save(signal = signal);
    }

    @GetMapping("/signal")
    fun test() = "Hello World"
}