/**
 * Note represents a note associated with a patient in the Note system.
 *
 * @patient its id of patient
 */
package com.thery.note.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


@Document(collection = "FollowNote")
@Getter
@Setter
public class Note {

    @Id
    private String id;

    @Field("patientId")
    private String patientId;

    @Field("date")
    private Date date;

    @Field("note")
    private String note;

    @Field("patient")
    private String patient;

}