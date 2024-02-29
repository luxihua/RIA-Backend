package com.example.ria.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleStorageConfig {
    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }

}
