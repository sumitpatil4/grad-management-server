package com.example.gradmanagementserver.Service;

import com.example.gradmanagementserver.Model.Intern;

import java.util.Map;

public interface InternService {

    public Map<String,Object> getInternById(Integer internId);
    public Map<String,Object> getInterns(Integer trainingId);

    public Map<String,Object> createInterns(String userId, Integer trainingId, Intern intern);

    public Map<String,Object> updateIntern(Integer internId,Intern intern);

    public Map<String,Object> deleteIntern(Integer internId);

}
