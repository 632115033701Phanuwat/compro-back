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
public class EventDTO {
    Long id;
    String vaccine;
    String vaccine1;
    String vaccine2;
    String name;
    String age;
    String location;
    String addimg;
    Boolean petAllowed;
    EventOrganizerDTO organizer;
    List<String> imageUrls;
    List<CommentDTO> commentList;
}

