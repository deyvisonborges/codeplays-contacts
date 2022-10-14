package codeplays.trainee.contacts.repository;

import codeplays.trainee.contacts.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactModel, Long> {
    @Query("SELECT contact FROM Contact contact" +
            "WHERE contact.name LIKE CONCAT('%', ?1, '%') ")
    List<ContactModel> findByNameContaining(String name);
}
