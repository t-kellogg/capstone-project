package com.ey.services;

import com.ey.models.Log;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface LogService {
    public Log addLog(Log log);
    public Log getLog(int id);
    public List<Log> getAllLogs();
    public Log updateLog(Log newLog);
    public boolean deleteLog(int id) throws EmptyResultDataAccessException;
}
