package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final CustomerRepository repository;
    private final Grid<Customer> grid = new Grid<>(Customer.class);

    public MainView(CustomerRepository repository) {
        this.repository = repository;

        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        Button addButton = new Button("Add Customer", e -> {
            repository.save(new Customer(firstName.getValue(), lastName.getValue()));
            firstName.clear();
            lastName.clear();
            refreshGrid();
        });

        add(new HorizontalLayout(firstName, lastName, addButton), grid);
        refreshGrid();
    }

    private void refreshGrid() {
        grid.setItems(repository.findAll());
    }
}