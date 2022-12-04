package by.ilyin.web.entity;

import lombok.*;

@Data
public class Storage {

    private Long id;
    private String name;
    private String address;
    private Client client;

}
