package com.ey.services;

import com.ey.models.Log;
import com.ey.repositories.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogService{
    @Autowired // Field Injection
    LogRepo logRepo;

    @Override
    public Log addLog(Log log) {
        return logRepo.save(log);
    }

    @Override
    public Log getLog(int id) {
        Optional<Log> logOptional = logRepo.findById(id);
        return logOptional.orElseGet(null);
    }

    @Override
    public List<Log> getAllLogs() {
        return (List<Log>)logRepo.findAll();
    }

    @Override
    public Log updateLog(Log newLog) {
        return logRepo.save(newLog);
    }

    @Override
    public boolean deleteLog(int id) throws EmptyResultDataAccessException {
        try {
            logRepo.deleteById(id);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
