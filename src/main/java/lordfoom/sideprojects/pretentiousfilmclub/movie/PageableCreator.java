package lordfoom.sideprojects.pretentiousfilmclub.movie;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableCreator {
    public PageableCreator() {
    }

    Pageable getPageable(Integer pageNum, Integer pageSize, String sortBy, Sort.Direction direction) {
        Sort sort;

        if (null == pageNum)
            pageNum = 0;
        if (null == pageSize || pageSize < 1)
            pageSize = 10;

        if (StringUtils.isBlank(sortBy)) {
            sort = Sort.by("created").descending();
        } else {
            sort = Sort.by(sortBy);
            if (direction == Sort.Direction.DESC)
                sort = sort.descending();
        }
        Pageable page = PageRequest.of(pageNum, pageSize, sort);
        return page;
    }
}