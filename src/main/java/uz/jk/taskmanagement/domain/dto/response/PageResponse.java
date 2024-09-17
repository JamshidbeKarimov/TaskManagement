package uz.jk.taskmanagement.domain.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageResponse<T> {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private List<T> content;


}
