package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.AccountTypeDAO;
import ar.com.ada.maven.root.model.dto.AccountType;
import ar.com.ada.maven.root.utils.Paginator;
import ar.com.ada.maven.root.view.AccountTypeView;
import ar.com.ada.maven.root.view.MainView;

import java.util.List;

public class AccountTypeController {
    private static AccountTypeView view = new AccountTypeView();
    private static AccountTypeDAO accountTypeDAO = new AccountTypeDAO();
    private static int listAccountTypesPerPage(String optionSelectEdithOrDelete, boolean showHeader) {
        int limit = 4, currentPage = 0, totalAccountType, totalPages, accountTypeIdSelected = 0;
        List<AccountType> accountTypes;
        List<String> paginator;
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            totalAccountType = accountTypeDAO.getTotalAccountTypes();
            totalPages = (int) Math.ceil((double) totalAccountType / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);

            accountTypes = accountTypeDAO.findAll(limit, currentPage * limit);
            String choice = view.printAccountTypesPerPage(accountTypes, paginator, optionSelectEdithOrDelete, showHeader);

            switch (choice) {
                case "i":
                case "I":
                    currentPage = 0;
                    break;
                case "a":
                case "A":
                    if (currentPage > 0) currentPage--;
                    break;
                case "s":
                case "S":
                    if (currentPage + 1 < totalPages) currentPage++;
                    break;
                case "u":
                case "U":
                    currentPage = totalPages - 1;
                    break;
                case "e":
                case "E":
                    if (optionSelectEdithOrDelete != null) {
                        accountTypeIdSelected = view.accountTypeIdSelected(optionSelectEdithOrDelete);
                        shouldGetOut = true;
                    }
                    break;
                case "q":
                case "Q":
                    shouldGetOut = true;
                    break;
                default:
                    if (choice.matches("^-?\\d+$")) {
                        int page = Integer.parseInt(choice);
                        if (page > 0 && page <= totalPages) currentPage = page - 1;
                    } else MainView.invalidData();
            }
        }
        return accountTypeIdSelected;
    }
}
