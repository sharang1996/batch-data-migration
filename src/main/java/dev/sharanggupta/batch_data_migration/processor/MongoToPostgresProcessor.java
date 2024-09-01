package dev.sharanggupta.batch_data_migration.processor;

import dev.sharanggupta.batch_data_migration.entity.DestinationRestaurantEntity;
import dev.sharanggupta.batch_data_migration.entity.SourceRestaurantEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MongoToPostgresProcessor implements ItemProcessor<SourceRestaurantEntity, DestinationRestaurantEntity> {

    @Override
    public DestinationRestaurantEntity process(SourceRestaurantEntity SourceRestaurantEntity) {
        DestinationRestaurantEntity DestinationRestaurantEntity = new DestinationRestaurantEntity();

        // Map simple fields
        DestinationRestaurantEntity.setBuilding(SourceRestaurantEntity.getAddress().getBuilding());
        DestinationRestaurantEntity.setStreet(SourceRestaurantEntity.getAddress().getStreet());
        DestinationRestaurantEntity.setZipcode(SourceRestaurantEntity.getAddress().getZipcode());
        DestinationRestaurantEntity.setBorough(SourceRestaurantEntity.getBorough());
        DestinationRestaurantEntity.setCuisine(SourceRestaurantEntity.getCuisine());
        DestinationRestaurantEntity.setName(SourceRestaurantEntity.getName());
        DestinationRestaurantEntity.setRestaurantId(SourceRestaurantEntity.getRestaurantId());

        // Calculate average score
        List<SourceRestaurantEntity.Grade> grades = SourceRestaurantEntity.getGrades();
        if (grades != null && !grades.isEmpty()) {
            double averageScore = grades.stream().mapToInt(dev.sharanggupta.batch_data_migration.entity.SourceRestaurantEntity.Grade::getScore).average().orElse(0.0);
            DestinationRestaurantEntity.setAverageScore(averageScore);

            // Find the most recent grade date
            Date lastGradeDate = grades.stream()
                    .map(dev.sharanggupta.batch_data_migration.entity.SourceRestaurantEntity.Grade::getDate)
                    .max(Date::compareTo)
                    .orElse(null);
            DestinationRestaurantEntity.setLastGradeDate(lastGradeDate);
        } else {
            DestinationRestaurantEntity.setAverageScore(0.0);
            DestinationRestaurantEntity.setLastGradeDate(null);
        }

        return DestinationRestaurantEntity;
    }
}
