package de.comsystoreply.gearbox.application.user.adapter.persistence

import com.google.cloud.storage.Acl
import com.google.firebase.cloud.StorageClient
import de.comsystoreply.gearbox.domain.user.port.persistance.CloudImageStorage
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
            throw RuntimeException("Failed to upload image to Firebase storage", e)
        }
    }
}