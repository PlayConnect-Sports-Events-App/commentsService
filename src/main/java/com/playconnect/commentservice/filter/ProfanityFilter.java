package com.playconnect.commentservice.filter;

import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ProfanityFilter {

    private static final Set<String> bannedWords = Set.of("badword1", "badword2", "badword3"); // Initialize with banned words

    public boolean containsProfanity(String text) {
        String lowerCaseText = text.toLowerCase();
        return bannedWords.stream().anyMatch(lowerCaseText::contains);
    }
}
