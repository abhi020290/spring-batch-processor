package com.springboot.springbatchprocessor.config;

import com.springboot.springbatchprocessor.entity.Sales;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Value("${csv.file.path}")
    public String filePath;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<Sales> itemReader,
                   ItemProcessor<Sales, Sales> itemProcessor, ItemWriter<Sales> itemWriter) {
        return jobBuilderFactory.get("Job-batch-processing")
                .incrementer(new RunIdIncrementer())
                .start(step(stepBuilderFactory, itemReader, itemProcessor, itemWriter))
                .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<Sales> itemReader,
                     ItemProcessor<Sales, Sales> itemProcessor, ItemWriter<Sales> itemWriter) {
        return stepBuilderFactory.get("Step-batch-processing")
                .<Sales, Sales>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<Sales> flatFileItemReader() {
        FlatFileItemReader<Sales> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource(filePath));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        flatFileItemReader.setName("CSV-Flat File Reader");
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Sales> lineMapper() {
        DefaultLineMapper<Sales> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<Sales> beanWrapperFieldExtractor = new BeanWrapperFieldSetMapper<>();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("Id", "Transaction_date", "Product", "Price", "Payment_Type", "Name", "City", "State", "Country", "Account_Created", "Last_Login", "Latitude", "Longitude", "US Zip");
        lineTokenizer.setStrict(false);

        beanWrapperFieldExtractor.setTargetType(Sales.class);

        lineMapper.setFieldSetMapper(beanWrapperFieldExtractor);
        lineMapper.setLineTokenizer(lineTokenizer);
        return lineMapper;
    }


}
