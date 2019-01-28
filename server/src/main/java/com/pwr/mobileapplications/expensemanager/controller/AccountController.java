package com.pwr.mobileapplications.expensemanager.controller;


import com.pwr.mobileapplications.expensemanager.dto.BudgetGetDto;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {


    @GetMapping("{accountId}/budget/{budgetId}")
    public List<BudgetGetDto> getBudgets(@PathVariable Long accountId, @PathVariable Long budgetId) {
        return Collections.emptyList();
    }
}
