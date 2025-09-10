package de.comsystoreply.gearbox.application.user.adapter.persistence

import com.google.cloud.storage.Acl
import com.google.firebase.cloud.StorageClient
import de.comsystoreply.gearbox.domain.user.port.persistance.CloudImageStorage
import de.comsystoreply.gearbox.domain.user.port.persistance.CloudImageStorageDeletionFailedException
import de.comsystoreply.gearbox.domain.user.port.persistance.CloudImageStorageImageNotFound
import de.comsystoreply.gearbox.domain.user.port.persistance.CloudImageStorageUploadFailedException
import org.springframework.stereotype.Service
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class FirebaseCloudImageStorage(private val storageClient: StorageClient) : CloudImageStorage {
    override fun uploadImage(imageData: ByteArray, namePrefix: String, contentType: String?): String {
        val bucket = storageClient.bucket()
        val fileName = "${namePrefix}-${UUID.randomUUID()}"

        try {
            val blob = bucket.create(fileName, imageData, contentType)
            blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))

            val publicUrl =
                "https://storage.googleapis.com/${bucket.name}/${URLEncoder.encode(fileName, StandardCharsets.UTF_8)}"

            return publicUrl
        } catch (e: Exception) {
            throw CloudImageStorageUploadFailedException("Failed to upload image to Firebase storage.\nCause: $e")
        }
    }

    override fun deleteImage(imageUrl: String) {
        val bucket = storageClient.bucket()

        try {
            val fileName = imageUrl.substringAfterLast("/")
            val blob = bucket.get(fileName)

            if (blob.exists()) blob.delete()
            else throw CloudImageStorageImageNotFound("Image with URL $imageUrl not found")
        } catch (e: Exception) {
            throw CloudImageStorageDeletionFailedException("Failed to delete image $imageUrl from Firebase storage.\nCause: $e")
        }
    }
}