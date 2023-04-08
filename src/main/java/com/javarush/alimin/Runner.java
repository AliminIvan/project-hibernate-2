package com.javarush.alimin;

import com.javarush.alimin.dao.*;
import com.javarush.alimin.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public class Runner {
    private final SessionFactory sessionFactory;

    private final ActorDAO actorDAO;

    private final AddressDAO addressDAO;

    private final CategoryDAO categoryDAO;

    private final CityDAO cityDAO;

    private final CountryDAO countryDAO;

    private final CustomerDAO customerDAO;

    private final FilmDAO filmDAO;

    private final FilmTextDAO filmTextDAO;

    private final InventoryDAO inventoryDAO;

    private final LanguageDAO languageDAO;

    private final PaymentDAO paymentDAO;

    private final RentalDAO rentalDAO;

    private final StaffDAO staffDAO;

    private final StoreDAO storeDAO;

    public Runner() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public static void main(String[] args) {

        Runner runner = new Runner();
        Customer customer = runner.createRandomCustomer();
        runner.customerRentInventory(customer);
        runner.customerReturnRentalFilm();
        runner.addNewFilmInStock();

    }

    private void addNewFilmInStock() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try (session) {
            tr = session.beginTransaction();

            Language language = languageDAO.getById(ThreadLocalRandom.current().nextInt(1, 7));
            List<Category> categories = categoryDAO.getItems(0, 8);
            List<Actor> actors = actorDAO.getItems(5, 30);

            Film film = new Film();
            film.setActors(actors);
            film.setRating(Rating.PG_13);
            film.setSpecialFeatures(Set.of(Feature.COMMENTARIES, Feature.BEHIND_THE_SCENES, Feature.DELETED_SCENES));
            film.setLength((short) 188);
            film.setReplacementCost(BigDecimal.valueOf(20));
            film.setRentalRate(BigDecimal.valueOf(6.99));
            film.setLanguage(language);
            film.setTitle("Bloody Monday");
            film.setDescription("This film is about bloody mondays, i hate mondays");
            film.setRentalDuration((byte) 4);
            film.setOriginalLanguage(language);
            film.setCategories(categories);
            film.setReleaseYear(Year.now());
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setId(film.getId());
            filmText.setTitle("Bloody Monday");
            filmText.setDescription("This film is about bloody mondays, i hate mondays");
            filmTextDAO.save(filmText);

            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    private void customerRentInventory(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try (session) {
            tr = session.beginTransaction();

            Film film = filmDAO.getAvailableForRentFilm();
            Store store = storeDAO.getById(ThreadLocalRandom.current().nextInt(1, 3));

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Staff staff = store.getStaff();

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(4.99));
            payment.setStaff(staff);
            paymentDAO.save(payment);

            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    private void customerReturnRentalFilm() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try (session) {
            tr = session.beginTransaction();

            Rental rental = rentalDAO.getRandomUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.update(rental);
            System.out.println(rental.getId());

            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    private Customer createRandomCustomer() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try (session) {
            tr = session.beginTransaction();
            Store store = storeDAO.getById(ThreadLocalRandom.current().nextInt(1, 3));
            City city = cityDAO.getById(ThreadLocalRandom.current().nextInt(1, 601));

            Address address = new Address();
            address.setAddress("Lenina street, 2a");
            address.setPhone("+79037777777");
            address.setCity(city);
            address.setDistrict("Dangerous");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setFirstName("Sherman");
            customer.setLastName("Pike");
            customer.setEmail("sherminator3000@gmail.com");
            customer.setActive(true);
            customer.setAddress(address);
            customer.setStore(store);
            customerDAO.save(customer);

            tr.commit();
            return customer;
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

}

