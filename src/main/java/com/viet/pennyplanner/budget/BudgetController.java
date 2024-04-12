package com.viet.pennyplanner.budget;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    private final BudgetRepository budgetRepository;

    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }
    @GetMapping("")
    List<Budget> findAll(){
        return budgetRepository.findAll();
    }

    @GetMapping("/{id}")
    Budget findById(@PathVariable String id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if(budget.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found.");
        }
        return budget.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Budget budget) {
        budgetRepository.save(budget);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Budget budget, @PathVariable String id) {
        budgetRepository.save(budget);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        budgetRepository.delete(budgetRepository.findById(id).get());
    }

}
