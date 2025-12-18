package main;

import repository.UserRepository;

public class TestRepository {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        System.out.println(repo.findAll());
    }
}
