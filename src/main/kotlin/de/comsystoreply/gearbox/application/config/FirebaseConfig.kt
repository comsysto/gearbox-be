package de.comsystoreply.gearbox.application.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.StorageClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import java.io.IOException

@Configuration
class FirebaseConfig {

    @Value("classpath:firebase-admin-sdk.json")
    private lateinit var serviceAccountKey: Resource

    @Value("\${firebase.storage-bucket}")
    private lateinit var storageBucketName: String

    @Bean
    fun firebaseApp(): FirebaseApp {
        return try {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountKey.inputStream))
                .setStorageBucket(storageBucketName)
                .build()

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
            }
            FirebaseApp.getInstance()
        } catch (e: IOException) {
            throw RuntimeException("Failed to initialize Firebase App", e)
        }
    }

    @Bean
    fun firebaseStorageClient(firebaseApp: FirebaseApp): StorageClient {
        return StorageClient.getInstance(firebaseApp)
    }

    @Bean
    fun firebaseStorage(firebaseApp: FirebaseApp): Storage {
        return StorageOptions.getDefaultInstance().service
    }
}