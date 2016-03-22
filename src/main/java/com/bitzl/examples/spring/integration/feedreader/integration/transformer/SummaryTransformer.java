package com.bitzl.examples.spring.integration.feedreader.integration.transformer;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;

public class SummaryTransformer extends AbstractTransformer {

    @Override
    protected Object doTransform(Message<?> message) throws Exception {
        SyndEntry syndEntry = (SyndEntry) message.getPayload();
        return "SUMMARY: " + syndEntry.getDescription().getValue();
    }

}
