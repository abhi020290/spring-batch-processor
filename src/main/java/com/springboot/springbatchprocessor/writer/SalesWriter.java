package com.springboot.springbatchprocessor.writer;

import com.springboot.springbatchprocessor.entity.Sales;
import com.springboot.springbatchprocessor.repo.SalesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class SalesWriter implements ItemWriter<Sales> {

    @Autowired
    SalesRepository salesRepository;

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void write(List<? extends Sales> list) throws Exception {
        salesRepository.saveAll(list);
        atomicInteger.getAndAdd(list.size());
        log.info("Counter value is " + atomicInteger.get());
    }
}
