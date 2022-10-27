package se331.rest.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String vaccine;
    String vaccine1;
    String vaccine2;
    String Name;
    String description;
    String location;
    String date;
    String date1;
    String date2;
    String time;
    String time1;
    String time2;
    Boolean petAllowed;
    @ManyToOne
    Organizer organizer;
    @ManyToMany(mappedBy = "eventHistory")
    List<Participant> participants;
    @ElementCollection
    List<String> imageUrls;


}


