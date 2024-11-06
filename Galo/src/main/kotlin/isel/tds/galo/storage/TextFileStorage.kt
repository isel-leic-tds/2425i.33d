package isel.tds.galo.storage

import java.nio.file.Path
import kotlin.io.path.*

class TextFileStorage<Key,Data>(
    baseFolderName: String,
    private val serializer: Serializer<Data>
) :Storage<Key, Data> {
    private val basePath = Path(baseFolderName)

    init {  // Create base folder if it does not exist
        with(basePath) {
            if (!this.exists() ) createDirectory()
            else check( isDirectory() ) { "$name is not a directory" }
        }
    }

    private inline fun <R> with(key: Key, fx: Path.()->R): R =
        (basePath / "$key.txt").fx()

    // CRUD operations
    override fun create(key: Key, data: Data) = with(key) {
        check( !exists() ) { "File $key exists" }
        writeText(serializer.serialize(data))
    }
    override fun read(key: Key): Data? = with(key) {
        try { serializer.deserialize( readText() ) }
        catch (e: NoSuchFileException) { null }
    }
    override fun update(key: Key, data: Data) = with(key) {
        check( exists() ) { "File $key does not exist" }
        writeText(serializer.serialize(data))
    }
    override fun delete(key: Key) = with(key) {
        check( deleteIfExists() ) { "File $key does not exist" }
    }
}