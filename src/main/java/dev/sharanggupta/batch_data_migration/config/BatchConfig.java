package dev.sharanggupta.batch_data_migration.config;

import dev.sharanggupta.batch_data_migration.entity.DestinationRestaurantEntity;
import dev.sharanggupta.batch_data_migration.entity.SourceRestaurantEntity;
import dev.sharanggupta.batch_data_migration.processor.MongoToPostgresProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoPagingItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class BatchConfig {

  @Bean
  public Job importUserJob(JobRepository jobRepository, Step step1) {
    return new JobBuilder("importUserJob", jobRepository)
            .start(step1)
            .build();
  }

  @Bean
  public Step step1(
          JobRepository jobRepository,
          PlatformTransactionManager transactionManager,
          MongoPagingItemReader<SourceRestaurantEntity> reader,
          ItemProcessor<SourceRestaurantEntity, DestinationRestaurantEntity> processor,
          JpaItemWriter<DestinationRestaurantEntity> writer) {
    return new StepBuilder("step1", jobRepository)
            .<SourceRestaurantEntity, DestinationRestaurantEntity>chunk(10, transactionManager)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .listener(new StepExecutionListener() {
              @Override
              public void beforeStep(StepExecution stepExecution) {
                log.info("Starting migration step...");
              }

              @Override
              public ExitStatus afterStep(StepExecution stepExecution) {
                log.info("Migration step finished.");
                return ExitStatus.COMPLETED;
              }
            })
            .build();
  }

  @Bean
  public ItemProcessor<SourceRestaurantEntity, DestinationRestaurantEntity> processor() {
    return new MongoToPostgresProcessor();
  }
}