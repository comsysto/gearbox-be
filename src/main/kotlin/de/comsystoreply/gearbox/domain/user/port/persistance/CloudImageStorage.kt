package de.comsystoreply.gearbox.domain.user.port.persistance

import org.springframework.web.multipart.MultipartFile

/**
* Port for fetching and uploading images from/to the cloud storage
 */
interface CloudImageStorage {
    /**
     * @property [image] image provided by user ready to upload
     * @return Image URL if upload was successful
     */
    fun uploadImage(image: MultipartFile, namePrefix: String = "gearbox"): String
}