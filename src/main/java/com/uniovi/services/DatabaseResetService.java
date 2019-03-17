package com.uniovi.services;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationRepository;
import com.uniovi.repositories.MessageRepository;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DatabaseResetService {


	@Autowired
	private RolesService rolesService;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private MessageService messageService;

	@Autowired
	private OffersRepository offersRepository;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ConversationRepository conversationRepository;
	    
	@Autowired
	private MessageRepository messageRepository;

    public void resetDb() {

        usersRepository.deleteAll();
        offersRepository.deleteAll();
        conversationRepository.deleteAll();
		messageRepository.deleteAll();

        User user1 = new User("admin@gmail.com", "admin", "istrador");
        user1.setPassword("admin");
        user1.setRole(rolesService.getRoles()[2]);
        User user2 = new User("lucas@gmail.com", "Lucas", "Núñez");
        user2.setPassword("123456");
        user2.setRole(rolesService.getRoles()[1]);
        User user3 = new User("maria@gmail.com", "María", "Rodríguez");
        user3.setPassword("123456");
        user3.setRole(rolesService.getRoles()[1]);
        User user4 = new User("marta@gmail.com", "Marta", "Almonte");
        user4.setPassword("123456");
        user4.setRole(rolesService.getRoles()[1]);
        User user5 = new User("pela@gmail.com", "Pelayo", "Valdes");
        user5.setPassword("123456");
        user5.setRole(rolesService.getRoles()[1]);
        User user6 = new User("ed@gmail.com", "Edward", "Núñez");
        user6.setPassword("123456");
        user6.setRole(rolesService.getRoles()[1]);


        Set<Offer> user2Offers = new HashSet<Offer>() {
            {
                add(new Offer("Nintendo", "consola", 100.0, user2));
                add(new Offer("Portátil", "ordenador", 600.0, user2));
                add(new Offer("Millenium Falcon", "Lego Star Wars", 100.0, user2));
                add(new Offer("Seagate Barracuda 4Tb", "HDD", 100.0, user2));
            }
        };
        user2.setOffers(user2Offers);
        Set<Offer> user3Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oneplus 5T", "movil", 400.0, user3));
                add(new Offer("Cristiano Ronaldo", "sabe jugar al futbol", 100000000.0, user3));
                add(new Offer("Pixel XL", "Mobile phone made by Google", 100.0, user3));
                add(new Offer("Joystick", "Thrustmaster", 100.0, user3));
            }
        };
        user3.setOffers(user3Offers);
        Set<Offer> user4Offers = new HashSet<Offer>() {
            {
                add(new Offer("Hitchikers guide to the galaxy", "Douglas Adams book", 20.0, user4));
                add(new Offer("Clean Code", "Robert C. Martin book", 40.0, user4));
                add(new Offer("Maigol", "No darle de comer después de media noche", 100.0, user4));
                add(new Offer("Rubik's Cube", "El buen rompecabezas", 100.0, user4));
            }
        };
        user4.setOffers(user4Offers);
        Set<Offer> user5Offers = new HashSet<Offer>() {
            {
                add(new Offer("Tesla Model  S", "electric SUV", 90000.0, user5));
                add(new Offer("Solid chat application", "fully decentralized as timbl likes it", 123.0, user5));
                add(new Offer("Hunger Games", "Some good literature", 100.0, user5));
                add(new Offer("BB8 Droid", "A helping ball", 100.0, user5));
            }
        };
        user5.setOffers(user5Offers);
        Set<Offer> user6Offers = new HashSet<Offer>() {
            {
                add(new Offer("Starship", "Rocket stage capable of propulsive landing on Mars", 10.0, user6));
                add(new Offer("Super Heavy", "Booster suitable for the Starship second stage", 30.0, user6));
                add(new Offer("Josecurioso", "A este si que no le des de comer", 100.0, user6));
                add(new Offer("Network Switch", "Some fine equipment for the home", 100.0, user6));
            }
        };
        user6.setOffers(user6Offers);


        usersService.addUser(user2);
        usersService.addUser(user1);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);
        usersService.addUser(user6);

        User u2 = usersRepository.findByEmail("lucas@gmail.com");
        User u3 = usersRepository.findByEmail("maria@gmail.com");
        User u4 = usersRepository.findByEmail("marta@gmail.com");
        User u5 = usersRepository.findByEmail("pela@gmail.com");
        User u6 = usersRepository.findByEmail("ed@gmail.com");


        Offer o1 = offersRepository.searchByDescriptionNameAndUser("Millenium Falcon", u2).get(0);
        Offer o2 = offersRepository.searchByDescriptionNameAndUser("Seagate Barracuda 4Tb", u2).get(0);
        Offer o3 = offersRepository.searchByDescriptionNameAndUser("Pixel XL", u3).get(0);
        Offer o4 = offersRepository.searchByDescriptionNameAndUser("Joystick", u3).get(0);
        Offer o5 = offersRepository.searchByDescriptionNameAndUser("Maigol", u4).get(0);
        Offer o6 = offersRepository.searchByDescriptionNameAndUser("Rubik's Cube", u4).get(0);
        Offer o7 = offersRepository.searchByDescriptionNameAndUser("Hunger Games", u5).get(0);
        Offer o8 = offersRepository.searchByDescriptionNameAndUser("BB8 Droid", u5).get(0);
        Offer o9 = offersRepository.searchByDescriptionNameAndUser("Josecurioso", u6).get(0);
		Offer o10 = offersRepository.searchByDescriptionNameAndUser("Network Switch", u6).get(0);
		Offer o11 = offersRepository.searchByDescriptionNameAndUser("Cristiano Ronaldo", u3).get(0);
		Offer o12 = offersRepository.searchByDescriptionNameAndUser("Clean Code", u4).get(0);
		Offer o13 = offersRepository.searchByDescriptionNameAndUser("Tesla Model  S", u5).get(0);
		Offer o14 = offersRepository.searchByDescriptionNameAndUser("Portátil", u2).get(0);
		Offer o15 = offersRepository.searchByDescriptionNameAndUser("Starship", u6).get(0);

        buy(o1, u3);
        buy(o2, u3);
        buy(o3, u4);
        buy(o4, u4);
        buy(o5, u5);
        buy(o6, u5);
        buy(o7, u6);
        buy(o8, u6);
        buy(o9, u2);
        buy(o10, u2);
        
        Conversation c1_2 = messageService.getCoversation(u3,o1.getId());
		Conversation c2_2 = messageService.getCoversation(u3,o2.getId());
		Conversation c3_3 = messageService.getCoversation(u2,o3.getId());
		Conversation c4_3 = messageService.getCoversation(u2,o4.getId());
		Conversation c5_4 = messageService.getCoversation(u2,o5.getId());
		Conversation c6_4 = messageService.getCoversation(u2,o6.getId());
		Conversation c7_5 = messageService.getCoversation(u2,o7.getId());
		Conversation c8_5 = messageService.getCoversation(u2,o8.getId());
		Conversation c9_6 = messageService.getCoversation(u2,o9.getId());
		Conversation c10_6 = messageService.getCoversation(u3,o10.getId());
		Conversation c11_3 = messageService.getCoversation(u2,o11.getId());
		Conversation c12_4 = messageService.getCoversation(u2,o12.getId());
		Conversation c13_5 = messageService.getCoversation(u2,o13.getId());
		Conversation c14_2 = messageService.getCoversation(u6,o14.getId());
		Conversation c15_6 = messageService.getCoversation(u2,o15.getId());
	
		
		
		chatInteraction(u2, u6, c1_2);
		chatInteraction(u2, u6, c2_2);
		chatInteraction(u3, u6, c3_3);
		chatInteraction(u3, u6, c4_3);
		chatInteraction(u4, u6, c5_4);
		
		chatInteraction(u4, u6, c6_4);
		chatInteraction(u5, u6, c7_5);
		chatInteraction(u5, u6, c8_5);
		chatInteraction(u6, u2, c9_6);
		chatInteraction(u6, u2, c10_6);
		
		chatInteraction(u3, u6, c11_3);
		chatInteraction(u4, u6, c12_4);
		chatInteraction(u5, u6, c13_5);
		chatInteraction(u2, u6, c14_2);
		chatInteraction(u6, u2, c15_6);

    }

    public void chatInteraction(User u2, User u6, Conversation c1_2) {
		messageService.addMsg(new Message("Mensaje de prueba1"), u6,c1_2);
		messageService.addMsg(new Message("Mensaje de prueba2"), u2,c1_2);
		messageService.addMsg(new Message("Mensaje de prueba3"), u6,c1_2);
		messageService.addMsg(new Message("Mensaje de prueba4"), u2,c1_2);
	}

    public void buy(Offer offer, User user) {
        offer.setBuyer(user);
        offersRepository.save(offer);
    }
}
