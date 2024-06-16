package com.desertgm.app.Services;

import java.text.SimpleDateFormat;
import java.util.List;

public interface GenericService<T> {
    T parseLine(String[] line, SimpleDateFormat format);
    void saveAll(List<T> entities);
}
