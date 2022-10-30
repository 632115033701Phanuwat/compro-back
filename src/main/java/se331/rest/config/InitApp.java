package se331.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.rest.entity.Comment;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
import se331.rest.repository.CommentRepository;
import se331.rest.repository.EventRepository;
import se331.rest.repository.OrganizerRepository;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    OrganizerRepository organizerRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Comment comment = null;
        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder()
                .name("Dr. Robert Rey").build());
        org2 = organizerRepository.save(Organizer.builder()
                .name("Dr. Leonard Hochstein").build());
        org3 = organizerRepository.save(Organizer.builder()
                .name("Dr. Terry Dubrow").build());
        Event tempEvent = null;
        tempEvent = eventRepository.save(Event.builder()
                .vaccine("sinopharm")
                .vaccine1("astrazeneca")
                .vaccine2("pfizer")
                .name("Dr.Chatchat Sitthiphan")
                .addimg("https://media.discordapp.net/attachments/919493057722855424/1035196186019233942/08041ccedaff25a9.jpg")
                .age("56")
                .location("Bangkok")
                .petAllowed(false)
                .organizer(org1)
                .build());
        comment = commentRepository.save(Comment.builder().comment("show time").name("aa").build());
        org1.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .vaccine("astrazeneca")
                .vaccine1("pfizer")
                .vaccine2("pfizer")
                .name("Tony Woodsome")
                .addimg("https://media.discordapp.net/attachments/919493057722855424/1035194630909730917/tonee.jpg?width=1191&height=670")
                .age("73")
                .location("United Arab Emirates")
                .petAllowed(false)
                .organizer(org1)
                .build());
        comment = commentRepository.save(Comment.builder().comment("show time").name("a").build());
        org1.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .vaccine("astrazeneca")
                .vaccine1("pfizer")
                .vaccine2("moderna")
                .name("Aranya Barameemahasan ")
                .addimg("https://media.discordapp.net/attachments/919493057722855424/1035196146617950280/aran.png")
                .age("20")
                .location("Changmai")
                .organizer(org2)
                .petAllowed(false)
                .build());
        comment = commentRepository.save(Comment.builder().comment("show time").name("b").build());
        org2.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .vaccine("sinovac")
                .vaccine1("sinovac")
                .vaccine2("sinovac")
                .name("Prayut Chan-o-cha")
                .addimg("https://media.discordapp.net/attachments/919493057722855424/1035193979639189544/Pra.jpg")
                .age("65")
                .location("Bangkok")
                .petAllowed(true)
                .organizer(org3)
                .build());
        comment = commentRepository.save(Comment.builder().comment("show time").name("c").build());
        org3.getOwnEvents().add(tempEvent);
        addUser();
        org1.setUser(user4);
        user4.setOrganizer(org1);
        org2.setUser(user5);
        user5.setOrganizer(org2);
        org3.setUser(user6);
        user6.setOrganizer(org3);
    }

    User user1,user2,user3,user4,user5,user6;
    private  void addUser(){

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
      Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
      Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user3 = User.builder()
                .username("disableUser")
                .password(encoder.encode("disableUser"))
                .firstname("disableUser")
                .lastname("disableUser")
                .email("disableUser@user.com")
                .enabled(false)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user4 = User.builder()
                .username("doctor1")
                .password(encoder.encode("doctor1"))
                .firstname("Dr. Robert")
                .lastname("Rey")
                .email("Robert_Rey@doctor.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user5 = User.builder()
                .username("doctor2")
                .password(encoder.encode("doctor2"))
                .firstname("Dr.Leonard")
                .lastname("Hochstein")
                .email("Leonard_Hochstein@doctor.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user6 = User.builder()
                .username("doctor3")
                .password(encoder.encode("doctor3"))
                .firstname("Dr.Terry")
                .lastname("Dubrow")
                .email("Dr. Terry Dubrow@docter.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
        user1.getAuthorities().add(authUser);
        user1.getAuthorities().add(authAdmin);
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authUser);
        user4.getAuthorities().add(authUser);
        user4.getAuthorities().add(authDoctor);
        user5.getAuthorities().add(authUser);
        user5.getAuthorities().add(authDoctor);
        user6.getAuthorities().add(authUser);
        user6.getAuthorities().add(authDoctor);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);

    }
}
