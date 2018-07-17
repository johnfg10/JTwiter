package io.github.johnfg10.jtweeter.model;

import lombok.Builder;
import lombok.Data;

import java.security.SecureRandom;
import java.util.Set;

@Data
@Builder
public class Tweet {
    protected SecureRandom random = new SecureRandom();

    public long id = random.nextLong();
    public long authorId;
    public String messageContent;
    public Set<Attachment> attachments;
}
