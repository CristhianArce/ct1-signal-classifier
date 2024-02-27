package com.ccs.ct1signalclassifier

import com.ccs.ct1signalclassifier.dto.ProcessedSignalMessage
import com.ccs.ct1signalclassifier.dto.Signal
import com.ccs.ct1signalclassifier.dto.SignalType
import com.ccs.ct1signalclassifier.sqs.SqsMessageSender
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class SignalClassifierService(
        private val sqsMessageSender: SqsMessageSender,
        private val objectMapper: ObjectMapper,
        @Value("\${aws.sqs.anomalous-queue}")  private val anomalousQueue: String,
        @Value("\${aws.sqs.emergency-queue}")  private val emergencyQueue: String,
        @Value("\${aws.sqs.info-queue}")  private val informativeQueue: String) {


    @Async
    fun save(signal: Signal): ProcessedSignalMessage {
        when (signal.type) {
            SignalType.EMERGENCY -> sendMessage(signal, emergencyQueue)
            SignalType.INFO -> sendMessage(signal, informativeQueue)
            SignalType.ANOMALOUS -> sendMessage(signal, anomalousQueue)
        }
        return ProcessedSignalMessage(200, "OK")
    }

    private fun sendMessage(signal: Signal, queueName: String) {
        sqsMessageSender.sendMessage(objectMapper.writeValueAsString(signal), queueName)
    }
}