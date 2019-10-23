package com.zrzk.service;

import com.zrzk.pojo.Region;

public interface RegionService {
    int update(Region region);

    Region findByGenreId(Integer genreId);
}
