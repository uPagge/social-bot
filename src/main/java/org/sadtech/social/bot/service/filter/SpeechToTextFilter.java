package org.sadtech.social.bot.service.filter;


import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.sadtech.social.core.domain.content.Mail;
import org.sadtech.social.core.domain.content.attachment.AttachmentType;
import org.sadtech.social.core.domain.content.attachment.AudioMessage;
import org.sadtech.social.core.service.Filter;

import java.io.IOException;
import java.util.List;

/**
 * Переводит голосовое сообщение пользователя в текстовое.
 *
 * @author upagge [11/07/2019]
 */
@Slf4j
public class SpeechToTextFilter implements Filter<Mail> {

    @Override
    public void processing(Mail mail) {
        if (mail.getAttachments() != null) {
            mail.getAttachments().stream()
                    .filter(attachment -> AttachmentType.AUDIO_MESSAGE.equals(attachment.getType()))
                    .forEach(attachment -> convertAudio(mail, (AudioMessage) attachment));
        }
    }

    private void convertAudio(Mail mail, AudioMessage attachment) {
        try (SpeechClient speechClient = SpeechClient.create()) {

            byte[] data = IOUtils.toByteArray(attachment.getLinkOdd().openStream());
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
                mail.setText(alternative.getTranscript());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
