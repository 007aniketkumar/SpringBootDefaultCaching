package com.example.caching;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable("books") //this is to make the service faster
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


    //The idea to introduce a new method is to check if we
    // can have different cache evict and cache size policies.

    @Override
    public Book getbyTitle(String title){
        simulateSlowService();
        return new Book("123",title);
    }

}
