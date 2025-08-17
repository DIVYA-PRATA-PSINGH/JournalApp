package com.example.demo.entity;

import javax.validation.constraints.NotBlank;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String content;

    private LocalDateTime date;
}
