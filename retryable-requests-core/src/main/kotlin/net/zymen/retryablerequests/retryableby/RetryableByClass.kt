package net.zymen.retryablerequests.retryableby

import net.zymen.retryablerequests.MyRequestWrapper

interface RetryableByClass {
    fun requestToChecksum(request: MyRequestWrapper, annotation: RetryableBy): String
}