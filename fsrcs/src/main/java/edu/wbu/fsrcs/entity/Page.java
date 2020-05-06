package edu.wbu.fsrcs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private int offset;
    private int limit;
    private int currentPage;
    private int pageSize;
}
