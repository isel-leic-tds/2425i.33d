package isel.tds.galo.storage

interface Storage <Key, Data>{
    fun create(key: Key, data: Data)
    fun read(key: Key): Data?
    fun update(key: Key, data: Data)
    fun delete(key: Key)
}