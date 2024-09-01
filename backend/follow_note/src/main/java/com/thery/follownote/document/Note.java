/**
 * Note represents a note associated with a patient in the Note system.
 *
 * @patient its id of patient
 */
package com.thery.follownote.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;


@Document(collection = "FollowNote")
@Getter
@Setter
public class Note {

    @Id
    private String id;

    @Field("patientId")
    private String patientId;

    @Field("date")
    private LocalDateTime date;

    @Field("note")
    private String note;

    @Field("patient")
    private String patient;

}