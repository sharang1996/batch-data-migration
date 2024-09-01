package dev.sharanggupta.batch_data_migration.reader;

import dev.sharanggupta.batch_data_migration.entity.SourceRestaurantEntity;
import org.springframework.batch.item.data.MongoPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;

@Configuration
public class SourceRestaurantReader {
  @Bean
  public MongoPagingItemReader<SourceRestaurantEntity> mongoItemReader(
      MongoTemplate mongoTemplate) {
    MongoPagingItemReader<SourceRestaurantEntity> reader = new MongoPagingItemReader<>();
    reader.setTemplate(mongoTemplate);
    reader.setQuery("{}"); // MongoDB query (example: empty to fetch all)
    reader.setTargetType(SourceRestaurantEntity.class);
    reader.setSort(new HashMap<>()); // Empty sort as default
    return reader;
  }
}
