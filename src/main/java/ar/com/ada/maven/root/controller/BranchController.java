package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.BranchDAO;
import ar.com.ada.maven.root.model.dto.Branch;
import ar.com.ada.maven.root.utils.Paginator;
import ar.com.ada.maven.root.view.BranchView;
import ar.com.ada.maven.root.view.MainView;

import java.util.List;

public class BranchController {
    private static BranchView view = new BranchView();
    private static BranchDAO branchDAO = new BranchDAO();

    private static int listBranchPerPage(String optionSelectEdithOrDelete, boolean showHeader) {
        int limit = 4, currentPage = 0, totalBranchs, totalPages, branchIdSelected = 0;
        List<Branch> branchs;
        List<String> paginator;
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            totalBranchs = branchDAO.getTotalBranchs();
            totalPages = (int) Math.ceil((double) totalBranchs / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);

            branchs = branchDAO.findAll(limit, currentPage * limit);
            String choice = view.printBranchsPerPage(branchs, paginator, optionSelectEdithOrDelete, showHeader);

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
                        branchIdSelected = view.branchIdSelected(optionSelectEdithOrDelete);
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
        return branchIdSelected;
    }
}
