package net.zymen.retryablerequests.storage;

interface ResponseStorage {
    fun set(key: String, content: String)
    fun get(key: String) : String
    fun hasKey(key: String): Boolean
}
