package utils;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailUtils {

    public static String getOtpFromEmail(String email, String appPassword) {
        String host = "imap.gmail.com";
        String otp = null;

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        try {
            System.out.println("🔐 Connecting to mail server...");
            Session session = Session.getInstance(props);
            Store store = session.getStore();
            store.connect(host, email, appPassword);
            System.out.println("✅ Connected to mail server.");

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            System.out.println("📥 Opened inbox. Total messages: " + inbox.getMessageCount());

            int attempts = 0;
            while (attempts < 6 && otp == null) { // Retry every 5 seconds
                System.out.println("🔍 Searching for OTP mail (Attempt " + (attempts + 1) + ")...");

                Message[] messages = inbox.getMessages();
                System.out.println("📨 Total messages fetched: " + messages.length);

                for (int i = messages.length - 1; i >= 0; i--) { // latest message first
                    Message msg = messages[i];
                    String subject = msg.getSubject();

                    System.out.println("✉️ Checking message #" + (i + 1) + " | Subject: " + subject);

                    if (subject != null && subject.contains("One-Time Password")) {
                        System.out.println("🔑 OTP mail found. Parsing content...");
                        String content = getTextFromMessage(msg);
                        System.out.println("📄 Parsed Email Content:\n" + content);

                        Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(content);
                        if (matcher.find()) {
                            otp = matcher.group();
                            System.out.println("✅ OTP extracted: " + otp);
                            break;
                        } else {
                            System.out.println("❌ OTP pattern not found in content.");
                        }
                    }
                }

                if (otp == null) {
                    System.out.println("⏳ OTP not found. Retrying in 5 seconds...");
                    Thread.sleep(5000);
                }

                attempts++;
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            System.out.println("❌ Error while fetching OTP from email:");
            e.printStackTrace();
        }

        if (otp == null) {
            System.out.println("❌ OTP retrieval failed after multiple attempts.");
            throw new RuntimeException("❌ Failed to fetch a valid 6-digit OTP from email.");
        }

        return otp;
    }

    private static String getTextFromMessage(Message message) throws Exception {
        Object content = message.getContent();

        if (content instanceof String) {
            return (String) content;
        }

        if (content instanceof MimeMultipart) {
            return getTextFromMimeMultipart((MimeMultipart) content);
        }

        return "";
    }

    private static String getTextFromMimeMultipart(MimeMultipart multipart) throws Exception {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart part = multipart.getBodyPart(i);

            if (part.isMimeType("text/plain")) {
                result.append(part.getContent().toString());
            } else if (part.isMimeType("text/html")) {
                String html = part.getContent().toString();
                result.append(Jsoup.parse(html).text()); // extract only visible text
            } else if (part.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) part.getContent())); // recursive parse
            }
        }

        return result.toString();
    }
}
