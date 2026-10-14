import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
public class Email {
    private final String from;
    private final String to;
    private final String subject;
    private final String body;
    private final String html;
    private final List<String> attachments;

    private Email(Builder b) {
        this.from = Objects.requireNonNull(b.from, "from is required");
        this.to = Objects.requireNonNull(b.to, "to is required");
        this.subject = Objects.requireNonNull(b.subject, "subject is required");
        this.body = b.body != null ? b.body : "";
        this.html = b.html != null ? b.html : "";
        this.attachments = b.attachments != null ? List.copyOf(b.attachments) : List.of();
    }

    public static class Builder {
        private String from;
        private String to;
        private String subject;
        private String body;
        private String html;
        private List<String> attachments;

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder html(String html) {
            this.html = html;
            return this;
        }

        public Builder attach(String path) {
            if (attachments == null) {
                attachments = new ArrayList<>();
            }

            attachments.add(path);
            return this;
        }


        public Email build() {
            return new Email(this);
        }

    }
}
