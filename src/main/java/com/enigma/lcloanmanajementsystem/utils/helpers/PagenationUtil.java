package com.enigma.lcloanmanajementsystem.utils.helpers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagenationUtil {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;

    public static Pageable createPageable(Integer page, Integer size , String sortBy, String direction) {
        int safePage = (page == null || page < 0) ? 0 : page;
        int safeSize = (size == null || size <= 0) ? DEFAULT_PAGE_SIZE : Math.min(size, MAX_PAGE_SIZE);
        String safeSortBy = (sortBy == null || sortBy.trim().isEmpty()) ? "id" : sortBy;

        Sort.Direction sortDirection = "DESC".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(safePage, safeSize, Sort.by(sortDirection, safeSortBy));
    }
}
