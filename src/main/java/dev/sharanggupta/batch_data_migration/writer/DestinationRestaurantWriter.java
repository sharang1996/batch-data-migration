package dev.sharanggupta.batch_data_migration.writer;

import dev.sharanggupta.batch_data_migration.entity.DestinationRestaurantEntity;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DestinationRestaurantWriter {
    @Bean
    public JpaItemWriter<DestinationRestaurantEntity> jpaItemWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<DestinationRestaurantEntity> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
}
