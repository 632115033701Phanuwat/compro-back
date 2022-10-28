package se331.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
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
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
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
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3-2-2021")
                .date1("6-5-2021")
                .date2("15-9-2021")
                .time("7.00 am.")
                .time1("11.00 pm.")
                .time2("2.00 pm.")
                .petAllowed(false)
                .organizer(org1)
                .build());
        org1.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .vaccine("astrazeneca")
                .vaccine1("pfizer")
                .vaccine2("pfizer")
                .name("Tony Woodsome")
                .addimg("https://media.discordapp.net/attachments/919493057722855424/1035194630909730917/tonee.jpg?width=1191&height=670")
                .age("73")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("6-3-2021")
                .date1("12-7-2021")
                .date2("2-1-2021")
                .time("8.00 am.")
                .time1("11.00 pm.")
                .time2("9.00 am.")
                .petAllowed(false)
                .organizer(org1)
                .build());
        org1.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .vaccine("astrazeneca")
                .vaccine1("pfizer")
                .vaccine2("moderna")
                .name("Aranya Barameemahasan ")
                .addimg("https://media.discordapp.net/attachments/919493057722855424/1035196146617950280/aran.png")
                .age("20")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21-5-2021")
                .date1("5-9-2021")
                .date2("8-2-2022")
                .time("1.00 pm.")
                .time1("9.00 am.")
                .time2("8.00 am.")
                .organizer(org2)
                .petAllowed(false)
                .build());
        org2.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .vaccine("sinovac")
                .vaccine1("sinovac")
                .vaccine2("sinovac")
                .name("Prayut Chan-o-cha")
                .addimg("https://media.discordapp.net/attachments/919493057722855424/1035193979639189544/Pra.jpg")
                .age("65")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13-7-2021")
                .date1("20-11-2021")
                .date2("9-3-2021")
                .time("4.00 pm.")
                .time1("10.00 am.")
                .time2("8.00 am.")
                .petAllowed(true)
                .organizer(org3)
                .build());
        org3.getOwnEvents().add(tempEvent);
        addUser();
        org1.setUser(user1);
        user1.setOrganizer(org1);
        org2.setUser(user2);
        user2.setOrganizer(org2);
        org3.setUser(user3);
        user3.setOrganizer(org3);
    }

    User user1,user2,user3;
    private  void addUser(){

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
      Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
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
        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        user1.getAuthorities().add(authUser);
        user1.getAuthorities().add(authAdmin);
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authUser);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }
}
