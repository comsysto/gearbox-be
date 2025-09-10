package de.comsystoreply.gearbox.domain.user.port.persistance

/**
* Port for fetching and uploading images from/to the cloud storage
 */
interface CloudImageStorage {
    /**
     * @property [imageData] image in byte array form provided by user ready to upload
     * @return Image URL [String] if upload was successful
     * @throws [CloudImageStorageUploadFailedException] if upload process failed
     */
    fun uploadImage(imageData: ByteArray, namePrefix: String = "gearbox", contentType: String? = "image/jpeg"): String

    /**
     * @property [imageUrl] URL to the existing image in the Firebase Cloud Storage
     * @throws [CloudImageStorageImageNotFound] if image for the given [imageUrl] is not found
     * @throws [CloudImageStorageDeletionFailedException] if deletion process failed
     */
    fun deleteImage(imageUrl: String)
}

sealed class CloudImageStorageException(message: String) : Exception(message)

class CloudImageStorageUploadFailedException(message: String) : CloudImageStorageException(message)

class CloudImageStorageImageNotFound(message: String) : CloudImageStorageException(message)

class CloudImageStorageDeletionFailedException(message: String) : CloudImageStorageException(message)

