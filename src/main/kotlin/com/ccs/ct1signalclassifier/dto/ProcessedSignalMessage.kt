package com.ccs.ct1signalclassifier.dto

/**
 * Response for Signal Classifier endpoint.
 */
data class ProcessedSignalMessage(
        private val code: Int,
        private val message: String
)
