package codeplays.trainee.contacts.controller;

import codeplays.trainee.contacts.exception.ContactNotFoundException;
import codeplays.trainee.contacts.model.ContactModel;
import codeplays.trainee.contacts.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody ContactModel contact) {
        contactService.create(contact);
    }

    @GetMapping("/contacts/{id}")
    public ContactModel getContactById(@PathVariable Long id) throws ContactNotFoundException {
        return contactService.getContactById(id);
    }

    @PutMapping("/contacts")
    public void upsertContact(@RequestBody ContactModel contact) {
        contactService.upsertContact(contact);
    }

    @DeleteMapping("/contacts/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.delete(id);
    }

    @GetMapping("/contacts")
    public List<ContactModel> findContacts(@RequestParam String name) {
        return contactService.findAllFiltering(name);
    }
}
