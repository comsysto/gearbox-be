package de.comsystoreply.gearbox.domain.user.port.persistance

/**
* Port for fetching and uploading images from/to the cloud storage
 */
interface CloudImageStorage {
    /**
     * @property [imageData] image in byte array form provided by user ready to upload
     * @return Image URL [String] if upload was successful
     * @throws [RuntimeException] if upload process failed
     */
    fun uploadImage(imageData: ByteArray, namePrefix: String = "gearbox", contentType: String? = "image/jpeg"): String
}