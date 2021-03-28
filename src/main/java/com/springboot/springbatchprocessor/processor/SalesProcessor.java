package com.springboot.springbatchprocessor.processor;

import com.springboot.springbatchprocessor.entity.Sales;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class SalesProcessor implements ItemProcessor<Sales, Sales> {
    @Override
    public Sales process(Sales sales) throws Exception {
        sales.setLastUpdatedDate(new Date());
        return sales;
    }
}
