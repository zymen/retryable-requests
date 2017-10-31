package net.zymen.retryablerequests.storage

internal class InMemoryResponseStorage : ResponseStorage {
    val storage = mutableMapOf<String, String>()

    override fun set(key: String, content: String) {
        storage[key] = content
    }

    override fun get(key: String): String {
        return storage[key]!!
    }

    override fun hasKey(key: String): Boolean {
        return storage.containsKey(key)
    }
}
