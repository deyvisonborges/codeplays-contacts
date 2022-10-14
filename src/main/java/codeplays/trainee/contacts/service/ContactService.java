package codeplays.trainee.contacts.service;

import codeplays.trainee.contacts.exception.ContactNotFoundException;
import codeplays.trainee.contacts.interfaces.IContactRules;
import codeplays.trainee.contacts.model.ContactModel;
import codeplays.trainee.contacts.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CustomizableThreadCreator;

import java.util.List;

@Service
public class ContactService implements IContactRules {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void create(ContactModel contactModel) {
        contactRepository.save(contactModel);
    }

    public ContactModel getContactById(Long id) throws ContactNotFoundException{
        return contactRepository
                .findById(id)
                .orElseThrow(ContactNotFoundException::new);
    }
    
    public void upsertContact(ContactModel contact) {
    	contactRepository.findById(contact.getId()).ifPresentOrElse(
                (savedContact) -> {
                    savedContact.setName(contact.getName());
                    savedContact.setPhone(contact.getPhone());

                    contactRepository.save(savedContact);
                },
                () -> create(contact)
        );
    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }

    public List<ContactModel> findAllFiltering(String name) {
        return contactRepository.findByNameContaining(name);
    }
}
