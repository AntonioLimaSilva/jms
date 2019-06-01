package br.com.luciano.jms.model;

import lombok.Data;

@Data
public class User {

    private String name;
    private String email;
    private Boolean active;

}
