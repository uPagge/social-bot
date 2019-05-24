package org.sadtech.bot.autoresponder.service.filter;


import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.apache.commons.io.IOUtils;
import org.sadtech.bot.core.domain.Mail;
import org.sadtech.bot.core.domain.attachment.Attachment;
import org.sadtech.bot.core.domain.attachment.AttachmentType;
import org.sadtech.bot.core.domain.attachment.AudioMessage;
import org.sadtech.bot.core.filter.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class SpeechToTextFilter implements Filter<Mail> {

    private static final Logger log = LoggerFactory.getLogger(SpeechToTextFilter.class);

    @Override
    public void doFilter(Mail mail) {
        if (mail.getAttachments() != null) {
            for (Attachment attachment : mail.getAttachments()) {
                if (AttachmentType.AUDIO_MESSAGE.equals(attachment.getType())) {
                    try (SpeechClient speechClient = SpeechClient.create()) {

                        byte[] data = IOUtils.toByteArray(((AudioMessage) attachment).getLinkOdd().openStream());
                        ByteString audioBytes = ByteString.copyFrom(data);

                        RecognitionConfig config = RecognitionConfig.newBuilder()
                                .setEncoding(RecognitionConfig.AudioEncoding.OGG_OPUS)
                                .setSampleRateHertz(16000)
                                .setLanguageCode("ru-RU")
                                .build();
                        RecognitionAudio audio = RecognitionAudio.newBuilder()
                                .setContent(audioBytes)
                                .build();

                        RecognizeResponse response = speechClient.recognize(config, audio);
                        List<SpeechRecognitionResult> results = response.getResultsList();

                        for (SpeechRecognitionResult result : results) {
                            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                            mail.setMessage(alternative.getTranscript());
                        }
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
    }
}
