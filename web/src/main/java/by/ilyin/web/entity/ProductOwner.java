package by.ilyin.web.entity;

import lombok.*;

@Data
public class ProductOwner {

    private Long id;
    private String name;
    private String address;
<<<<<<< HEAD
    private Client client;
=======
    private Long clientId;
>>>>>>> dd56a77 (CTB-11 CRUD product owners added)

}
