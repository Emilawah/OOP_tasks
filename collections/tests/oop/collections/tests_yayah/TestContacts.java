//package oop.collections.tests_yayah;
//
//import oop.collections.ICollection.Iterator;
//import oop.contacts.IContacts;
//import oop.contacts.IContacts.IContact;
//import oop.contacts.IContacts.IName;
//import oop.contacts.IContacts.IPhoneNumber;
//import oop.contacts.IContacts.IValue;
//import oop.utils.contacts.Contacts;
//import oop.utils.collections.LinkedList;
//
//public class TestContacts {
//
//    public void runTests() {
//        System.out.println("TestContacts started...");
//        IContacts contacts = new Contacts();
//
//        // === Test 1: Création et ajout d’un contact ===
//        System.out.println("Test 1: Création et ajout d’un contact");
//        IName name = contacts.newName("Doe", "John");
//        IPhoneNumber phone = contacts.newPhoneNumber(33, "123456789");
//        IContact contact = contacts.add(name, phone);
//
//        assert contact.name().first().equals("John");
//        assert contact.phone().number().equals("123456789");
//
//        // === Test 2: Vérification de get() ===
//        System.out.println("Test 2: Recherche d’un contact avec get()");
//        IContact fetched = contacts.get(phone);
//        assert fetched != null;
//        assert fetched.name().last().equals("Doe");
//
//        // === Test 3: Ajout de champs personnalisés ===
//        System.out.println("Test 3: Ajout de champs (email, country)");
//        IValue email = contacts.newValue("john@doe.com");
//        IValue country = contacts.newValue("France");
//        contact.field("email", email);
//        contact.field("country", country);
//        assert contact.field("email").value().equals("john@doe.com");
//        assert contact.field("country").value().equals("France");
//
//        // === Test 4: Mise à jour de plusieurs champs ===
//        System.out.println("Test 4: Mise à jour de champs (email et country)");
//        LinkedList names = new LinkedList();
//        LinkedList values = new LinkedList();
//        names.add("email"); values.add(contacts.newValue("new@doe.com"));
//        names.add("country"); values.add(contacts.newValue("Canada"));
//        contacts.update(contact, names, values);
//
//        assert contact.field("email").value().equals("new@doe.com");
//        assert contact.field("country").value().equals("Canada");
//
//        // === Test 5: Champ inexistant ===
//        System.out.println("Test 5: Accès à un champ inexistant");
//        assert contact.field("unknown") == null;
//
//        // === Test 6: Sélection par filtre ===
//        System.out.println("Test 6: Sélection de contact par filtre (country = Can*)");
//        Iterator filtered = contacts.select("country", "Can*");
//        assert filtered.hasNext();
//        IContact selected = (IContact) filtered.next();
//        assert selected.name().first().equals("John");
//
//        // === Test 7: Filtre ne donnant aucun résultat ===
//        System.out.println("Test 7: Filtrage sans résultat (country = XYZ)");
//        Iterator empty = contacts.select("country", "XYZ");
//        assert !empty.hasNext();
//
//        // === Test 8: Changement de numéro de téléphone ===
//        System.out.println("Test 8: Changement de numéro de téléphone");
//        IPhoneNumber newPhone = contacts.newPhoneNumber(33, "987654321");
//        LinkedList changeNumNames = new LinkedList();
//        LinkedList changeNumValues = new LinkedList();
//        changeNumNames.add("phone");
//        changeNumValues.add(newPhone);
//        contacts.update(contact, changeNumNames, changeNumValues);
//        assert contacts.get(phone) == null;
//        assert contacts.get(newPhone) != null;
//
//        // === Test 9: Suppression ===
//        System.out.println("Test 9: Suppression du contact");
//        contacts.remove(contact);
//        assert contacts.get(newPhone) == null;
//
//        // === Test 10: Rejet des doublons ===
//        System.out.println("Test 10: Ajout de deux contacts avec le même numéro");
//        IContact c1 = contacts.add(
//            contacts.newName("Smith", "Alice"),
//            contacts.newPhoneNumber(33, "111111111")
//        );
//
//        boolean exceptionThrown = false;
//        try {
//            contacts.add(
//                contacts.newName("Smith", "Bob"),
//                contacts.newPhoneNumber(33, "111111111")  // même numéro
//            );
//        } catch (IllegalArgumentException e) {
//            exceptionThrown = true;
//        }
//        assert exceptionThrown : "Ajout de doublon non détecté";
//
//        System.out.println("TestContacts passed.\n");
//    }
//}
