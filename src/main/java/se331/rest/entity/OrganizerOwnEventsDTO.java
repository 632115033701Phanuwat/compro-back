package se331.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerOwnEventsDTO {
    Long id;
    String vaccine;
    String vaccine1;
    String vaccine2;
    String name;
    String age;
    String description;
    String location;
    String date;
    String date1;
    String date2;
    String time;
    String time1;
    String time2;
    String addimg;
    Boolean petAllowed;
    List<Participant> participants;
}

