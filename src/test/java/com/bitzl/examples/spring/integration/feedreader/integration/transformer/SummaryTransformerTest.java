package com.bitzl.examples.spring.integration.feedreader.integration.transformer;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;

public class SummaryTransformerTest {

    private Transformer summaTransformer;

    @Before
    public void setUp() throws Exception {
        summaTransformer = new SummaryTransformer();
    }

    private SyndEntry syndEntryWith(String description) {
        SyndEntry syndEntry = new SyndEntryImpl();
        SyndContent syndContent = new SyndContentImpl();
        syndContent.setValue(description);
        syndEntry.setDescription(syndContent);
        return syndEntry;
    }

    @Test
    public void summaryShouldExtractDescription() {
        String description = "This is a Description.";
        Message<SyndEntry> message = MessageBuilder.withPayload(syndEntryWith(description)).build();
        String summary = (String) summaTransformer.transform(message).getPayload();
        assertThat(summary, endsWith(description));
    }
}