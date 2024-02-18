package com.ccs.ct1signalclassifier.sqs

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

@Service
class SqsMessageSender(private val sqsAsyncClient: SqsAsyncClient)  {

    fun sendMessage(message: String?, queueUrl: String) {
        sqsAsyncClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build())
    }

}